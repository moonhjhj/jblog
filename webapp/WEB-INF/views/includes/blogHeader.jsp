<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 블로그 해더 -->
<div id="header">
	<h1>
		<a href="${pageContext.request.contextPath }/{id}">${authUser.userName } 님의 블로그 입니다.</a>
	</h1>
	<ul>

		<c:choose>
			<c:when test="${authUser eq null }">
				<!-- 로그인 전 메뉴 -->
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<!-- 로그인 후 메뉴 -->
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
					<li><a href="">내블로그 관리</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>