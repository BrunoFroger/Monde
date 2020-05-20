//----------------------------------------------
//
//      kill.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------

package Monde


import Monde.Shell.*



//--------------------------
//      kill
//--------------------------
fun kill(parametres:List<String>){
    if (parametres.size == 0){
        println("ERREUR : pas assez de parametres")
        return
    }
    val type = parametres[0]
    if (parametres.size != 2){
        println("ERREUR : nombre de parametres incorrect pour la commande : kill $type <nom>")
    } else {
        val nom = parametres[1]
        log("on essaie de supprimer $nom de type $type")
        for (item in listeItems){
            when (item){
                is Animal -> {
                    if (item.nom == nom){
                        log("on supprime ce $type ($nom)")
                        listeItems.remove(item)
                        return
                    }
                }
                is Continent -> {
                    if (item.nom == nom){
                        log("on supprime ce $type ($nom)")
                        listeItems.remove(item)
                        return
                    }
                }
                is Individu -> {
                    if (item.nom == nom){
                        log("on supprime ce $type ($nom)")
                        listeItems.remove(item)
                        return
                    }
                }
                is Pays -> {
                    if (item.nom == nom){
                        log("on supprime ce $type ($nom)")
                        listeItems.remove(item)
                        return
                    }
                }
                is Planete -> {
                    if (item.nom == nom){
                        log("on supprime ce $type ($nom)")
                        listeItems.remove(item)
                        return
                    }
                }
                is Univers -> {
                    if (item.nom == nom){
                        log("on supprime ce $type ($nom)")
                        listeItems.remove(item)
                        return
                    }
                }
                else -> println("impossible de supprimer un " + type)
            }
        }
        println("$type ($nom) non trouve")
    }
}