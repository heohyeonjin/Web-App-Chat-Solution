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
        <link rel="stylesheet" href="corporation.css?after">
        <title>corporation info</title>
    </head>

    <body>
        <?php require('../nav/nav.php'); ?>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 wrapper">
            <div class="pt-3 pb-2 mb-3 border-bottom">
                <h3 style="text-align:left;">기업상세조회</h3>
            </div>

            <img src="../resources/corporation.svg" class="rounded-circle" alt="" width="100px">
            
            <!-- 기업정보 상세조회-->
            <table class="table table-bordered corp_info">
                <tbody>
                    <tr>
                        <th class="corp_info_th">상태</th>
                        <td class="corp_info_td">사용가능</td>
                    </tr>
                    <tr>
                        <th>기업명</th>
                        <td class="corp_info_td">달봉이</td>
                    </tr>
                    <tr>
                        <th>대표자</th>
                        <td class="corp_info_td">홍길동</td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td class="corp_info_td">qwerty1</td>
                    </tr>
                    <tr>
                        <th>연락처</th>
                        <td class="corp_info_td">010-1234-5678</td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td class="corp_info_td">alphcity@gmail.com</td>
                    </tr>
                    <tr>
                        <th>기업상세정보</th>
                        <td class="corp_info_td info_descrip"></td>
                    </tr>
                </tbody>
            </table>
            <div class="reg_btn">
                    <button type="submit" class="btn btn-dark btn-sm px-4">수정하기</button>
                    <button type="submit" class="btn btn-warning btn-sm px-4">목록으로</button>
            </div>
        </main>

    </body>
</html>