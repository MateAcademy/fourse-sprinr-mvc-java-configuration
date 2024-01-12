<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main page</title>
</head>
<body>

    <%--    <a th:href="@{/people/}" th:text="Show all people">Show all people</a>--%>
    <%--    <br>--%>
    <%--    <a th:href="@{/people/new}" >Add new person</a>--%>
    <%--    <br>--%>
    <%--    <a th:href="@{/people/{id}/edit(id=${person.getId()})}" th:text="update"></a>--%>

    <a href="/people/" >Show all people</a>
    <br>
    <a href="/people/new">Add new person</a>

</body>
</html>
