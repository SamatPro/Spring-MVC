<%--
  Created by IntelliJ IDEA.
  User: Самат
  Date: 06.11.2018
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${locale.get("registration")}</title>
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
                <li class="menu__item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${locale.get("select.language")}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <a class="dropdown-item" href="/signUp?lang=Ru">${locale.get("russian-lang")}</a>
                        <a class="dropdown-item" href="/signUp?lang=En">${locale.get("english-lang")}</a>
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
                        <a href="/signIn">
                            ${locale.get("login.in")}
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="home">
                            ${locale.get("main.page")}
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
            <form method="post">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputEmail4">${locale.get("signup.email")}</label>
                        <input type="email" class="form-control" id="inputEmail4" name="email" placeholder="${locale.get("signup.email")}" value="<%if(request.getAttribute("email")!=null){out.println(request.getAttribute("email"));}%>"><br>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="inputPassword4">${locale.get("signup.password")}</label>
                        <input type="password" class="form-control" id="inputPassword4" name="password" placeholder="${locale.get("signup.password")}" /><br>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="inputPassword5">${locale.get("signup.reapeat.password")}</label>
                        <input type="password" class="form-control" id="inputPassword5" name="repassword" placeholder="${locale.get("signup.reapeat.password")}" />
                    </div>
                </div><br><br>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <input type="text" class="form-control" id="last_name" name="last_name" placeholder="${locale.get("signup.last.name")}" /><br>
                    </div>
                    <div class="form-group col-md-12">
                        <input type="text"  class="form-control" id="first_name" name="first_name" placeholder="${locale.get("signup.first.name")}" /><br>

                    </div>
                    <div class="form-group col-md-12">
                        <input type="text" class="form-control"  id="middle_name" name="middle_name" placeholder="${locale.get("signup.middle.name")}" /><br>

                    </div>
                    <div class="form-group col-md-12">
                        <input type="text" class="form-control"  id="address" name="address" placeholder="${locale.get("signup.address")}" /><br>
                        <%--<select class="form-control" name="city">
                            <option value="" selected="true" disabled="true">Выберите город:</option>
                            <c:forEach var="city" items="${cities}">
                                <option value="${city.id}">${city.name}</option>
                            </c:forEach>

                        </select>--%>
                    </div>

                    <div class="form-group col-md-12">
                        <input type="text" class="form-control"  id="phone_number" name="phone_number" maxlength="12" placeholder="${locale.get("signup.phone.number")}" /><br>

                    </div>
                    <div class="form-group col-md-12">
                        <span class="gender"><input type="radio" id="male" name="gender" value="true" ><label for="male">${locale.get("signup.male")}</label></span><br>
                        <span class="gender"><input type="radio" id="female" name="gender" value="false"><label for="female">${locale.get("signup.female")}</label></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label"><input class="form-check-input" type="checkbox" name="subscription" value="true">${locale.get("signup.news-subscription")}</label><br>
                        <label class="form-check-labe2"><input class="form-check-input" type="checkbox" name="consent" value="true">${locale.get("signup.consent")}</label><br>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary" name="submit" value="${locale.get("signup.SigningUp.reg")}" id="signUp" /><br>
                <div id="status1"><br><br>
                    <%if(request.getAttribute("existingEmailError")!=null){out.println("<p> -" + request.getAttribute("existingEmailError") + "</p>");}%>
                    <%if(request.getAttribute("email")!=null){out.println("<p> -" + request.getAttribute("email") + "</p>");}%>
                    <%if(request.getAttribute("fieldsError")!=null){out.println("<p> -"+request.getAttribute("fieldsError")+"<p>");}%>
                    <%if(request.getAttribute("consentError")!=null){out.println("<p> -"+request.getAttribute("consentError")+"</p>");}%>
                    <%if(request.getAttribute("passwordError")!=null){out.println("<p> -"+request.getAttribute("passwordError")+"</p>");}%>
                </div>
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
