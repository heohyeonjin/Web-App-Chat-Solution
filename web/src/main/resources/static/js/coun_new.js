var idValidation = 0;
var pwValidation = 0;

function checkCounselorId(){
    var id = $('#counselor_id').val();
    console.log(id);
    var buttonStatus = $('#Validation').html();
    console.log(buttonStatus);
    if (buttonStatus === "중복확인") {
        $.ajax({
            url:'/counselor/idCheck',
            type:'post', //POST 방식으로 전달
            data:{id : id},
            success: function(corpIdCheck){
                if (corpIdCheck) {
                    alert("존재하는 아이디입니다.");
                } else {
                    alert("사용 가능한 아이디입니다.");
                    $('#counselor_id').attr("readonly", true);
                    $('#Validation').html("변경");
                    idValidation = 1;
                }
            },
            error: function(){
                alert("서버 에러입니다");
            }
        });
    } else {
        $('#counselor_id').removeAttr("readonly");
        $('#id_validation').html("중복확인");
        idValidation = 0;
    }

}
function pw_Councheck() {
    var pw = $('#counselor_pw').val();
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if(pw.length < 8 || pw.length > 20){
        alert("8자리 ~ 20자리 이내로 입력해주세요.");
        $('#counselor_pw').val('');
        $('#counselor__pw').focus();
        return false;
    }
    else if(pw.search(/\s/) != -1){
        alert("비밀번호는 공백 없이 입력해주세요.");
        $('#counselor_pw').val('');
        $('#counselor_pw').focus();
        return false;
    }
    else if(num < 0 || eng < 0 || spe < 0 ){
        alert("영문, 숫자, 특수문자를 혼합하여 입력해주세요.");
        $('#counselor_pw').val('');
        $('#counselor_pw').focus();
        return false;
    }
    else {
        console.log("통과");
        return true;
    }

}
function pw_Counconfirm() {
    var pw = $('#counselor_pw').val();
    var pw_check = $('#counselor_check').val();

    if (pw !== pw_check) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }

    return true;
}

function NewCheck(form) {
    var coun_name = form.coun_name;
    var coun_id = form.coun_id;
    var coun_pw = form.coun_pw;
    var coun_phone = form.coun_phone;
    var coun_email = form.coun_email;

    if(!coun_name.value) {
        alert('성명을 입력하세요.');
        coun_name.focus();
        return false;
    }

    if(!coun_id.value) {
        alert('아이디를 입력하세요.');
        coun_name.focus();
        return false;
    }
    if(!coun_pw.value) {
        alert('비밀번호를 입력하세요.');
        coun_name.focus();
        return false;
    }
    if(idValidation===0){
        alert('아이디 중복확인하세요.');
        coun_id.focus();
        return false;
    }

    if(!coun_phone.value) {
        alert('전화번호를 입력하세요.');
        coun_phone.focus();
        return false;
    }

    if(!coun_email.value) {
        alert('이메일을 입력하세요.');
        coun_email.focus();
        return false;
    }


}