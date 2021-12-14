package kr.go.openapi;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.ServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.go.openapi.dao.ServiceListDao;
import kr.go.openapi.service.ServiceListService;
import kr.go.openapi.service.impl.ServiceListServiceImpl;
import kr.go.openapi.vo.ServiceDetailVo;
import kr.go.openapi.vo.ServiceListVo;
import kr.go.openapi.vo.ServiceVo;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class OpenApiControllerTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	DataSource dataSource;

//	@Autowired
//	private ServiceListDao serviceListDao;
		
	MockMvc mockMvc;

	@Test
	public void insertTest() throws Exception {

		ServiceVo service = new ServiceVo();
		
//		service.setAppMethod("�슦�렪�젒�닔 \\r\\n�뀋 以묒븰�빐�뼇�븞�쟾�떖�뙋�썝 : �꽭醫낆떆 媛�由꾨줈232 �꽭醫낅퉬利덈땲�뒪�꽱�꽣 A�룞 521�샇(30121)\\r\\n�뀋 遺��궛吏�諛⑺빐�뼇�븞�쟾�떖�뙋�썝 : 遺��궛愿묒뿭�떆 �룞援� 異⑹옣��濡� 351(48755)\\r\\n�뀋 �씤泥쒖�諛⑺빐�뼇�븞�쟾�떖�뙋�썝 : �씤泥쒓킅�뿭�떆 誘몄텛��援� �꽍�젙濡�239, 8痢�(22101)\\r\\n�뀋 紐⑺룷吏�諛⑺빐�뼇�븞�쟾�떖�뙋�썝 : �쟾�궓 紐⑺룷�떆 �넻�씪��濡� 130(58746)\\r\\n�뀋 �룞�빐吏�諛⑺빐�뼇�븞�쟾�떖�뙋�썝 : 媛뺤썝�룄 �룞�빐�떆 �븳�꽟濡� 141-1(25769)");
//		service.setServiceId("119200000001");
//		service.setServiceRule(
//				"\"�뿃 湲곌��냽�옣鍮꾠냽�꽕鍮꾩궗�뾽�쓽 �궗�뾽�옄 �꽑�젙�� �떎�쓬�쓽 �닚�쐞濡� �븯�릺, �룞�씪 �닚�쐞�옄�쓽 �꽭遺� �슦�꽑�닚�쐞�뒗 ��留덈젰 湲곌� ��泥� �떊泥��옄, �냼�삎�뼱�꽑 �뼱�뾽�씤(�닔�삊 �쐞�뙋�떎�쟻 誤�), �깮遺꾪빐�꽦 \\r\\n\\r\\n�뿃 湲곌�쨌�옣鍮꽷룹꽕鍮꾩궗�뾽�쓽 �궗�뾽�옄 �꽑�젙�� �떎�쓬�쓽 �닚�쐞濡� �븯�릺, �룞�씪 �닚�쐞�옄�쓽 �꽭遺� �슦�꽑�닚�쐞�뒗 ��留덈젰 湲곌� ��泥� �떊泥��옄, �냼�삎�뼱�꽑 �뼱�뾽�씤(�닔�삊 �쐞�뙋�떎�쟻 誤�), �깮遺꾪빐�꽦 �뼱援� �떆踰붿궗�뾽 李몄뿬 �뼱�뾽�씤, �뼱�뾽�씤 �썑怨꾩옄, 援��궛湲곌� �궗�슜�옄 �닚�쑝濡� �븿\\r\\n - �떎留�, LED�벑�쓽 寃쎌슦 吏묒뼱�벑�쓣 �옉�뾽�벑蹂대떎 �슦�꽑�쑝濡� �븯硫�, �룞�씪 �닚�쐞�옄�쓽 �슦�꽑�닚�쐞�뒗 �냼�삎�뼱�꽑 �뼱�뾽�씤(�닔�삊 �쐞�뙋�떎�쟻 誤�), �뼱�뾽�씤 �썑怨꾩옄 �닚�쑝濡� �븿\\r\\n      * ��泥닿린愿��쓽 �떒醫� �벑 遺��뱷�씠�븳 寃쎌슦 �긽�쐞 留덈젰�쓣 �쟻�슜�븷 �닔 �엳�쑝硫� 洹� �뿀�슜踰붿쐞�뒗 10% �씠�븯濡� �븳�떎.\\r\\n    �몺 �쑀瑜섏젅媛먯옣移� 諛� LED �벑�쓣 �꽕移섑븯怨좎옄 �븯�뒗 �옄(�떊泥��옄媛� �엳�쓣 寃쎌슦 理쒖슦�꽑 吏��썝)\\r\\n    �몼 �쑁�긽�슜 湲곌��쓣 �빐�긽�슜 �뵒�젮湲곌��쑝濡� ��泥댄븯怨좎옄 �븯�뒗 �옄\\r\\n    �몾 �궗�슜�뿰�닔媛� 留롮� �끂�썑 湲곌��쓣 �빐�긽�슜 �뵒�젮湲곌��쑝濡� ��泥댄븯怨좎옄 �븯�뒗 �옄\\r\\n    �몿 臾대룞�젰 �뼱�꽑�뿉 �떊洹쒕줈 �빐�긽�슜 �뵒�젮湲곌��쓣 �꽕移섑븯怨좎옄 �븯�뒗 �옄\\r\\n    �뫀 �끂�썑�옣鍮꽷룹꽕鍮꾨�� ��泥댄븯怨좎옄 �븯�뒗 �옄\\r\\n    �뫁 踰뺣졊�쑝濡� �꽕移섍� �쓽臾댄솕�맂 �끂�썑�옣鍮꽷룹꽕鍮꾨�� �씗留앺븯�뒗 �옄\\r\\n    �뫂 �깉濡쒖슫 �옣鍮꽷룹꽕鍮꾨�� �꽕移섑븯怨좎옄 �븯�뒗 �옄\\r\\n    �뫃 媛��넄由� 湲곌��쓣 ��泥댄븯怨좎옄 �븯�뒗 �옄. �떒, �몺�댘�뫂 �닚�쐞蹂대떎 �슦�꽑吏��썝 遺덇��븯硫� �쟾泥� 蹂댁“湲덉쓽 30%瑜� �꽆�쓣 �닔 �뾾�쓬 \\r\\n      * 湲고� 吏��옄泥� �옄泥댁떎�젙�뿉 �뵲�씪 �슦�꽑�닚�쐞 異붽� 媛��뒫\\r\\n\\r\\n�뿃  �궗�뾽�옄 �꽑�젙�쓣 �쐞�븯�뿬 �븘�슂�븳 寃쎌슦 �옄泥댁떖�쓽 湲곌뎄�뿉 �긽�젙쨌�떖�쓽�븷 �닔 �엳�쑝硫� �궗�뾽�룷湲곗뿉 ��鍮꾪븯�뿬 �삁鍮꾩궗�뾽�옄瑜� �꽑�젙�븷 �닔 �엳�쓬 (�떦�빐�뀈�룄 �궗�뾽�뿉留� �쟻�슜)\\r\\n      * �떒, 異붽�紐⑥쭛 �궗�뾽�옄 �꽑�젙�떆 吏��옄泥� �뙋�떒�뿉 �쓽嫄� �떖�쓽�깮�왂 媛��뒫\"");
//		serviceListDao.insertService(service);
//		
//		ServiceDetailVo serviceDetailVo = new ServiceDetailVo();
//		
//		serviceDetailVo.setAppDoc("�뿃 �냼�뱷�옱�궛 �벑 利앷굅�옄猷� �젣異� 遺덊븘�슂�븳 寃쎌슦 : 援��꽭泥��뿉�꽌 �솗�씤�븳 �냼�뱷, �옱�궛 �벑�쓽 �옄猷뚯� �룞�씪�븳 寃쎌슦\\r\\n�뿃 �냼�뱷�옱�궛 �벑 利앷굅�옄猷� �젣異� �븘�슂�븳 寃쎌슦 : 援��꽭泥��뿉�꽌 �솗�씤�븳 �냼�뱷, �옱�궛 �벑�쓽 �옄猷뚯� �떎瑜� 寃쎌슦");
//		serviceDetailVo.setServiceId("105100000001");
//		
//		serviceListDao.insertServiceDetail(serviceDetailVo);
//		
		//assertNotNull(service);
		assertNotNull(context);
		assertNotNull(dataSource);
		
		
		//�뀒�뒪�듃 �넻怨� �썑 �궘�젣
//		serviceListDao.deleteById(service.getServiceId());
//		serviceListDao.deleteById(serviceDetailVo.getServiceId());

	}

}
