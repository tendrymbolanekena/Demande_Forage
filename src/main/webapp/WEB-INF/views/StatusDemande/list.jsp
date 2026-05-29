<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Status Demandes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/unc/list.css">
</head>
<body class="with-sidebar">
    <div class="page-wrapper">
        <jsp:include page="/WEB-INF/views/shared/sidebar.jsp" />
        <div class="main-content">
            <div class="breadcrumb">
                <a href="${pageContext.request.contextPath}/">Accueil</a> > Liste des status demandes
            </div>
            
            <div class="container">
                <div class="header">
                    <h1>Liste des Status Demandes</h1>
                    <a href="${pageContext.request.contextPath}/statusdemandes/new">+ Nouveau Status</a>
                </div>
                
                <c:if test="${empty statusDemandes}">
                    <div class="empty">
                        <p>Aucun status trouvé. <a href="${pageContext.request.contextPath}/statusdemandes/new">Créer le premier status</a></p>
                    </div>
                </c:if>
                
                <c:if test="${not empty statusDemandes}">
                    <table>
                        <thead>
                            <tr>
                                <th>Demande</th>
                                <th>Status</th>
                                <th>Date Status</th>
                                <th>durée</th>
                                <th>Observations</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="statusDemande" items="${statusDemandes}">
                                <tr>
                                    <td>
                                        <c:if test="${statusDemande.getDemande() != null}">
                                            <strong>${statusDemande.getDemande().getReference()}</strong>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${statusDemande.getStatus() != null}">
                                            ${statusDemande.getStatus().getLibelle()}
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${statusDemande.getDateStatus() != null}">
                                            ${statusDemande.getDateStatus()}
                                        </c:if>
                                        <c:if test="${statusDemande.getDateStatus() == null}">
                                            <em>-</em>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${statusDemande.getCouleur() != null}">
                                            <span class="color-badge" style="background-color: ${statusDemande.getCouleur()};">${statusDemande.getNbJours()}</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty statusDemande.getObservations()}">
                                                ${statusDemande.getObservations()}
                                            </c:when>
                                            <c:otherwise>
                                                <em>-</em>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="actions">
                                            <a href="${pageContext.request.contextPath}/statusdemandes/${statusDemande.getIdStatusDemande()}/edit" class="btn btn-edit">Éditer</a>
                                            <a href="${pageContext.request.contextPath}/statusdemandes/${statusDemande.getIdStatusDemande()}/delete" class="btn btn-delete" onclick="return confirm('Êtes-vous sûr?')">Supprimer</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
