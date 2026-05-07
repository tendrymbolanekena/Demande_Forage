#!/bin/bash

# ================================

# 🚀 Déploiement Forage sur Tomcat

# ================================

TOMCAT_DIR="/opt/lampp/tomcat"
PROJECT_DIR="$(dirname "$0")"
WAR_NAME="forage.war"

echo "================================"
echo "🚀 Déploiement Forage"
echo "================================"

cd "$PROJECT_DIR"

# 1. Build

echo ""
echo "📦 Compilation..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
echo "❌ Erreur compilation"
exit 1
fi

echo "✅ Build OK"

# 2. Vérifier WAR

if [ ! -f "target/$WAR_NAME" ]; then
echo "❌ WAR introuvable"
exit 1
fi

# 3. Stop Tomcat

echo ""
echo "🛑 Arrêt Tomcat..."
sudo "$TOMCAT_DIR/bin/shutdown.sh"
sleep 5

# 4. Nettoyage

echo "🧹 Nettoyage ancienne version..."
sudo rm -rf "$TOMCAT_DIR/webapps/forage"
sudo rm -f "$TOMCAT_DIR/webapps/$WAR_NAME"

# 5. Déploiement

echo "📂 Copie du WAR..."
sudo cp "target/$WAR_NAME" "$TOMCAT_DIR/webapps/"

# 6. Start Tomcat

echo ""
echo "🚀 Démarrage Tomcat..."
sudo "$TOMCAT_DIR/bin/startup.sh"

echo ""
echo "✅ Déploiement terminé !"
echo "🌐 http://localhost:8080/forage"
echo "================================"
