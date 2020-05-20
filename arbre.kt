//----------------------------------------------
//
//      arbre.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde

import Monde.Shell.*

data class Noeud(val nom:String, val id:Int, val parent:Int)
var ListeNoeuds = ArrayList<Noeud>()
var level:Int=0
var newLine:Boolean=false

//--------------------------
//      arbre
//--------------------------
fun arbre(){
    var nom:String=""
    var NoeudBase = Noeud("",0,0)

    // on construit la liste des noeuds independament des classes
    for (item in listeItems){
        when (item){
            is Animal -> {
                ListeNoeuds.add(Noeud(item.nom, item.id, item.parentId))
                if (item.id == 0) NoeudBase = Noeud(item.nom, item.id, item.parentId)
            }
            is Continent -> {
                ListeNoeuds.add(Noeud(item.nom, item.id, item.parentId))
                if (item.id == 0) NoeudBase = Noeud(item.nom, item.id, item.parentId)
            }
            is Individu -> {
                ListeNoeuds.add(Noeud(item.nom, item.id, item.parentId))
                if (item.id == 0) NoeudBase = Noeud(item.nom, item.id, item.parentId)
            }
            is Pays -> {
                ListeNoeuds.add(Noeud(item.nom, item.id, item.parentId))
                if (item.id == 0) NoeudBase = Noeud(item.nom, item.id, item.parentId)
            }
            is Planete -> {
                ListeNoeuds.add(Noeud(item.nom, item.id, item.parentId))
                if (item.id == 0) NoeudBase = Noeud(item.nom, item.id, item.parentId)
            }
            is Univers -> {
                ListeNoeuds.add(Noeud(item.nom, item.id, item.parentId))
                if (item.id == 0) NoeudBase = Noeud(item.nom, item.id, item.parentId)
            }
        }
    }
    analyseNoeud(NoeudBase)
}

fun analyseNoeud(noeud:Noeud){
    var sousListeNoeuds = ArrayList<Noeud>()

    level++
    for (item in ListeNoeuds){
        // on construit la sous liste de sfils de ce noeud
        if (item.parent == noeud.id){
            sousListeNoeuds.add(item)
        }
    }
    if (sousListeNoeuds.size == 0){
        // si la sous liste est vide on remonte d'un cran
        println()
        newLine=true
    } else {
        // sinon on analyse les elements de la sous liste
        for (item in sousListeNoeuds){
            afficheNoeud(item)
            analyseNoeud(item)
            ListeNoeuds.remove(item)
        }
    }
    level--
}

fun afficheNoeud(noeud:Noeud){
    if (newLine){
        for (i in 0..(level-2)){
            when (i){
                (level-2) -> {
                    print("      +-->")
                }
                else -> {
                    print("      |   ")
                }
            }
        }
    }
    print("%10s".format(noeud.nom))
    newLine=false
}