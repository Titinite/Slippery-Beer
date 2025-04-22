# Slippery Beer
 
Ce projet est un mini-jeu semblable au célèbre jeu du dinosaure de Google Chrome, disponible lorsque nous n'avons plus de connexion à Internet. Cette version apporte un thème de bar avec une bière qui glisse sur le comptoir et qui doit éviter les différents obstacles (des contenants comme des verres) pour aller le plus loin possible.
Nous avons réalisé ce petit jeu à 2 en 4 jours en utilisant Java.
 
 
 ## Table des Matières
 1. [Fonctionnalités](#fonctionnalités)
 2. [Installation](#installation)
 3. [Structure du Projet](#structure-du-projet)
 4. [Technologies](#technologies)
 5. [Auteurs](#auteurs)
 
 
 ## Fonctionnalités
 
 - **Sauter** : le seul mouvement possible est le saut (avec la touche ESPACE)
 - **Score** : disponible en haut à droite de l'écran de la partie
 - **Game Over** : écran de fin de partie en cas de collision avec un obstacle
   
 
 ## Installation
 
 1. Ouvrir le projet dans Intellij ou VSCode (ou autre)
 2. Accéder au Gradle (icône d'éléphant sur Intellij)
 3. Démarrer le projet en cliquant sur `Tasks > Application > Run`
 
 
 ## Structure du Projet

 - `entities` : Contient toutes les entités utilisées (Player, Ennemy)
 - `game` : Contient la scène principale et les classes qui en découlent
 - `screens` : Contient les autres scènes du jeu (Game Over)
 - `utils` : Contient les constantes centralisées utilisées dans le projet
 - `test` : Contient les différents tests de notre jeu (uniquement disponible dans la branche `tests`)
 
 
 ## Technologies
 
 - **Java** : unique langage utilisé pour le projet
 
 
 ## Auteurs
 Développé par [Matheo-dlvt](https://github.com/Matheo-dlvt) (Mathéo DELVERT) et [Titinite](https://github.com/Titinite) (Thibault LERAY). Retrouvez plus de détails dans le dépôt [GitHub](https://github.com/Titinite/Slippery-Beer).
