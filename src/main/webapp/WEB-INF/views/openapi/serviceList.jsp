<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="row justify-content-center">
	<h1>서비스 목록 화면</h1>
	<form method="GET" name="cFrm">
		<div class="row">
			<div class="col-4">
				<p>성별</p>
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
			<div class="col-4">
				<p>연령대</p>
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
			<div class="col-4">
				<p>소득구간</p>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="Y"
						name="JA0201">
					<label class="form-check-label" for="flexCheckChecked">0 ~
						50</label>
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
			<p>개인특성</p>
			<div class="col-12"
				style="display: flex; flex-direction: row; flex-wrap: wrap">
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
			<p>가구 특성</p>
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
			<div id="textBox"></div>
			<button id="search" class="btn btn-primary">검색</button>
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
	<div>${pagination}</div>
	<div id="ajaxContainer"></div>
	<div id="pageContainer"></div>
</div>
<script>
	//상세페이지 조회
	function serviceDetail(serviceId) {
		
		location.href = `${pageContext.request.contextPath}/serviceDetail/\${serviceId}`;
	}
	//데이터
	const data = {};

	//체크박스 제어
	$("input[type=checkbox]").click(function() {
			
			$(this).each(function() {
				var checked = $(this).is(":checked")
				var id = $(this).attr("name");
				var txt = $(this).next().text();
				//체크확인 텍스트 추가
				if (checked) {
					$("#textBox").append(`<span id=\${id} class="badge bg-primary">\${txt}</span>`);
				} else {
					//체크해제 시 객체에서 삭제, 텍스트 삭제
					delete data[id];
					$(`#\${id}`).remove();
				}
			})
	});

	//폼 제출 제어
	$("[name=cFrm]").submit(function(e) {
		e.preventDefault();
		const cFrm = e.target;
		
		var frmData = new FormData(cFrm);
		
		frmData.forEach(function(value, key){
			console.log(value);
			data[key] = value;
		})

		//선택한 조건 없을 경우 기본 1페이지
		if(Object.keys(data).length == 0){
			getDefault(1);
			
		} else {
			//조건으로 처음 검색 시 첫 페이지로
			var url = location.href.substr(0, 34)+"1";
			history.pushState(null, null, url);
			getCondition(1, data)
		}

	});
	
	$("#pageContainer").on("click", function(e){
	
		history.pushState(null, null, e.target.href);
		var uArr = e.target.href.split("/");
		
		e.preventDefault();	
		
		var tf;
		$("#textBox").children().each(function(index, item){
			console.log(index, data);
			tf = index;
		})
		console.log(tf);
		if(tf == null){
			$("#ajaxContainer").children().remove();
			getDefault(uArr[4]);
		} else {
			$("#ajaxContainer").children().remove();
			getCondition(uArr[4], data);
		}
		
		
	})
	
	
	function getDefault(no){
	
		var url = {url : location.pathname};
		var urlArr = url.url.split("/")
	
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