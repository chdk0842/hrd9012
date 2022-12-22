<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Member"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Member> list = new ArrayList<Member>();
list = (ArrayList<Member>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="topmenu.jsp"%>
	<section>
		<div class="title">투표검수조회</div>
		<form name="frm" action="insert">
			<input type="hidden" id="GUBUN" value=insert>
			<div class="wrapper">
				<table>
				<tr>
					<th>성명</th>
					<th>생년월일</th>
					<th>나이</th>
					<th>성별</th>
					<th>후보번호</th>
					<th>투표시간</th>
					<th>유권자확인</th>
				</tr>
					
			<%
				for (Member m : list) {
				%>
				<tr>
					<td><%=m.getM_no()%></td>
					<td><%=m.getM_name()%></td>
					<td><%=m.getP_code()%></td>
					<td><%=m.getP_shool()%></td>
					<td><%=m.getM_jumin()%></td>
					<td><%=m.getM_city()%></td>
					<td><%=m.getTel()%></td>
				</tr>
				<%
				}
				%>

				</table>
			</div>
		</form>
	</section>
	<%@ include file ="footer.jsp" %>
</body>
</html>