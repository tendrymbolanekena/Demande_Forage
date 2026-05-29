# Guide d'intégration de la Sidebar

## Structure créée

J'ai créé une sidebar réutilisable pour votre application Spring MVC. Voici les fichiers créés:

### 1. Fichiers de la Sidebar

- **[shared/sidebar.jsp](WEB-INF/views/shared/sidebar.jsp)** - Le composant sidebar avec les liens de navigation
- **[shared/layout.jsp](WEB-INF/views/shared/layout.jsp)** - Layout principal (optionnel)
- **[resources/css/sidebar.css](resources/css/sidebar.css)** - Styles CSS pour la sidebar

### 2. Pages mises à jour

- **[home.jsp](WEB-INF/views/home.jsp)** - Page d'accueil avec sidebar intégrée
- **[demandes/form.jsp](WEB-INF/views/demandes/form.jsp)** - Formulaire demande avec sidebar intégrée

## Comment intégrer la sidebar dans une page existante

### Étape 1: Ajouter le lien CSS
Ajoutez cette ligne dans le `<head>`:
```html
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidebar.css">
```

### Étape 2: Structurer la page avec la sidebar
Remplacez votre structure existante par:

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ma Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidebar.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        html, body {
            height: 100%;
        }
        body {
            font-family: Arial, sans-serif;
            display: flex;
        }
        .page-wrapper {
            display: flex;
            width: 100%;
            min-height: 100vh;
        }
        .main-content {
            flex: 1;
            padding: 30px;
            overflow-y: auto;
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <div class="page-wrapper">
        <!-- Inclusion de la sidebar -->
        <jsp:include page="/WEB-INF/views/shared/sidebar.jsp" />
        
        <!-- Contenu principal -->
        <div class="main-content">
            <!-- Votre contenu ici -->
            <div class="container">
                <h1>Titre de votre page</h1>
                <!-- ... le reste du contenu ... -->
            </div>
        </div>
    </div>
</body>
</html>
```

## Fonctionnalités de la Sidebar

✅ Navigation responsive (Desktop, Tablette, Mobile)
✅ Menu avec icônes et labels
✅ Lien vers: Accueil, Demandes, Nouvelle Demande, Devis, Statuts Demandes
✅ Gradient violet moderne
✅ Transitions fluides au survol
✅ Footer avec copyright

## Personnalisation

### Modifier les couleurs
Éditez [resources/css/sidebar.css](resources/css/sidebar.css):
```css
.sidebar {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
```

### Ajouter ou modifier les liens
Éditez [shared/sidebar.jsp](WEB-INF/views/shared/sidebar.jsp):
```html
<li>
    <a href="${pageContext.request.contextPath}/votre-lien" class="nav-link">
        <span class="icon">🎯</span>
        <span class="label">Mon lien</span>
    </a>
</li>
```

## Intégrer dans les autres pages

Appliquez le même modèle aux fichiers:
- `demandes/list.jsp`
- `demandes/view.jsp`
- `devis/devis.jsp`
- `StatusDemande/form.jsp`

Toutes les pages auront une navigation cohérente et accessible!
