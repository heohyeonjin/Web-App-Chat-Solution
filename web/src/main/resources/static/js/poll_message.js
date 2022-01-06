let lastMsg = 0;

function checkLastMsg(roomNo){
    console.log("방정보: " + roomNo);
    $.ajax({
        url: "/poll/chat/lastMsg",
        type: "get",
        data: { roomNo:roomNo },
        success: function(response) {
            if (response) {
                lastMsg = response;
                console.log("마지막 메세지 id: " + lastMsg);
            }
            addMessage();
        },
        error: function() {
            alert("서버 에러입니다.");
        }
    });
}

function addMessage() {
    $.ajax({
        url: "/poll/chat",
        type: 'get',
        data: { lastMsg:lastMsg },
        timeout:60000,
        success: function(response){
            if (response) {
                for (let i=0; i<response.length; i++) {
                    console.log(response);
                    let content = response[i]['content'];
                    let time = response[i]['time'];
                    let sender = response[i]['sender'];
                    let date = response[i]['date'];

                    if (date !== lastDate) {
                        let dateHtml = `<div class="hr-sect">${date}</div>`;
                        $('#messages').append(dateHtml);
                        lastDate = date;
                    }

                    if (sender === 0) {
                        let myChat = `<div class="item mymsg">
                                        <div class="box">
                                            <p class="msg">${content}</p>
                                            <span class="time">${time}</span>
                                        </div>
                                    </div>`
                        $('#messages').append(myChat);
                        $('.inner').scrollTop($(document).height());

                    }
                    else if (sender === 1) {
                        let clientChat = `<div class="item">
                                            <div class="box">
                                                <p class="msg">${content}</p>
                                                <span class="time">${time}</span>
                                            </div>
                                        </div>`
                        $('#messages').append(clientChat);
                        $('.inner').scrollTop($(document).height());
                    }
                    else {
                        alert("sender validation 오류");
                    }
                }
                lastMsg = response[response.length-1]['no'];
            }
            setTimeout(addMessage(),10);
        },
        error: function(){
            //alert("서버 에러입니다");
        }
    });
}



