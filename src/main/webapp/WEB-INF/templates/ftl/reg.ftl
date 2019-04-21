<#ftl encoding="UTF-8">
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Регистрация</title>
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans+SC:400,700&amp;subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/myStyle.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js"></script>

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
                        <a href="#">
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
                        <a href="home">
                            Главная страница
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="registerform">
    <div class="container">
        <div class="col-lg-12">
            <form method="post" action="saveClient" modelAttribute="clientForm">
                <div class="text-center mb-4">
                    <h1>Регистрация</h1>
                </div>
                <#if error??>
                <#list error as errors>
                    <div class="alert alert-danger" role="alert"> ${errors.defaultMessage}</div>
                </#list>
                </#if>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputEmail">Эл. почта</label>
                        <input path="email" id="inputEmail" type="email" class="form-control" name="email" placeholder="Эл. почта" value=""/><br>

                    </div>
                    <div class="form-group col-md-12">
                        <label for="inputPassword4">Пароль</label>
                        <input path="password" id="inputPassword4" type="password" class="form-control" name="password" placeholder="Пароль" value="" /><br>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="inputPassword5">Повтор пароля</label>
                        <input path="repassword" id="inputPassword5" type="password" class="form-control" name="repassword" placeholder="Повтор пароля" value=""/>
                    </div>
                </div><br><br>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <input path="lastName" type="text" class="form-control" name="lastName" placeholder="Фамилия" value=""/><br>
                    </div>
                    <div class="form-group col-md-12">
                        <input path="firstName" type="text"  class="form-control" name="firstName" placeholder="Имя" value=""/><br>

                    </div>
                    <div class="form-group col-md-12">
                        <input path="middleName" type="text" class="form-control" name="middleName" placeholder="Отчество" value=""/><br>

                    </div>
                    <div class="form-group col-md-12">
                        <input path="address" type="text" class="form-control" name="address" placeholder="Адрес" value=""/><br>
                    </div>

                    <div class="form-group col-md-12">
                        <input path="phoneNumber" type="text" class="form-control" name="phoneNumber" maxlength="12" placeholder="Номер телефона" value=""/><br>

                    </div>
                    <div class="form-group col-md-12">
                        <span class="gender"><input name="isMale" type="radio" id="male" value="true" /><label for="male">Мужской</label></span><br>
                        <span class="gender"><input name="isMale" type="radio" id="female" value="false"/><label for="female">Женский</label></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label"><input name="newsSubscription" class="form-check-input" type="checkbox" value="true"/>Подписка</label><br>
                        <label class="form-check-labe2"><input name="consent" class="form-check-input" type="checkbox" value="true"/>Согласие на обработку данных</label><br>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary" name="submit" value="Регистрация" id="signUp" /><br>

            </form>
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