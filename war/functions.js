var map;
var panel;
var initialize;
var calculate;
var direction;

$(function(){
	$("#but").click(function(e){startAlarm(e);});
	
	regleAlarme();
});

setTimeout(function(){window.location.reload();}, 1000* 60 * 60);

function startAlarm(e){
	$("#myAudio")[0].play();
	if (typeof e != "undefined")
		e.stopPropagation();
	$("body").click(stopAlarme);
}

function stopAlarme(){	
	window.location.reload();
}

//jj/mm/aaaa - hh:nn

function regleAlarme(){
	var date_reveil_value = "17/12/2014 - 17:16";
	var date_rev = date_reveil_value.split(" - ")[0];
	var heure_rev = date_reveil_value.split(" - ")[1];
	
	var date_reveil = new Date(date_rev.split("/")[2], date_rev.split("/")[1]-1, date_rev.split("/")[0], heure_rev.split(":")[0], heure_rev.split(":")[1]);
	var now = new Date();
	console.log(date_reveil, now);
	alert(date_reveil.getTime() - now.getTime());
	setTimeout(startAlarm, date_reveil.getTime() - now.getTime());
}


function StartOrStop() {
    var audie = document.getElementById("myAudio");
    //if (!audie.src || audie.src !== audioFile) audie.src = audioFile; // check if there's a src already and if the current src is not the same with the new one, change it. Or don't do anything.
    if (audie.paused == false)
        audie.pause();
    else
        audie.play();
}


initialize = function(){

  panel    = document.getElementById('panel');
  direction = new google.maps.DirectionsRenderer({
	    panel : panel // Dom element pour afficher les instructions d'itinéraire
	  });
};

calculate = function(){
	var itineraire = "";
	var test = false;
	var transport;
	var bool = false;
	if(document.getElementById('voiture').checked){
		transport = google.maps.DirectionsTravelMode.DRIVING;
		bool = true;
	}
	else if(document.getElementById('velo').checked){
		transport = google.maps.DirectionsTravelMode.BICYCLING;
		bool = true;
	}
	else if(document.getElementById('pied').checked){
		transport = google.maps.DirectionsTravelMode.WALKING;
		bool = true;
	}
	if(bool){
		origin      = document.getElementById('depart').value; // Le point départ
		destination = document.getElementById('arrivee').value; // Le point d'arrivé
		if(origin && destination){
			var request = {
					origin      : origin,
					destination : destination,
					travelMode  : transport // Mode de conduite
			}
			var directionsService = new google.maps.DirectionsService(); // Service de calcul d'itinéraire
			directionsService.route(request, function(response, status){ // Envoie de la requête pour calculer le parcours
				if(status == google.maps.DirectionsStatus.OK){
					var route = response.routes[0].legs[0];
					itineraire += ":dist:" + route.distance.text + ":findist" + "\n" + ":duree:" + route.duration.value + ":finduree:" + "\n";
					itineraire += ":iti:";
					for(var i = 0; i<route.steps.length; i++){
						itineraire += route.steps[i].instructions + "<br/>\n";
					}
					itineraire += ":finiti:";
					document.getElementById('itineraire').value = itineraire;
				}
				else if (status == google.maps.DirectionsStatus.NOT_FOUND){
					itineraire = "ERREUR une des adresses fournis n'est pas valide";
					document.getElementById('itineraire').value = itineraire;
				}
				else if(status == google.maps.DirectionsStatus.ZERO_RESULT){
					itineraire = "ERREUR aucune route trouvée entre ces deux adresses";
					document.getElementById('itineraire').value = itineraire;	
				}
				else{
					itineraire = "ERREUR un probleme est survenu";
					document.getElementById('itineraire').value = itineraire;	
				}
				document.forms["form"].submit();
			});

		}
    }
	else{
		document.forms["form"].submit();
	}
};



//faire une fonction pour récupérer les infos voulues puis envoyer a la jsp 

initialize();

var options = {
  
   componentRestrictions: {country: 'fr'}
};

var depart = document.getElementById('depart');
autocomplete = new google.maps.places.Autocomplete(depart, options);
var arrivee = document.getElementById('arrivee');
autocomplete = new google.maps.places.Autocomplete(arrivee, options);