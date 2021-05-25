<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="header.jsp" %>
<div class="btn_group">
	<button type="button" onclick="location.href='./insert.jsp'">성적입력</button>
	<button type="button" onclick="location.href='./list.jsp'">성적목록</button>
	<button type="button" onclick="location.href='./search.jsp'">학생검색</button>
</div>
<%@ include file="footer.jsp" %>