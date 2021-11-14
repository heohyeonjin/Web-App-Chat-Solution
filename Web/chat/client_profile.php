<!DOCTYPE html>
<!-- Description: Kakao PC ver. Clone -->
<html lang="ko" data-dark="false">
    <head>
        <meta charset="utf-=8">
        <title>Counseling Room</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <link rel="stylesheet" href="client_profile.css">
    </head>
    <body>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
         <!-- Option 2: Separate Popper and Bootstrap JS -->
         <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
       
         <header class ="bg-dark text-white sticky-top d-flex w-100 justify-content-center" style="height: 100px;">
                 <div class="my-4" style="font-size:30px">
                   고객 프로필
                </div>  
        </header>

        
        <div class="client_profile d-flex justify-content-center">
            <form>
              <br><br>
              <img src="../resources/person.svg" style="width:300px"><br>
                <br>
                <div class= client-detail style="font-size:30px">
                    <div class="name">이름 :</div>
                    <div class="age">나이 :${}</div>
                    <div class="gender">성별 :</div>
                    <div class="detail">특이사항 :</div>
                </div>
                <br><br><br>
                <button class="d-flex justify-content-center mx-5 ml-3 btn btn-primary profile-btn" style="background-color: #EEB500;border:solid  #EEB500;">확인</button>  
              </form>
        </div>

        


    </body>
</html>