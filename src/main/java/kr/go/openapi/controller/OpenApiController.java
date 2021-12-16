package kr.go.openapi.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.*;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

	@RequestMapping(value = "/serviceList/{page}", method = RequestMethod.GET)
	public String serviceList(Model model, @PathVariable(required = false) int page, HttpServletRequest request) {

		try {
			String url = "https://api.odcloud.kr/api/gov24/v1/";

			// int page = 1;
			// 요청할 uri 생성
			URI uri = UriComponentsBuilder.fromUriString(url + "serviceList").queryParam("page", 1)
					.queryParam("perPage", 1).queryParam("serviceKey", serviceKey).build(true).toUri();

			// 커넥션 풀 설정
			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100).setMaxConnPerRoute(5).build();

			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

			// api 요청할 restTemplate
			RestTemplate restTemplate = new RestTemplate(factory);

			PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

			int currentCnt = pageVo.getCurrentCount();
			int apiTotalCnt = pageVo.getTotalCount();

			// 레코드수 확인
			int serviceDbCount = serviceListService.getDbCount();

			int serviceConditionDbCount = serviceListService.serviceConditionDbCount();

			logger.info("dbcount {} apiTotalCnt {}", serviceDbCount, apiTotalCnt);

			// 레코드 수 체크 후 db 저장 진행
			if (apiTotalCnt > serviceDbCount) {

				fetchApi(url, restTemplate, apiTotalCnt);

				fetchDetailApi(url, restTemplate, apiTotalCnt);
			}
			;
			if (362 != serviceConditionDbCount) {
				fetchConditionApi(url, restTemplate, apiTotalCnt);
			}
			;

			Map<String, Object> map = new HashMap<>();
			map.put("page", page == 1 ? page : page * 5 - 4);
			int pageEnd = page * 5;
			map.put("pageEnd", pageEnd);

			// logger.info("selectServiceList {}", selectServiceList);

			// List<ServiceListVo> serviceList = serviceListService.serviceList(map);
			// String pUrl = request.getRequestURI();

			// model.addAttribute("pagination", pagination(page, pUrl, serviceDbCount));
			// model.addAttribute("serviceList", serviceList);
		} catch (Exception e) {
			throw e;
		}
		return "/openapi/serviceList";

	}

	// ���� ������
	@RequestMapping(value = "/serviceDetail/{serviceId}", method = RequestMethod.GET)
	public String serviceDetail(Model model, @PathVariable String serviceId) {

		ServiceDetailVo serviceDetailVo = serviceListService.findById(serviceId);
		model.addAttribute("serviceDetailVo", serviceDetailVo);

		return "/openapi/serviceDetail";
	}

	// service, seviceList api ��������
	public void fetchApi(String url, RestTemplate restTemplate, int perPage) {

		try {
			int page = 1;
			// ��û�� uri ����
			// build(true)�� ���� ���ڵ� ����
			// toUri() �ռ� �ۼ��� �������� uri��
			URI uri = UriComponentsBuilder.fromUriString(url + "serviceList").queryParam("page", page)
					.queryParam("perPage", perPage).queryParam("serviceKey", serviceKey).build(true).toUri();

			PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

			// String xml = XML.toString(pageVo);

			// logger.info("xml {}", xml);

			// json �迭 �ݺ� ��ü�� ����
			for (int i = 0; i < pageVo.getData().size(); i++) {

				JsonObject tmp = (JsonObject) pageVo.getData().get(i);

				String serviceId = getAsString(tmp, "서비스ID");
				String serviceType = getAsString(tmp, "지원유형");
				String serviceName = getAsString(tmp, "서비스명");
				String servicePurpose = getAsString(tmp, "서비스목적");
				String serviceTarget = getAsString(tmp, "지원대상");
				String serviceRule = getAsString(tmp, "선정기준");
				String serviceContent = getAsString(tmp, "지원내용");
				String appMethod = getAsString(tmp, "신청방법");
				String appPeriod = getAsString(tmp, "신청기한");
				String detailUrl = getAsString(tmp, "상세조회URL");
				String orgCode = getAsString(tmp, "소관기관코드");
				String orgName = getAsString(tmp, "소관기관명");
				String deptName = getAsString(tmp, "부서명");
				int readCount = !tmp.get("��ȸ��").isJsonNull() ? tmp.get("조회수").getAsInt() : 0;

				ServiceVo serviceVo = new ServiceVo(serviceId, serviceType, serviceName, servicePurpose, serviceTarget,
						serviceRule, serviceContent, appMethod);
				ServiceListVo serviceListVo = new ServiceListVo(appPeriod, detailUrl, orgCode, orgName, deptName,
						readCount);
				serviceListVo.setServiceId(serviceId);

				if (serviceListService.findById(serviceId) == null) {

					int result = serviceListService.insertService(serviceVo);
					int result2 = serviceListService.insertServiceList(serviceListVo);
					logger.info("aserviceListVo Result {}", result);
				}
			}

		} catch (Exception e) {
			throw e;
		}

		// resttemplate�� ������ json ����, ���ڿ��� ����
//		ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);
		//
//		// json��ü ����
//		PageVo obj = gson.fromJson(response.getBody().toString(), PageVo.class);
		//
//		int count = (int) Math.ceil(obj.getTotalCount() / obj.getPerPage()) + 1;
		//
//		// json ��ü ��ȯ
//		JsonObject jObj = gson.fromJson(response.getBody().toString(), JsonObject.class);
//		// data �迭, json �迭�� ����
//		JsonArray arr = jObj.getAsJsonArray("data");

		// Set<Map.Entry<String, JsonElement>> tmpEntry = tmp.entrySet();
//		int index = 0;
//		for (Map.Entry<String, JsonElement> entry : tmpEntry) {
//			
//			logger.info("key set {} value {}", entry.getKey(),
//					!entry.getValue().isJsonNull() ? entry.getValue().getAsString() : "");

	}

	// @RequestMapping(value = "/fetchDetailApi", method = RequestMethod.GET)
	public void fetchDetailApi(String url, RestTemplate restTemplate, int perPage) {

		try {
			int page = 1;
			// ��û�� uri ����
			URI uri = UriComponentsBuilder.fromUriString(url + "serviceDetail").queryParam("page", page)
					.queryParam("perPage", perPage).queryParam("serviceKey", serviceKey).build(true).toUri();

			PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

			// json �迭 �ݺ� ��ü�� ����
			for (int i = 0; i < pageVo.getData().size(); i++) {
				JsonObject tmp = (JsonObject) pageVo.getData().get(i);

				String serviceId = getAsString(tmp, "SVC_ID");
				String appDoc = getAsString(tmp, "구비서류");
				String appOrgName = getAsString(tmp, "접수기관명");
				String phone = getAsString(tmp, "문의처전화번호");
				String appUrl = getAsString(tmp, "온라인신청사이트URL");
				String editDate = getAsString(tmp, "수정일시");
				String orgName = getAsString(tmp, "소관기관명");
				String adminRule = getAsString(tmp, "행정규칙");
				String law = getAsString(tmp, "자치법규");
				String lawOrder = getAsString(tmp, "법령");

				ServiceDetailVo serviceDetailVo = new ServiceDetailVo(appDoc, appOrgName, phone, appUrl, editDate,
						orgName, adminRule, law, lawOrder);
				serviceDetailVo.setServiceId(serviceId);

				if (serviceListService.findById(serviceId) == null) {
					int result = serviceListService.insertServiceDetail(serviceDetailVo);
					logger.info("serviceDetail result {}", result);
				}
			}
		} catch (Exception e) {
			throw e;
		}

	}

	// ���� ��������
	public void fetchConditionApi(String url, RestTemplate restTemplate, int perPage) {

		try {
			int page = 1;
			// ��û�� uri ����
			URI uri = UriComponentsBuilder.fromUriString(url + "supportConditions").queryParam("page", page)
					.queryParam("perPage", perPage).queryParam("serviceKey", serviceKey).build(true).toUri();

			// pageVo Ÿ������ ����
			PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

			Gson gson = new Gson();

			for (int i = 0; i < pageVo.getData().size(); i++) {

				// jsonArrary condition �迭 Ÿ������ ����
				ServiceConditionVo[] scVo = gson.fromJson(pageVo.getData(), ServiceConditionVo[].class);
				// logger.info("scVo {}", scVo);
				if (serviceListService.findConditionById(scVo[i].getSVC_ID()) == null) {
					int result = serviceListService.insertServiceCondition(scVo[i]);
					logger.info("serviceDetail result {}", result);
				}
			}
		} catch (Exception e) {
			throw e;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/slByAjax/{page}", method = RequestMethod.GET)
	public Map<String, Object> slcByAjax(@PathVariable(required = false) int page,
			@RequestParam(value = "url") String url, HttpServletRequest request) {

		try {

			int pageEnd = page * 5;

			// 페이징 정보 입력
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
		} catch (Exception e) {
			throw e;
		}
	}

	// 조건에 따른 서비스 리스트 비동기
	@ResponseBody
	@RequestMapping(value = "/serviceList/{page}", method = RequestMethod.POST)
	public Map<String, Object> slcByAjax(@RequestBody ServiceConditionVo svo, Model model, @PathVariable int page,
			HttpServletRequest request) throws Exception {

		try {
			int pageEnd = page * 5;

			// 페이징 정보 입력
			Map<String, Object> voMap = new HashMap<>();
			Map<String, Object> resultMap = new HashMap<>();

			voMap.put("page", page == 1 ? page : page * 5 - 4);
			voMap.put("pageEnd", pageEnd);
			logger.info("svo {}", svo);

			if (svo == null) {
				List<ServiceListVo> serviceList = serviceListService.serviceList(voMap);

				resultMap.put("serviceList", serviceList);
			} else {

				// 조건 정보 입력
				Map<String, Object> condMap = new HashMap<>();

				// 마이바티스 맵핑을 위한 객체 분리
				Field[] fields = svo.getClass().getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);

					if (field.get(svo) != null) {
						condMap.put(field.getName(), field.get(svo));
						logger.info("condMap {}", condMap);
						if (svo.getJA0110() == 0 && svo.getJA0110() == 0) {
							condMap.remove("JA0110");
							condMap.remove("JA0111");

						}
					}
				}
				String pUrl = request.getRequestURI() + "/" + page;

				pUrl = pUrl.lastIndexOf("/") > -1 ? pUrl.substring(0, pUrl.lastIndexOf("/")) : pUrl;

				voMap.put("condMap", condMap);

				List<ServiceListVo> selectServiceList = serviceListService.selectServiceListByCondition(voMap);
				int conditionCnt = serviceListService.conditionCnt(voMap);

				resultMap.put("serviceList", selectServiceList);
				resultMap.put("pagination", pagingAjax(page, pUrl, conditionCnt));
			}

			return resultMap;
		} catch (Exception e) {
			throw e;
		}

	}

	// 페이징처리 ajax
	public String pagingAjax(int page, String pUrl, int totalCnt) {

		StringBuffer pagination = new StringBuffer();

		int pageBarSize = 5;
		int totalPage = (int) Math.ceil((double) totalCnt / pageBarSize);
		int pageStart = ((page - 1) / pageBarSize) * pageBarSize + 1;
		int pageBarEnd = pageStart + pageBarSize - 1;
		int left = page >= 1 && page <= 5 ? 1 : pageStart - 1;
		// pUrl = (pUrl.indexOf(String.valueOf(page)) > -1) ? p" : pUrl + "?";

		pUrl = pUrl.indexOf("/") > -1 ? pUrl.substring(0, pUrl.lastIndexOf("/")) : pUrl;
		logger.info("pUrl {}", pUrl);
		int pageNo = pageStart;
		pagination.append("<nav aria-label=\"Page navigation example\">\r\n"
				+ "			<ul class=\"pagination justify-content-center\">\r\n"
				+ "				<li class=\"page-item\"><a class=\"page-link\"\r\n" + "					href=" + pUrl
				+ "/" + left + "\r\n"
				+ "					aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
				+ "				</a></li>");
		logger.info("totalPage {}", totalPage);

		while (pageNo <= pageBarEnd && pageNo <= totalPage) {
			pagination.append("<li class=\"page-item\"><a class=\"page-link\"\r\n" + "href=" + pUrl + "/" + pageNo + ">"
					+ pageNo + "</a></li>");
			pageNo++;
		}
		pagination.append("<li class=\"page-item\"><a class=\"page-link\" href=" + pUrl + "/" + pageNo
				+ "					aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>\r\n"
				+ "				</a></li>\r\n" + "			</ul>\r\n" + "		</nav>");

		return pagination.toString();

	}

	// 조건에 따른 서비스 리스트
	@Deprecated
	@RequestMapping(value = "/serviceListCondition", method = RequestMethod.GET)
	public String serviceListCondition(ServiceConditionVo svo, Model model,
			@RequestParam(defaultValue = "1", required = false) int page, HttpServletRequest request) throws Exception {

		try {

			int pageEnd = page * 5;

			// 페이징 정보 입력
			Map<String, Object> voMap = new HashMap<>();
			voMap.put("page", page == 1 ? page : page * 5 - 4);
			voMap.put("pageEnd", pageEnd);

			// 조건 정보 입력
			Map<String, Object> condMap = new HashMap<>();

			// 마이바티스 맵핑을 위한 객체 분리
			Field[] fields = svo.getClass().getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);

				if (field.get(svo) != null) {
					condMap.put(field.getName(), field.get(svo));
					logger.info("condMap {}", condMap);
					if (svo.getJA0110() == 0 && svo.getJA0110() == 0) {
						condMap.remove("JA0110");
						condMap.remove("JA0111");

					}
				}
			}
			String pUrl = request.getRequestURI();

			// url 설정
			for (String key : condMap.keySet()) {
				pUrl += (pUrl.indexOf("?") > -1) ? "&" + key + "=" + condMap.get(key)
						: "?" + key + "=" + condMap.get(key);
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
		} catch (Exception e) {
			throw e;
		}

	}

	// 일반 페이징
	@Deprecated
	public String pagination(int page, String pUrl, int totalCnt) {

		StringBuilder pagination = new StringBuilder();

		int pageBarSize = 5;
		int totalPage = (int) Math.ceil((double) totalCnt / 5);
		int pageStart = ((page - 1) / pageBarSize) * pageBarSize + 1;
		int pageBarEnd = pageStart + pageBarSize - 1;
		int left = page >= 1 && page <= 5 ? 1 : pageStart - 1;
		logger.info("2purl {}", pUrl);

		pUrl = (pUrl.indexOf("?") > -1) ? pUrl + "&" : pUrl + "?";
		logger.info("3purl {}", pUrl);
		int pageNo = pageStart;
		pagination.append("<nav aria-label=\"Page navigation example\">\r\n"
				+ "			<ul class=\"pagination justify-content-center\">\r\n"
				+ "				<li class=\"page-item\"><a class=\"page-link\"\r\n" + "					href=" + pUrl
				+ left + "\r\n"
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

	// �ѱ� key�� -> ��ü �ʵ��
	public String getAsString(JsonObject tmp, String jsonKey) {
		String str = !tmp.get(jsonKey).isJsonNull() ? tmp.get(jsonKey).getAsString() : "";
		return str;
	}

}
