<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="container">

	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<button id="serviceListBtn" type="button" class="btn btn-primary">serviceList</button>
</div>
<script>
	$("#serviceListBtn").click(function() {
		location.href = "${pageContext.request.contextPath}/serviceList";
	})
</script>
</body>
</html>
