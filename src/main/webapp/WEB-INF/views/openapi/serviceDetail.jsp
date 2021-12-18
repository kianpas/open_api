<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h1>서비스 상세 화면</h1>

<div class="row justify-content-center mt-3">
	<div class="col-10">
		<div class="row mt-3">
			<h3 class="fw-bold">
				<c:out value="${serviceDetailVo.serviceName}" />
			</h3>
			<hr>
		</div>
		<div class="row mt-3">
			<p>
				<c:out value="${seviceDetailVo.servicePurpose}" />
			</p>
		</div>
		<div class="row mt-3">
			<div class="col-3">
				<p>지원형태</p>
				<p>
					<c:out value="${serviceDetailVo.serviceType}" />
				</p>

			</div>
			<div class="col-3">
				<p>접수기관</p>
				<p>
					<c:out value="${serviceDetailVo.appOrgName}" />
				</p>
			</div>
		</div>
		<div class="row"></div>
		<div class="row mt-3">
			<div class="col-2">
				<h5 class="fw-bold">누가 받을 수 있나요?</h5>
			</div>
			<div class="col-10">
				<div class="row">
					<div class="col-2">
						<p>지원대상</p>
					</div>
					<div class="col-10">
						<p>
							<c:out value="${serviceDetailVo.serviceTarget}" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-2">
				<h5 class="fw-bold">어떤 혜택을 받을 수 있나요?</h5>
			</div>
			<div class="col-10">
				<div class="row">
					<div class="col-2">
						<p>지원내용</p>
					</div>
					<div class="col-10">
						<p>
							<c:out value="${serviceDetailVo.serviceContent}" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-2">
				<h5 class="fw-bold">어떻게 신청하나요?</h5>
			</div>
			<div class="col-10">
				<div class="row">
					<div class="col-2">
						<p>신청방법</p>
					</div>
					<div class="col-10">
						<p>
							<c:out value="${serviceDetailVo.appMethod}" />
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-2">
						<p>제출서류</p>
					</div>
					<div class="col-10">
						<p>
							<c:out value="${serviceDetailVo.appDoc}" />
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col-2">
				<h5 class="fw-bold">궁금한 사항이 더 있어요</h5>
			</div>
			<div class="col-10">
				<div class="row">
					<div class="col-2">
						<p>접수기관</p>
					</div>
					<div class="col-10">
						<p>
							<c:out value="${serviceDetailVo.appOrgName}" />
						</p>
					</div>
				</div>
				<div class="row">
					<div class="col-2">
						<p>문의처</p>
					</div>
					<div class="col-10">
						<p>
							<c:out value="${serviceDetailVo.phone}" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-2">
				<h5 class="fw-bold">관계법령을 확인하세요</h5>
			</div>
			<div class="col-10">
				<div class="row">
					<div class="col-10">
						<p>
							<c:out value="${serviceDetailVo.lawOrder}" />
						</p>
					</div>
				</div>	
			</div>
		</div>


		<div class="row mt-3">
			<h5 class="fw-bold">이용 방법은 이렇습니다.</h5>
			<hr>
		</div>
		<div class="row">
			<div class="col-2">
				<p>온라인신청</p>
			</div>
			<div class="col-10">
				<p>
					<c:choose>
						<c:when test="${serviceDetailVo.appUrl eq '-'}">-</c:when>
						<c:otherwise>신청가능<a
								href="${serviceDetailVo.appUrl}">사이트 가기</a>
						</c:otherwise>
					</c:choose>
				</p>
			</div>
		</div>
		<div class="row">
			<div class=col-2>
				<p>접수기관</p>
			</div>
			<div class="col-10">
				<p>
					<c:out value="${serviceDetailVo.appOrgName}" />
					/ 연락처
					<c:out value="${serviceDetailVo.phone}" />
				</p>
			</div>
		</div>
		<div class="row mt-3">
			<p>${serviceDetailVo.adminRule}</p>
		</div>
	
		<div class="row mt-3">
			<span>최종수정일 <c:out value="${serviceDetailVo.editDate}" />
				소관기관 <c:out value="${serviceDetailVo.orgName}" />
			</span>
		</div>
	</div>
</div>

</body>
</html>