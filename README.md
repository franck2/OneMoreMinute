OneMoreMinute
=============

projet web and cloud

Pour utiliser ce reveil il suffit de lancer le script suivant à chaque démarage d'un raspberry:

#!/bin/bash

firefox -new-window "page.html?`ifconfig eth0 | grep -o -E '([[:xdigit:]]{1,2}:){5}[[:xdigit:]]{1,2}'`" &

sleep 2

xdotool key "F11"

Le reveil reloadera toutes les heures, il sonnera tan que l'utilisateur n'aura pas cliqué sur l'écran tactile. Une vérification de l'heure du reveil et de l'itinéraire est faite tous les matins à 1h00 du matin.