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
	 * ����ID string �������� ���� �ĺ���
	 */
	private String serviceId;

	/*
	 * �������� string ����, ����, �̿��, ���� �� ���� ����
	 */
	private String serviceType;

	/*
	 * ���񽺸� string �������� ��Ī
	 */
	private String serviceName;

	/*
	 * ���񽺸��� string �������� ����
	 */
	private String servicePurpose;

	/*
	 * ������� string �������� �������
	 */
	private String serviceTarget;

	/*
	 * �������� string ������� ��������
	 */
	private String serviceRule;

	/*
	 * �������� string �������� ��������
	 */
	private String serviceContent;

	/*
	 * ��û��� string �������� ��û���
	 */
	private String appMethod;

	

}
