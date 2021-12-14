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
	 * SVC_ID string �������� ���� �ĺ���
	 */
	// private String svcId;

	/* ���񼭷� string */
	private String appDoc;

	/* ��������� string */
	private String appOrgName;

	/* ����ó��ȭ��ȣ string */
	private String phone;

	/* �¶��ν�û����ƮURL string */
	private String appUrl;

	/* �����Ͻ� string */
	private String editDate;

	/* �Ұ������ string */
	private String orgName;

	/* ������Ģ string */
	private String adminRule;

	/* ��ġ���� string */
	private String law;

	/* ���� string */
	private String lawOrder;
}
