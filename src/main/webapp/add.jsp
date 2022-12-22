<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="title">투표하기</div>
		<form name="frm" action="insert">
			<input type="hidden" id="GUBUN" value=insert>
			<div class="wrapper">
				<table>
					<tr>
						<th>주민번호</th>
						<td><input type="text" name="v_jumin"> 예 : 8906153154726</td>
					</tr>
					<tr>
						<th>성명</th>
						<td><input type="text" name="v_name"></td>
					</tr>
					<tr>
						<th>투표번호</th>
						<td><select name="m_no">
								<option value=""></option>
								<option value="김후보">[1] 김후보</option>
								<option value="이후보">[2] 이후보</option>
								<option value="박후보">[3] 박후보</option>
								<option value="조후보">[4] 조후보</option>
								<option value="최후보">[5] 최후보</option>
						</select></td>
					</tr>
					<tr>
						<th>투표시간</th>
						<td><input type="text" name="v_time" placeholder= "09시30분 -> 0930"></td>
					</tr>
					<tr>
						<th>투표장소</th>
						<td><input type="text" name="v_area" placeholder= "'제1투표장' 혹은 '제2투표장'"></td>
					</tr>
					<tr>
						<th>유권자확인</th>
						<td>
						<input type="radio" name="v_confirm" value="Y">확인
						<input type="radio" name="v_confirm" value="N">미확인
						</td>
					</tr>

					<tr>
						<td colspan="2">
							<button class="btn" type="submit"
								onclick="fn_submit(); return false;">투표하기</button>
							<button class="btn" type="button">다시하기</button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</section>
	<%@ include file ="footer.jsp" %>
</body>
</html>