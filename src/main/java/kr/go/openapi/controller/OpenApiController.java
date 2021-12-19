package kr.go.openapi.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.*;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jdk.nashorn.internal.objects.annotations.Getter;
import kr.go.openapi.HomeController;
import kr.go.openapi.service.ServiceListService;
import kr.go.openapi.vo.PageVo;
import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

@Controller
@PropertySource("classpath:application.properties")
public class OpenApiController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ServiceListService serviceListService;

	@Value("${openapi.serviceKey}")
	private String serviceKey;

	@RequestMapping(value = { "/serviceList/{page}", "/serviceList" }, method = RequestMethod.GET)
	public String serviceList(Model model, @PathVariable(required = false) Integer page, HttpServletRequest request) {

		if (page == null) {
			page = 1;
		}

		String url = "https://api.odcloud.kr/api/gov24/v1/";

		// int page = 1;
		// ��û�� uri ����
		URI uri = UriComponentsBuilder.fromUriString(url + "serviceList").queryParam("page", 1).queryParam("perPage", 1)
				.queryParam("serviceKey", serviceKey).build(true).toUri();

		try {
			// Ŀ�ؼ� Ǯ ����
			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100).setMaxConnPerRoute(5).build();

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

			// api ��û�� restTemplate
			RestTemplate restTemplate = new RestTemplate(factory);

			PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

			int currentCnt = pageVo.getCurrentCount();
			int apiTotalCnt = pageVo.getTotalCount();

			// ���ڵ�� Ȯ��
			int serviceDbCount = serviceListService.getDbCount();

			int serviceConditionDbCount = serviceListService.serviceConditionDbCount();

			logger.info("dbcount {} apiTotalCnt {}", serviceDbCount, apiTotalCnt);

			// ���ڵ� �� üũ �� db ���� ����
			if (apiTotalCnt > serviceDbCount) {

				fetchApi(url, restTemplate, apiTotalCnt);

				fetchDetailApi(url, restTemplate, apiTotalCnt);
			}

			if (362 != serviceConditionDbCount) {
				fetchConditionApi(url, restTemplate, apiTotalCnt);
			}
			return "/openapi/serviceList";
//			Map<String, Object> map = new HashMap<>();
//			map.put("page", page == 1 ? page : page * 5 - 4);
//			int pageEnd = page * 5;
//			map.put("pageEnd", pageEnd);

			// logger.info("selectServiceList {}", selectServiceList);

//			List<ServiceListVo> serviceList = serviceListService.serviceList(map);
//			String pUrl = request.getRequestURI();

			// model.addAttribute("pagination", pagination(page, pUrl, serviceDbCount));
			// model.addAttribute("serviceList", serviceList);
		} catch (HttpClientErrorException e) {
			throw e;
		}

	}

	// ���� ������
	@RequestMapping(value = "/serviceDetail/{serviceId}", method = RequestMethod.GET)
	public String serviceDetail(Model model, @PathVariable String serviceId) {

		ServiceDetailVo serviceDetailVo = serviceListService.findById(serviceId);
		model.addAttribute("serviceDetailVo", serviceDetailVo);

		return "/openapi/serviceDetail";
	}

	@ResponseBody
	@RequestMapping(value = "/slByAjax/{page}", method = RequestMethod.GET)
	public Map<String, Object> slcByAjax(@PathVariable(required = false) int page,
			@RequestParam(value = "url") String url, HttpServletRequest request) {
		logger.info("page {}", page);
		logger.info("url {}", url);
		int pageEnd = page * 5;

		// ����¡ ���� �Է�
		Map<String, Object> voMap = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();

		voMap.put("page", page == 1 ? page : page * 5 - 4);
		voMap.put("pageEnd", pageEnd);

		List<ServiceListVo> serviceList = serviceListService.serviceList(voMap);
		int conditionCnt = serviceListService.conditionCnt(voMap);
		// String pUrl = request.getRequestURI();
		resultMap.put("serviceList", serviceList);
		resultMap.put("page", page);
		resultMap.put("pagination", pagingAjax(page, url, conditionCnt));

		return resultMap;
	}

	// ���ǿ� ���� ���� ����Ʈ �񵿱�
	@ResponseBody
	@RequestMapping(value = "/serviceList/{page}", method = RequestMethod.POST)
	public Map<String, Object> slcByAjax(@RequestBody ServiceConditionVo svo, Model model, @PathVariable int page,
			HttpServletRequest request) throws Exception {

		int pageEnd = page * 5;

		// ����¡ ���� �Է�
		Map<String, Object> voMap = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();

		voMap.put("page", page == 1 ? page : page * 5 - 4);
		voMap.put("pageEnd", pageEnd);
		// logger.info("svo {}", svo);

		if (svo == null) {
			List<ServiceListVo> serviceList = serviceListService.serviceList(voMap);

			resultMap.put("serviceList", serviceList);
		} else {

			// ���� ���� �Է�
			Map<String, Object> condMap = new HashMap<>();
			List<Map<String, Object>> ageList = new ArrayList<>();

			// ���̹�Ƽ�� ������ ���� ��ü �и�
			Field[] fields = svo.getClass().getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);

				if (field.get(svo) != null) {
					condMap.put(field.getName(), field.get(svo));
					logger.info("condMap {}", condMap);

					String[] tempArr = { "JA0103", "JA0104", "JA0105", "JA0106", "JA0107", "JA0108", "JA0109" };
					int[] min = { 0, 6, 13, 19, 30, 50, 65 };
					int[] max = { 5, 12, 18, 29, 49, 64, 120 };

					// ���� ���� ���ÿ� ���� �˻��� ���� ���� x
					// ���� �� ����Ʈ�� ���ϱ� ó���ϸ� ��
					for (int i = 0; i < tempArr.length; i++) {
						if (tempArr[i].equals(field.getName())) {
							logger.info("field {}", field.getName());
							Map<String, Object> ageMap = new HashMap<>();
							// svo.setJA0110(svo.getJA0110() <= min[i] ? svo.getJA0110() : min[i]);
							// svo.setJA0111(svo.getJA0111() >= max[i] ? svo.getJA0111() : max[i]);
							svo.setJA0110(min[i]);
							svo.setJA0111(max[i]);
							ageMap.put("JA0110", svo.getJA0110());
							ageMap.put("JA0111", svo.getJA0111());
							ageList.add(ageMap);

							condMap.remove(field.getName());

							logger.info("ageMap {}", ageMap);

							logger.info("ageList {}", ageList);
						}

					}
					condMap.remove("JA0110");
					condMap.remove("JA0111");
				}

			}

			voMap.put("ageList", ageList);

			if (svo.getJA0110() == 0 && svo.getJA0111() == 0) {
				condMap.remove("JA0110");
				condMap.remove("JA0111");
			}

			String pUrl = request.getRequestURI() + "/" + page;

			pUrl = pUrl.lastIndexOf("/") > -1 ? pUrl.substring(0, pUrl.lastIndexOf("/")) : pUrl;
			// logger.info("Post pUrl {}", pUrl);

			voMap.put("condMap", condMap);
			logger.info("vomap {}", voMap);

			List<ServiceListVo> selectServiceList = serviceListService.selectServiceListByCondition(voMap);
			int conditionCnt = serviceListService.conditionCnt(voMap);

			// String pUrl = request.getRequestURI();
			// model.addAttribute("serviceList", selectServiceList);
			// model.addAttribute("pagination", pagination(page, pUrl, conditionCnt));

			resultMap.put("serviceList", selectServiceList);
			resultMap.put("pagination", pagingAjax(page, pUrl, conditionCnt));

		}
		return resultMap;

	}

	// service, seviceList api ��������
	public void fetchApi(String url, RestTemplate restTemplate, int perPage) {

		int page = 1;
		// ��û�� uri ����
		// build(true)�� ���� ���ڵ� ����
		// toUri() �ռ� �ۼ��� �������� uri��
		URI uri = UriComponentsBuilder.fromUriString(url + "serviceList").queryParam("page", page)
				.queryParam("perPage", perPage).queryParam("serviceKey", serviceKey).build(true).toUri();

		PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

		// String xml = XML.toString(pageVo);
		int result = serviceListService.insertPageVo(pageVo);

	}

	// ���� ������ api ��������
	public void fetchDetailApi(String url, RestTemplate restTemplate, int perPage) {

		int page = 1;
		// ��û�� uri ����
		URI uri = UriComponentsBuilder.fromUriString(url + "serviceDetail").queryParam("page", page)
				.queryParam("perPage", perPage).queryParam("serviceKey", serviceKey).build(true).toUri();

		PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

		int result = serviceListService.insertPageVoDetail(pageVo);

	}

	// ���� ��������
	public void fetchConditionApi(String url, RestTemplate restTemplate, int perPage) {

		int page = 1;
		// ��û�� uri ����
		URI uri = UriComponentsBuilder.fromUriString(url + "supportConditions").queryParam("page", page)
				.queryParam("perPage", perPage).queryParam("serviceKey", serviceKey).build(true).toUri();

		// pageVo Ÿ������ ����
		PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

		int result = serviceListService.insertPageVoCondition(pageVo);

	}

	// ����¡ó�� ajax
	public String pagingAjax(int page, String pUrl, int totalCnt) {

		StringBuffer pagination = new StringBuffer();
		logger.info("totalCnt{}", totalCnt);
		int pageBarSize = 5;
		int totalPage = (int) Math.ceil((double) totalCnt / pageBarSize);
		int pageStart = ((page - 1) / pageBarSize) * pageBarSize + 1;
		int pageBarEnd = pageStart + pageBarSize - 1;
		int left = page >= 1 && page <= 5 ? 1 : pageStart - 1;
		// pUrl = (pUrl.indexOf(String.valueOf(page)) > -1) ? p" : pUrl + "?";
		logger.info("totalPage {}", totalPage);

		pUrl = pUrl.indexOf("/") > -1 ? pUrl.substring(0, pUrl.lastIndexOf("/")) : pUrl;
		logger.info("pUrl {}", pUrl);
		int pageNo = pageStart;

		pagination.append("<nav aria-label=\"Page navigation example\">\r\n"
				+ "			<ul class=\"pagination justify-content-center\">\r\n");

		if (page == 1 || totalPage <= pageBarSize) {
			//pagination.append("				<li class=\"page-item\"><a class=\"page-link\"\r\n"
			//		+ "	href=\"javascript:void(0);\"" + "\r\n"
			//		+ "					aria-label=\"Previous\" disabled> <span aria-hidden=\"true\">&laquo;</span>\r\n"
			//		+ "				</a></li>");
		} else {
			pagination
					.append("<li class=\"page-item\"><a class=\"page-link\"\r\n" + "href=" + pUrl + "/" + left + "\r\n"
							+ "					aria-label=\"Previous\"> &laquo;\r\n"
							+ "				</a></li>");
		}

		while (pageNo <= pageBarEnd && pageNo <= totalPage) {
			pagination.append("<li class=\"page-item\"><a class=\"page-link\"\r\n" + "href=" + pUrl + "/" + pageNo + ">"
					+ pageNo + "</a></li>");
			pageNo++;
		}

		if (pageNo > totalPage) {
			//pagination.append("<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0);\""
			//		+ "					aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
			//		+ "				</a></li>\r\n" + "			</ul>\r\n" + "		</nav>");

		} else {
			pagination.append("<li class=\"page-item\"><a class=\"page-link\" href=" + pUrl + "/" + pageNo
					+ "					aria-label=\"Next\"> &raquo;\r\n"
					+ "				</a></li>\r\n" + "			</ul>\r\n" + "		</nav>");
		}

		return pagination.toString();

	}

	// ����¡ó��
	public String pagination(int page, String pUrl, int totalCnt) {

		StringBuffer pagination = new StringBuffer();

		int pageBarSize = 5;
		int totalPage = (int) Math.ceil((double) totalCnt / pageBarSize);
		int pageStart = ((page - 1) / pageBarSize) * pageBarSize + 1;
		int pageBarEnd = pageStart + pageBarSize - 1;
		int left = page >= 1 && page <= 5 ? 1 : pageStart - 1;
		pUrl = (pUrl.indexOf("?") > -1) ? pUrl + "&" : pUrl + "?";

		int pageNo = pageStart;
		pagination.append("<nav aria-label=\"Page navigation example\">\r\n"
				+ "			<ul class=\"pagination justify-content-center\">\r\n"
				+ "				<li class=\"page-item\"><a class=\"page-link\"\r\n" + "					href=" + pUrl
				+ "page=" + left + "\r\n"
				+ "					aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
				+ "				</a></li>");
		logger.info("totalPage {}", totalPage);

		while (pageNo <= pageBarEnd && pageNo <= totalPage) {
			pagination.append("<li class=\"page-item\"><a class=\"page-link\"\r\n" + "href=" + pUrl + "page=" + pageNo
					+ ">" + pageNo + "</a></li>");
			pageNo++;
		}
		pagination.append("<li class=\"page-item\"><a class=\"page-link\" href=" + pUrl + "page=" + pageNo
				+ "					aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
				+ "				</a></li>\r\n" + "			</ul>\r\n" + "		</nav>");

		return pagination.toString();

	}

	// ���ǿ� ���� ���� ����Ʈ
	@RequestMapping(value = "/serviceListCondition", method = RequestMethod.GET)
	public String serviceListCondition(ServiceConditionVo svo, Model model,
			@RequestParam(defaultValue = "1", required = false) int page, HttpServletRequest request) throws Exception {

		int pageEnd = page * 5;

		// ����¡ ���� �Է�
		Map<String, Object> voMap = new HashMap<>();
		voMap.put("page", page == 1 ? page : page * 5 - 4);
		voMap.put("pageEnd", pageEnd);

		// ���� ���� �Է�
		Map<String, Object> condMap = new HashMap<>();

		// ���̹�Ƽ�� ������ ���� ��ü �и�
		Field[] fields = svo.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);

			if (field.get(svo) != null || "0".equals(String.valueOf(field.get(svo)))) {
				condMap.put(field.getName(), field.get(svo));
				logger.info("condMap {}", condMap);
				if (svo.getJA0110() == 0 && svo.getJA0111() == 0) {
					// condMap.remove("JA0110");
					// condMap.remove("JA0111");

				}
			}
		}
		String pUrl = request.getRequestURI();

		// url ����
		for (String key : condMap.keySet()) {
			pUrl += (pUrl.indexOf("?") > -1) ? "&" + key + "=" + condMap.get(key) : "?" + key + "=" + condMap.get(key);
		}

		logger.info("pUrl {}", pUrl);

		voMap.put("condMap", condMap);

		List<ServiceListVo> selectServiceList = serviceListService.selectServiceListByCondition(voMap);
		int conditionCnt = serviceListService.conditionCnt(voMap);

		// String pUrl = request.getRequestURI();
		model.addAttribute("serviceList", selectServiceList);
		model.addAttribute("pagination", pagination(page, pUrl, conditionCnt));
		logger.info("vomap {}", voMap);

		return "/openapi/serviceList";

	}

}
