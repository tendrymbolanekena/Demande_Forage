<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<aside class="sidebar">
    <div class="sidebar-header">
        <h2>Forage</h2>
    </div>
    <nav class="sidebar-nav">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/" class="nav-link">
                    <i class="fas fa-home"></i>
                    <span class="label">Accueil</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/demandes" class="nav-link">
                    <i class="fas fa-list"></i>
                    <span class="label">Demandes</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/demandes/new" class="nav-link">
                    <i class="fas fa-plus"></i>
                    <span class="label">Nouvelle Demande</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/statusdemandes" class="nav-link">
                    <i class="fas fa-check-circle"></i>
                    <span class="label">Statuts Demandes</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/statusdemandes/new" class="nav-link">
                    <i class="fas fa-plus-circle"></i>
                    <span class="label">Nouveau Statut</span>
                </a>
            </li>
        </ul>
    </nav>
    <div class="sidebar-footer">
        <p>&copy; 2026 Forage</p>
    </div>
</aside>
