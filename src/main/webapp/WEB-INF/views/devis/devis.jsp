<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ref = "DEV-" + System.currentTimeMillis();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Création de Devis</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/devis.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Création de Devis</h1>
            <p>Remplissez le formulaire ci-dessous pour créer un nouveau devis avec les détails</p>
        </div>
        
        <div class="content">
            <div class="success-message" id="successMessage">
                Devis créé avec succès!
            </div>
            
            <form id="devisForm" method="POST" action="${pageContext.request.contextPath}/devis/create">
                <div class="form-section">
                    <h2>Informations du Devis</h2>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="reference">Référence du Devis <span style="color: red;">*</span></label>
                            <input type="text" id="reference" name="reference" value="<%= ref %>" required placeholder="Ex: DEV-2026-001" readonly>
                            <span class="error-message" id="referenceError">La référence est requise</span>
                        </div>
                        <div class="form-group">
                            <label for="dateDevis">Date du Devis <span style="color: red;">*</span></label>
                            <input type="date" id="dateDevis" name="dateDevis" required>
                            <span class="error-message" id="dateError">La date est requise</span>
                        </div>
                    </div>
                    <input type="hidden" id="idDemande" name="idDemande" value="${demande.getIdDemande()}">
                    
                    <div class="form-row">
                        <!-- <div class="form-group">
                            <label for="idDemande">Demande <span style="color: red;">*</span></label>
                            <select id="idDemande" name="idDemande" required>
                                <option value="">-- Sélectionnez une demande --</option>
                                <option value="1">Demande #001 - Zone A</option>
                                <option value="2">Demande #002 - Zone B</option>
                                <option value="3">Demande #003 - Zone C</option>
                            </select>
                            <span class="error-message" id="demandeError">Veuillez sélectionner une demande</span>
                        </div> -->
                        <div class="form-group">
                            <label for="montant">Montant Total (Calculé automatiquement)</label>
                            <input type="number" id="montant" name="montant" step="0.01" readonly placeholder="0.00">
                        </div>
                    </div>
                </div>
                
                <div class="form-section">
                    <h2>Détails du Devis</h2>
                    
                    <div class="details-container">
                        <div id="detailsList">
                        </div>
                        
                        <div class="detail-item template" id="detailTemplate">
                            <div class="detail-header">
                                <span class="detail-number">Détail #<span class="itemNumber">1</span></span>
                                <button type="button" class="btn-remove" onclick="removeDetail(this)">Supprimer</button>
                            </div>
                            
                            <div class="form-row full">
                                <div class="form-group">
                                    <label for="description">Description <span style="color: red;">*</span></label>
                                    <textarea name="detailDevisList[0].description" placeholder="Décrivez le travail ou le service..."></textarea>
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="quantite">Quantité <span style="color: red;">*</span></label>
                                    <input type="number" name="detailDevisList[0].quantite" min="1" placeholder="0">
                                </div>
                                <div class="form-group">
                                    <label for="prixUnitaire">Prix Unitaire (Ar) <span style="color: red;">*</span></label>
                                    <input type="number" name="detailDevisList[0].prixUnitaire" step="0.01" placeholder="0.00" oninput="calculateTotal()">
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class="form-group">
                                    <label>Montant Ligne</label>
                                    <input type="text" class="montantLigne" readonly placeholder="0.00">
                                </div>
                            </div>
                        </div>
                        
                        <button type="button" class="btn-add-detail" onclick="addDetail()">+ Ajouter un détail</button>
                    </div>
                </div>
                
                <!-- Résumé -->
                <div class="summary">
                    <div class="summary-row">
                        <span>Nombre de détails :</span>
                        <span id="detailCount">0</span>
                    </div>
                    <div class="summary-row">
                        <span>Montant HT :</span>
                        <span id="montantHT">0.00 Ar</span>
                    </div>
                    <div class="summary-row total">
                        <span>Montant Total :</span>
                        <span id="montantTotal">0.00 Ar</span>
                    </div>
                </div>
                
              
                <div class="actions">
                    <button type="submit" class="btn-primary">✓ Créer le Devis</button>
                    <button type="reset" class="btn-secondary">↻ Réinitialiser</button>
                    <a href="${pageContext.request.contextPath}/" class="btn-secondary" style="text-decoration: none; display: inline-flex; align-items: center; justify-content: center;">← Retour</a>
                </div>
            </form>
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/resources/js/devis.js"></script>
</body>
</html>
