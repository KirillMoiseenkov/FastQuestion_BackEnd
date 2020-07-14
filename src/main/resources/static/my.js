var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#answerinfo").html("");
}

function connect() {
    xhr.response;
    que = JSON.parse(xhr.response);
    $("#qTitle").append("<tr><td>" + que.text + " que id equals = " +  que.id +  "</td></tr>");
    newQid = que.id;
    var socket = new SockJS('/fast-question');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user/' + newQid, onMessageReceived);
    });
}

function onMessageReceived(payload) {
   var message = JSON.parse(payload.body);
   $("#answerinfo").append("<tr><td>" + message.text + "</td></tr>");
}
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    getRandomAnswer();
}

function sendName() {
    roomIds = que.id;
    text = JSON.stringify({'text': $("#text").val()})
    stompClient.send("/app/user/send-answer/" + roomIds, {}, text);
}

function showGreeting(text) {
    $("#answerinfo").append("<tr><td>" + text + "</td></tr>");
}

var RQid;
var xhr = new XMLHttpRequest();
var que;
function getRandomAnswer() {
    xhr.open('GET', 'http://localhost:8080/api/v1/question/getRandomQuestion');
    xhr.send();
}

function askQuestion() {
    $("#answers").hide();
}

$(function () {
    getRandomAnswer();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#send_qid" ).click(function() { connect(); });
    $( "#ask_question" ).click(function() { askQuestion(); });
});