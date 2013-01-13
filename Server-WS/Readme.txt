1. Mettez le jar mysql connector dans le répertoire lib de tomcat

2. Le fichier de persistence est préconfiguré

3. Pour visualiser les données des entitées  :
http://88.176.4.252:8081/phpMyAdmin
user locusta
pass locusta
bdd locusta
ATTENTION! SI JAMAIS VOUS CHANGEZ LA STRUCTURE DE LA BASE
EN CHANGEANT LES ENTITIES (pas recommandé!), 
PREVENEZ LES AUTRES AVANT !

4. Une fois les projets importés avec Maven, n'oubliez pas de faire un maven update puis install.
Récupérez le jarWithDependencies à mettre dans le répertoire easybeans-deploy de TomCat 
(il est dans le projet bean). Quand vous commitez n'oubliez pas de faire un maven clean.

5.Un projet SampleClient vous indiquera comment procéder du côté de android
pour appeler les méthodes. Vous pouvez l'exécuter avec notre ami toto.

------------------------------------------------------------------------------------

Note 1 En principe vous n'avez rien à changer du côté des entities.

Note 2 Pour ce qui est de la date de fin sur l'entity Event 
elle est automatiquement calculée via le constructeur à une 1h après 
la date de début.

Notes 3 Ne faites pas attention si vous retrouvez sur phpMyAdmin une liste d'evenements
dans la table event. Ca n'est pas le cas en vrai ! Mais on va laisser la JPA faire son job ;).

Notes avancées :
- Si jamais vous voulez utilisez un serveur mysql local : ne pas oublier 
de modifier le fichier de persitence pour y rentrer votre URL, user et pass 
de votre base de donnée et de créer un utilisateur sur le 
serveur mysql avec comme client localhost (important)

- Attention, si vous utilisez une serveur apache 
(avec wamp par ex) pour mysql par ex en même temps que TomCat vous aurez surement
un conflit de port, il vous suffir de changer le port dans l'une des deux configurations.