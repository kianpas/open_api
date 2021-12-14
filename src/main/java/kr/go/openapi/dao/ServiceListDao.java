package kr.go.openapi.dao;

import java.util.List;
import java.util.Map;

import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

public interface ServiceListDao {

	public int insertService(ServiceVo serviceVo);

	public int insertServiceList(ServiceListVo serviceListVo);

	public int insertServiceDetail(ServiceDetailVo serviceDetailVo);

	public int insertServiceCondition(ServiceConditionVo scVo);

	public ServiceDetailVo findById(String serviceId);
	
	public ServiceConditionVo findConditionById(String serviceId);

	public int test();

	public int getDbCount();
	
	public int serviceConditionDbCount();

	public List<ServiceListVo> serviceList(Map<String, Object> map);
	
	public List<ServiceListVo> selectServiceListByCondition();

}
