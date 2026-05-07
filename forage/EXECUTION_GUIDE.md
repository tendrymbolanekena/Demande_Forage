# Guide d'Exécution - Forage

## Option 1 : Déployer le WAR sur Tomcat installé localement

### 1. Télécharger et installer Tomcat 10

```bash
# Ubuntu/Debian
sudo apt-get install tomcat10

# Ou télécharger manuellement
wget https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.0/bin/apache-tomcat-10.1.0.tar.gz
tar -xzf apache-tomcat-10.1.0.tar.gz
```

### 2. Compiler le WAR

```bash
cd /path/to/forage
mvn clean package -DskipTests
```

### 3. Déployer

```bash
# Copier le WAR dans le dossier webapps
cp target/forage.war /path/to/tomcat/webapps/

# Démarrer Tomcat
/path/to/tomcat/bin/startup.sh
```

### 4. Accéder à l'application

```
http://localhost:8080/forage/
```

---

## Option 2 : Créer une base de données MySQL et configurer

### 1. Créer la base de données

```bash
mysql -u root -p
```

```sql
CREATE DATABASE IF NOT EXISTS forage;
USE forage;
SOURCE /path/to/table.sql;
```

### 2. Configurer la connexion

Modifiez `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forage
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

### 3. Recompiler et redéployer

```bash
mvn clean package -DskipTests
```

---

## Option 3 : Utiliser un serveur de développement simple

### Alternative : Utiliser python pour servir le WAR

```bash
cd /path/to/forage/target
python3 -m http.server 8080
```

Puis accédez à : `http://localhost:8080/forage.war`

(Note : Cette approche ne fonctionne que pour télécharger le fichier)

---

## Troubleshooting

### 404 Not Found
- ✅ Vérifiez que Tomcat est démarré
- ✅ Vérifiez que le WAR est dans `/webapps/`
- ✅ Attendez quelques secondes après le démarrage de Tomcat pour le déploiement
- ✅ Regardez les logs Tomcat: `/path/to/tomcat/logs/catalina.out`

### Erreur de connexion MySQL
- ✅ Vérifiez que MySQL est en cours d'exécution
- ✅ Vérifiez les identifiants dans `application.properties`
- ✅ Vérifiez que la base de données `forage` existe

### Erreur Spring
- ✅ Vérifiez les logs Tomcat pour plus de détails
- ✅ Vérifiez que les dépendances sont correctement téléchargées: `mvn dependency:tree`

---

## Fichiers importants

- **WAR compilé**: `target/forage.war`
- **Script SQL**: `table.sql`
- **Configuration**: `src/main/resources/application.properties`
- **POM**: `pom.xml`

---

## Étapes rapides (Avec Tomcat installé)

```bash
# 1. Compiler
mvn clean package -DskipTests

# 2. Copier
cp target/forage.war /usr/local/tomcat/webapps/

# 3. Démarrer Tomcat
sudo /usr/local/tomcat/bin/startup.sh

# 4. Accéder
# http://localhost:8080/forage/
```

---

## Support

Pour toute question, consultez:
- [Spring MVC Documentation](https://spring.io/projects/spring-framework)
- [Tomcat Documentation](https://tomcat.apache.org/)
- [Hibernate/JPA Guide](https://hibernate.org/)
