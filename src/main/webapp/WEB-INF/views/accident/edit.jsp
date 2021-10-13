<%@ page contentType="text/html;charset=UTF-8" %>
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
                Редактирование инцидента
            </div>
            <div class="card-body">
                <form class="form-horizontal" action="<c:url value='/save'/>" method='POST'>
                    <input type="hidden" name="id" value="${accident.id}">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">Наименование инцидента:</label>
                        <input type="text" class="form-control" id="name" name="name" value="${accident.name}">
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="text">Описание инцидента:</label>
                        <input type="text" class="form-control" id="text" name="text" value="${accident.text}">
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="address">Адрес инцидента:</label>
                        <input type="text" class="form-control" id="address" name="address" value="${accident.address}">
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="type.id">Тип инцидента:</label>
                        <select class="form-control" id="type.id" name="type.id" required>
                            <c:forEach var="type" items="${types}" >
                                <c:choose>
                                    <c:when test="${type.id eq accident.type.id}">
                                        <option value="${type.id}" selected>${type.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${type.id}">${type.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="rIds">Статьи инцидента:</label>
                        <select id="rIds" name="rIds" class="form-control" multiple required>
                            <c:forEach var="rule" items="${rules}" >
                                <c:choose>
                                    <c:when test="${accident.rules.contains(rule)}">
                                        <option value="${rule.id}" selected>${rule.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${rule.id}">${rule.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>