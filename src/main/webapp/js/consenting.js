function consent(id, bool) {
    $.ajax({
        url: '/order/consent',
        type: 'POST',
        data:{
            'id': id,
            'bool': bool
        }
    }).done(function () {
        alert("success")
    }).fail(function () {
        alert("fail")
    });
}