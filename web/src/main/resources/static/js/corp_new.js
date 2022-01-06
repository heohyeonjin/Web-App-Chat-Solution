var idValidation = 0;
var pwValidation = 0;

function checkId(){
    var id = $('#corp_id').val();
    var buttonStatus = $('#id_validation').html();

    if (buttonStatus === "중복확인") {
        $.ajax({
            url:'/corporation/idCheck',
            type:'post', //POST 방식으로 전달
            data:{id : id},
            success: function(corpIdCheck){
                if (corpIdCheck) {
                    alert("존재하는 아이디입니다.");
                } else {
                    alert("사용 가능한 아이디입니다.");
                    $('#corp_id').attr("readonly", true);
                    $('#id_validation').html("변경");
                    idValidation = 1;
                }
            },
            error: function(){
                alert("서버 에러입니다");
            }
        });
    } else {
        $('#corp_id').removeAttr("readonly");
        $('#id_validation').html("중복확인");
        idValidation = 0;
    }

}

function pw_check() {
    var pw = $('#corp_pw').val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if(pw.length < 8 || pw.length > 20){
        alert("8자리 ~ 20자리 이내로 입력해주세요.");
        $('#corp_pw').val('');
        $('#corp_pw').focus();
        return false;
    }
    else if(pw.search(/\s/) != -1){
        alert("비밀번호는 공백 없이 입력해주세요.");
        $('#corp_pw').val('');
        $('#corp_pw').focus();
        return false;
    }
    else if(num < 0 || eng < 0 || spe < 0 ){
        alert("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
        $('#corp_pw').val('');
        $('#corp_pw').focus();
        return false;
    }
    else {
        console.log("통과");
        return true;
    }

}

function pw_confirm() {
    var pw = $('#corp_pw').val();
    var pw_check = $('#corp_check').val();

    if (pw !== pw_check) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }

    return true;
}

function corpNewCheck(form) {
    var corp_name = form.corp_name;
    var corp_admin = form.corp_admin;
    var corp_id = form.corp_id;
    var corp_pw = form.corp_pw;
    var corp_check = form.corp_check;
    var corp_phone = form.corp_phone;
    var corp_email = form.corp_email;
    var corp_logo = form.corp_logo;
    var corp_descrip = form.corp_descrip;

    // 기업명 입력 유무 체크
    if(!corp_name.value) {
        alert('기업명을 입력하세요.');
        corp_name.focus();
        return false;
    }

    // 대표자 입력 유무 체크
    if(!corp_admin.value) {
        alert('대표자를 입력하세요.');
        corp_admin.focus();
        return false;
    }

    // 아이디 입력 유무 체크
    if(!corp_id.value) {
        alert('아이디를 입력하세요.');
        corp_id.focus();
        return false;
    }

    // 아이디 중복 유효성 체크
    if(idValidation === 0) {
        alert('아이디 중복확인하세요.');
        corp_id.focus();
        return false;
    }

    // 비밀번호 입력 유무 체크
    if(!corp_pw.value) {
        alert('비밀번호를 입력하세요.');
        corp_pw.focus();
        return false;
    }

    // 전화번호 입력 유무 체크
    if(!corp_phone.value) {
        alert('전화번호를 입력하세요.');
        corp_phone.focus();
        return false;
    }

    // 이메일 입력 유무 체크
    if(!corp_email.value) {
        alert('이메일를 입력하세요.');
        corp_email.focus();
        return false;
    }


}