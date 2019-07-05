<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Places</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/main.js"></script>
    <script>
        function validateForm(url, id) {
            var rsl = false;
            var action = "?action=" + document.getElementById('action').value;
            if (action === '?action=update') {
                action += "&id=" + id;
            }
            if (validateInput('countryName') && validateInput('cityName')) {
                changeElement(
                    url,
                    action,
                    $('#countryId').val(),
                    $('#countryName').val(),
                    $('#cityId').val(),
                    $('#cityName').val()
                );
                rsl = true;
            }
            return rsl;
        }

        function getCitiesByCurrentCountry(countryId) {
            $.ajax({
                type: "GET",
                url: "././places",
                data: 'countryId=' + countryId,
                success: function (data, textStatus, request) {
                    var link = document.getElementById("link" + countryId);
                    var list = JSON.parse(request.getResponseHeader('citiesByCountry'));
                    var parent_element = document.getElementById('countryId' + countryId);
                    if (list.length === 0) {
                        document.getElementById('dt' + countryId).removeChild(link);
                        show_form();
                        document.getElementById('countryId').value = countryId;
                        document.getElementById('countryName').value = document.getElementById('dt' + countryId).innerText;
                    } else {
                        link.innerText = '-';
                        link.onclick = function () {
                            resetCitiesByCurrentCountryId(countryId)
                        };
                    }
                    for (i = list.length - 1; i >= 0; i--) {

                        var dd = document.createElement("dd");
                        dd.className = "ml-4 col-8";
                        var row = document.createElement("div");
                        row.className = "row";
                        var col3 = document.createElement("div");
                        col3.className = "col-sm";
                        var col1 = document.createElement("div");
                        col1.className = "col-sm-1";
                        var a1 = document.createElement("a");
                        a1.href = "././places?id=" + list[i].cityId;
                        a1.innerText = list[i].cityName;
                        var a2 = document.createElement("a");
                        a2.href = "././places";
                        a2.className = "badge badge-dark";
                        var data = list[i].cityId + '&obj = city';
                        a2.onclick = function () {
                            deleteElement('././places', data)
                        };
                        a2.innerText = "X";

                        col3.appendChild(a1);
                        col1.appendChild(a2);
                        row.appendChild(col3);
                        row.appendChild(col1);
                        dd.appendChild(row);
                        parent_element.appendChild(dd);
                    }
                }
            })
        }

        function resetCitiesByCurrentCountryId(countryId) {
            var parentElement = document.getElementById('countryId' + countryId);
            while (parentElement.lastChild) {
                parentElement.removeChild(parentElement.lastChild);
            }
            var link = document.getElementById("link" + countryId);
            link.innerText = '+';
            link.onclick = function () {
                getCitiesByCurrentCountry(countryId)
            };
        }

        function resetId(elementId) {
            var element = document.getElementById(elementId);
            element.value = "";
        }

        function show_form() {
            var doc = document;
            var formStyleDisplay = doc.getElementById('editForm').style.display;
            doc.getElementById('editForm').style.display = (formStyleDisplay === 'none') ? 'block' : 'none';
            doc.getElementById('spSel').innerHTML = (formStyleDisplay === 'none') ? '-' : '+';
            if (formStyleDisplay === 'none') {
                $('#spSel').addClass('btn-secondary').removeClass('btn-primary');
            } else {
                $('#spSel').addClass('btn-primary').removeClass('btn-secondary');
            }
        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/users">Users</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${param.id != null}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/places">All places</a>
                </li>
            </c:if>
            <c:if test="${isAdmin}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/roles">Roles</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.servletContext.contextPath}/persons">Persons</a>
            </li>
        </ul>
        <c:if test="${login != null}">
            <span class="navbar-text mr-2">${login}</span>
        </c:if>
        <form class="form-inline my-2 my-lg-0" action="${pageContext.servletContext.contextPath}/signIn" method="get">
            <input type="hidden" name="logout" value="true">
            <button class="btn btn-primary" type="submit">Logout</button>
        </form>
    </div>
</nav>
<div>
    <div class="container">
        <c:choose>
            <c:when test="${result != null}">
                <div class="alert alert-light text-center" role="alert">
                    <h5><c:out value="${result}"/></h5>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-light text-center" role="alert">
                    <c:choose>
                        <c:when test="${city != null}">
                            <h1><c:out value="Country: ${city.country.name}. city: ${city.name}."/></h1>
                        </c:when>
                        <c:otherwise>
                            <h1><c:out value="Places list"/></h1>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-1 mb-3">
                <button class="btn btn-secondary" type="button" id="spSel" onclick="show_form()">+</button>
            </div>
            <div class="col-sm">
                <form id="editForm">
                    <c:set var="action" value="add"/>
                    <c:set var="idForUpdate" value="-1"/>
                    <c:if test="${param.id != null}">
                        <c:set var="action" value="update"/>
                        <c:set var="idForUpdate" value="${param.id}"/>
                    </c:if>
                    <input type="hidden" name="action" id="action" value="${action}">
                    <div class="row">
                        <input class="form-control" id="countryId" type="hidden" name="countryId"
                               value="<c:if test="${param.id != null}">${city.country.id}</c:if>">
                        <input class="form-control" id="cityId" type="hidden" name="cityId"
                               value="<c:if test="${param.id != null}">${city.id}</c:if>">
                        <div class="col-sm-5">
                            <div class="input-group">
                                <label class="mr-2 mt-2" for="countryName">Country name:</label>
                                <input class="form-control" id="countryName" type="text" placeholder="enter country"
                                       name="countryName"
                                       value="<c:if test="${param.id != null}">${city.country.name}</c:if>">
                                <c:if test="${param.id != null}">
                                    <input type="button" class="btn-success" value="+" onclick="resetId('countryId')">
                                </c:if>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <label class="mr-2 mt-2" for="cityName">City name:</label>
                                <input class="form-control" id="cityName" type="text" placeholder="enter city"
                                       name="cityName"
                                       value="<c:if test="${param.id != null}">${city.name}</c:if>">
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <button class="btn btn-primary" type="button" id="btn_submit"
                                    onclick="return validateForm('././places', ${idForUpdate})">${action}</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-8">
                <dl>
                    <c:forEach items="${countries}" var="country">
                        <div class="col-3">
                            <dt id="dt${country.id}">
                                    ${country.name}
                                <c:choose>
                                    <c:when test="${param.countryId == null || (param.countryId != null && param.countryId != country.id)}">
                                        <a href="#" id="link${country.id}"
                                           onclick="getCitiesByCurrentCountry(${country.id})">+</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.servletContext.contextPath}/places"
                                           id="link${country.id}">-</a>
                                    </c:otherwise>
                                </c:choose>
                            </dt>
                        </div>
                        <div id="countryId${country.id}">
                        </div>
                    </c:forEach>
                </dl>
            </div>
            <div class="col-4">
                <img src="http://itd3.mycdn.me/image?id=878934972140&t=20&plc=WEB&tkn=*DAzfir_EE1xz4_RkOQb2KLb4TEo"
                     alt="..." class="img-thumbnail">
            </div>
        </div>

    </div>
</div>
<c:if test="${param.id == null}">
    <script>
        $(show_form());
    </script>
</c:if>
</body>
</html>
