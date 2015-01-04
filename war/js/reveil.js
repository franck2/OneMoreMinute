$(function(){
	$("#but").click(function(e){startAlarm(e);});
	$("#body").click(function(e){stopAlarme(e);})
	regleAlarme();
});

setTimeout(function(){window.location.reload();}, 1000*60);

function startAlarm(e){
	$("#myAudio")[0].play();
	if (typeof e != "undefined")
		e.stopPropagation();
	$("body").click(stopAlarme);
}

function stopAlarme(){	
	document.getElementById('maj').value="true";
	document.forms["form"].submit();
}

//jj/mm/aaaa - hh:nn

function regleAlarme(){

	var date_reveil_value = document.getElementById("heure_reveil").value;
	if(date_reveil_value != ""){
		var date_rev = date_reveil_value.split(" - ")[0];
		var heure_rev = date_reveil_value.split(" - ")[1];
		var date_reveil = new Date(date_rev.split("/")[2], date_rev.split("/")[1]-1, date_rev.split("/")[0], heure_rev.split(":")[0], heure_rev.split(":")[1]);
		var now = new Date();
		setTimeout(startAlarm, date_reveil.getTime() - now.getTime());
		$(".alarm").addClass("active");
		if(date_reveil>new Date()){
			$("#countdown").timeTo({
				timeTo : date_reveil,
				theme: "black",
				displayCaptions: true,
				lang: "fr",
				fontSize: 40,
				
				
			});
		}
	}
}


function StartOrStop() {
    var audie = document.getElementById("myAudio");
    //if (!audie.src || audie.src !== audioFile) audie.src = audioFile; // check if there's a src already and if the current src is not the same with the new one, change it. Or don't do anything.
    if (audie.paused == false)
        audie.pause();
    else
        audie.play();
}
