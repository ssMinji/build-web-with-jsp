<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="header.jsp" %>
<h2>성적 목록</h2>
<table>
	<colgroup>
		<col width="10%">
		<col width="10%">
		<col width="20%">
		<col width="20%">
		<col width="20%">
		<col width="10%">
		<col width="10%">
	</colgroup>
	<thead>
		<tr>
			<th>학번</th>
			<th>성명</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>과학</th>
			<th>평균</th>
		</tr>
	</thead>
	<tbody>
		<%
			PreparedStatement pstmt = null;
			sql = "SELECT studentId, studentNM, kor, eng, math, science, (kor+eng+math+science)/4 as avg from students where studentNM like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + request.getParameter("studentNM") + "%");
			//pstmt.setString(1, "aa");
			ResultSet res = pstmt.executeQuery();
			String studentId, studentNM, kor, eng, math, science, avg;
			while (res.next()) {
				studentId = res.getString("studentId");
				studentNM = res.getString("studentNM");
				kor = res.getString("kor");
				eng = res.getString("eng");
				math = res.getString("math");
				science = res.getString("science");
				avg = res.getString("avg");
				
		%>
		<tr style="text-align:center;">
			<td><a href="./update.jsp?studentId=<%=studentId%>"><%= studentId %></a></td>
			<td><%= studentNM %></td>
			<td><%= kor %></td>
			<td><%= eng %></td>
			<td><%= math %></td>
			<td><%= science %></td>
			<%-- <td><a href="./delete.jsp?studentId=<%=studentId%>"><%= avg %></a></td> --%>
			<td><a href="./action.jsp?action=delete&studentId=<%=studentId%>"><%= avg %></a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<div class="btn_group">
	<button type="button" onclick="location.href='./'">홈으로</button>
</div>