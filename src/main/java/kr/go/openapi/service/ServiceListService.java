package kr.go.openapi.service;

import java.util.List;
import java.util.Map;

import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

public interface ServiceListService {

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
	
	public List<ServiceListVo> selectServiceListByCondition();
		
	public int test();
}
