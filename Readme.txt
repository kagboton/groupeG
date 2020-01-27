Le fichier README

Ce projet contient les micro-services :

- ms_users : pour la gestion des utilisations
- ms_evenements : pour la gestion des évènements
- ms_soirees: pour la gestion des soirées
- ms_authentification: serveur oauth2

Mais aussi :

- Une gateway dans laquelle se trouve les ressources HTML et Js
- Eureka-server : pour la redirection dynamique
- Config-server : qui centralise le paramétrage de chaque service


Avant de lancer l'application, veuillez procéder comme suit :

1. "mvn clean package -DskipTests" à la racine du projet.

2. Toujours à la racine, exécuter "docker-compose up" pour déployer les services.

3. Vérifier que tous les 4 services et la gateway sont enregistrés auprès d'Eureka
   via "localhost:9102".

4. Enfin, naviguer en allant sur "loccalhot:8080".