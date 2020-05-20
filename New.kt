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
//      new
//--------------------------
fun new(parametres:List<String>){
    if (parentId != 0){
        // lancement du process new sur l'objet correspondant
        for (item in listeItems){
            when(item){
                is Animal -> {
                    if (parentId == item.id){
                        log("appel new Animal")
                        item.new(parametres)
                        return
                    }
                }
                is Continent -> {
                    if (parentId == item.id){
                        log("appel new Continent")
                        item.new(parametres)
                        return
                    }
                }
                is Individu -> {
                    if (parentId == item.id){
                        log("appel new Individu")
                        item.new(parametres)
                        return
                    }
                }
                is Pays -> {
                    if (parentId == item.id){
                        log("appel new Pays")
                        item.new(parametres)
                        return
                    }
                }
                is Planete -> {
                    if (parentId == item.id){
                        log("appel new Planete")
                        item.new(parametres)
                        return
                    }
                }
                is Univers -> {
                    if (parentId == item.id){
                        log("appel new Univers")
                        item.new(parametres)
                        return
                    }
                }
                else -> {
                    println("New : type inconnu")
                }
            }
        }
    }
    if (parametres.size == 0){
        println("ERREUR : pas assez de parametres")
        return
    }
    val type = parametres[0]
    if (parametres.size < 2){
        println("ERREUR : nombre de parametres incorrect pour la commande : new $type <nom>")
    } else {
        val nom = parametres[1]
        when (type){
            "univers" -> {
                var item = Univers(++nextId,nom, parentId)
                listeItems.add(item)
            }
            else -> {
                println(" ERREUR : impossible de creer un $type a ce niveau")
            }
        }
    }
}