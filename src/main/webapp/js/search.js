// document.addEventListener('DOMContentLoaded', function(){
//     document.getElementsByName("searchField").addEventListener("click", search);
// });
function search(buttid, cityId) {
    var name = document.getElementById(buttid).value;
    // document.getElementById(elemId).textContent='Идет загрузка...';
    $.ajax({
        url: '/order/search',
        type: 'POST',
        data:{
            'name': name
        },
        success: function (data) {
            let contentTableHTML = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
            for (let i = 0; i < data.length; i++) {
                contentTableHTML += "<tr>";
                contentTableHTML += "<td> " + data[i].lastName + " </td><td> </td>";
                contentTableHTML += "<td> " + data[i].firstName+ "   "+"</td><td> </td>";
                contentTableHTML += "<td> " + data[i].middleName+ " </td><td>  </td> ";
                contentTableHTML += "<td>   <button type=\"button\" class=\"btn btn-default btn-sm\" align='right' id='client"+data[i].id+"' onclick='addToBasket("+data[i].id+", this.id, "+")'>Отправить приглашение</button>"+ "</td> ";
                contentTableHTML += "</tr>";
                contentTableHTML += "<tr></tr>";
            }
            contentTableHTML +="</table>";
            let contentTableDiv = document.getElementById('inner'+cityId);
            contentTableDiv.innerHTML = contentTableHTML;
        }
    }).done(function (data) {

    }).fail(function () {
        alert("NOOO")
        /*document.getElementById(elemId).style.backgroundColor='red';
        document.getElementById(elemId).textContent='Не добавлен';*/
    });
}