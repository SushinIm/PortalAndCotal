function buttonClick(){
    
    var idRegExp01 = /^[0-9]{7}$/; 
    var idRegExp02 = /^[0-9]{6}$/;
	
	if (document.login.stuId.value == ""){
		alert("학번을 입력하세요.");
		document.login.stuId.focus();
		return false;
	} else if (document.login.stuPw.value == ""){
		alert("비밀번호를 입력하세요.");
		document.login.stuPw.focus();
		return false;
    }/* else if (!idRegExp01.test(document.login.stuId.value)){
		alert("학번을 다시 입력하세요.");
		document.login.stuId.value = "";
		document.login.stuId.focus();
		return false;
	} else if (!idRegExp02.test(document.login.stuPw.value)){
		alert("비밀번호를 다시 입력하세요.");
		document.login.stuPw.value = "";
		document.login.stuPw.focus();
		return false;
	}*/else{
		return true;
	}
}