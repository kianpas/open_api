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
	 * ��û���� string �������� ��û����
	 */
	private String appPeriod;

	/*
	 * ����ȸURL string ����24 �������� �ȳ� ������ URL
	 */
	private String url;

	/*
	 * �Ұ�����ڵ� string �������� �Ұ���� ����ǥ���ڵ�
	 */
	private String orgCode;

	/*
	 * �Ұ������ string �������� �Ұ���� ��Ī
	 */
	private String orgName;

	/*
	 * �μ��� string �������� �Ұ������ �μ� ��Ī
	 */
	private String deptName;

	/*
	 * ��ȸ�� integer ����24 �������� �ȳ� ������ ��ȸ��
	 */
	private int readCount;

}