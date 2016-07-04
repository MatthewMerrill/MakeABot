$(document).ready(function() {
    function sendMessage(message, callback) {
        $.ajax({
            type: 'POST',
            url: '/rest/bot/sendMessage',
            data: message,
            success: callback
        });
    }

    $('#bot-submit').click(function() {
        sendMessage(
            $('#bot-input').text(),
            function(data) { console.log(data); }
        );
    });
})