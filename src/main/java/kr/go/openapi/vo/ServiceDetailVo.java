package kr.go.openapi.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ServiceDetailVo extends ServiceVo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2691622485731027962L;

	/*
	 * SVC_ID string 공공서비스 고유 식별자
	 */
	// private String svcId;

	/* 구비서류 string */
	private String appDoc;

	/* 접수기관명 string */
	private String appOrgName;

	/* 문의처전화번호 string */
	private String phone;

	/* 온라인신청사이트URL string */
	private String appUrl;

	/* 수정일시 string */
	private String editDate;

	/* 소관기관명 string */
	private String orgName;

	/* 행정규칙 string */
	private String adminRule;

	/* 자치법규 string */
	private String law;

	/* 법령 string */
	private String lawOrder;
}
