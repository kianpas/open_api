package kr.go.openapi.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7200261764056793008L;

	/*
	 * 서비스ID string 공공서비스 고유 식별자
	 */
	private String serviceId;

	/*
	 * 지원유형 string 현금, 현물, 이용권, 서비스 등 지원 형태
	 */
	private String serviceType;

	/*
	 * 서비스명 string 공공서비스 명칭
	 */
	private String serviceName;

	/*
	 * 서비스목적 string 공공서비스 목적
	 */
	private String servicePurpose;

	/*
	 * 지원대상 string 공공서비스 지원대상
	 */
	private String serviceTarget;

	/*
	 * 선정기준 string 지원대상 선정기준
	 */
	private String serviceRule;

	/*
	 * 지원내용 string 공공서비스 지원내용
	 */
	private String serviceContent;

	/*
	 * 신청방법 string 공공서비스 신청방법
	 */
	private String appMethod;

	

}
