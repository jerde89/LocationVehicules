<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>Title</title>
    </head>

    <body>
        <p>Nom : ${ utilisateur.nom }</p>

        <input type="text" id="nom" name="nom" value="<c:out value="${ utilisateur.nom }"/>" size="20" maxlength="20" />
    </body>
</html>
