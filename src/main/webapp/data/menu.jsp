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
            <div class="par">
                <div class="menu">
                    <input type="submit" value="Добавить устройство" formaction="security_tech?action=add_device">
                    <input type="submit" value="Изменить устройство" formaction="security_tech?action=change_device">
                    <input type="submit" value="Получить по ID устройства" formaction="security_tech?action=get_device">
                    <input type="submit" value="Список устройств" formaction="security_tech?action=get_list_device">
                    <input type="submit" value="Вкл/Выкл устройство" formaction="security_tech?action=switch_device">
                    <input type="submit" value="Удалить устройство" formaction="security_tech?action=delete_device">
                </div>
                <div class="menu">
                    <input type="submit" value="Добавить сотрудника" formaction="security_tech?action=add_employee">
                    <input type="submit" value="Изменить запись о сотруднике" formaction="security_tech?action=change_employee">
                    <input type="submit" value="Получить по ID сотрудника" formaction="security_tech?action=get_employee">
                    <input type="submit" value="Список сотрудников" formaction="security_tech?action=get_list_employee">
                    <input type="submit" value="Вкл/Выкл карточку" formaction="security_tech?action=switch_employee">
                    <input type="submit" value="Удалить сотрудника" formaction="security_tech?action=delete_employee">
                </div>
            </div>
        </form>
    </div>
</body>