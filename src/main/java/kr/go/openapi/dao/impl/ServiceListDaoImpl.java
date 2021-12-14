package kr.go.openapi.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.go.openapi.dao.ServiceListDao;
import kr.go.openapi.vo.ServiceConditionVo;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;

@Repository
public class ServiceListDaoImpl implements ServiceListDao {

	@Autowired
	private SqlSessionTemplate session;

	@Override
	public int insertService(ServiceVo serviceVo) {

		return session.insert("serviceVo.insertService", serviceVo);
	}

	
	@Override
	public int insertServiceList(ServiceListVo serviceListVo) {
	
		return session.insert("serviceListVo.insertServiceList", serviceListVo);
	}

	@Override
	public int insertServiceCondition(ServiceConditionVo scVo) {
		
		return session.insert("serviceConditionVo.insertServiceCondition", scVo);
	}
	
	
	
	@Override
	public ServiceDetailVo findById(String serviceId) {

		return session.selectOne("serviceDetailVo.findById", serviceId);
	}
	
	

	@Override
	public ServiceConditionVo findConditionById(String serviceId) {
		
		return session.selectOne("serviceConditionVo.findConditionById", serviceId);
	}


	@Override
	public int getDbCount() {

		return session.selectOne("serviceVo.getDbCount");
	}

	
	@Override
	public int serviceConditionDbCount() {

		return session.selectOne("serviceConditionVo.serviceConditionDbCount");
	}


	@Override
	public List<ServiceListVo> serviceList(Map<String, Object> map) {

		return session.selectList("serviceListVo.serviceList", map);
	}
	


	@Override
	public List<ServiceListVo> selectServiceListByCondition() {

		return session.selectList("serviceListVo.selectServiceListByCondition");
	}


	@Override
	public int insertServiceDetail(ServiceDetailVo serviceDetailVo) {

		return session.insert("serviceDetailVo.insertServiceDetail", serviceDetailVo);
	}

	@Override
	public int test() {
		// TODO Auto-generated method stub
		return session.selectOne("serviceVo.test");
	}

	
	

}
