<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>

    <title>Туры</title>
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
            <div class="col-lg-5 ml-auto">
                <ul class="menu d-flex justify-content-center">
                    <li class="menu__item">
                        <a href="reviews">
                            Отзывы
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="#">
                            О нас
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="signIn">
                            Вход
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="signUp">
                            Регистрация
                        </a>
                    </li>

                </ul>

            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 offer">
                <h1 class="offer__title">
                    «Путешествия помогают нам быть скромнее. Каждый из нас лишь крохотная песчинка в этой пустыне людей»
                </h1>
                <div class="offer__intro">
                    — Гюстав Флобер.
                </div>
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
            <div class="col-lg-12">
                <div class="feature__text" align="center">

                    <#list reviews as review>

                    <div class="card mb-4 box-shadow">
                        <div class="card-header">
                            <h4 class="my-0 font-weight-normal">${review.client.firstName} ${review.client.lastName}</h4>
                        </div>
                        <div class="card-body">
                            <ul class="list-unstyled mt-3 mb-4">
                                <li>${review.text}</li>
                            </ul>
                        </div>
                    </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</section>

<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="credits">
                    Сделано Саматом Зайдуллиным<br>
                    Все права защищены
                </div>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
