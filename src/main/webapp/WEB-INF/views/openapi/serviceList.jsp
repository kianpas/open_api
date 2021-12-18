<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="row justify-content-center">
	<h1>서비스 목록 화면</h1>
	<form method="GET" name="cFrm">
		<div class="row">
			<div class="row">
				<div class="col-4">
					<div class="card" style="height: 100%">
						<div class="card-header">성별</div>
						<div class="card-body">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0101" id="flexCheckDefault">
								<label class="form-check-label" for="flexCheckDefault">남성</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0102" id="flexCheckChecked">
								<label class="form-check-label" for="flexCheckChecked">여성</label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-4">
					<div class="card" style="height: 100%">
						<div class="card-header">연령대</div>
						<div class="card-body">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0103">
								<label class="form-check-label" for="flexCheckDefault">영유아</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0104">
								<label class="form-check-label" for="flexCheckChecked">아동</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0105">
								<label class="form-check-label" for="flexCheckChecked">청소년</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0106">
								<label class="form-check-label" for="flexCheckChecked">청년</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0107">
								<label class="form-check-label" for="flexCheckChecked">중년</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0108">
								<label class="form-check-label" for="flexCheckChecked">장년</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0109">
								<label class="form-check-label" for="flexCheckChecked">노년</label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-4">
					<div class="card" style="height: 100%">
						<div class="card-header">소득구간</div>
						<div class="card-body">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="Y"
									name="JA0201">
								<label class="form-check-label" for="flexCheckChecked">0
									~ 50</label>
							</div>

							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="JA0202"
									id="flexRadioDefault2" value="Y">
								<label class="form-check-label" for="flexRadioDefault2">51
									~ 75 </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="JA0203"
									id="flexRadioDefault2" value="Y">
								<label class="form-check-label" for="flexRadioDefault2">76
									~ 100 </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="JA0204"
									id="flexRadioDefault2" value="Y">
								<label class="form-check-label" for="flexRadioDefault2">101
									~ 200 </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="JA0205"
									id="flexRadioDefault2" value="Y">
								<label class="form-check-label" for="flexRadioDefault2">200%
									초과 </label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row mt-3">
				<div>
					<div class="card" style="height: 100%">
						<div class="card-header">개인특성</div>
						<div class="card-body">
							<div style="display: flex; flex-direction: row; flex-wrap: wrap;">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0301">
									<label class="form-check-label" for="flexCheckChecked">예비부모/난임</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0302">
									<label class="form-check-label" for="flexCheckChecked">임신부</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0303">
									<label class="form-check-label" for="flexCheckChecked">출산/입양</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0304">
									<label class="form-check-label" for="flexCheckChecked">심한
										장애</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0305">
									<label class="form-check-label" for="flexCheckChecked">심하지
										않은 장애</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0306">
									<label class="form-check-label" for="flexCheckChecked">독립유공자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0307">
									<label class="form-check-label" for="flexCheckChecked">국가유공자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0308">
									<label class="form-check-label" for="flexCheckChecked">참전유공자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0309">
									<label class="form-check-label" for="flexCheckChecked">보훈보상대상자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0310">
									<label class="form-check-label" for="flexCheckChecked">특수임무유공자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0311">
									<label class="form-check-label" for="flexCheckChecked">5·18민주유공자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0312">
									<label class="form-check-label" for="flexCheckChecked">제대군인</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0313">
									<label class="form-check-label" for="flexCheckChecked">농업인</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0314">
									<label class="form-check-label" for="flexCheckChecked">어업인</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0315">
									<label class="form-check-label" for="flexCheckChecked">축산업인</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0316">
									<label class="form-check-label" for="flexCheckChecked">임업인</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0317">
									<label class="form-check-label" for="flexCheckChecked">초등학생</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0318">
									<label class="form-check-label" for="flexCheckChecked">중학생</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0319">
									<label class="form-check-label" for="flexCheckChecked">고등학생</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0320">
									<label class="form-check-label" for="flexCheckChecked">대학생/대학원생</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0322">
									<label class="form-check-label" for="flexCheckChecked">해당사항없음</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0323">
									<label class="form-check-label" for="flexCheckChecked">질병/부상/질환자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0324">
									<label class="form-check-label" for="flexCheckChecked">중증·난치·희귀질환자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0325">
									<label class="form-check-label" for="flexCheckChecked">요양환자/치매환자</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0326">
									<label class="form-check-label" for="flexCheckChecked">근로자/직장인</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0327">
									<label class="form-check-label" for="flexCheckChecked">구직자/실업자</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="row mt-3">
				<div>
					<div class="card" style="height: 100%">
						<div class="card-header">가구특성</div>
						<div class="card-body">
							<div class="col-12"
								style="display: flex; flex-direction: row; flex-wrap: wrap">

								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0401">
									<label class="form-check-label" for="flexCheckChecked">다문화가족</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0402">
									<label class="form-check-label" for="flexCheckChecked">북한이탈주민</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0403">
									<label class="form-check-label" for="flexCheckChecked">한부모가정/조손가정</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0404">
									<label class="form-check-label" for="flexCheckChecked">1인가구</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0410">
									<label class="form-check-label" for="flexCheckChecked">해당사항없음</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0411">
									<label class="form-check-label" for="flexCheckChecked">다자녀가구</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0412">
									<label class="form-check-label" for="flexCheckChecked">무주택세대</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0413">
									<label class="form-check-label" for="flexCheckChecked">신규전입</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="Y"
										name="JA0414">
									<label class="form-check-label" for="flexCheckChecked">확대가족</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="row">
						<div id="textBox" style="height: 5em"></div>
					</div>
					<div class="row">
						<div class="col offset-md-10">
							<button id="search" class="btn btn-primary">검색</button>
						</div>
					</div>
				</div>
			</div>
	</form>
	<div class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach items="${serviceList}" var="serviceList">
			<a
				href="${pageContext.request.contextPath}/serviceDetail/${serviceList.serviceId}"
				style="text-decoration: none; color: #000">
				<div class="col">
					<div class="card h-100">
						<div class="card-body">
							<h5 class="card-title">
								<c:out value="${serviceList.serviceName}" />
							</h5>
							<p class="card-text" style="overflow: hidden;">
								<c:out value="${serviceList.servicePurpose}" />
							</p>
						</div>
					</div>
				</div>
			</a>
		</c:forEach>

		<%-- <div class="row justify-content-center mt-3">
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
		</div> --%>

	</div>
	<div class="row mt-3">
		<div id="ajaxContainer"></div>
		<div id="pageContainer" class="mt-3"></div>
	</div>
