<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Creation ou modification de StatusDemande</h1>

    <form action="${pageContext.request.contextPath}/statusdemandes/save" method="post">
        <input type="hidden" name="idStatusDemande" value="${statusDemande.idStatusDemande}">

        <label for="idDemande">Demande:</label>
        <select name="idDemande" id="idDemande" required>
            <option value="0">-- Sélectionnez une demande --</option>
            <c:forEach var="demande" items="${demandes}">
                <option value="${demande.idDemande}" ${statusDemande.demande != null && statusDemande.demande.idDemande == demande.idDemande ? 'selected' : ''}>
                    ${demande.description}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <label for="idStatus">Status:</label>
        <select name="idStatus" id="idStatus" required>
            <option value="0">-- Sélectionnez un status --</option>
            <c:forEach var="status" items="${statuses}">
                <option value="${status.idStatus}" ${statusDemande.status != null && statusDemande.status.idStatus == status.idStatus ? 'selected' : ''}>
                    ${status.libelle}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <button type="submit">Enregistrer</button>
    </form>

   <script>
        const demandeSelect = document.getElementById('idDemande');
        const statusSelect = document.getElementById('idStatus');

        const idStatus=statusSelect.value;
        const idDemande=demandeSelect.value;

        const response = await fetch('/forage/statusdemandes/' + idDemande + '/' + idStatus);
        const statusDemande = await response.json();
        console.log(statusDemande);

   </script> 
</body>
</html>