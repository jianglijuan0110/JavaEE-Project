/*!
* Start Bootstrap - Personal v1.0.1 (https://startbootstrap.com/template-overviews/personal)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-personal/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

/*function toggleDepartementNew() {
    var dep = document.getElementById("dep");
    var deptNew = document.getElementById("deptNew");

    if (dep.option.value === "NOUVEAU_DEPARTEMENT") {
        deptNew.style.display = "block";
    } else {
        deptNew.style.display = "none";
    }
}*/


////////////////////////////////////////////////////////////////////////

/*function initMap() {
	var mylatlng = {
	    lat: 46.232192999999995, 
	    lng: 2.209666999999996
	};

	var mapOptions = {
	    center: mylatlng,
	    zoom: 7,
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	};

    //CREER LA MAP
    var map = new google.maps.Map(document.getElementById("map"), mapOptions)

    //DIRECTION
    var directionService = new google.maps.DirectionsService()

    //DIRECTION RENDER
    var directionsDisplay = new google.maps.DirectionsRenderrer()

    //
    directionsDisplay.setMap(map)

    function calculRoute(){
        var request = {
            origin: document.getElementById("from").value,
            destination: document.getElementById("to").value,
            travelMode: google.maps.TravelMode.DRIVVING, //WALKING
            unitSystem: google.maps.UnitSystem.IMPERIAL
        }

        directionService.route(request, (result, status) => {
            if (status == google.maps.DirectionsStatus.OK){
                const output = document.querySelector('#output')
                output.innerHTML = "<div class='alert-info'> FRrom: " 
                + document.getElementById("from").value 
                + ".<br/>To: " 
                + document.getElementById("to").value 
                + ".<br/>Driving distance: " 
                + result.routes[0].legs[0].distance.text 
                + ".<br/>Durée: " 
                + result.routes[0].legs[0].duration.text 
                + ".</div>";

                //display route
                directionsDisplay.setDirections(result);
            } else {
                directionsDisplay.setDirections({routes: []});
                map.setCenter(mylatlng);
                output.innerHTML = "<div class='alert-danger'> Nous ne pouvons trouver la distance. </div>";
            }
        });
    }

    //
    var options = {
        types: ['(cities)']
    }

    var input1 = document.getElementById("from");
    var autoComplete1 = new google.maps.places.AutoComplete(input1, options)

    var input2 = document.getElementById("to");
    var autoComplete2 = new google.maps.places.AutoComplete(input2, options)
}*/

function calculerDistance() {
    // Obtenir les valeurs sélectionnées dans les menus déroulants
    var m1Select = document.getElementById("m1");
    var m2Select = document.getElementById("m2");

    var m1Option = m1Select.options[m1Select.selectedIndex];
    var m2Option = m2Select.options[m2Select.selectedIndex];

    // Extraire les coordonnées des attributs personnalisés
    var lat1 = parseFloat(m1Option.getAttribute("data-lat"));
    var lng1 = parseFloat(m1Option.getAttribute("data-lng"));
    var lat2 = parseFloat(m2Option.getAttribute("data-lat"));
    var lng2 = parseFloat(m2Option.getAttribute("data-lng"));

    // Remplir les champs de coordonnées cachés
    document.getElementById("lat1").value = lat1;
    document.getElementById("lng1").value = lng1;
    document.getElementById("lat2").value = lat2;
    document.getElementById("lng2").value = lng2;

    // Afficher la carte avec les deux points
    initMap(lat1, lng1, lat2, lng2);

    // Empêcher le formulaire de se soumettre normalement
    return false;
}

function initMap(lat1, lng1, lat2, lng2) {
    // Utiliser les coordonnées du centre par défaut si non définies
    var centerLat = lat1 && lat2 ? (lat1 + lat2) / 2 : 46.232192999999995;
    var centerLng = lng1 && lng2 ? (lng1 + lng2) / 2 : 2.209666999999996;

    // Créer la carte avec les deux points
    var map = new google.maps.Map(document.getElementById("map-container"), {
        center: { lat: centerLat, lng: centerLng },
        zoom: 8
    });

    // Ajouter des marqueurs pour les deux points
    if (lat1 && lng1) {
        var marker1 = new google.maps.Marker({
            position: { lat: lat1, lng: lng1 },
            map: map,
            title: 'Monument 1'
        });
    }

    if (lat2 && lng2) {
        var marker2 = new google.maps.Marker({
            position: { lat: lat2, lng: lng2 },
            map: map,
            title: 'Monument 2'
        });
    }

    // Calculer et afficher la distance entre les deux points (utilisez votre fonction de calcul de distance)
    if (lat1 && lng1 && lat2 && lng2) {
        var distance = calculDistance(lat1, lng1, lat2, lng2);
        console.log('Distance entre les deux monuments : ' + distance + ' km');
        // Mettre à jour la div "display result"
        var displayResultDiv = document.getElementById("display-result");
        displayResultDiv.innerHTML = '<p>Distance entre Monument 1 et Monument 2 : ' + distance + ' kilomètres</p>';
    }

}

// Fonction de calcul de distance
function calculDistance(lat1, lng1, lat2, lng2) {
    // Convertir les degrés en radians
    lat1 = degresEnRadians(lat1);
    lng1 = degresEnRadians(lng1);
    lat2 = degresEnRadians(lat2);
    lng2 = degresEnRadians(lng2);

    // Différences de coordonnées
    var dLat = lat2 - lat1;
    var dLng = lng2 - lng1;

    // Formule Haversine
    var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(lat1) * Math.cos(lat2) *
            Math.sin(dLng / 2) * Math.sin(dLng / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    // Rayon de la Terre en kilomètres (approximatif)
    var rayonTerre = 6371;

    // Distance en kilomètres
    var distance = rayonTerre * c;

    return distance;
}

// Fonction utilitaire pour convertir les degrés en radians
function degresEnRadians(degrees) {
    return degrees * Math.PI / 180;
}