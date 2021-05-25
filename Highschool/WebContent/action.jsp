<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbConnection.jsp" %>
<%
	String action = request.getParameter("action");
	String move_url = "./";
	PreparedStatement pstmt = null;
	
	switch (action) {
	case "insert":
		sql = "INSERT INTO students values(member_seq.nextval, ?, ?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("studentNM"));
		pstmt.setString(2, request.getParameter("kor"));
		pstmt.setString(3, request.getParameter("eng"));
		pstmt.setString(4, request.getParameter("math"));
		pstmt.setString(5, request.getParameter("science"));
		move_url = "./list.jsp";
		break;
	case "update":
		sql  = "update students SET studentNM = ?, kor = ?, eng = ?, math = ?, science = ? where studentId = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("studentNM"));
		pstmt.setString(2, request.getParameter("kor"));
		pstmt.setString(3, request.getParameter("eng"));
		pstmt.setString(4, request.getParameter("math"));
		pstmt.setString(5, request.getParameter("science"));
		pstmt.setInt(6, Integer.parseInt(request.getParameter("studentId")));
		move_url = "./list.jsp";
		break;
	case "delete":
		sql = "delete from students where studentId = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("studentId"));
		move_url = "./list.jsp";
		break;
	}
	pstmt.executeQuery();
%>
<script>
	alert('완료되었습니다.');
	location.replace("<%=move_url%>");
</script>