<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ref = "DMD-" + System.currentTimeMillis(); %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Formulaire Demande</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #667eea;
            margin-bottom: 20px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        input[type="date"],
        input[type="datetime-local"],
        textarea,
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-family: Arial, sans-serif;
            font-size: 1em;
        }

        input:focus,
        textarea:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 5px rgba(102, 126, 234, 0.3);
        }

        textarea {
            resize: vertical;
            min-height: 80px;
        }

        .btn-group {
            display: flex;
            gap: 10px;
            justify-content: center;
            margin-top: 30px;
        }

        button {
            padding: 12px 30px;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            font-size: 1em;
            transition: 0.3s;
        }

        .btn-submit {
            background: linear-gradient(135deg, #1a40e7ff 0%, #1c9bc6ff 100%);
            color: white;
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .btn-cancel {
            background-color: #bdc3c7;
            color: white;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }

        .btn-cancel:hover {
            background-color: #95a5a6;
        }

        .breadcrumb {
            padding: 10px 0;
            margin-bottom: 20px;
        }

        .breadcrumb a {
            color: #667eea;
            text-decoration: none;
            margin-right: 10px;
        }

        .info {
            background: #f0f4ff;
            padding: 15px;
            border-left: 4px solid #667eea;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 0.9em;
            color: #555;
        }

        .suggestions {
            width: 100%;
            background: white;
            border: 1px solid #ddd;
            border-top: none;
            border-radius: 0 0 8px 8px;
            max-height: 220px;
            overflow-y: auto;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.08);
            margin-top: -2px;
            position: relative;
            z-index: 1000;
        }

        .suggestion-item {
            padding: 12px 15px;
            cursor: pointer;
            transition: background 0.2s ease;
            border-bottom: 1px solid #f1f1f1;
            font-size: 14px;
            color: #333;
        }

        .suggestion-item:last-child {
            border-bottom: none;
        }

        .suggestion-item:hover {
            background: #f0f4ff;
            color: #1a40e7;
        }

        .suggestion-item:active {
            background: #dbe4ff;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/">Accueil</a> >
            <a href="${pageContext.request.contextPath}/demandes">Demandes</a> >
            <c:if test="${demande.idDemande != null}">
                Éditer
            </c:if>
            <c:if test="${demande.idDemande == null}">
                Créer
            </c:if>
        </div>

        <h1>
            <c:if test="${demande.idDemande != null}">
                Éditer une Demande
            </c:if>
            <c:if test="${demande.idDemande == null}">
                Créer une Demande
            </c:if>
        </h1>

        <div class="info">
            <c:if test="${demande.getIdDemande() == null}">
                Remplissez le formulaire ci-dessous pour créer une nouvelle demande de forage.
            </c:if>
            <c:if test="${demande.getIdDemande() != null}">
                Modifiez les informations ci-dessous pour mettre à jour la demande.
            </c:if>
        </div>

        <c:if test="${demande.getIdDemande() != null}">
            <form action="${pageContext.request.contextPath}/demandes/${demande.getIdDemande()}" method="post">
        </c:if>
        <c:if test="${demande.getIdDemande() == null}">
            <form action="${pageContext.request.contextPath}/demandes/creer" method="post">
        </c:if>

        <div class="form-group">
            <label for="idUser">Utilisateur *</label>
            <select id="idUser" name="idUser" required>
                <option value="">-- Sélectionnez un utilisateur --</option>
                <c:forEach var="user" items="${users}">
                    <option value="${user.getIdUser()}" <c:if
                        test="${demande.getUser() != null && demande.getUser().getIdUser() == user.getIdUser()}">
                        selected</c:if>>
                        ${user.getUsername()}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="reference">Référence *</label>
            <input type="text" id="reference" name="reference" value="<%= ref %>" required
                placeholder="Ex: DMD-001" readonly>
        </div>

        <div class="form-group">
            <label for="date">Date de creation</label>
            <input type="datetime-local" id="date" name="date" value="${demande.getDate()}" required
                placeholder="Ex: Jean Dupont">
        </div>

        <div class="form-group">
            <label for="lieu">Lieu *</label>
            <input type="text" id="lieu" name="lieu" value="${demande.getLieu()}" required placeholder="Ex: Région A">
        </div>

        <div class="form-group">
            <label for="commune">Commune *</label>

            <input type="text" id="commune" autocomplete="off" placeholder="Tapez une commune..." value="${demande.getCommune() != null ? demande.getCommune().getNom() : ''}" required>

            <input type="hidden" id="idCommune" name="idCommune">

            <div id="suggestions" class="suggestions"></div>
        </div>

        <div class="btn-group">
            <button type="submit" class="btn-submit">
                <c:if test="${demande.idDemande != null}">Mettre à jour</c:if>
                <c:if test="${demande.idDemande == null}">Créer</c:if>
            </button>
            <a href="${pageContext.request.contextPath}/demandes" class="btn-cancel">Annuler</a>
        </div>
        </form>
    </div>

    <script>

        const input = document.getElementById("commune");
        const hiddenId = document.getElementById("idCommune");
        const suggestions = document.getElementById("suggestions");

        input.addEventListener("input", async function () {

            const value = input.value;

            suggestions.innerHTML = "";

            if (value.length < 1) {
                return;
            }

            const response = await fetch(
                "/forage/search/communes?nom=" + encodeURIComponent(value)
            );

            const communes = await response.json();

            communes.forEach(commune => {

                const div = document.createElement("div");

                div.classList.add("suggestion-item");

                div.textContent = commune.nom;

                div.addEventListener("click", function () {

                    input.value = commune.nom;

                    hiddenId.value = commune.idCommune;

                    suggestions.innerHTML = "";
                });

                suggestions.appendChild(div);
            });
        });
    </script>
</body>

</html>