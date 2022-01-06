let lastRoom = 0;

function checkLastRoom(){
    $.ajax({
        url: "/poll/room/lastRoom",
        type: "get",
        success: function(response) {
            if (response) {
                lastRoom = response;
                console.log("마지막 방 id: " + lastRoom);
            }
            addRoom();
        },
        error: function() {
            alert("서버 에러입니다.");
        }
    });
}

function addRoom(){
    $.ajax({
        url: "/poll/room",
        type: 'get',
        data: { lastRoom:lastRoom },
        timeout: 60000,
        success: function(response){
            if (response) {
                $('#rooms').empty();

                for (let i=0; i<response.length; i++) {
                    console.log(response);
                    let roomNo = response[i]['roomNo'];
                    let name = response[i]['name'];
                    let content = response[i]['content'];
                    let time = response[i]['time'];
                    let counRead = response[i]['counRead'];

                    let room;
                    if (counRead === 0) {
                        room = `<a onclick="window.open('/counseling/${roomNo}', '', 'width=450, height=800')" class="list-group-item" aria-current="true">
                                    <div class="d-flex w-100 justify-content-between">
                                        <img src='/images/person.svg' style="width:50px" href="#" alt=""/>
                                        <h5 class="text mb-1 mx-3 flex-fill">
                                            <div class="client my-1">${name}</div>
                                            <div class="last-comment">${content}</div>
                                        </h5>
                                        <small id="newChat">
                                            <div>${time}</div>
                                            <div id="circle">new</div>
                                        </small>
                                    </div>
                                </a>`
                    } else {
                        room = `<a onclick="window.open('/counseling/${roomNo}', '', 'width=450, height=800')" style="cursor:hand" class="list-group-item" aria-current="true">
                                    <div class="d-flex w-100 justify-content-between">
                                        <img src='/images/person.svg' style="width:50px" href="#" alt=""/>
                                        <h5 class="text mb-1 mx-3 flex-fill">
                                            <div class="client my-1">${name}</div>
                                            <div class="last-comment">${content}</div>
                                        </h5>
                                        <small id="newChat">
                                            <div>${time}</div>
                                        </small>
                                    </div>
                                </a>`
                    }

                    $('#rooms').append(room);
                }

                lastRoom = response[response.length-1]['roomNo'];
            }
            setTimeout(addRoom,1000);
        },
        error: function(){
            alert("서버 에러입니다");
        }
    });
}