Jb 28/03
+ toute les images sont terminées
+ changement des descriptions objet
+ compteur TourMax mis a 45

Roland 27/03
+ Modification dialogue vétéran de guerre, zombie
+ Ajout de compteur pour la fin de jeu
+ Réduction du tableau de zones dans jeu de 18 -> 16


khamis 26 /03
 * image fin jeu :
     + ajout setNomImage dans Zone.java 
     + Jeu  : changement switch etat de jeu  :  case "Fin" au lieu debloquer fin en ajoutant nouvelle zone ,
          on change seulement nomImage ... (voir commentaire )
          modif methode se deplacer ( condition si fin jeu ) 
     + JeuPanel  : modif methede changerZone
     + panelZone  :  nouvelle methode pour ajouter imageFinJeu ( spéciale pour les GIF)
     + panelCdes :  ajout methode cacherAllCdes 
     


Khamis 24/03

     ATTENTION : 
           Pb des GIF , j'arrive à les afficher mais il reste au premier plan au lieu au fond de l'ecran.
           pour le regler,   j'ai besoins de changer pas mal de la structure de code ....!
           on aura pas assez de temps pour le faire , je propose d'utiliser des simples images...!!!
 modif :      
+ ajout JETER item dans inventaire  : fichier panelInventaire + JeuPanel + panelZone
+ ajout affichage description zone
+ amelioré code panelZone affichage items (nbItems Max  :Constant déclaré dans JeuPanel , java ); pour permettre d'ajouter plusieurs items dans meme zone ) : panelZone / JeuPanel
+ Ajout méthode initAffichageZC  : pour modif  manip graphic de Jeu pour affichage items+ imgzone + et les modifié en jeuPanel 
+ correction de bug 
+ ajout qlq effets graphique sur les btn des commandes 

Roland 21/03
+ Ajout de la Map

jb 21/03
+ refonte des switch zone et item dans jeu.java
+ story de gauche fini
+ story de droite fini mais pas dialogue (+ bug de la pince en meme temps que le veteran)
---------------------------------------------------------------------------
Roland 20/03 
+ Harmonisation des noms de méthode dans la classe Jeu
   - Nom de la méthode getNomImage -> getImage
   - Nom des méthodes afficherDialoguePNJ -> jeuPanel.afficherPensee / jeuPanel.afficherDialoguePNJ

Roland 19/03
+ Modif de la classe jeu pour finir les switches des zones et objets.
   - Les items sont crées lors de l'utilisation de d'autres items ou lors de l'arrivée du joueur dans certaines zones
   - AJout d'un item à donner au vétéran de guerre pour débloquer le parachute.
   - Les dialogues ne se font plus en cliquant sur le PNJ mais à chaque arrivé de zone.


khamis 19/03
+ affiche dialogPNJ complété + ajout aficherPensee 
+ ajout au click sur la zone , INVENTAIRE + MSGBOX doivent  être cachés .. 
+ modif Item.java zone utiliée en STRING ; modif Jeu.java méthode creerItems() / modif JeuPanel.java methode checkItemWithZone() ..


khamis update 18/03
+ ajout affiche message dialogue PNJ à completer  : nv fichier MsgBox.java ; fichier concernés JeuPanel.java / Jeu.java
+ ajout afficher PNJ  : fichiers concernés  : Jeu.java / JeuPanel / PanelZone.java
+ méthode utiliserItem à completer dans Jeu.java ( méthode opérationnelle , il manque d'ajouter que fait l'item dans le jeu ...! ) 

roland update 18/03
+ class jeu :
   finit switch zone Bar, Hotel, Entrée Aéroport, Métro
+ class Zone :
   ajout méthode enleveSortie


jb update 18/03
+ ajout d'un int zoneUtilise dans Item et ajout du parametre dans jeu.java
+ ajout d'un etatItem boolean dans itam
+ creation de tout les personnages et ajout chaque perso dans leur zones
+ setter pnjZone dans zone

------------------------------------------------------------------------------

Khamis update 15/03
+ ajouter la foncionnalité ramasserItem ( recuperer de la zone courante et ajouter à la liste inventaire ) 
   fichiers concernés MAJ( Jeu.java / JeuPanel.java / PanelZone.java / PanelInventaire.java )
+ changement liste item zone en HashMap au lieu arrayList : fichiers modifiés en Zone.java / Jeu.java 


roland update 15/03/2019
+ Changement dans jeu avec ajout des different switch


jb update 13/03
+ PNJ.java completement refait
+ ajout creerPNJ dans jeu.ava
et ajout de la fille avec son image fille.png dans le dossier image

----------------------------------------------------------
jb update 11/03

+ AJOUT DES IMAGE ZONE AVEC NUMERO ET NOM + DIRECTION

+ arraylist pour item
+ changement dans jeu.java, zone.java jeupanel.java, et panelZone.java

+ changement des indices des zones et ajustement des sorties

---------------------------------------------------
jb update 06/03 13:30
+ ajout images zone et image telephone dans folder image

+ ajout dans la class jeu: ajouterItem() dans chaque zone
+ ajout dans la class item: ajout des position et size



-----------------------
jb update 06/03 12:00

+ ajout dans class Jeu:
+ methode créerItem()

+ changement de de la taille du tableau zone 14->15

-------------------------------------------
# JeuJAVA
ProjetJAVA
   
   
   
Compo dossiers jeu :   
    images      
    jeuRIP
           elementsGraophiques
                  JeuPanel.java
                  PanelCdes.java
                  PanelInventaire.java
                  PanelZone.java

           Entites
                  Zone.java
                  Item.java
                  PersoNonJoueur.java

          MainJeu.java 
          Fenetre.java
          Jeu.java
