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

const map = document.getElementById("carte");
const regions = document.getElementById("valeurs");
const paths = document.querySelectorAll(".carte a");
const links = document.querySelectorAll(".valeurs li a");
/***************************************************PARTIE SURVOLE **********************************************/


//fonction  qui se charge de surligné les listes quand on survole les regions
function addActiveClassReg(id_region,id_gisement){
    document.querySelectorAll('.is-active').forEach((element)=>{
        element.classList.remove('is-active');
    }) //avant d'addibuer la class is-active a un element on va d'abord l'enelever pour tout le monde pour ne pas avoir plusieurs element selectionnés a la fois

    if( id_region !== undefined){
        document.querySelector(".carte #region-" + id_region).classList.add('is-active') ;//selectionnre les elts avec id=region-A
        document.querySelector(".valeurs li #Gisement-" + id_gisement).classList.add('is-active'); ////selectionnre les elts avec class=Gisement-B
    }
}

//fonction  qui se charge de surligné les regions quand on survole les listes
function addActiveClassLien(id_valeur){
    document.querySelectorAll('.is-active').forEach((element)=>{
        element.classList.remove('is-active');
    }) //avant d'addibuer la class is-active a un element on va d'abord l'enelever pour tout le monde pour ne pas avoir plusieurs element selectionnés a la fois

    if( id_valeur !== undefined){
        let regionsAssociées = document.querySelectorAll(".carte .GisReg-" + id_valeur);////selectionnre les elts avec class="GisReg-B"
        regionsAssociées.forEach((region)=>{
            region.classList.add('is-active') ;//pour chaque elt avec class=Gis-Reg-B ajouter class=is-active
        })
        //document.querySelectorAll(".carte .GisReg-" + id_valeur).classList.add('is-active') ;
        document.querySelector(".valeurs li #Gisement-" + id_valeur).classList.add('is-active'); //
    }
}

///survole des regions
paths.forEach((path)=>{
    path.addEventListener('mouseenter' ,function(e){
        let id_region = this.id.replace('region-','');//de base recupere region-A mais avec replace on aura seulement A
        let id_gisement = this.classList.value.replace('GisReg-','');//recuperer val des Gis-Reg pour faire le regroupemnt
        //console.log(id_region , id_gisement)
        addActiveClassReg(id_region,id_gisement);
    } )
})

//survole des liens
links.forEach((link)=>{
    link.addEventListener('mouseenter' ,function(e){
        let id_valeur = this.id.replace('Gisement-','');//de base recupere lien-A mais avec replace on aura seulement A
        addActiveClassLien(id_valeur);
    } )
})

//quitter le survole quand on quitte la map
map.addEventListener('mouseleave',function(e){
    //console.log('you left map')
    addActiveClassReg();
})

//quitter le survole quand on quitte la la liste des regions
regions.addEventListener('mouseleave',function(e){
    //console.log('you left map')
    addActiveClassLien();
})


/******************************************PARTIE CLICK ************************************************************/
const infosbox = document.querySelector('.informations')
paths.forEach((path)=>{
    path.addEventListener('click',function(){
        let id_region = this.id.replace('region-','');
        console.log(id_region)
        const region = RegionInfos.find((elements) => elements.id === id_region) ;
        /*alert('nom: '+ region.nom+ ' gisement: '+ region.gisement);*/
        //effacer le contenue de infoBox avant de ecrire a nuveau
        infosbox.innerHTML = ""

        const Title = document.createElement("h4");
        TitleTxt = document.createTextNode("Résultat");
        Title.appendChild(TitleTxt);

        const nomVille = document.createElement("p")
        nomVilleTxt = document.createTextNode("Nom de la région "+ region.nom)
        nomVille.appendChild(nomVilleTxt);

        const ValVille = document.createElement("p")
        ValVilleTxt = document.createTextNode("Valeur du gisement "+ region.gisement)
        ValVille.appendChild(ValVilleTxt);

        infosbox.appendChild(Title)
        infosbox.appendChild(nomVille)
        infosbox.appendChild(ValVille)



        /*
        console.log(region)
        const emp = [];
        emp.push(region);

        $.ajax({
            url: "readJson.php",
            method: "post",
            data: {tab: JSON.stringify(emp)},
            success: function(res){
                console.log(res);
            }
        }) */


    })
})



const RegionInfos=[
    {
        nom : "Grand Est",
        gisement: '4800',
        id : "A"
    },
    {
        nom : "Nouvelle-Aquitaine",
        gisement:1600,
        id : "B"
    },
    {
        nom : "Auvergne-Rhône-Alpes",
        gisement:1500,
        id : "C"
    },
    {
        nom : "Bourgogne-Franche-Comté",
        gisement:1450,
        id : "D"
    },
    {
        nom : "Bretagne",
        gisement:1450,
        id : "E"
    },
    {
        nom : "Centre-Val de Loire",
        gisement:1450,
        id :"F"
    },
    {
        nom : "Corse",
        gisement:1900,
        id : "G"
    },
    {
        nom : "Île-de-France",
        gisement:1900,
        id : "H"
    },
    {
        nom : "Occitanie",
        gisement:1450,
        id : "I"
    },
    {
        nom : "Hauts-de-France",
        gisement:1250,
        id : "J"
    },
    {
        nom : "Normandie",
        gisement:1750,
        id : "K"
    },
    {
        nom : "Pays de la Loire",
        gisement:1200,
        id :"L"
    },
    {
        nom : "Provence-Alpes-Côte d'Azur",
        gisement: 1900,
        id : "M"
    },
]