</div>
<script>
	function serviceDetail(serviceId) {
		
		location.href = `${pageContext.request.contextPath}/serviceDetail/\${serviceId}`;
	};
	
	//조건을 담아 보낼 변수 선언
	var data = {};

	//체크박스 제어
	$("input[type=checkbox]").click(function() {
		$(this).each(function() {
			var checked = $(this).is(":checked");
			var id = $(this).attr("name");
			var txt = $(this).next().text();
			if (checked) {
				$("#textBox").append(
						`<span id=\${id} class="badge bg-primary mx-1">\${txt}</span>`);
			} else {
				//체크해제 시 객체에서 삭제, 텍스트 삭제
				delete data[id];
				for(var i = 3; i <= 9; i++){
					if(id == `JA010\${i}`){
						delete data['JA0110']
						delete data['JA0111']
					}
				}
			
				console.log(data);
				$(`#\${id}`).remove();
			}
		})

	});
	//폼 제출 제어
	$("[name=cFrm]").submit(function(e) {
		e.preventDefault();
		var cFrm = e.target;
		
		var frmData = new FormData(cFrm);
		
		frmData.forEach(function(value, key){
			data[key] = value;
			
		})
		
		
		//선택한 조건 없을 경우 기본 1페이지
		if(Object.keys(data).length == 0){
			
			getDefault(1);
			
		} else {
			
			//조건으로 처음 검색 시 첫 페이지로
			var url = location.href.substr(0, 34)+"1";
			history.pushState(null, null, url);
			
			getCondition(1, data);
		}

	});
	
	$("#pageContainer").on("click", function(e){
		
		history.pushState(null, null, e.target.href);
		var uArr = e.target.href.split("/");
		
		e.preventDefault();	
		
		var tf;
		$("#textBox").children().each(function(index, item){
			
			tf = index;
		});
		console.log(tf);
		 
		if(tf == null){
			$("#ajaxContainer").empty();
			getDefault(uArr[4]);
		} else {
			$("#ajaxContainer").empty();
			getCondition(uArr[4], data);
		}
		
	})
	
	
	function getDefault(no){
		var url = {url : location.pathname};
		var urlArr = url.url.split("/");
		
		var pageNo = urlArr[2];
		pageNo = no != null ? no : pageNo;
		$.ajax({
			url : `${pageContext.request.contextPath}/slByAjax/\${pageNo}`,
			method : "GET",
			data : url,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
			
				let html = "";
				$.each(data.serviceList, function(index, item){
					html += `<a
						href="${pageContext.request.contextPath}/serviceDetail/\${item.serviceId}"
						style="text-decoration: none; color: #000">
						<div class="col">
							<div class="card h-100">
								<div class="card-body">
									<h5 class="card-title">
										\${item.serviceName}
									</h5>
									<p class="card-text" style="overflow: hidden;">
										\${item.servicePurpose}
									</p>
								</div>
							</div>
						</div>
					</a>`;
					
					
				});
				
				$("#ajaxContainer").html(html);
				$("#pageContainer").html(data.pagination);
				
				
			},
			error : console.log

		});
		
	}
	function getCondition(pageNo, data){
		$.ajax({
			url : `${pageContext.request.contextPath}/serviceList/\${pageNo}`,
			data : JSON.stringify(data),
			method : "POST",
			contentType : "application/json; charset=utf-8",
			success : function(data) {
			
				console.log(data.serviceList)
				let html = "";
				$.each(data.serviceList, function(index, item){
					html += `<a
						href="${pageContext.request.contextPath}/serviceDetail/\${item.serviceId}"
						style="text-decoration: none; color: #000">
						<div class="col">
							<div class="card h-100">
								<div class="card-body">
									<h5 class="card-title">
										\${item.serviceName}
									</h5>
									<p class="card-text" style="overflow: hidden;">
										\${item.servicePurpose}
									</p>
								</div>
							</div>
						</div>
					</a>`;
					
					
				});
				
				$("#ajaxContainer").html(html);
				$("#pageContainer").html(data.pagination);
				
				
			},
			error : console.log

		});
	}

	$(function() {
		getDefault();
	});
</script>
</body>
</html>