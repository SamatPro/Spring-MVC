var basket ={};
function addToBasket(clientId, elemId) {

    basket[clientId] = parseInt(clientId);
    document.getElementById(elemId).style.backgroundColor="green";
    document.getElementById(elemId).textContent='Добавлено';
}

function sendTo(cityId) {
    document.getElementById("btnn"+cityId).textContent='Отправляется...';

    var data = [], id;
    for (id in basket){
        data.push({
            client: basket[id]
        })
    }
    $.ajax({
        url: '/order/addToDB',
        type: 'POST',
        // contentType: 'application/json',
        data:{
            'cityId':cityId,
            'json':JSON.stringify(data)
        }

    }).done(function () {
        alert("Success");
    }).fail(function () {
        alert("error");
    })

}