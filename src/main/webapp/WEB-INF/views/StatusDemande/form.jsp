<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Status Demande</title>
</head>
<body>
    <h1>Création ou modification de StatusDemande</h1>

    <form action="${pageContext.request.contextPath}/statusdemandes/save" method="post">
        <!-- Input caché indispensable pour savoir si on modifie ou si on crée -->
        <input type="hidden" name="idStatusDemande" id="idStatusDemande" value="${statusDemande.idStatusDemande}">

        <label for="idDemande">Demande :</label>
        <select name="idDemande" id="idDemande" required>
            <option value="0">-- Sélectionnez une demande --</option>
            <c:forEach var="demande" items="${demandes}">
                <option value="${demande.idDemande}" ${statusDemande.demande != null && statusDemande.demande.idDemande == demande.idDemande ? 'selected' : ''}>
                    ${demande.getReference()}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <label for="dateStatus">Date du statut :</label>
        <input type="datetime-local" name="dateStatus" id="dateStatus" value="${statusDemande.getDateStatus() != null ? statusDemande.getDateStatus().toString().replace('T', ' ') : ''}">
        <br><br>

        <label for="observations">Observations :</label>
        <textarea name="observations" id="observations">${statusDemande.getObservations()}</textarea>
        <br><br>

        <label for="idStatus">Statut :</label>
        <select name="idStatus" id="idStatus" required>
            <option value="0">-- Sélectionnez un statut --</option>
            <c:forEach var="status" items="${statuses}">
                <option value="${status.idStatus}" ${statusDemande.status != null && statusDemande.status.idStatus == status.idStatus ? 'selected' : ''}>
                    ${status.getLibelle()}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <button type="submit">Enregistrer</button>
    </form>

    <script>
        const demandeSelect = document.getElementById('idDemande');
        const statusSelect = document.getElementById('idStatus');
        const idStatusDemandeInput = document.getElementById('idStatusDemande');
        const dateStatusInput = document.getElementById('dateStatus');
        const observationsInput = document.getElementById('observations');

        async function verifierStatusDemande() {
            const idDemande = demandeSelect.value;
            const idStatus = statusSelect.value;

            if (idDemande !== "0" && idStatus !== "0") {
                try {
                    const response = await fetch('${pageContext.request.contextPath}/statusdemandes/' + idDemande + '/' + idStatus);
                    if (response.ok) {
                        const data = await response.json(); // Reçoit le tableau JSON []
                        console.log("Données reçues du serveur :", data);

                        if (data) {
                            console.log("Statut Demande existant trouvé ! ID :", data.idStatusDemande);
                            idStatusDemandeInput.value = data.idStatusDemande || "";
                            dateStatusInput.value = data.dateStatus || "";
                            observationsInput.value = data.observations || "";
                        } else {
                            console.log("Aucun historique trouvé. Mode création active.");
                            idStatusDemandeInput.value = "";
                            dateStatusInput.value = "";
                            observationsInput.value = "";
                        }
                    } else if (response.status === 404) {
                        console.log("Aucun statutDemande trouvé pour cette combinaison.");
                        idStatusDemandeInput.value = "";
                    } else {
                        console.error("Erreur serveur : Status " + response.status);
                    }
                } catch (error) {
                    console.error("Erreur lors de l'analyse des données :", error);
                }
            }
        }

        demandeSelect.addEventListener('change', verifierStatusDemande);
        statusSelect.addEventListener('change', verifierStatusDemande);


        verifierStatusDemande();
    </script> 
</body>
</html>