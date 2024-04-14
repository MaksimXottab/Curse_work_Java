<%@ page import="by.maksim.curse_work.model.Device" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<header>
    <style> @import url("css.css");</style>
</header>
<body>

<% if (request.getAttribute("titleText") != null) { %>
<% if (request.getAttribute("titleText").equals("create")) { %>
<h2>Запись успешно добавленна в базу данных</h2>
<% } else if (request.getAttribute("titleText").equals("update")) { %>
<h2>Запись успешно обновлена</h2>
<% } else if (request.getAttribute("titleText").equals("get")) { %>
<h2>Результаты по вашему запросу</h2>
<% } else if (request.getAttribute("titleText").equals("delete")) { %>
<h2>Запись с id=<%=request.getAttribute("deletedId")%> успешно удалена</h2>
<% } %>
<% } %>

<% if (request.getAttribute("trains") != null) { %>
<% java.util.List<Train> trains = (java.util.List<Train>) request.getAttribute("trains"); %>
<div style="display: flex; flex-direction: column; align-items: center; justify-content: center;">
    <% for (Train train : trains) { %>
    <div style="text-align: left; padding: 10px;">
        <div style="margin-left: 30px;">
            <%=train.formatForJsp()%>
        </div>
    </div>
    <% } %>
</div>
<% } %>

<form action="train" method="get">
    <div>
        <input type="submit" value="Продолжить" formaction="train">
    </div>
</form>
</body>
</html>