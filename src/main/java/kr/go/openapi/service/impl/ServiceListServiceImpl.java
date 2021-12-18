package kr.go.openapi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.go.openapi.dao.ServiceListDao;
import kr.go.openapi.service.ServiceListService;
import kr.go.openapi.vo.PageVo;
import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

@Service
public class ServiceListServiceImpl implements ServiceListService {

	@Autowired
	private ServiceListDao serviceListDao;

	@Override
	public int insertPageVo(PageVo pageVo) {
		int result = 0;
		// json �迭 �ݺ� -> ��ü�� ����
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
			int readCount = !tmp.get("조회수").isJsonNull() ? tmp.get("조회수").getAsInt() : 0;


			ServiceVo serviceVo = new ServiceVo(serviceId, serviceType, serviceName, servicePurpose, serviceTarget,
					serviceRule, serviceContent, appMethod);
			ServiceListVo serviceListVo = new ServiceListVo(appPeriod, detailUrl, orgCode, orgName, deptName,
					readCount);
			serviceListVo.setServiceId(serviceId);

			if (findById(serviceId) == null) {

				result = insertService(serviceVo);
				result += insertServiceList(serviceListVo);
				// logger.info("aserviceListVo Result {}", result);
			}

		}

		return result;
	}

	@Override
	public int insertPageVoDetail(PageVo pageVo) {
		// json �迭 �ݺ� ��ü�� ����
		int result = 0;
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

			ServiceDetailVo serviceDetailVo = new ServiceDetailVo(appDoc, appOrgName, phone, appUrl, editDate, orgName,
					adminRule, law, lawOrder);
			serviceDetailVo.setServiceId(serviceId);

			if (findById(serviceId) == null) {
				result = insertServiceDetail(serviceDetailVo);
				// logger.info("serviceDetail result {}", result);
			}
		}

		return result;
	}

	@Override
	public int insertPageVoCondition(PageVo pageVo) {
		Gson gson = new Gson();

		for (int i = 0; i < pageVo.getData().size(); i++) {

			// jsonArrary condition �迭 Ÿ������ ����
			ServiceConditionVo[] scVo = gson.fromJson(pageVo.getData(), ServiceConditionVo[].class);
			// logger.info("scVo {}", scVo);
			if (findConditionById(scVo[i].getSVC_ID()) == null) {
				int result = insertServiceCondition(scVo[i]);
				//logger.info("serviceDetail result {}", result);
			}
		}
		return 0;
	}

	@Override
	public int insertService(ServiceVo serviceVo) {

		return serviceListDao.insertService(serviceVo);
	}

	@Override
	public int insertServiceList(ServiceListVo serviceListVo) {

		return serviceListDao.insertServiceList(serviceListVo);
	}

	@Override
	public int insertServiceCondition(ServiceConditionVo scVo) {

		return serviceListDao.insertServiceCondition(scVo);
	}

	@Override
	public ServiceDetailVo findById(String serviceId) {

		return serviceListDao.findById(serviceId);
	}

	@Override
	public ServiceConditionVo findConditionById(String serviceId) {

		return serviceListDao.findConditionById(serviceId);
	}

	@Override
	public int getDbCount() {

		return serviceListDao.getDbCount();
	}

	@Override
	public int serviceConditionDbCount() {

		return serviceListDao.serviceConditionDbCount();
	}

	@Override
	public List<ServiceListVo> serviceList(Map<String, Object> map) {

		return serviceListDao.serviceList(map);
	}

	@Override
	public List<ServiceListVo> selectServiceListByCondition(Map<String, Object> voMap) {

		return serviceListDao.selectServiceListByCondition(voMap);
	}

	@Override
	public int insertServiceDetail(ServiceDetailVo serviceDetailVo) {

		return serviceListDao.insertServiceDetail(serviceDetailVo);
	}

	@Override
	public int conditionCnt(Map<String, Object> voMap) {

		return serviceListDao.conditionCnt(voMap);
	}

	// �ѱ� key�� -> ��ü �ʵ��
	public String getAsString(JsonObject tmp, String jsonKey) {
		String str = !tmp.get(jsonKey).isJsonNull() ? tmp.get(jsonKey).getAsString() : "";
		return str;
	}

}
