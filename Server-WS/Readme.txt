1. Mettez le jar mysql connector dans le r�pertoire lib de tomcat

2. Le fichier de persistence est pr�configur�

3. Pour visualiser les donn�es des entit�es  :
http://88.176.4.252:8081/phpMyAdmin
user locusta
pass locusta
bdd locusta
ATTENTION! SI JAMAIS VOUS CHANGEZ LA STRUCTURE DE LA BASE
EN CHANGEANT LES ENTITIES (pas recommand�!), 
PREVENEZ LES AUTRES AVANT !

4. Une fois les projets import�s avec Maven, n'oubliez pas de faire un maven update puis install.
R�cup�rez le jarWithDependencies � mettre dans le r�pertoire easybeans-deploy de TomCat 
(il est dans le projet bean). Quand vous commitez n'oubliez pas de faire un maven clean.

5.Un projet SampleClient vous indiquera comment proc�der du c�t� de android
pour appeler les m�thodes. Vous pouvez l'ex�cuter avec notre ami toto.

------------------------------------------------------------------------------------

Note 1 En principe vous n'avez rien � changer du c�t� des entities.

Note 2 Pour ce qui est de la date de fin sur l'entity Event 
elle est automatiquement calcul�e via le constructeur � une 1h apr�s 
la date de d�but.

Notes 3 Ne faites pas attention si vous retrouvez sur phpMyAdmin une liste d'evenements
dans la table event. Ca n'est pas le cas en vrai ! Mais on va laisser la JPA faire son job ;).

Notes avanc�es :
- Si jamais vous voulez utilisez un serveur mysql local : ne pas oublier 
de modifier le fichier de persitence pour y rentrer votre URL, user et pass 
de votre base de donn�e et de cr�er un utilisateur sur le 
serveur mysql avec comme client localhost (important)

- Attention, si vous utilisez une serveur apache 
(avec wamp par ex) pour mysql par ex en m�me temps que TomCat vous aurez surement
un conflit de port, il vous suffir de changer le port dans l'une des deux configurations.