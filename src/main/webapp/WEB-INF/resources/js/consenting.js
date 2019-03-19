function consent(id, bool, elemId) {
    document.getElementById(elemId).textContent='Идет загрузка...';
    $.ajax({
        url: '/order/consent',
        type: 'POST',
        data:{
            'id': id,
            'bool': bool
        }
    }).done(function () {
        document.getElementById(elemId).style.backgroundColor='green';
        document.getElementById(elemId).style.color='white';
        document.getElementById(elemId).textContent='Принято';
    }).fail(function () {
        document.getElementById(elemId).style.backgroundColor='red';
        document.getElementById(elemId).textContent='Ошибка';
    });
}