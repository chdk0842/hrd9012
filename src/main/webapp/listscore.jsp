<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Member"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Member> scorelist = new ArrayList<Member>();
scorelist = (ArrayList<Member>) request.getAttribute("scorelist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="topmenu.jsp"%>
	<section>
		<center>후보자등수</center>
		<div class="wrapper">
			<table style="width: 900px; height : 400px;">
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>총투표건수</th>
				</tr>
<%
				for (Member m : scorelist) {
				%>
				<tr>
					<td><%=m.getM_no()%></td>
					<td><%=m.getM_name()%></td>
					<td><%=m.getScore()%></td>
	                
				</tr>
				<%
				}
				%>

			</table>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>