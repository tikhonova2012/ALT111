<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 12.07.2018
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            background: #99ddf0;
            color: #4D555C;
            font-family: Arial, sans-serif;
            font-size: 16px;
            font-weight: bold;
        }

        #header {
            background: #99ddf0;
            margin: auto auto;
            width: 100%;
            height: 20px;
            font-family: Arial, sans-serif;
            font-size: 30px;
        }

        #container {
            margin: auto auto;
            text-align: left;
            width: 100%;
            height: 80%;
        }

        #sidebar {
            background: #DCDCDC;
            float: right;
            width: 20%;
            height: 70%;
        }

        #content {
            background: #DCDCDC;
            float: left;
            width: 100%;
            height: 70%;
        }

        #clear {
            clear: both;
        }

        #footer {
            background: #99ddf0;
            width: 100%;
            height: 10px;
            bottom: 0;
        }

        .elementHidden {
            display: none;
        }

        .errorWarn {
            position: absolute;
        }
        form
        {
            width: 100%;
            height: 700px;
        }
    </style>
    <title>Введите параметры конструкции</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div id="container">
    <div id="header">
        <jsp:directive.include file="header.jsp"/>
    </div>

    <div id="content">

        <table class="warn-table">
            <thead>
            </thead>
            <tbody>
            <c:forEach var="valid" items="${requestScope.valid}" >
                <tr>
                    <td>${valid}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h3>Введите параметры конструкции для рассчета:</h3>
        <form method="GET" action="attr">
            <label>Длина констукции</label>
            <input type="1" name="CONSTRUCTION_LENGTH"required pattern="[0-9]+">мм<br><br>

            <label> Высота конструкции</label>
            <input type="1" name="CONSTRUCTION_HEIGHT" required pattern="[0-9]+">мм<br><br>

            <label>Цвет профилей</label>
            <select name="P_COLOR">
            <option value="00">00</option>
            <option value="RAL8017">RAL8017</option>
            <option value="RAL9006">RAL9006</option>
            <option value="RAL9016">RAL9016</option>
            <option value="A00-E6">A00-E6</option>
        </select><br><br>

            <label>Цвет комплектации</label>
            <select name="C_COLOR">
            <option value="-">-</option>
            <option value="RAL7037">RAL7037</option>
        </select><br><br>

            <label>Тип заполнения</label>
            <select id="FILLING_TYPE" name="FILLING_TYPE">
                <option value="empty"> </option>
            <option value="gl">остекление</option>
            <option value="blank">глухое заполнение</option>
        </select><br><br>

            <div class="elementHidden">
                <label>Тип остекления</label>
                <select id="GLAZING_TYPE" name="GLAZING_TYPE">
                    <option value="1">одинарное</option>
                    <option value="2">двойное</option>
                </select><br><br>
            </div>

            <div class="elementHidden">
                <label>Толщина стекла</label>
                <select id="GLAZING_THICKNESS" name="GLAZING_THICKNESS">мм
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="8">8</option>
                </select><br><br>
            </div>

            <div class="elementHidden">
                <label>Толщина глухого заполнения в мм</label>
                <select id="BLANK_FILLING_THICKNESS" name="BLANK_FILLING_THICKNESS">мм
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="8">8</option>
                    <option value="10">10</option>
                    <option value="12,5">12,5</option>
                </select><br><br>
            </div>

            <label> Количество ригелей между стойками (в одном проеме)</label>
            <input type="1" name="HEADER_QUANTITY" required pattern="[0-9]+"> шт.<br><br>

            <label>Тип ригеля</label>
            <select name="HEADER_TYPE">
            <option value="main">основной</option>
            <option value="ec">экономичный</option>
        </select><br><br>

            <label>Шаг между стойками</label>
            <input type="1" name="CEILING_FITTING_POINT_QUANTITY" required
                                                    pattern="[0-9]+"> мм<br><br>

            <label>Количество точек крепления к потолку</label>
            <input type="1" name="WALL_FITTING_POINT_QUANTITY"
                   required pattern="[0-9]+"> шт.<br><br>

            <label>Количество примыканий перегородки к стенам помещения+крайние стойки</label>
            <input type="1"
                   name="STEP_BETWEEN_RACKS"
                   required
                   pattern="[0-9]+"><br><br>

            <label>Количество фиксированных углов поворота 90 градусов</label>
            <input type="1"
                   name="NINTY_GRAD_ANGLES_QUANTITY"
                   required pattern="[0-9]+"><br><br>

            <label>Количество произвольных углов поворота каркаса перегородки</label>
            <input type="1"
                   name="FREE_GRAD_ANGLES_QUANTITY"
                   required pattern="[0-9]+"><br><br>

            <label> Количество стыковок/примыканий перегородки между собой</label>
            <input type="1"
                   name="PARTITION_CONTIGUITY_QUANTITY"
                   required pattern="[0-9]+"><br><br>

            <label> Количество "пустых" дверных проемов</label>
            <input type="1" name="FREE_DOORS_QUANTITY" required
                   pattern="[0-9]+"> шт.<br><br>
            <input type="submit" value="Рассчитать" style="width: 120px; height:40px" >

        </form>
    </div>

    <div id="clear">

    </div>

    <div id="footer">
        <jsp:directive.include file="footer.jsp"/>
    </div>
</div>

<script>
    (function () {
        var filingType = document.getElementById('FILLING_TYPE');
        var glazingType = document.getElementById('GLAZING_TYPE');
        var blankFillingThickness = document.getElementById('BLANK_FILLING_THICKNESS');
        var glassThickness = document.getElementById('GLAZING_THICKNESS');
        filingType.addEventListener('change', onFillingTypeChanged);

        function onFillingTypeChanged(event) {
            var value = event.target.value;
            if (value === 'gl') {
                glazingType.parentNode.classList.remove('elementHidden');
                glassThickness.parentNode.classList.remove('elementHidden');
            } else {
                glazingType.parentNode.classList.add('elementHidden');
                glassThickness.parentNode.classList.add('elementHidden');
            }

            if (value === 'blank') {
                blankFillingThickness.parentNode.classList.remove('elementHidden');
            } else {
                blankFillingThickness.parentNode.classList.add('elementHidden');
            }
        }
    })()
</script>
</body>
</html>