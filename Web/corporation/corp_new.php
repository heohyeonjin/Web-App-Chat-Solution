<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap core CSS -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous">

        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
            integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
            crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
            crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../nav/nav.css">
        <link rel="stylesheet" href="corporation.css">
        <title>corporation</title>
    </head>

    <body>
        <?php require('../nav/nav.php'); ?>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 wrapper">
            <div class="pt-3 pb-2 mb-3 border-bottom">
                <h3 style="text-align:left;">기업신규등록</h3>
            </div>

            <!-- 신규등록 form-->
            <form action="" method="post" name="" class="reg_form">
                <div class="form-group row">
                    <label for="corp_name" class="col-sm-2 col-form-label reg_label">기업명</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control form-control-sm" id="corp_name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="corp_admin" class="col-sm-2 col-form-label reg_label">대표자</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control form-control-sm" id="corp_admin">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="corp_id" class="col-sm-2 col-form-label reg_label">아이디</label>
                    <div class="col-sm-7">
                        <div class="input-group mb-4 col-8">
                            <input type="text" class="form-control form-control-sm px-3" id="corp_id">
                            <div class="input-group-append">
                                <button class="btn btn-outline-dark btn-sm px-3" type="button" id="" >중복확인</button>
                            </div>
                        </div>
                </div>
                <div class="form-group row">
                    <label for="corp_pw" class="col-sm-2 col-form-label reg_label">비밀번호</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control form-control-sm" id="corp_pw">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="corp_check" class="col-sm-2 col-form-label reg_label">비밀번호 확인</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control form-control-sm" id="corp_check">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="corp_phone" class="col-sm-2 col-form-label reg_label">연락처</label>
                    <div class="col-sm-8">
                        <input type="tel" class="form-control form-control-sm" id="corp_phone">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="corp_email" class="col-sm-2 col-form-label reg_label">이메일</label>
                    <div class="col-sm-8">
                        <input type="email" class="form-control form-control-sm" id="corp_email">
                    </div>
                    
                </div>
                <div class="form-group row">
                    <label for="corp_logo" class="col-sm-2 col-form-label reg_label">기업로고</label>
                    <div class="col-sm-8">
                        <input type="file" class="form-control-file form-control-sm" id="corp_logo" style="float:left;">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="corp_descrip" class="col-sm-2 col-form-label reg_label">기업상세정보</label>
                    <div class="col-sm-8">
                        <textarea class="form-control form-control-sm" rows="5" id="corp_discrip"></textarea>
                    </div>
                </div>
                <br>
                <div class="reg_btn">
                    <button type="submit" class="btn btn-dark btn-sm px-4">등록하기</button>
                    <button type="submit" class="btn btn-warning btn-sm px-4">목록으로</button>
                </div>
            </form>
        </main>

    </body>
</html>