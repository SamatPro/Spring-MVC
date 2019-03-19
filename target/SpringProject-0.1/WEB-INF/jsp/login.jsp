<%--
  Created by IntelliJ IDEA.
  User: Самат
  Date: 06.11.2018
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${locale.get("login.in")}</title>
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
                <li class="menu__item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${locale.get("select.language")}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <a class="dropdown-item" href="?lang=Ru">${locale.get("russian-lang")}</a>
                        <a class="dropdown-item" href="?lang=En">${locale.get("english-lang")}</a>
                    </div>
                </li>
                <ul class="menu d-flex justify-content-center">
                    <li class="menu__item">
                        <a href="#">
                            ${locale.get("signup.reviews")}
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="#">
                            ${locale.get("about.us")}
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="<c:url value="home"/>">
                            ${locale.get("main.page")}
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <form class="form-signin login" method="post">
                <div class="text-center mb-4">
                    <h1> ${locale.get("login.in")}</h1>
                </div>
                <div class="form-label-group">
                    <input type="email" name="email" id="inputEmail" class="form-control" placeholder="${locale.get("signup.email")}" required autofocus>
                    <label for="inputEmail">${locale.get("signup.email")}</label>
                </div>

                <div class="form-label-group">
                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="${locale.get("signup.password")}" required>
                    <label for="inputPassword">${locale.get("signup.password")}</label>
                </div>
                <div class="form-label-group">
                    <div class="form-check">
                        <input type="checkbox" name="remember" id="inputRemember" class="form-check-input" value="true">
                        <label class="form-check-label" for="inputRemember">Запомнить меня</label>
                    </div>
                </div>

                <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="${locale.get("signup.SigningIn.reg")}">
                <p class="mt-5 mb-3 text-muted text-center"><a href="<c:url value="signUp"/>">${locale.get("registration")}</a></p>
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
