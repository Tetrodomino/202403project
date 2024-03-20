/*
    Aside menu control
*/
// 이벤트 등록
$(document).ready(function() {
    $('#replyclick').click(function(e) {
        $('#replyInput').attr({'class': 'mt-2'}); // 입력창 보이게 하기
        alert('클릭!');
    });
    $('#stateMsgSubmit').click(changeStateMsg); // 이벤트 등록
    $('#getWeatherButton').click(getWeather);
});

function changeStateMsg() {
    let stateInputVal = $('stateInput').val(); // 사용자가 수정한 글 읽기
    $('#stateMsgInput').attr({'class': 'mt-2 d-none'}); // 입력창 감추기
    
    // 입력시킨 내용을 보내기
    // Asynchronous Javascript and XML, 화면의 일부분만 바꿀 때 주로 사용
    $.ajax({
        type: 'GET', 
        url: '/abbs/aside/stateMsg',
        data: {stateMsg: stateInputVal},
        success: function(e) {
            console.log('state message:', stateInputVal, e);
            $('#stateMsg').html(stateInputVal);
        }
    });
}

function getWeather() {
    $.ajax({
        type: 'GET',
        url: '/abbs/aside/weather',
        success: function(result) {
            $('#weather').html(result);
        }
    });
}