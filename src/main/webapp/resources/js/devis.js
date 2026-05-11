let detailCount = 0;

// Initialiser avec un détail vide
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
    
    const itemNumber = newDetail.querySelector('.itemNumber');
    itemNumber.textContent = detailCount + 1;
    
    const inputs = newDetail.querySelectorAll('input, textarea');
    inputs.forEach(input => {
        if (input.name) {
            // Remplacer l'indice [0] par le nouvel indice
            input.name = input.name.replace(/\[0\]/, '[' + detailCount + ']');
        }
        input.value = '';
        
        // Ajouter l'événement input pour recalculer
        if (input.name && (input.name.includes('quantite') || input.name.includes('prixUnitaire'))) {
            input.addEventListener('input', calculateTotal);
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
        const quantiteInput = item.querySelector('input[name*="quantite"]');
        const prixInput = item.querySelector('input[name*="prixUnitaire"]');
        const montantLigneInput = item.querySelector('.montantLigne');
        
        if (quantiteInput && prixInput && montantLigneInput) {
            const quantite = parseFloat(quantiteInput.value) || 0;
            const prixUnitaire = parseFloat(prixInput.value) || 0;
            const montantLigne = quantite * prixUnitaire;
            
            montantLigneInput.value = montantLigne.toFixed(2);
            total += montantLigne;
        }
    });
    
    const montantInput = document.getElementById('montant');
    if (montantInput) {
        montantInput.value = total.toFixed(2);
    }
    
    const montantTotalSpan = document.getElementById('montantTotal');
    const montantHTSpan = document.getElementById('montantHT');
    if (montantTotalSpan) {
        montantTotalSpan.textContent = formatCurrency(total);
    }
    if (montantHTSpan) {
        montantHTSpan.textContent = formatCurrency(total);
    }
}

function updateSummary() {
    const items = document.querySelectorAll('#detailsList .detail-item:not(.template)');
    const detailCountSpan = document.getElementById('detailCount');
    if (detailCountSpan) {
        detailCountSpan.textContent = items.length;
    }
    calculateTotal();
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('mg-MG', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).format(amount) + ' Ar';
}

// Validation du formulaire
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('devisForm');
    if (form) {
        form.addEventListener('submit', function(e) {
            let isValid = true;
            
            // Vérifier la référence
            const referenceInput = document.getElementById('reference');
            if (referenceInput && !referenceInput.value.trim()) {
                const referenceError = document.getElementById('referenceError');
                if (referenceError) referenceError.style.display = 'block';
                isValid = false;
            } else {
                const referenceError = document.getElementById('referenceError');
                if (referenceError) referenceError.style.display = 'none';
            }
            
            // Vérifier la date
            const dateInput = document.getElementById('dateDevis');
            if (dateInput && !dateInput.value) {
                const dateError = document.getElementById('dateError');
                if (dateError) dateError.style.display = 'block';
                isValid = false;
            } else {
                const dateError = document.getElementById('dateError');
                if (dateError) dateError.style.display = 'none';
            }
            
            // Vérifier que les détails sont remplis
            const items = document.querySelectorAll('#detailsList .detail-item:not(.template)');
            let detailsValid = true;
            
            items.forEach(item => {
                const descriptionInput = item.querySelector('textarea');
                const quantiteInput = item.querySelector('input[name*="quantite"]');
                const prixInput = item.querySelector('input[name*="prixUnitaire"]');
                
                if (descriptionInput && quantiteInput && prixInput) {
                    const description = descriptionInput.value.trim();
                    const quantite = parseFloat(quantiteInput.value);
                    const prixUnitaire = parseFloat(prixInput.value);
                    
                    if (!description || !quantite || !prixUnitaire || quantite <= 0 || prixUnitaire <= 0) {
                        detailsValid = false;
                        item.style.borderColor = '#ff6b6b';
                    } else {
                        item.style.borderColor = '#667eea';
                    }
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
    }
});

// Ajouter des événements de calcul
document.addEventListener('input', function(e) {
    if (e.target.name && (e.target.name.includes('quantite') || e.target.name.includes('prixUnitaire'))) {
        calculateTotal();
    }
});

