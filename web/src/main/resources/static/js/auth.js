function form_login_chk(form) {
    var login_id = form.login_id;
    var login_pw = form.login_pw;

    // 아이디 입력 유뮤 체크
    if(!login_id.value) {
        alert('아이디를 입력하세요.');
        login_id.focus();
        return false;
    }

    // 비밀번호 입력 유무 체크
    if (!login_pw.value) {
        alert('비밀번호를 입력하세요');
        login_pw.focus();
        return false;
    }
}