<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel ="stylesheet" href="auth.css?after">
    <title>login</title>
</head>
<script>
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
</script>
<body>
    <div class="login">
        <div class="login_left" >
            <br><br>
            <img src="../resources/client.svg" alt="" width="210px"> 
            <br><br>
            <h5><b>Welcome to Fast Response.</b></h5>
            <h5>This is Fast Response manager page.</h5>
        </div>
        <div class="login_right">
            <br><br>
            <h1 style="text-align: center; color:#343a40"><b>SIGN IN</b></h1>
            <br>
            <div class="login_form">
              <!-- 로그인 form -->
              <form name="login_form" method="get" action="./auth_ok.php" onsubmit="return form_login_chk(this);">
                <input class="form-control" name="login_id" type="text" placeholder="아이디">
                <br>
                <input class="form-control" name="login_pw" type="password" placeholder="비밀번호">
                <br>
                <div class="login_radio">
                    <div class="form-check">
                      <input class="form-check-input" type="radio" name="login_radio" id="login_radio1" value="login_admin" checked>
                      <label class="form-check-label radio_label" for="login_radio1">
                        최고 관리자   
                      </label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="radio" name="login_radio" id="login_radio2" value="login_corp">
                      <label class="form-check-label radio_label" for="login_radio2">
                        기업 관리자
                      </label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="radio" name="login_radio" id="login_radio3" value="login_coun">
                      <label class="form-check-label" for="login_radio3">
                        상담원
                      </label>
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-primary signIn_btn">로그인</button>
              </form>
            </div>
        </div>
    </div>
</body>
</html>