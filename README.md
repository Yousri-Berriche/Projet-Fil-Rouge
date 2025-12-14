#  Geo-Monitor – Fail2Ban IP Geolocation

Geo-Monitor est un **outil Java** qui analyse des logs de sécurité (Fail2Ban), géolocalise les adresses IP détectées et affiche les attaques sur une **carte interactive** via une interface Web (**Leaflet**).

---

## Fonctionnement

1. Lecture d’un fichier de log (`simulation.log`)
2. Extraction des adresses IP
3. Géolocalisation via une API externe
4. Génération d’un fichier `data.json`
5. Affichage des attaques sur une carte OpenStreetMap

---

## Architecture

- **Back-End (Java)**  
  - POO + Design Pattern **Strategy**
  - Lecture des logs, géolocalisation, export JSON

- **Front-End (Web)**  
  - HTML / JavaScript  
  - Leaflet.js pour l’affichage cartographique

---

## Lancer le projet (VS Code)

1. Cloner le dépôt :
https://github.com/Yousri-Berriche/Projet-Fil-Rouge.git

2. Lancer le Back-End :
Ouvrir src/Main.java
Cliquer sur Run

3.Lancer le Front-End :
Ouvrir web/index.html avec Live Server

#Outils utilisés

Java (POO, Strategy Pattern)

Leaflet.js (OpenStreetMap)

JSON Simple

IA :
ChatGPT: réfleion du projet;
Gemini: gestion des erreurs et réflexion sur mon code;
Perplexity: Recherche des lien et site pour les doc et le code;
Mistral: Revu et dernière lecture du projet avant lancement.
