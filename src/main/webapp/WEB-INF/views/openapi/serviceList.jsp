<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="row justify-content-center">
	<h1>서비스 목록 화면</h1>
	<div class="form-check">
		<input class="form-check-input" type="checkbox" value="1" name="test"
			id="flexCheckDefault"> <label class="form-check-label"
			for="flexCheckDefault">남성</label>
	</div>
	<div class="form-check">
		<input class="form-check-input" type="checkbox" value="2" name="test"
			id="flexCheckChecked"> <label class="form-check-label"
			for="flexCheckChecked">여성</label>
	</div>
	<div id="textBox"></div>
	<button id="search">검색</button>
	<c:forEach items="${serviceList}" var="serviceList">
		<div class="row justify-content-center mt-3">
			<a
				href="${pageContext.request.contextPath}/serviceDetail/${serviceList.serviceId}"
				style="text-decoration: none; color: #000">
				<div class="col-8"
					onclick="serviceDetail('${serviceList.serviceId}');">
					<h5 class="fw-bold">${serviceList.serviceName}</h5>
					<p>${serviceList.servicePurpose}</p>
					<p>${serviceList.orgName}</p>
				</div>
			</a>
		</div>
	</c:forEach>
	<div>${pagination}</div>
</div>
<script>
	function serviceDetail(serviceId) {
		//serviceId = "000000465790";
		location.href = `${pageContext.request.contextPath}/serviceDetail/\${serviceId}`;
	}
	
	//나중에
	$("input[name=test]").click(function() {
		$(this).each(function() {
			var checked = $(this).is(":checked")
			console.log(checked);

	var id = $(this).val();
			if (checked) {
				//console.log($(this).val());
				$("#textBox").append(`<span id=\${id}>\${id}</span>`);
			} else {
				console.log(id)
				$(`#\${id}`).remove();
			}
		})

		//}
		//	$("input[name=test]:checked").each(function(e) {
		// 			console.log($(this).val());
		//			var id = $(this).val();
		//		$("#textBox").append(`<span id=\${id}>\${id}</span>`);
		//	})
	})

	$("#search").click(function() {

	})

	$(function() {
		//serviceList();
	});
</script>
</body>
</html>