<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Туры</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>

    <script src="js/ajax.js"></script>
    <script src="js/search.js"></script>
    <script src="js/addClient.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans+SC:400,700&amp;subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
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
                        <a href="orders">
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
<section class="cities">
    <div class="container">
        <div class="row priceTitle">
            <div class="row priceTitle">
                <h1>${country}</h1>
            </div>
        </div>
         <#list cityList as city>
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">${city.name}</h4>
                    </div>
                    <div class="card-img-top">
                        <div id="carouselExampleIndicators${city.id}" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <#assign st = 0>
                                <#list picture as picture>
                                    <#if city.id==picture.city.id>
                                        <li data-target="#carouselExampleIndicators${city.id}" data-slide-to="${st}" <#if st==0> class="active" <#assign st++><#else><#assign st++></#if></li>
                                    </#if>
                                </#list>
                            </ol>
                            <div class="carousel-inner">
                                <#assign st = 0>
                                <#list picture as picture>
                                    <#if city.id==picture.city.id>
                                        <div class='carousel-item <#if st==0>active<#assign st++></#if>'>
                                            <img class="d-block w-100" src="${picture.href}?auto=yes&bg=666&fg=444&text=Second slide" alt="Second slide">
                                        </div>
                                    </#if>
                                </#list>
                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleIndicators${city.id}" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators${city.id}" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">$400</h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>${city.description}</li>
                        </ul>
                        <button class="btn btn-primary btn-lg btn-block quest" data-toggle="modal" data-target="#questCooperative${city.id}" type="button" id="coop${city.id}${city.id}" >Cooperative surve</button>

                        <button class="btn btn-primary btn-lg btn-block quest" data-toggle="modal" data-target="#questModal${city.id}" type="button" id="${city.id}${city.id}" >Single surve</button>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="questModal${city.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabe${city.id}">Пожалуйста, заполните форму.</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="recipient-name${city.id}" class="col-form-label">Город отправления:</label>
                                    <input type="text" class="form-control" id="recipient-name${city.name}" name="city_name">
                                </div>
                                <div class="form-group">
                                    <label for="recipient-name${city.id}" class="col-form-label">Город прибытия:</label>
                                    <input type="text" class="form-control" id="recipient-name${city.id}" placeholder="${city.name}" disabled>
                                </div>
                                <select class="form-control form-control-sm">
                                    <option>Дорогой</option>
                                    <option>Менее дорогой</option>
                                    <option>Дешевый</option>
                                </select>
                                <label for="date${city.id}" class="col-form-label">Выберите дату отправления:</label>
                                <input data-provide="datepicker" id="date${city.id}" class="date-picker">
                                <label for="date${city.id}2" class="col-form-label">Выберите дату прибытия:</label>
                                <input data-provide="datepicker" id="date${city.id}2" class="date-picker">
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть/button>
                            <button type="button" onclick='addToOrder(${city.id}, this.id)' class="btn btn-lg btn-block btn-outline-primary" id="button${city.id}">Отправить</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="questCooperative${city.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel${city.id}">Пожалуйста, заполните форму.</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="recipient-name-coop${city.id}" class="col-form-label">Город отправления:</label>
                                    <input type="text" class="form-control" id="recipient-name-coop${city.id}" name="city_name">
                                </div>
                                <div class="form-group">
                                    <label for="recipient-name-coop${city.id}" class="col-form-label">Город прибытия:</label>
                                    <input type="text" class="form-control" id="recipient-name-coop${city.id}" placeholder="${city.name}" disabled>
                                </div>
                                <div class="form-group">

                                </div>
                                <form class="form-search">
                                    <label for="searchf-coop${city.id}">Поиск друга : </label>
                                    <input type="text" class="input-medium search-query" name="searchField"id="searchf-coop${city.id}" oninput="search('searchf-coop${city.id}', ${city.id})">
                                    <button type="button" class="btn" onclick="search('searchf-coop${city.id}', ${city.id})">Найти</button>
                                </form>
                                <div class="form-group" id="inner${city.id}"> </div>

                                <label for="date-coop${city.id}" class="col-form-label">Выберите дату отправления:</label>
                                <input data-provide="datepicker" id="date-coop${city.id}" class="date-picker">
                                <label for="date-coop${city.id}2" class="col-form-label">Выберите дату прибытия:</label>
                                <input data-provide="datepicker" id="date-coop${city.id}2" class="date-picker">
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                            <button onclick="sendTo(${city.id})" formmethod="post" type="button" class="btn btn-lg btn-block btn-outline-primary" id="btnn${city.id}">Отправить</button>
                        </div>
                    </div>
                </div>
            </div>
        </#list>

    </div>
</section>
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="credts">
                    Made by Samat Zaydullin<br>
                    All rights reserved
                </div>
            </div>
        </div>
    </div>
</footer>

</body>
</html>