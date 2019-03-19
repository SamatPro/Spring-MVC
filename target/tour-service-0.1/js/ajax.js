function addToOrder(cityId, elemId) {
    document.getElementById(elemId).textContent='Идет загрузка...';
    $.ajax({
        url: '/order/update',
        type: 'POST',
        data:{
            'city_id': cityId
        }
    }).done(function () {
        document.getElementById(elemId).style.backgroundColor='green';
        document.getElementById(elemId).style.color='white';
        document.getElementById(elemId).textContent='Заказ добавлен';
    }).fail(function () {
        document.getElementById(elemId).style.backgroundColor='red';
        document.getElementById(elemId).textContent='Не добавлен';
    });
}

function deleteOrder(orderId, elemId) {
    document.getElementById(elemId).textContent='Deleting...';
    $.ajax({
        url:'/order/delete',
        type:'POST',
        data:{
            'del_order':orderId
        }
    }).done(function () {
        document.getElementById(elemId).style.backgroundColor='white';
        document.getElementById(elemId).style.color='black';
        document.getElementById(elemId).textContent='Deleted';
    }).fail(function () {
        document.getElementById(elemId).style.backgroundColor='yellow';
        document.getElementById(elemId).style.color='white';
        document.getElementById(elemId).textContent='Error';
    });
}
function reject(orderId, elemId) {
    document.getElementById(elemId).textContent='Отправляется...';
    $.ajax({
        url: '/order/check',
        type: 'POST',
        data:{
            'order_id': orderId,
            'bool': false
        }
    }).done(function () {
        document.getElementById(elemId).textContent='Отклонен';
    }).fail(function () {
        document.getElementById(elemId).style.backgroundColor='yellow';
        document.getElementById(elemId).textContent='Ошибка';
    });
}

function accept(orderId, elemId) {
    document.getElementById(elemId).textContent='Отправляется...';
    $.ajax({
        url: '/order/check',
        type: 'POST',
        data:{
            'order_id': orderId,
            'bool': true
        }
    }).done(function () {
        document.getElementById(elemId).textContent='Принят';
    }).fail(function () {
        document.getElementById(elemId).style.backgroundColor='yellow';
        document.getElementById(elemId).textContent='Ошибка';
    });
}
