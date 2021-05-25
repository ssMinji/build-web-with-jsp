<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbConnection.jsp" %>
<%
	PreparedStatement pstmt = null;
	sql = "delete from students where studentId = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, request.getParameter("studentId"));
	String move_url = "./list.jsp";
	
	pstmt.executeQuery();
%>
<script>
	alert('완료되었습니다.');
	location.replace("<%=move_url%>");
</script>