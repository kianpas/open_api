package kr.go.openapi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.openapi.dao.ServiceListDao;
import kr.go.openapi.service.ServiceListService;
import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

@Service
public class ServiceListServiceImpl implements ServiceListService {

	@Autowired
	private ServiceListDao serviceListDao;

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
	public int test() {

		return serviceListDao.test();
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
	public List<ServiceListVo> selectServiceListByCondition() {
		
		return serviceListDao.selectServiceListByCondition();
	}

	@Override
	public int insertServiceDetail(ServiceDetailVo serviceDetailVo) {

		return serviceListDao.insertServiceDetail(serviceDetailVo);
	}

}
