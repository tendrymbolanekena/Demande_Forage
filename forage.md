# FORAGE - Gestion des Demandes de Forage

## Architecture et Configuration

### 1. Configuration Base de Données

#### Fichiers de Configuration
- **[application.properties](src/main/resources/application.properties)** - Configuration connexion MySQL
  - URL: `jdbc:mysql://localhost:3306/forage`
  - Utilisateur: `root`
  - Mot de passe: (vide par défaut)
  - Dialecte: `MySQL8Dialect`
  - DDL: `update` (crée/met à jour les tables)

- **[DatabaseConfig.java](src/main/java/com/example/forage/config/DatabaseConfig.java)** - Configuration Spring JPA
  - Configuration EntityManagerFactory
  - Configuration TransactionManager
  - Activation des Repositories JPA

### 2. Entités (Entities)

- **[Demande.java](src/main/java/com/example/forage/entity/Demande.java)**
  - idDemande (PK, AUTO_INCREMENT)
  - reference (UNIQUE)
  - lieu
  - nom
  - date (DATETIME)
  - Relation: OneToMany avec StatusDemande

- **[Status.java](src/main/java/com/example/forage/entity/Status.java)**
  - idStatus (PK, AUTO_INCREMENT)
  - libelle (UNIQUE)
  - Relation: OneToMany avec StatusDemande

- **[StatusDemande.java](src/main/java/com/example/forage/entity/StatusDemande.java)**
  - idStatusDemande (PK, AUTO_INCREMENT)
  - idDemande (FK)
  - idStatus (FK)
  - dateStatus (DATETIME)
  - Relations: ManyToOne vers Demande et Status

### 3. Repositories (Accès aux données)

- **[DemandeRepository.java](src/main/java/com/example/forage/repository/DemandeRepository.java)**
  - `findByReference(String)` - Rechercher par référence
  - `existsByReference(String)` - Vérifier si référence existe

- **[StatusRepository.java](src/main/java/com/example/forage/repository/StatusRepository.java)**
  - `findByLibelle(String)` - Rechercher par libellé

- **[StatusDemandeRepository.java](src/main/java/com/example/forage/repository/StatusDemandeRepository.java)**
  - `findByDemande(Demande)` - Récupérer statuts d'une demande

### 4. Services (Logique métier)

- **[DemandeService.java](src/main/java/com/example/forage/service/DemandeService.java)**
  - creerDemande()
  - obtenirToutesDemandes()
  - obtenirDemandeParId()
  - obtenirDemandeParReference()
  - mettreAJourDemande()
  - supprimerDemande()

- **[StatusService.java](src/main/java/com/example/forage/service/StatusService.java)**
  - Gestion complète des statuts (CRUD)

- **[StatusDemandeService.java](src/main/java/com/example/forage/service/StatusDemandeService.java)**
  - ajouterStatusAuDemande()
  - obtenirStatusParDemande()
  - Gestion des transitions de statuts

### 5. Script Base de Données

**[table.sql](table.sql)** - Script SQL contenant:
- Création table Demande
- Création table Status
- Création table Status_Demande (association)
- Index pour optimisation
- Données de test

### 6. Dépendances Maven

Les dépendances suivantes sont configurées dans [pom.xml](pom.xml):
- Spring Data JPA 3.3.0
- Spring ORM 6.1.8
- Hibernate ORM 6.4.4
- MySQL Connector 8.0.33
- Jakarta Persistence API 3.1.0

## Déroulement de Demande
- Creation de demande
- Creation de devis (etude)
- Une fois devis accepte -> On commence
- Forage
- Cloture
/opt/lampp/apache-tomcat-10.0.16)
## Prochaines Étapes
- ✅ Créer entités (Demande, Status, StatusDemande)
- ✅ Créer Repositories
- ✅ Créer Services
- ✅ Configurer base de données
- ⏳ Créer Controllers
- ⏳ Créer JSP/Vues
- ⏳ Formulaires de création/édition