<%--
  Created by IntelliJ IDEA.
  User: Самат
  Date: 13.11.2018
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${locale.get("signup.title")}</title>
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
                <a href="<c:url value="home"/>"><img src="img/travel.png" width=350 alt="travel"></a>
            </div>
            <div class="col-lg-7 ml-auto">
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
                        <a href="<c:url value="myInvitations"/>">
                            Приглашения
                        </a>
                    </li>
                    <li class="menu__item">
                        <a href="<c:url value="orders"/>">
                            ${locale.get("my.orders")}
                        </a>
                    </li>
                    <li class="menu__item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${locale.get("signup.navigation")}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                            <a class="dropdown-item" href="<c:url value="asiaTours"/>">${locale.get("signup.asia")}</a>
                            <a class="dropdown-item" href="<c:url value="europeTours"/>">${locale.get("signup.europe")}</a>
                            <a class="dropdown-item" href="<c:url value="americaTours"/>">${locale.get("signup.america")}</a>
                            <a class="dropdown-item" href="<c:url value="australiaTours"/>">${locale.get("signup.australia")}</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<c:url value="tours"/>">${locale.get("analogs.russia")}</a>
                        </div>
                    </li>
                    <li class="menu__item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${locale.get("tour.services")}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                            <a class="dropdown-item" href="<c:url value="hotels"/>">${locale.get("signup.hotels")}</a>
                            <a class="dropdown-item" href="#">${locale.get("signup.prices")}</a>
                            <a class="dropdown-item" href="#">${locale.get("signup.insurance")}</a>
                        </div>
                    </li>
                    <li class="menu__item">
                        <a href="<c:url value="signOut"/>">
                            ${locale.get("log.out")}
                        </a>
                    </li>

                </ul>

            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 offer">
                <h1 class="offer__title">
                    <%if(request.getAttribute("clientFirstName")!=null) {
                        out.print(request.getAttribute("clientLastName")+" "+request.getAttribute("clientFirstName"));
                    }else{
                        out.print("Anonimus");
                        response.sendRedirect("/signIn");
                    }
                    %>
                </h1>
                <div class="offer__intro">
                    <%--${locale.get("quote.exp")}--%>
                </div>
                <p class="offer__text">
                    <%--${locale.get("quote.author")}--%>
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
                <h1><%out.print(request.getAttribute("country")); %></h1>
            </div>
        </div>

        <c:forEach var="city" items="${cityList}" varStatus="qwer">
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">${city.name}</h4>
                    </div>
                    <div class="card-img-top">
                        <div id="carouselExampleIndicators${city.id}" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <%int st = 0;%>
                                <c:forEach var="picture" items="${picture}" varStatus="stat">
                                    <c:if test="${city.id==picture.city.id}">
                                        <li data-target="#carouselExampleIndicators${city.id}" data-slide-to="<%{out.print(st);}%>" <%if(st==0){out.print("class='active'");st++;}else{st++;}%>></li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                            <div class="carousel-inner">
                                <% st = 0;%>
                                <c:forEach var="picture" items="${picture}" varStatus="stat">
                                    <c:if test="${city.id==picture.city.id}">
                                        <div class='carousel-item <%if(st==0){out.print("active");st++;}%>'>
                                            <img class="d-block w-100" src="${picture.href}?auto=yes&bg=666&fg=444&text=Second slide" alt="Second slide">
                                        </div>
                                    </c:if>
                                </c:forEach>
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
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">${locale.get("close")}</button>
                            <button type="button" onclick='addToOrder(${city.id}, this.id)' class="btn btn-lg btn-block btn-outline-primary" id="button${city.id}">${locale.get("send")}</button>
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
                                    <%--<select class="form-control">--%>
                                        <%--<option class="col-form-label" value="" selected disabled>Выберите людей</option>--%>
                                        <%--<c:forEach var="clientList" items="${clientList}">
                                            &lt;%&ndash;<option class="col-form-label" value="${clientList.id}" >&ndash;%&gt;
                                            <label for="${clientList.firstName}${clientList.id}">
                                                <input type="checkbox" name="clientIds" value="${clientList.id}" id="${clientList.firstName}${clientList.id}">${clientList.lastName} ${clientList.firstName}
                                            </label> <br>

                                            &lt;%&ndash;<p id="${clientList.firstName}${clientList.id}" onclick="addClient(${clientList.id}, this.id)">${clientList.lastName} ${clientList.firstName}</p>&ndash;%&gt;

                                            &lt;%&ndash;</option>&ndash;%&gt;
                                        </c:forEach>--%>
                                    <%--</select>--%>
                                    <%--<input type="text" name="count" id="count">--%>

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
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">${locale.get("close")}</button>
                            <button onclick="sendTo(${city.id})" formmethod="post" type="button" class="btn btn-lg btn-block btn-outline-primary" id="btnn${city.id}">${locale.get("send")}</button>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>
</section><%--
<c:forEach var="city" items="${cityList}" varStatus="qwer">
    <div class="modal fade" id="questModal${city.name}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">${locale.get("new.question")}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">${locale.get("your.number")}</label>
                            <input type="text" class="form-control" id="recipient-name">
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">${locale.get("message")}</label>
                            <textarea class="form-control" id="message-text"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${locale.get("close")}</button>
                    <button type="button" onclick='addToOrder(${city.id}, this.id)' class="btn btn-lg btn-block btn-outline-primary">${locale.get("send")}</button>
                </div>
            </div>
        </div>
    </div>
</c:forEach>--%>
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