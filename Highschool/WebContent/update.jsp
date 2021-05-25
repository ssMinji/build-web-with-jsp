<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="header.jsp" %>
<%
	/* data 가져오기 */
	String studentId = request.getParameter("studentId");
	String studentNM, kor, eng, math, science;
	
	sql = "SELECT * from students where studentId = "+studentId;
	ResultSet res = conn.prepareStatement(sql).executeQuery();
	res.next();
	
	studentNM = res.getString("studentNM");
	kor = res.getString("kor");
	eng = res.getString("eng");
	math = res.getString("math");
	science = res.getString("science");
%>
<h2>성적수정</h2>
<form action="./action.jsp" method="post">
	<input type="hidden" name="action" value="update">
	<table>
		<colgroup>
			<col width="20%">
			<col width="80%">
		</colgroup>
		<tr>
			<th>학번(자동발생)</th>
			<td><input type="text" name="studentId" size="20" value="<%= studentId %>" readonly></td>
		</tr>
		<tr>
			<th>성명</th>
			<td><input type="text" name="studentNM" size="20" value="<%= studentNM %>"></td>
		</tr>
		<tr>
			<th>국어</th>
			<td><input type="text" name="kor" size="30" value="<%= kor %>"></td>
		</tr>
		<tr>
			<th>영어</th>
			<td><input type="text" name="eng" size="40" value="<%= eng %>"></td>
		</tr>
		<tr>
			<th>수학</th>
			<td><input type="text" name="math" size="20" value="<%= math %>"></td>
		</tr>
		<tr>
			<th>과학</th>
			<td><input type="text" name="science" size="20" value="<%= science %>"></td>
		</tr>
	</table>
	<div class="btn_group">
		<button type="submit">수정</button>
		<button type="button" onclick="history.back();">목록</button>
	</div>
</form>
