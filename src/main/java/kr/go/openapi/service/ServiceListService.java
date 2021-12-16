package kr.go.openapi.service;

import java.util.List;
import java.util.Map;

import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

public interface ServiceListService {

	//serviceVo �Է�
	public int insertService(ServiceVo serviceVo);
	
	//serviceListVo �Է�
	public int insertServiceList(ServiceListVo serviceListVo);
	
	//serviceDetailVo �Է�
	public int insertServiceDetail(ServiceDetailVo serviceDetailVo);
	
	public int insertServiceCondition(ServiceConditionVo scVo);

	//serviceDetailVo ã��
	public ServiceDetailVo findById(String serviceId);
	
	//serviceDetailVo ã��
	public ServiceConditionVo findConditionById(String serviceId);
	
	//�� DB
	public int getDbCount();
	
	public int serviceConditionDbCount();
	
	//serviceList ��������
	public List<ServiceListVo> serviceList(Map<String, Object> map);
	
	//���ǿ� ���� ����Ʈ
	public List<ServiceListVo> selectServiceListByCondition(Map<String, Object> voMap);
	
		
	public int test();

	public int conditionCnt(Map<String, Object> voMap);
}
