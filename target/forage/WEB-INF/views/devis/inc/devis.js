     let detailCount = 0;
        

        document.addEventListener('DOMContentLoaded', function() {
            addDetail();
            
            // Définir la date d'aujourd'hui par défaut
            const today = new Date().toISOString().split('T')[0];
            document.getElementById('dateDevis').value = today;
        });
        
        function addDetail() {
            const template = document.getElementById('detailTemplate');
            const newDetail = template.cloneNode(true);
            newDetail.classList.remove('template');
            newDetail.id = 'detail-' + detailCount;
            
            // Mettre à jour le numéro
            const itemNumber = newDetail.querySelector('.itemNumber');
            itemNumber.textContent = detailCount + 1;
            
            // Mettre à jour les noms des inputs
            const inputs = newDetail.querySelectorAll('input, textarea');
            inputs.forEach(input => {
                if (input.name) {
                    input.name = input.name.replace(/\[/, '[' + detailCount + ']');
                }
            });
            
            document.getElementById('detailsList').appendChild(newDetail);
            detailCount++;
            updateSummary();
        }
        
        function removeDetail(button) {
            const detail = button.closest('.detail-item');
            const items = document.querySelectorAll('#detailsList .detail-item:not(.template)');
            
            if (items.length === 1) {
                alert('Vous devez avoir au moins un détail dans le devis!');
                return;
            }
            
            detail.remove();
            updateSummary();
        }
        
        function calculateTotal() {
            const items = document.querySelectorAll('#detailsList .detail-item:not(.template)');
            let total = 0;
            
            items.forEach(item => {
                const quantite = parseFloat(item.querySelector('input[name*="quantite"]').value) || 0;
                const prixUnitaire = parseFloat(item.querySelector('input[name*="prixUnitaire"]').value) || 0;
                const montantLigne = quantite * prixUnitaire;
                
                item.querySelector('.montantLigne').value = montantLigne.toFixed(2);
                total += montantLigne;
            });
            
            document.getElementById('montant').value = total.toFixed(2);
            document.getElementById('montantTotal').textContent = formatCurrency(total);
            document.getElementById('montantHT').textContent = formatCurrency(total);
        }
        
        function updateSummary() {
            const items = document.querySelectorAll('#detailsList .detail-item:not(.template)');
            document.getElementById('detailCount').textContent = items.length;
            calculateTotal();
        }
        
        function formatCurrency(amount) {
            return new Intl.NumberFormat('mg-MG', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
            }).format(amount) + ' Ar';
        }
        
        // Validation du formulaire
        document.getElementById('devisForm').addEventListener('submit', function(e) {
            let isValid = true;
            
            // Vérifier la référence
            if (!document.getElementById('reference').value.trim()) {
                document.getElementById('referenceError').style.display = 'block';
                isValid = false;
            } else {
                document.getElementById('referenceError').style.display = 'none';
            }
            
            // Vérifier la demande
            if (!document.getElementById('idDemande').value) {
                document.getElementById('demandeError').style.display = 'block';
                isValid = false;
            } else {
                document.getElementById('demandeError').style.display = 'none';
            }
            
            // Vérifier la date
            if (!document.getElementById('dateDevis').value) {
                document.getElementById('dateError').style.display = 'block';
                isValid = false;
            } else {
                document.getElementById('dateError').style.display = 'none';
            }
            
            // Vérifier que les détails sont remplis
            const items = document.querySelectorAll('#detailsList .detail-item:not(.template)');
            let detailsValid = true;
            
            items.forEach(item => {
                const description = item.querySelector('textarea').value.trim();
                const quantite = parseFloat(item.querySelector('input[name*="quantite"]').value);
                const prixUnitaire = parseFloat(item.querySelector('input[name*="prixUnitaire"]').value);
                
                if (!description || !quantite || !prixUnitaire || quantite <= 0 || prixUnitaire <= 0) {
                    detailsValid = false;
                    item.style.borderColor = '#ff6b6b';
                }
            });
            
            if (!detailsValid) {
                alert('Tous les champs des détails doivent être remplis avec des valeurs valides!');
                isValid = false;
            }
            
            if (!isValid) {
                e.preventDefault();
            }
        });
        
        // Ajouter des événements de calcul
        document.addEventListener('input', function(e) {
            if (e.target.name && (e.target.name.includes('quantite') || e.target.name.includes('prixUnitaire'))) {
                calculateTotal();
            }
        });