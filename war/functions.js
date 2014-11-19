var map;
var panel;
var initialize;
var calculate;
var direction;

initialize = function(){

  panel    = document.getElementById('panel');
  direction = new google.maps.DirectionsRenderer({
	    panel : panel // Dom element pour afficher les instructions d'itinéraire
	  });
};

calculate = function(){
	var itineraire="";
	var test=false;
	var transport;
	var bool=false;
	if(document.getElementById('voiture').checked){
		transport = google.maps.DirectionsTravelMode.DRIVING;
		bool=true;
	}
	else if(document.getElementById('velo').checked){
		transport = google.maps.DirectionsTravelMode.BICYCLING;
		bool=true;
	}
	else if(document.getElementById('pied').checked){
		transport = google.maps.DirectionsTravelMode.WALKING;
		bool=true;
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
					itineraire+=":dist:"+route.distance.text+":findist"+"\n"+":duree:"+route.duration.value+":finduree:"+"\n";
					itineraire +=":iti:";
					for(var i = 0; i<route.steps.length; i++){
						itineraire += route.steps[i].instructions+"\n";
					}
					itineraire+=":finiti:";
					document.getElementById('itineraire').value=itineraire;
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