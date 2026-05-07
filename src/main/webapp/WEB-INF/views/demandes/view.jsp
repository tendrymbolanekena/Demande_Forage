<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails Demande</title>
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
            color: #bec0cbff;
            margin-bottom: 20px;
            text-align: center;
        }
        .info-group {
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 1px solid #eee;
        }
        .info-group:last-child {
            border-bottom: none;
        }
        .label {
            font-weight: bold;
            color: #667eea;
            font-size: 0.9em;
            text-transform: uppercase;
            margin-bottom: 5px;
        }
        .value {
            color: #333;
            font-size: 1.1em;
        }
        .btn-group {
            display: flex;
            gap: 10px;
            justify-content: center;
            margin-top: 30px;
        }
        a {
            padding: 12px 30px;
            border-radius: 5px;
            font-weight: bold;
            text-decoration: none;
            cursor: pointer;
            font-size: 1em;
            transition: 0.3s;
            display: inline-block;
        }
        .btn-edit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .btn-edit:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .btn-delete {
            background-color: #e74c3c;
            color: white;
        }
        .btn-delete:hover {
            background-color: #c0392b;
        }
        .btn-back {
            background-color: #bdc3c7;
            color: white;
        }
        .btn-back:hover {
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
        .status-badge {
            display: inline-block;
            padding: 5px 15px;
            background-color: #27ae60;
            color: white;
            border-radius: 20px;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/">Accueil</a> >
            <a href="${pageContext.request.contextPath}/demandes">Demandes</a> >
            Détails
        </div>
        
        <h1>📝 Détails de la Demande</h1>
        
        <div class="info-group">
            <div class="label">ID Demande</div>
            <div class="value">${demande.getIdDemande()}</div>
        </div>
        
        <div class="info-group">
            <div class="label">Référence</div>
            <div class="value"><strong>${demande.getReference()}</strong></div>
        </div>
        
        <div class="info-group">
            <div class="label">Nom Demandeur</div>
            <div class="value">${demande.getNom()}</div>
        </div>
        
        <div class="info-group">
            <div class="label">Lieu</div>
            <div class="value">${demande.getLieu()}</div>
        </div>
        
        <div class="info-group">
            <div class="label">Date de Création</div>
            <div class="value">
                <fmt:formatDate value="${demande.date}" pattern="dd/MM/yyyy HH:mm:ss"/>
            </div>
        </div>
        
        <c:if test="${not empty demande.statusDemandes}">
            <div class="info-group">
                <div class="label">Statuts</div>
                <c:forEach var="sd" items="${demande.statusDemandes}">
                    <div class="value">
                        <span class="status-badge">${sd.status.libelle}</span>
                        - <fmt:formatDate value="${sd.dateStatus}" pattern="dd/MM/yyyy"/>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/demandes/${demande.idDemande}/edit" class="btn-edit">✏️ Éditer</a>
            <a href="${pageContext.request.contextPath}/demandes/${demande.idDemande}/delete" class="btn-delete" onclick="return confirm('Êtes-vous sûr?')">🗑️ Supprimer</a>
            <a href="${pageContext.request.contextPath}/demandes" class="btn-back">← Retour</a>
        </div>
    </div>
</body>
</html>
