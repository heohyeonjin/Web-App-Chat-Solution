let lastDate = 0;

// 채팅방 입장 시 존재하는 메세지 붙이기
function addMsg(msg) {

    let curDate = msg.date;
    let sender = msg.sender;

    // 날짜가 다를 경우 표기
    if (lastDate !== curDate) {
        let dateHtml = `<div class="hr-sect">${curDate}</div>`;
        $('#messages').append(dateHtml);
        lastDate = curDate;
    }

    if (sender === 1) {
        let otherMsg = `<div class="item">
                        <div class="box">
                            <p class="msg">${msg.content}</p>
                            <span class="time">${msg.time}</span>
                        </div>
                    </div>`;
        $('#messages').append(otherMsg);
        $('.inner').scrollTop($(document).height());
    }
    else if (sender === 0) {
        let meMsg = `<div class="item mymsg">
                        <div class="box">
                            <p class="msg">${msg.content}</p>
                            <span class="time">${msg.time}</span>
                        </div>
                    </div>`
        $('#messages').append(meMsg);
        $('.inner').scrollTop($(document).height());
    }
    else {
        alert("sender validation 오류");
    }

}

function msgSend(roomNo){
    let msg = $('#input_message').val();
    let msgType = 1;

    if (!msg) {
        alert('내용을 입력하세요.');
        $('#input_message').focus();
        return false;
    }

    $('#input_message').val("");

    $.ajax({
        url:'/counseling/' + roomNo + '/send',
        type:'post', //POST 방식으로 전달
        data:{
            msg:msg
        },
        success: function(returnValue){
            if (returnValue) {
                console.log("메세지 전송 성공");
            } else {
                alert("error");
            }
        },
        error: function(){
            alert("메세지 전송에 실패하였습니다.");
        }
    });
}

function decreasingMatching(){
    $.ajax({
        url:'/counseling/matching',
        type: 'post',
        success: function(returnValue){
            window.close();
        },
        error: function(){
            alert("상담 종료에 실패하였습니다.");
        }
    })
}