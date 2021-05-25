<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbConnection.jsp" %>
<%
	sql = "SELECT studentId from students where rownum <= 1 order by studentId desc";
	ResultSet res = conn.prepareStatement(sql).executeQuery();
	int studentId = 10001;
	if (res.next()) studentId = res.getInt(1)+1;
%>
<h2>성적입력</h2>
<form action="./action.jsp" method="post">
	<input type="hidden" name="action" value="insert">
	<table>
		<colgroup>
			<col width="20%">
			<col width="80%">
		</colgroup>
		<tr>
			<th>학번(자동발생)</th>
			<td><input type="text" size="20" value="<%= studentId %>"></td>
		</tr>
		<tr>
			<th>성명</th>
			<td><input type="text" name="studentNM" size="20"></td>
		</tr>
		<tr>
			<th>국어</th>
			<td><input type="text" name="kor" size="20"></td>
		</tr>
		<tr>
			<th>영어</th>
			<td><input type="text" name="eng" size="20"></td>
		</tr>
		<tr>
			<th>수학</th>
			<td><input type="text" name="math" size="20"></td>
		</tr>
		<tr>
			<th>과학</th>
			<td><input type="text" name="science" size="20"></td>
		</tr>
	</table>
	<div class="btn_group">
		<button type="submit">입력</button>
		<button type="button" onclick="location.href='./'">홈으로</button>
	</div>
</form>