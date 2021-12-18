package kr.go.openapi.service;

import java.util.List;
import java.util.Map;

import kr.go.openapi.vo.PageVo;
import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

public interface ServiceListService {

	//pageVo로 입력
	public int insertPageVo(PageVo pageVo);
	
	public int insertPageVoDetail(PageVo pageVo);
	
	public int insertPageVoCondition(PageVo pageVo);
	
	
	//serviceVo 입력
	public int insertService(ServiceVo serviceVo);
	
	//serviceListVo 입력
	public int insertServiceList(ServiceListVo serviceListVo);
	
	//serviceDetailVo 입력
	public int insertServiceDetail(ServiceDetailVo serviceDetailVo);
	
	public int insertServiceCondition(ServiceConditionVo scVo);

	//serviceDetailVo 찾기
	public ServiceDetailVo findById(String serviceId);
	
	//serviceDetailVo 찾기
	public ServiceConditionVo findConditionById(String serviceId);
	
	//총 DB
	public int getDbCount();
	
	public int serviceConditionDbCount();
	
	//serviceList 가져오기
	public List<ServiceListVo> serviceList(Map<String, Object> map);
	
	//조건에 따른 리스트
	public List<ServiceListVo> selectServiceListByCondition(Map<String, Object> voMap);
	
		
	public int conditionCnt(Map<String, Object> voMap);

	
}
