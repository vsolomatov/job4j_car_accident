<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Accident</title>

</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Правонарушения
            </div>
            <div>
                Login as : ${user.username}
                <a href="<c:url value='/reg'/>">Регистрация</a>
            </div>
            <div class="card-body">
                <table id="myTable" class="table">
                    <thead>
                    <tr>
                        <th scope="col"># правонарушения</th>
                        <th scope="col">Наименование</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Адрес</th>
                        <th scope="col">Тип инцидента</th>
                        <th scope="col">Статьи</th>
                    </tr>
                    </thead>
                    <tbody id="myTableBody">
                        <c:forEach var="accident" items="${accidents}">
                            <tr>
                                <td>
                                    <a href='<c:url value="/edit?id=${accident.id}"/>'>
                                        <i class="fa fa-edit mr-3"></i>
                                    </a>
                                    ${accident.id}
                                </td>
                                <td>${accident.name}</td>
                                <td>${accident.text}</td>
                                <td>${accident.address}</td>
                                <td>
                                    <select name="type.id" disabled>
                                            <option value="${accident.type.id}">${accident.type.name}</option>
                                    </select>
                                </td>
                                <td>
                                    <select name="rIds" disabled multiple>
                                        <c:forEach var="rule" items="${accident.rules}" >
                                            <option value="${rule.id}">${rule.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer">
                <a href="<c:url value='/create'/>">Добавить инцидент</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>