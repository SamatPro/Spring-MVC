<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход</title>
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
                        <a href="home">
                            Главная старница
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <form class="form-signin login" method="post" modelAttribute="loginForm">
                <div class="text-center mb-4">
                    <h1>Вход</h1>
                </div>

                <#if loginError??>
                    <div class="alert alert-danger" role="alert"> ${loginError}</div>
                </#if>

                <div class="form-label-group">
                    <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Введите эл.почту" required value="<#if login??>${login}</#if>">
                    <label for="inputEmail">Эл.почта</label>
                </div>

                <div class="form-label-group">
                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Введите пароль" required value="<#if password??>${password}</#if>">
                    <label for="inputPassword">Пароль</label>
                </div>
                <div class="form-label-group">
                    <div class="form-check">
                        <input type="checkbox" name="remember" id="inputRemember" class="form-check-input" value="true" >
                        <label class="form-check-label" for="inputRemember">Запомнить меня</label>
                    </div>
                </div>

                <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="Войти">

                <p class="mt-5 mb-3 text-muted text-center"><a href="signUp">Зарегистрироваться</a></p>
            </form>
        </div>
    </div>
</header>

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
