package kr.go.openapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
//@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ServiceListVo extends ServiceVo {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5316945524056103859L;

	/*
	 * 신청기한 string 공공서비스 신청기한
	 */
	private String appPeriod;

	/*
	 * 상세조회URL string 정부24 공공서비스 안내 페이지 URL
	 */
	private String url;

	/*
	 * 소관기관코드 string 공공서비스 소관기관 행정표준코드
	 */
	private String orgCode;

	/*
	 * 소관기관명 string 공공서비스 소관기관 명칭
	 */
	private String orgName;

	/*
	 * 부서명 string 공공서비스 소관기관의 부서 명칭
	 */
	private String deptName;

	/*
	 * 조회수 integer 정부24 공공서비스 안내 페이지 조회수
	 */
	private int readCount;

}