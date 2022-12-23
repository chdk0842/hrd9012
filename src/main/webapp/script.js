function fn_submit() {
	var fn = document.frm;

	if (fn.v_jumin.value == "") { //input에 값이 빈값인지 체크
		alert("주민번호가 입력되지 않았습니다.");
		fn.v_jumin.focus(); //포커싱주기
		return false; //함수 끝내기
	}
	if (fn.v_name.value == "") { //input에 값이 빈값인지 체크
		alert("성명이 입력되지 않았습니다.");
		fn.v_name.focus(); //포커싱주기
		return false; //함수 끝내기
	}
	if (fn.m_no.value == "") { //input에 값이 빈값인지 체크
		alert("후보번호가 입력되지 않았습니다.");
		fn.m_no.focus(); //포커싱주기
		return false; //함수 끝내기
	}
	if (fn.v_time.value == "") { //input에 값이 빈값인지 체크
		alert("투표시간이 입력되지 않았습니다.");
		fn.v_time.focus(); //포커싱주기
		return false; //함수 끝내기
	}
	if (fn.v_area.value == "") { //input에 값이 빈값인지 체크
		alert("투표장소가 입력되지 않았습니다.");
		fn.v_area.focus(); //포커싱주기
		return false; //함수 끝내기
	}
	if (fn.v_confirm.value == "") {
		alert("유권자확인이 선택되지 않았습니다.");
		fn.v_confirm.focus();
		return false;
	}

	fn.submit(); //컨트롤러(서버)에 전송

}



function fn_reset() {

	alert("정보를 지우고 처음부터 다시 입력합니다!");

}


