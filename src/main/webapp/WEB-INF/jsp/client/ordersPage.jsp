<%--
  Created by IntelliJ IDEA.
  User: Самат
  Date: 08.11.2018
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
    <title>${locale.get("signup.title")}</title>
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
                        <a href="<c:url value="/orders"/>">
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
                            <a class="dropdown-item" href="<c:url value="americaTours"/>">${locale.get("signup.australia")}</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<c:url value="tours"/>">${locale.get("analogs.russia")}</a>
                        </div>
                    </li>
                    <li class="menu__item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${locale.get("tour.service")}
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
<section class="price">
    <div class="container">
        <div class="row priceTitle">
            <div class="row priceTitle">
                <h1>${locale.get("my.orders")}</h1>
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
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <th scope="row">${order.id}</th>
                            <td><c:out value="${order.employee.position}"></c:out></td>
                            <td><c:out value="${order.employee.firstName}"></c:out></td>
                            <td><c:out value="${order.employee.lastName}"></c:out></td>
                            <td><c:out value="${order.city.name}"></c:out></td>
                            <%--<c:if test="${order.isAccepted==true}"><c:out value="Принят"></c:out></c:if>--%>
                            <td><c:if test="${order.isAccepted==true}"><c:out value="Принят"></c:out></c:if>
                                <c:if test="${order.isAccepted==false}"><c:out value="Отклонен"></c:out></c:if>
                                <%--<c:out value="${order.isAccepted}"></c:out>--%></td>
                            <td><button type="button" class="btn btn-danger" id="d${order.id}" onclick='deleteOrder(${order.id}, this.id)'>Delete</button></td>
                        </tr>
                    </c:forEach>
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
