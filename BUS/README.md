# BUS APP
## Dispositif de transmission de données partagées entre tous les composants de notre système de drone.

### Contenu du projet
***Src <br>
------Server.java <br>
------AcceptClient.java <br>
------dbConnexion.java <br>
------MD5.java <br>
------IHM_BUS.java <br>
------Log.java <br>
------Client.java <br>
------log.txt <br>
***DB <br>
----DataBase.sql <br>
        
### Utilisation:

- Ajouter au projet les librairies : org.json et JDBC;
- Importer le fichier DataBase.sql pour créer les tables nécessaires;
- Configurer les parametres d'accès à la base dans dbConnexion.java;
- Lancer le serveur : Server.java;
- Pour tester la connexion de plusieurs clients lancer Client.java plusieurs fois, ici par défaut le TCP/IP du serveur est 2000 et l'adresse est "localhost" vous pouvez la changer avec l'adresse d'un hôte distant contenant le serveur;
- Pour tester la communication faire des manipulations via des commandes JSON d'actions "PUT" "GET" dans le Client.java.

@authors: Imane & Ali

