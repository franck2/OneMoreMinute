OneMoreMinute
=============

projet web and cloud

I - Principe
------------

  Ce projet a pour but de fournir un réveil connecté intelligent. En effet, après que l'utilisateur ai entré son emploi du temps, son réveil connecté va alors sonné à l'heure la plus tardive possible. Le calcul de cette heure de réveil prend en compte diverses informations fournis par l'utilisateur telles que son lieu de départ et d'arrivée (le réveil calculera l'itinéraire) ou le temps que celui-ci met à se préparer.
  
  L'utilisateur peut alors rester dans son lit un maximum.
  
II - Côté serveur
-----------------

  La partie serveur de l'application doit être déployée via Google App Engine.

III - Configuration du Raspberry Pi (réveil)
--------------------------------------------

  La procédure de configuration du Raspberry Pi servant de Réveil est simple.
  On suppose que votre Raspberry Pi dispose d'un OS Raspbian et d'une connection internet.
  
  Il faut préalablement avoir installé 2 logiciels: firefox et xdotool (pour passer en plein écran).
  
  Pour les installer, exécutez la commande suivante :  
    `sudo apt-get update && sudo apt-get install iceweasel && sudo apt-get install xdotool`

  Ensuite, il faut lancer le script bash suivant à chaque démarrage du Raspberry Pi. Si votre connection n'est pas en ethernet changez eth0 en conséquence.

  `#!/bin/bash
  
  firefox -new-window "http://one-more-minute.appspot.com/ServletReveil?\`ifconfig eth0 | grep -o -E '([[:xdigit:]]{1,2}:){5}[[:xdigit:]]{1,2}'\`" &
  sleep 2
  xdotool key "F11"`

Le reveil reloadera toutes les heures, il sonnera tant que l'utilisateur n'aura pas cliqué sur l'écran tactile. Une vérification de l'heure du reveil et de l'itinéraire est faite tous les matins à 1h00 du matin.
