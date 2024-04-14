<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <style> @import url("css.css");</style>
</header>
<body>
    <div class="container">
        <div class="title">
            <h1>Security</h1>
            <h1 style="color: red; text-decoration: solid;">Tech</h1>
        </div>
        <form action="security_tech" method="post">
            <select class="inp select" name="turn" required>
                <option value>Выберите режим карточки:</option>
                <option>Включить</option>
                <option>Выключить</option>
            </select>
            <input type="number" min="0" class="inp" placeholder="Введите ID сотрудника" required>
            <div class="bth">
                <button class="form b" type="submit" formaction="security_tech?action=switchEmployeeForm">Применить</button>
                <button class="form b" type="reset">Очистить</button>
                <a href="menu.jsp" class="form">Назад</a>
            </div>
        </form>
    </div>
</body>