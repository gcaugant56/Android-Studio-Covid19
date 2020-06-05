## Récuperation du repo

git clone https://github.com/gcaugant56/Android-Studio-Covid19.git

Ouvrir le projet dans Android studio (ne pas l'importé)

## Consignes respectées
- Architecture MVC
- Appels REST
- 2 écrans, un pour une liste l'autre pour des détails
- Affichage d'une liste dans un RecyclerView
- Affichage du détail d'un item de la liste
- Gitflow
- Utilisation de Thread
- Données en cache
- Tentative d'intégration continue
- Test unitaire
---

## Principe de fonctionnement

Au démarrage l'application va demander l'accès à la géolocalisation du téléphone afin de proposer les statistiques du pays ou se trouve l'utilisateur: 

 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Authorize.png) 




Il est possible de valider que la permission a bien été accorder:

 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Validation.png) 



Ensuite l'application va faire un appel serveur (https://covid19api.com/summary).
Le serveur va nous réponse un json, une liste de pays et les statistiques liées au COVID (nombre de mort totaux, nouveau mort, guéri total, nouveau guéri, contaminé total, et guérie dans la journée).

Nous allons donc arriver sur cet écran : 

 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Your%20location.png) 

Global stats correspond aux statistiques  mondials et Your location aux statistiques  du pays ou se trouve l'utilisateur. Sur l'appui d'un des éléments nous allons être rédiriger vers un écran de détail : 


 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Details.png) 


Dans les cas ou nous choissisons globals stats ou un pays ou le drapeau n'est pas renseigné, un logo "générique" est utilisez:


 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Global.png) 


---
## Test unitaires
 Des tests unitaire ont été réaliser afin de validé le bon fonctionnement d'une partie des fonctionnalités du code, notamment des objets utilisés pendant la déserialization et la création des différents vues

 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/test%20unitaire.png) 
---

## Intégration continue
 Une tentative d'intégration continue a été réalisé, le but étant d'éviter toute régression lors de l'avancement du projet:
 
  ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Integration%20continu.png) 
  
  
 Cette tentative a été réaliser avec l'outil "Workflow" de git autrement dit dans ce cas a chaque push(master/develop) , ou   sur chaque pull requests (master/develop) le code est compilé

 

---
## Version

1.00 :
- Appel de l'API COVID-19 (https://covid19api.com/summary)
- Création de liste des pays
- Création de l'écran detaillant l'état des pays
- Ajout du global stats, qui récupere les stats du COVIS-19 a l'échelle mondial
- Ajout des drapeaux pour chaque pays

2.00 : 
- Ajout de la géolocalisation de l'utilisateur
- Ajout de la case "Your location" permettant d'afficher les statistiques du COVID-19 dans le pays dans lequel ce trouve l'utilisateur
- Ajout des tests unitaire sur les get/setter des objets de base.
- Ajout test unitaire sur les écrans MainActivity (écran principal) et DetailsActivity (écran affichant les détails sur le pays selectionné)
---
