$(document).ready(function() {

    function fromBot(data) {
        var jsonData = JSON.parse(data);
        $("#bot-messages").append(
            "<h3>Bot:</h3>");
        $("#bot-messages").append(
            "<p class='frombot'></p>");
        $("#bot-messages .frombot:last").text(jsonData.message)

        var scroll = $("#bot-messages-scroll")
        scroll.animate({scrollTop: scroll.prop("scrollHeight")}, 0);

    }

    function fromUser(message) {
        $("#bot-messages").append(
            "<h3>You:</h3>");
        $("#bot-messages").append(
            "<p class='fromuser'></p>");
        $("#bot-messages .fromuser:last").text(message)
    }

    function sendMessage(message) {
        $.ajax({
            method: "POST",
            url:"/REST/bot/sendMessage?botid=1",
            data: JSON.stringify({message: message}),
            contentType: "application/json; charset=utf-8",
            success: fromBot});
    }

    function submitInput() {
        var input = $('#bot-input');
        var messageToSend = input.val();
        fromUser(messageToSend);
        input.val("");
        sendMessage(messageToSend);
    }

    $('#bot-submit').click(submitInput);
    $('#bot-input').keypress("key", function(e){
        console.log(e.keyCode);
        if (e.keyCode == 13)
            submitInput()
    });
})