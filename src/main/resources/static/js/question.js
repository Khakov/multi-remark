function send_question() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "/questions/123",
        data: '{"value": "lol"}',
        success: function (data) {
            console.log(data)
        },
        fail: function (data) {
            console.log(data)
        },
        dataType: "json"
    });
}