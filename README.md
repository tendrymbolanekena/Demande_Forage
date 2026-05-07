# FORAGE - Application de Gestion des Demandes de Forage

## Installation et Exécution

### Prérequis
- JDK 17 ou supérieur
- Maven 3.8.0 ou supérieur
- MySQL 8.0
- Tomcat 10.x (optionnel si vous utilisez le plugin Tomcat Maven)

### Étape 1 : Créer la base de données

```bash
mysql -u root -p
```

Puis exécutez le script SQL :

```sql
SOURCE /chemin/vers/table.sql;
```

Ou copiez-collez le contenu du fichier `table.sql` dans MySQL Workbench.

### Étape 2 : Configurer la connexion à la base de données

Modifiez les paramètres de connexion dans :
**`src/main/resources/application.properties`**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forage
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

### Étape 3 : Compiler le projet

```bash
cd /chemin/vers/forage
mvn clean install -DskipTests
```

### Étape 4 : Exécuter l'application

#### Option 1 : Avec le plugin Tomcat Maven (Recommandé pour le développement)

```bash
mvn tomcat7:run
```

L'application sera accessible à l'adresse :
**http://localhost:8080/forage**

#### Option 2 : Avec Tomcat décentralisé

1. Compiler le WAR :
```bash
mvn clean package
```

2. Copier le fichier WAR dans Tomcat :
```bash
cp target/forage.war $CATALINA_HOME/webapps/
```

3. Démarrer Tomcat :
```bash
$CATALINA_HOME/bin/startup.sh
```

L'application sera accessible à :
**http://localhost:8080/forage**

### Étape 5 : Vérifier l'installation

Accédez à :
- **Page d'accueil** : http://localhost:8080/forage/
- **Liste des demandes** : http://localhost:8080/forage/demandes
- **Créer une demande** : http://localhost:8080/forage/demandes/new

## Structure du Projet

```
forage/
├── src/
│   ├── main/
│   │   ├── java/com/example/forage/
│   │   │   ├── config/          # Configuration Spring
│   │   │   ├── controller/      # Controllers MVC
│   │   │   ├── entity/          # Entités JPA
│   │   │   ├── repository/      # Repositories
│   │   │   └── service/         # Services (logique métier)
│   │   ├── resources/
│   │   │   └── application.properties  # Configuration BD
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── views/       # Pages JSP
│   │       └── index.jsp        # Page d'accueil
│   └── test/                    # Tests unitaires
├── table.sql                    # Script de création BD
├── pom.xml                      # Dépendances Maven
└── README.md                    # Ce fichier
```

## Dépendances principales

- **Spring Web MVC** 6.1.8
- **Spring Data JPA** 3.3.0
- **Hibernate ORM** 6.4.4
- **MySQL Connector** 8.0.33
- **Tomcat Maven Plugin** 2.2

## API Endpoints

### Demandes
- `GET /forage/demandes` - Lister toutes les demandes
- `GET /forage/demandes/new` - Formulaire de création
- `POST /forage/demandes` - Créer une demande
- `GET /forage/demandes/{id}` - Détails d'une demande
- `GET /forage/demandes/{id}/edit` - Formulaire d'édition
- `POST /forage/demandes/{id}` - Mettre à jour une demande
- `GET /forage/demandes/{id}/delete` - Supprimer une demande

## Configuration avancée

### Logs Hibernate
Pour voir les requêtes SQL générées, vérifiez le fichier `application.properties` :

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Changer la stratégie DDL
Modifiez dans `application.properties` :

```properties
# create: Crée les tables (supprime l'existant)
# update: Met à jour les tables existantes (RECOMMANDÉ)
# validate: Valide que les tables existent
spring.jpa.hibernate.ddl-auto=update
```

## Troubleshooting

### Erreur : "Cannot find database"
- Vérifiez que MySQL est en cours d'exécution
- Vérifiez les identifiants dans `application.properties`
- Vérifiez que la base de données `forage` existe

### Erreur : "Port 8080 déjà utilisé"
- Changez le port dans `pom.xml` :
```xml
<port>9090</port>
```

### Erreur : "Tomcat not found"
Installez Tomcat Maven plugin :
```bash
mvn tomcat7:deploy
```

## Prochaines étapes

- [ ] Créer les vues JSP (list.jsp, form.jsp, view.jsp)
- [ ] Ajouter la validation des formulaires
- [ ] Implémenter la gestion des statuts de demande
- [ ] Ajouter l'authentification utilisateur
- [ ] Configurer les logs avec SLF4J
- [ ] Ajouter des tests unitaires

## Contact et Support

Pour toute question ou problème, consultez la documentation Spring MVC :
https://spring.io/projects/spring-framework
