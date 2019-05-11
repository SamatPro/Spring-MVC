<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Профиль</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans+SC:400,700&amp;subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 logo">
                <a href="profilePage"><img src="img/travel.png" width=350 alt="travel"></a>
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

<section class="features">

    <div class="container">
        <div class="row">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="5"></li>

                </ol>
                <div class="carousel-inner">

                    <div class="carousel-item active">
                        <img class="d-block w-100" src="img/palms.jpg?auto=yes&bg=666&fg=444&text=Second slide" alt="Second slide" width="300" style="overflow: hidden">
                    </div>
                    <#list pictures as picture>
                        <div class="carousel-item">
                        <img class="d-block w-100" src="${picture.href}?auto=yes&bg=666&fg=444&text=Second slide" alt="Second slide" width="300" style="overflow: hidden">
                        </div>
                    </#list>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="feature__text" align="center">
                    <p>Баден-Баден изначально место, куда все едут за лечением, здоровьем и восстановлением. И Brenners Park-Hotel & SPA (от 23 тыс. руб./сутки) как раз специализируется на этом. Точнее, часть отеля, расположенного на вилле. Там всего 16 номеров и отдельная зона со спа: массажи, сауны, хаммам. Еще здесь есть разные детокс-программы. Самая популярная – 10-дневный «Королевский детокс» стоимостью 10 тысяч евро (около 740 тыс. рублей). Но свою цену он оправдывает – здесь включено все, что только можно: чекап (комплексная проверка здоровья), анализы, круглосуточный надзор личного врача, персонализированная диета с учетом предпочтений.
                        Кроме того, здесь есть и digital detox. В каждой комнате есть специальная кнопка, нажимая которую можно отключить в номере WiFi и все электромагнитные поля. В ванной остается одна розетка, где можно подключить мобильный телефон и закрыть дверь. Телефон будет заряжаться, но электромагнитных полей в комнате не будет.
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="contact">
    <div class="container">
        <div class="col-lg-12">
            <h1 align="center">Оставить отзыв</h1><br>
            <button type="button" class="btn btn-primary btn-lg btn-block quest" data-toggle="modal" data-target="#questModal">Отправить</button>
        </div>
    </div>
</section>

<div class="modal fade" id="questModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Текст</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" id="review">
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Сообщение</label>
                        <textarea class="form-control" id="message-text" name="reviewText"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                <button type="submit" form="review" class="btn btn-primary" formmethod="post">Отправить</button>

            </div>
        </div>
    </div>
</div>


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
