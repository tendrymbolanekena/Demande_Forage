<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forage - Accueil</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #e2e4ecff 0%, #ece8efff 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .container {
            background: white;
            padding: 50px;
            border-radius: 10px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            text-align: center;
            max-width: 600px;
            width: 90%;
        }
        h1 {
            color: #667eea;
            margin-bottom: 20px;
            font-size: 2.5em;
        }
        p {
            color: #666;
            font-size: 1.1em;
            margin-bottom: 30px;
            line-height: 1.6;
        }
        .btn-group {
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }
        a {
            display: inline-block;
            padding: 12px 30px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        a:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .info {
            background: #f0f4ff;
            padding: 20px;
            border-radius: 5px;
            margin-top: 30px;
            border-left: 4px solid #667eea;
        }
        .info p {
            margin: 0;
            font-size: 0.95em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1> Gestion de Forage</h1>
        <p>Bienvenue dans l'application de gestion des demandes de forage</p>
        
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/demandes">Voir les demandes</a>
            <a href="${pageContext.request.contextPath}/demandes/new">Créer une demande</a>
            <a href="${pageContext.request.contextPath}/statusdemandes/new">Voir les statuts des demandes</a>
        </div>
        
        <div class="info">
            <p><strong>Info :</strong> Utilisez le menu ci-dessus pour gérer vos demandes de forage</p>
        </div>
    </div>
</body>
</html>
