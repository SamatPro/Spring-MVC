<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Мои заказы</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="js/ajax.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans+SC:400,700&amp;subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 logo">
                <a href="home"><img src="img/travel.png" width=350 alt="travel"></a>
            </div>
            <div class="col-lg-7 ml-auto">

                <ul class="menu d-flex justify-content-center">
                    <li class="menu__item">
                        <a href="myInvitations">
                            Приглашения
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="/orders">
                            Мои заказы
                        </a>
                    </li>
                    <li class="menu__item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Навигация
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <#list countries as country>
                                <a class="dropdown-item" href="/tours?country=${country.name}">${country.name}</a>
                            </#list>

                        </div>
                    </li>
                    <li class="menu__item">
                        <a href="signOut">
                            Выйти
                        </a>
                    </li>

                </ul>

            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 offer">
                <h1 class="offer__title">
                    ${clientFirstName} ${clientLastName}
                </h1>
                <div class="offer__intro">

                </div>
                <p class="offer__text">

                </p>
            </div>
            <div class="col-lg-2 ml-auto shuttle">
                <img src="img/plane.png" width=450>
            </div>
        </div>
    </div>
</header>
<section class="price">
    <div class="container">
        <div class="row priceTitle">
            <div class="row priceTitle">
                <h1>Мои заказы</h1>
            </div>
        </div>
        <div class="card-deck mb-3 text-center">
            <div class="card mb-4 box-shadow">

                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Position</th>
                        <th>Employee Name</th>
                        <th>Employee Surname</th>
                        <th>City</th>
                        <th>Status</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list orders as order>
                        <tr>
                            <th scope="row">${order.id}</th>
                            <td>${order.employee.position}</td>
                            <td>${order.employee.firstName}</td>
                            <td>${order.employee.lastName}</td>
                            <td>${order.city.name}</td>
                            <td>${order.isAccepted?string('Принят', 'Отклонен')}</td>
                            <td><button type="button" class="btn btn-danger" id="d${order.id}" onclick='deleteOrder(${order.id}, this.id)'>Delete</button></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</section>
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="credits">
                    Made by Samat Zaydullin<br>
                    All rights reserved
                </div>
            </div>
        </div>
    </div>
</footer>

</body>
</html>