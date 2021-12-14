package kr.go.openapi.controller;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

		// logger.info("xml {}", xml);

		// json �迭 �ݺ� ��ü�� ����
		for (int i = 0; i < pageVo.getData().size(); i++) {

			JsonObject tmp = (JsonObject) pageVo.getData().get(i);

			String serviceId = getAsString(tmp, "����ID");
			String serviceType = getAsString(tmp, "��������");
			String serviceName = getAsString(tmp, "���񽺸�");
			String servicePurpose = getAsString(tmp, "���񽺸���");
			String serviceTarget = getAsString(tmp, "�������");
			String serviceRule = getAsString(tmp, "��������");
			String serviceContent = getAsString(tmp, "��������");
			String appMethod = getAsString(tmp, "��û���");
			String appPeriod = getAsString(tmp, "��û����");
			String detailUrl = getAsString(tmp, "����ȸURL");
			String orgCode = getAsString(tmp, "�Ұ�����ڵ�");
			String orgName = getAsString(tmp, "�Ұ������");
			String deptName = getAsString(tmp, "�μ���");
			int readCount = !tmp.get("��ȸ��").isJsonNull() ? tmp.get("��ȸ��").getAsInt() : 0;

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

			// resttemplate�� ������ json ����, ���ڿ��� ����
//			ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);
			//
//			// json��ü ����
//			PageVo obj = gson.fromJson(response.getBody().toString(), PageVo.class);
			//
//			int count = (int) Math.ceil(obj.getTotalCount() / obj.getPerPage()) + 1;
			//
//			// json ��ü ��ȯ
//			JsonObject jObj = gson.fromJson(response.getBody().toString(), JsonObject.class);
//			// data �迭, json �迭�� ����
//			JsonArray arr = jObj.getAsJsonArray("data");

			// Set<Map.Entry<String, JsonElement>> tmpEntry = tmp.entrySet();
//			int index = 0;
//			for (Map.Entry<String, JsonElement> entry : tmpEntry) {
//				
//				logger.info("key set {} value {}", entry.getKey(),
//						!entry.getValue().isJsonNull() ? entry.getValue().getAsString() : "");
//				
//				Gson gson = new GsonBuilder().setLenient().create();
//			}

		}

	}

	// @RequestMapping(value = "/fetchDetailApi", method = RequestMethod.GET)
	public void fetchDetailApi(String url, RestTemplate restTemplate, int perPage) {

		int page = 1;
		// ��û�� uri ����
		URI uri = UriComponentsBuilder.fromUriString(url + "serviceDetail").queryParam("page", page)
				.queryParam("perPage", perPage).queryParam("serviceKey", serviceKey).build(true).toUri();

		PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

		// json �迭 �ݺ� ��ü�� ����
		for (int i = 0; i < pageVo.getData().size(); i++) {
			JsonObject tmp = (JsonObject) pageVo.getData().get(i);

			String serviceId = getAsString(tmp, "SVC_ID");
			String appDoc = getAsString(tmp, "���񼭷�");
			String appOrgName = getAsString(tmp, "���������");
			String phone = getAsString(tmp, "����ó��ȭ��ȣ");
			String appUrl = getAsString(tmp, "�¶��ν�û����ƮURL");
			String editDate = getAsString(tmp, "�����Ͻ�");
			String orgName = getAsString(tmp, "�Ұ������");
			String adminRule = getAsString(tmp, "������Ģ");
			String law = getAsString(tmp, "��ġ����");
			String lawOrder = getAsString(tmp, "����");

			ServiceDetailVo serviceDetailVo = new ServiceDetailVo(appDoc, appOrgName, phone, appUrl, editDate, orgName,
					adminRule, law, lawOrder);
			serviceDetailVo.setServiceId(serviceId);

			if (serviceListService.findById(serviceId) == null) {
				int result = serviceListService.insertServiceDetail(serviceDetailVo);
				logger.info("serviceDetail result {}", result);
			}
		}

	}

	@RequestMapping(value = "/serviceList", method = RequestMethod.GET)
	public String serviceList(Model model, @RequestParam(defaultValue = "1", required = false) int page) {

		String url = "https://api.odcloud.kr/api/gov24/v1/";

		// int page = 1;
		// ��û�� uri ����
		URI uri = UriComponentsBuilder.fromUriString(url + "serviceList").queryParam("page", 1).queryParam("perPage", 1)
				.queryParam("serviceKey", serviceKey).build(true).toUri();

		// Ŀ�ؼ� Ǯ ����
		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(100).setMaxConnPerRoute(5).build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

		// api ��û�� restTemplate
		RestTemplate restTemplate = new RestTemplate(factory);

		PageVo pageVo = restTemplate.getForObject(uri, PageVo.class);

		// gson ��ü ����
		// Gson gson = new GsonBuilder().setLenient().create();

		int currentCnt = pageVo.getCurrentCount();
		int apiTotalCnt = pageVo.getTotalCount();

		// apiTotalCnt = 10;
		// ���ڵ�� Ȯ��
		int serviceDbCount = serviceListService.getDbCount();

		int serviceConditionDbCount = serviceListService.serviceConditionDbCount();

		// logger.info("dbcount {} apiTotalCnt {}", dbCount, apiTotalCnt);

		// ���ڵ� �� üũ �� db ���� ����
		if (apiTotalCnt != serviceDbCount) {

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

		List<ServiceListVo> selectServiceList = serviceListService.selectServiceListByCondition();

		//logger.info("selectServiceList {}", selectServiceList);

		List<ServiceListVo> serviceList = serviceListService.serviceList(map);

		model.addAttribute("pagination", pagination(page));
		model.addAttribute("serviceList", serviceList);

		return "/openapi/serviceList";

	}

	// ���� ������
	@RequestMapping(value = "/serviceDetail/{serviceId}", method = RequestMethod.GET)
	public String serviceDetail(Model model, @PathVariable String serviceId) {

		ServiceDetailVo serviceDetailVo = serviceListService.findById(serviceId);
		model.addAttribute("serviceDetailVo", serviceDetailVo);

		return "/openapi/serviceDetail";
	}

	// ���� ��������
	public void fetchConditionApi(String url, RestTemplate restTemplate, int perPage) {

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

	}

	public String pagination(int page) {

		String pUrl = "/serviceList?page=";
		// String pUrl = request.getContextPath();
		StringBuffer pagination = new StringBuffer();
		int i;
		int pageBarSize = 5;
		int pageStart = ((page - 1) / pageBarSize) * pageBarSize + 1;
		int pageBarEnd = pageStart + pageBarSize - 1;
		int left = page >= 1 && page <= 5 ? 1 : pageStart - 1;
		pagination.append("<nav aria-label=\"Page navigation example\">\r\n"
				+ "			<ul class=\"pagination justify-content-center\">\r\n"
				+ "				<li class=\"page-item\"><a class=\"page-link\"\r\n" + "					href=" + pUrl
				+ left + "\r\n"
				+ "					aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span>\r\n"
				+ "				</a></li>");
		// page 6 > barend 6 ~ 10
		// page 11> barend 11 ~ 15 11 10 /5 2 *5 11
		// ������ 6 -1 5/5 1 * 5 +1
		for (i = pageStart; i <= pageBarEnd; i++) {
			pagination.append(
					"<li class=\"page-item\"><a class=\"page-link\"\r\n" + "href=" + pUrl + i + ">" + i + "</a></li>");
		}
		pagination.append("<li class=\"page-item\"><a class=\"page-link\" href=" + pUrl + i
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
