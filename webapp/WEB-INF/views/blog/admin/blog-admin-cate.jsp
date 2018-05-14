<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" 	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">
		
			<c:import url="/WEB-INF/views/includes/blogHeader.jsp"></c:import>


		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath }/${blogVo.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id=cateList>
		      		<%-- 
		      			<tr>
							<td>3</td>
							<td>영화</td>
							<td>5</td>
							<td>영화에 관한 이야기입니다.</td>
							<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
						</tr>
						<tr>
							<td>2</td>
							<td>음악</td>
							<td>3</td>
							<td>음악에 관한 이야기입니다.</td>
							<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
						</tr>
						<tr>
							<td>1</td>
							<td>미분류</td>
							<td>0</td>
							<td>기본으로 만들어지는 카테고리 입니다.</td>
							<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>
						</tr>
						 --%>
					</tbody>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add" >
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="cateName" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blogFooter.jsp"></c:import>

	</div>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		
		fetchList();
		//원래 이 안에 ajax 있었음. =>ready안을 간단하게 바꾼것.
	});
	$("#btnAddCate").on("click", function(){
		
		console.log("btnAddCate");
// 		var cateName = $("[name = name]").val();
// 		var description = $("[name = desc]").val();
		var cateName = $("[name = cateName]").val();
		var description = $("[name = description]").val();
		
		console.log(cateName, description);
		
		cateVo = { cateName : $("input[name = 'cateName']").val(), 
				   description : $("input[name = 'description']").val()};
		
		$.ajax({
				
		// 		url : "${pageContext.request.contextPath }/api/gb/add?name=name&password=password", //원래 이렇게 써야 되는데 data : 뒤에 정의해줬으니까 add만써도됨.		
				url : "${pageContext.request.contextPath }/addCate",		
				type : "post",
				data : {cateName : cateName, description : description},
				dataType : "json",
		// 		여기까지 controller로 감. 갔다가 성공했을 시 success로 옴.
				success : function(cateList){
					/*성공시 처리해야될 코드 작성*/
					render(cateList, "up");
					var cateName = $("[name = cateName]").val("");
					var description = $("[name = description]").val("");
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
				
				
			});
		
	});



function render(cateList, updown){
	
	var str = "";
// 	str += "<tbody id = " + cateList.cateNo + ">";
	str += "	<tr id = " + cateList.cateNo + ">";
	str += "		<td>[" + cateList.cateNo + "]</td>";
	str += "		<td>[" + cateList.cateName + "]</td>";
	str += "		<td>" + "5" + "</td>";			
	str += " 		<td>[" + cateList.description + "]</td>";			
	str += " 		<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>";			
	str += "	</tr>";	
// 	str += "</tbody>";
			
	
	if(updown == "up"){
		
		$("#cateList").prepend(str);
	}else if(updown == "down"){
		
		$("#cateList").append(str);
	}else{
		console.log("오류");
	}
	
	
}



function fetchList(){
	
$.ajax({
		
		url : "${pageContext.request.contextPath }/{id}/admin/category",		
		type : "post",

		dataType : "json",
		success : function(cateList){ 
			/*성공시 처리해야될 코드 작성*/
			console.log(cateList);
			for(var i = 0; i<cateList.length; i++){
				
					render(cateList[i], "down");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	
}
</script>

</html>