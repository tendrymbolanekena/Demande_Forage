```php
<?php
$reference = isset($_GET['reference']) ? trim($_GET['reference']) : '';

$demandeData = null;
$error_message = null;

if (!empty($reference)) {
    $apiUrl = "http://localhost:8080/forage/api/consultation?reference=" . urlencode($reference);

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $apiUrl);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_TIMEOUT, 5);

    $response = curl_exec($ch);
    $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    curl_close($ch);

    if ($httpCode === 200) {
        $demandeData = json_decode($response, true);
    } elseif ($httpCode === 404) {
        $error_message = "Aucune demande trouvée avec la référence : <strong>" . htmlspecialchars($reference) . "</strong>";
    } else {
        $error_message = "Erreur de connexion avec le serveur Spring (Code HTTP : $httpCode)";
    }
}
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultation de la Demande</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        h1, h2, h3 {
            color: #4f46e5;
        }

        .search-box {
            margin-bottom: 30px;
            padding: 15px;
            background: #eef2ff;
            border-radius: 6px;
            display: flex;
            gap: 10px;
        }

        .search-box input {
            flex: 1;
            padding: 10px;
            border: 1px solid #cbd5e1;
            border-radius: 4px;
            font-size: 16px;
        }

        .search-box button {
            padding: 10px 20px;
            background: #4f46e5;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        .alert-error {
            padding: 15px;
            background-color: #fee2e2;
            color: #991b1b;
            border-left: 4px solid #dc2626;
            border-radius: 4px;
            margin-bottom: 20px;
        }

        .grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-bottom: 20px;
        }

        .card {
            padding: 15px;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            background: #fafafa;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background: white;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            background-color: #f1f5f9;
        }

        .badge {
            display: inline-block;
            padding: 4px 10px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: bold;
            color: white;
            text-transform: uppercase;
        }

        .badge-green {
            background-color: #22c55e;
        }

        .badge-orange {
            background-color: #f97316;
        }

        .badge-red {
            background-color: #ef4444;
        }

        .badge-blue {
            background-color: #3b82f6;
        }

        .badge-default {
            background-color: #64748b;
        }

        i {
            margin-right: 5px;
        }
    </style>
</head>
<body>

<div class="container">

    <h1><i class="fa-solid fa-magnifying-glass"></i> Suivi de Forage</h1>

    <form method="GET" action="consultation.php" class="search-box">
        <input
            type="text"
            name="reference"
            placeholder="Entrez la référence de la demande"
            value="<?php echo htmlspecialchars($reference); ?>"
            required
        >
        <button type="submit">
            <i class="fa-solid fa-search"></i> Rechercher
        </button>
    </form>

    <?php if ($error_message): ?>
        <div class="alert-error">
            <i class="fa-solid fa-circle-exclamation"></i>
            <?php echo $error_message; ?>
        </div>
    <?php endif; ?>

    <?php if ($demandeData):

        $demande = $demandeData['demande'];
        $historiques = $demandeData['historiquesStatuts'];
        $alertes = $demandeData['alertes'];
        $devis = $demandeData['devis'];
    ?>

        <div class="grid">

            <div class="card">
                <h2><i class="fa-solid fa-clipboard-list"></i> Détails de la Demande</h2>

                <p>
                    <strong><i class="fa-solid fa-hashtag"></i> ID :</strong>
                    <?php echo $demande['idDemande']; ?>
                </p>

                <p>
                    <strong><i class="fa-solid fa-barcode"></i> Référence :</strong>
                    <?php echo htmlspecialchars($demande['reference']); ?>
                </p>

                <p>
                    <strong><i class="fa-solid fa-location-dot"></i> Lieu :</strong>
                    <?php echo htmlspecialchars($demande['lieu']); ?>
                </p>

                <p>
                    <strong><i class="fa-solid fa-calendar-days"></i> Date de création :</strong>
                    <?php echo date('d/m/Y H:i', strtotime($demande['date'])); ?>
                </p>
            </div>

            <div class="card">
                <h2><i class="fa-solid fa-file-invoice-dollar"></i> Informations Devis</h2>

                <?php if ($devis): ?>

                    <p>
                        <strong><i class="fa-solid fa-file-contract"></i> Réf Devis :</strong>
                        <?php echo htmlspecialchars($devis['reference']); ?>
                    </p>

                    <p>
                        <strong><i class="fa-solid fa-flag"></i> Statut :</strong>
                        <?php echo htmlspecialchars($devis['status']); ?>
                    </p>

                    <p>
                        <strong><i class="fa-solid fa-money-bill-wave"></i> Montant Total :</strong>
                        <?php echo number_format($devis['montant'], 2, ',', ' '); ?> €
                    </p>

                <?php else: ?>

                    <p style="color:#64748b;font-style:italic;">
                        <i class="fa-solid fa-circle-info"></i>
                        Aucun devis lié à cette demande pour le moment.
                    </p>

                <?php endif; ?>
            </div>

        </div>

        <h2><i class="fa-solid fa-triangle-exclamation"></i> Alertes de Temps</h2>

        <?php if (!empty($alertes)): ?>

            <table>
                <thead>
                <tr>
                    <th>ID Alerte</th>
                    <th>Date Déclenchement</th>
                    <th>Seuil Max</th>
                    <th>Niveau Critique</th>
                </tr>
                </thead>

                <tbody>

                <?php foreach ($alertes as $alerte):

                    $couleurBase = strtoupper($alerte['parametre']['couleur']);
                    $badgeClass = 'badge-default';

                    if ($couleurBase === 'GREEN') {
                        $badgeClass = 'badge-green';
                    } elseif ($couleurBase === 'ORANGE') {
                        $badgeClass = 'badge-orange';
                    } elseif ($couleurBase === 'RED') {
                        $badgeClass = 'badge-red';
                    } elseif ($couleurBase === 'BLUE') {
                        $badgeClass = 'badge-blue';
                    }
                ?>

                    <tr>
                        <td><?php echo $alerte['idAlerte']; ?></td>

                        <td>
                            <?php echo date('d/m/Y H:i', strtotime($alerte['dateAlerte'])); ?>
                        </td>

                        <td>
                            <?php echo $alerte['parametre']['max']; ?> minutes
                        </td>

                        <td>
                            <span class="badge <?php echo $badgeClass; ?>">
                                <?php echo $couleurBase; ?>
                            </span>
                        </td>
                    </tr>

                <?php endforeach; ?>

                </tbody>
            </table>

        <?php else: ?>

            <p style="color:#22c55e;">
                <i class="fa-solid fa-circle-check"></i>
                Aucune alerte critique hors-délai n'a été constatée pour cette demande.
            </p>

        <?php endif; ?>

        <?php if ($devis && !empty($devis['details'])): ?>

            <h2>
                <i class="fa-solid fa-screwdriver-wrench"></i>
                Détails des Prestations (Devis)
            </h2>

            <table>
                <thead>
                <tr>
                    <th>Description</th>
                    <th>Quantité</th>
                    <th>Prix Unitaire</th>
                    <th>Montant</th>
                </tr>
                </thead>

                <tbody>

                <?php foreach ($devis['details'] as $detail): ?>

                    <tr>
                        <td><?php echo htmlspecialchars($detail['description']); ?></td>

                        <td><?php echo $detail['quantite']; ?></td>

                        <td>
                            <?php echo number_format($detail['prixUnitaire'], 2, ',', ' '); ?> €
                        </td>

                        <td>
                            <strong>
                                <?php echo number_format($detail['montant'], 2, ',', ' '); ?> €
                            </strong>
                        </td>
                    </tr>

                <?php endforeach; ?>

                </tbody>
            </table>

        <?php endif; ?>

    <?php endif; ?>

</div>

</body>
</html>
```
