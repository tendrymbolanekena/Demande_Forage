<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Demandes</title>
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
        .header {
            background: linear-gradient(135deg, #dfe1ecff 0%, #eceaeeff 100%);
            color: white;
            padding: 20px;
            border-radius: 10px 10px 0 0;
            margin-bottom: 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header h1 {
            font-size: 2em;
        }
        .header a {
            background: white;
            color: #667eea;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: 0.3s;
        }
        .header a:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        thead {
            background-color: #edeff8ff;
            color: white;
        }
        th {
            padding: 15px;
            text-align: left;
            font-weight: bold;
        }
        td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
        }
        tbody tr:hover {
            background-color: #f0f4ff;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .btn {
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 0.9em;
            display: inline-block;
            text-align: center;
        }
        .btn-view {
            background-color: #e0e2eeff;
            color: white;
        }
        .btn-edit {
            background-color: #f39c12;
            color: white;
        }
        .btn-delete {
            background-color: #e74c3c;
            color: white;
        }
        .btn:hover {
            opacity: 0.9;
        }
        .empty {
            text-align: center;
            padding: 40px;
            color: #666;
        }
        .breadcrumb {
            padding: 10px 20px;
            background-color: #ecf0f1;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .breadcrumb a {
            color: #667eea;
            text-decoration: none;
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="breadcrumb">
        <a href="${pageContext.request.contextPath}/">Accueil</a> > Liste des demandes
    </div>
    
    <div class="container">
        <div class="header">
            <h1> Liste des Demandes</h1>
            <a href="${pageContext.request.contextPath}/demandes/new">+ Créer une demande</a>
        </div>
        
        <c:if test="${empty demandes}">
            <div class="empty">
                <p>Aucune demande trouvée. <a href="${pageContext.request.contextPath}/demandes/new">Créer une première demande</a></p>
            </div>
        </c:if>
        
        <c:if test="${not empty demandes}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Référence</th>
                        <th>Lieu</th>
                        <th>Nom</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="demande" items="${demandes}">
                        <tr>
                            <td>${demande.getIdDemande()}</td>
                            <td><strong>${demande.getReference()}</strong></td>
                            <td>${demande.getLieu()}</td>
                            <td>${demande.getNom()}</td>
                            <td>
                                <fmt:formatDate value="${demande.getDateAsDate()}" pattern="dd/MM/yyyy HH:mm"/>
                            </td>
                            <td>
                                <div class="actions">
                                    <a href="${pageContext.request.contextPath}/demandes/${demande.idDemande}" class="btn btn-view">Voir</a>
                                    <a href="${pageContext.request.contextPath}/demandes/${demande.idDemande}/edit" class="btn btn-edit">Éditer</a>
                                    <a href="${pageContext.request.contextPath}/demandes/${demande.idDemande}/delete" class="btn btn-delete" onclick="return confirm('Êtes-vous sûr?')">Supprimer</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
