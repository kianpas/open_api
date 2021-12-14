package kr.go.openapi.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceConditionVo {

	/*
	 * 공공서비스 고유 식별자
	 */ 
	private String SVC_ID;

	/* 남성 */
	private String JA0101;

	/* 여성 */
	private String JA0102;

	/* 영유아(0~5) */
	private String JA0103;

	/* 아동(6~12) */
	private String JA0104;

	/* 청소년(13~18) */
	private String JA0105;

//	청년(19~29)
	private String JA0106;

//	중년(30~49)
	private String JA0107;

//	장년(50~64)
	private String JA0108;

//	노년기(65~)
	private String JA0109;

//	대상연령(시작)
	private int JA0110;

//	대상연령(종료)
	private int JA0111;

//	0 ~ 50
	private String JA0201;
//
//	51 ~ 75
	private String JA0202;

	// 76 ~ 100
	private String JA0203;
//
//	101 ~ 200
	private String JA0204;
//
//	200% 초과
	private String JA0205;

//	예비부모/난임
	private String JA0301;

//	임신부
	private String JA0302;
//
//	출산/입양
	private String JA0303;
//
//	심한 장애
	private String JA0304;
//
//	심하지 않은 장애
	private String JA0305;
//
//	독립유공자
	private String JA0306;
//
//	국가유공자
	private String JA0307;
//
//	참전유공자
	private String JA0308;
//
//	보훈보상대상자
	private String JA0309;

//	특수임무유공자
	private String JA0310;

//	5·18민주유공자
	private String JA0311;

//	제대군인
	private String JA0312;

//	농업인
	private String JA0313;
//
//	어업인
	private String JA0314;
//
//	축산업인
	private String JA0315;
//
//	임업인
	private String JA0316;
//
//	초등학생
	private String JA0317;
//
//	중학생
	private String JA0318;
//
//	고등학생
	private String JA0319;
//
//	대학생/대학원생
	private String JA0320;
//
//	해당사항없음
	private String JA0322;
//
//	질병/부상/질환자
	private String JA0323;
//
//	중증·난치·희귀질환자
	private String JA0324;
//
//	요양환자/치매환자
	private String JA0325;
//
//	근로자/직장인
	private String JA0326;
//
//	구직자/실업자
	private String JA0327;
//
//	다문화가족
	private String JA0401;
//
//	북한이탈주민
	private String JA0402;
//
//	한부모가정/조손가정
	private String JA0403;
//
//	1인가구
	private String JA0404;
//
//	해당사항없음
	private String JA0410;
//
//	다자녀가구
	private String JA0411;
//
//	무주택세대
	private String JA0412;
//
//	신규전입
	private String JA0413;
//
//	확대가족
	private String JA0414;

}
