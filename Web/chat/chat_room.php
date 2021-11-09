<!DOCTYPE html>
<!-- Description: Kakao PC ver. Clone -->
<html lang="ko" data-dark="false">
    <head>
        <meta charset="utf-=8">
        <title>Counseling Room</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <link rel="stylesheet" href="chat_room.css?after">
    </head>
    <body>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
         <!-- Option 2: Separate Popper and Bootstrap JS -->
         <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
         <header class ="bg-dark text-white sticky-top">
                 <div class="d-flex w-100 justify-content-between">
                     <button class="profile_btn my-2 mx-2">
                         <img src="../resources/person.svg">
                    </button>
                    <h5 class="mx-3 my-3 flex-fill">
                        <div class="counseler-name">허현진</div>
                    </h5>
                    <button class="answer_btn mx-2">
                         답변완료
                    </button>
                </div>  
        </header>
    
        <div id="chat_warp">
        <div class="inner">
            
            <div class="item">
                <div class="box">
                    <p class="msg">안녕하세요</p>
                    <span class="time">오전 10:05</span>
                </div>
            </div>

            <div class="item mymsg">
                <div class="box">
                    <p class="msg">안녕하세요</p>
                    <span class="time">오전 10:05</span>
                </div>
            </div>
           
        </div>

            <form id="chat_form" class="d-flex justify-content-between" > 
                    <input type="text" id="input_message" placeholder="상담을 시작하세요"></input>
                    <input class="send_btn" type="submit" value="전송">
            </form>
            <div class="file-upload" style="float:right">
               <label for="file-input">
                    <img src="../resources/drive.svg" class="my-2 mx-1"> 
               </label> 
                <input type="file" id="file-input" style="display:none"> 
            </div>
        </div>
    </body>
</html>