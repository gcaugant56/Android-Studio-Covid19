## Récuperation du repo

git clone https://github.com/gcaugant56/Android-Studio-Covid19.git

Ouvrir le projet dans Android studio (ne pas l'importé)

## Consignes respectées
- Architecture MVC
- Appels REST
- 2 ecrans, un pour une liste l'autre pour des details
- Affichage d'une liste dans un RecyclerView
- Affichage du détail d'un item de la liste
- Gitflow
- Utilisation de Thread
- Données en cache
---

## Principe de fonctionnement

Au démarrage l'application va demander l'accès a la géolocalisation du téléphone afin de proposer les statistiques du pays ou se trouve l'utilisateur: 

 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Authorize.png) 




Il est possible de validé que la permission a bien été accorder:

 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Validation.png) 



Ensuite l'application va faire un appel serveur (https://covid19api.com/summary).
Le serveur va nous réponse un json, une liste de pays et les statistiques liées au COVID (nombre de mort total, nouveau mort, guéri total, nouveau guéri, contaminé total, et guérie dans la journée).

Nous allons donc arrivé sur cet écran : 

 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Your%20location.png) 

Global stats correspond au statistique  mondial et Your location au statistique  du pays ou se trouve l'utilisateur. Sur l'appuie d'un des élément nous allons être rediriger vers un écran de détail : 


 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Details.png) 


Dans les cas ou nous choissisons globals stats ou un pays ou le drapeau n'est pas renseigné, un logo "générique" est utilisez:


 ![alt text](https://github.com/gcaugant56/Android-Studio-Covid19/blob/master/Global.png) 


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
- Ajout des tests unitaire sur les get/setter des objet de base.
- Ajout test unitaire sur les écran MainActivity (écran principal) et DetailsActivity (écran affichant les détails sur le pays selectionné)
---
