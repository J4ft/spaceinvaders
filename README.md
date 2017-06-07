spaceinvaders
=============

3 éléments importants :
-----------------------

**Vaisseau** : véhicule commandé par le joueur, pouvant se déplacer de droite à gauche et ayant la possibilité de lancer des missiles destinés à détruire le(s) envahisseurs.

**Envahisseur** : Ennemi qui apparaît à l'écran, se déplace automatiquement et qui doit être détruit par un missile lancé depuis le vaisseau du joueur.

**Horde** : Plusieurs lignes d'envahisseurs synchronisées.

**Missile** : projectile envoyé à la verticale par le vaisseau vers l'envahisseur dans le but de le détruire.

**Personnage** : Etre fictif et virtuel, contrôlé ou non par le joueur, qui apparaît dans un jeu vidéo.

**sprite (ou lutin)** : Terme utilisé pour désigner un élément graphique qui peut se déplacer sur l'écran (...) Le fond de l'écran constitue généralement le décor et les sprites sont les personnages et les objets qui se superposent au fond d'écran et qui se déplacent. (Extrait de la rubrique Sprite de Wikipedia).

Contrôles :
-----------

**Aller vers la gauche** : flèche gauche du clavier.

**Aller vers la droite** : flèche droite du clavier.

**Aller vers le haut** : flèche haut du clavier.

**Aller vers le bas** : flèche bas du clavier.

**Tirer missile** : touce espace.

Calcul du score :
-----------------

Le calcul du score est simple, pour chaque envahisseur détruit, 100 points sont attribués au joueur.

Fin du jeu :
------------

Il y a plusieurs possibités pour finir le jeu. Malheureusement, le joueur est destiné à perdre.

Les différentes fins sont :
* La destruction du vaisseau du joueur par un missile ennemi.
* La descente de la horde d'envahisseur jusqu'à la position du vaisseau.
