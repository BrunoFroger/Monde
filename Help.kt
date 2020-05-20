//----------------------------------------------
//
//      help.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------

package Monde


import Monde.Shell.*

//--------------------------
//      help
//--------------------------
fun help(parametre:String){
    println("+======================================================+")
    println("|   Help (commandes disponibles)                       |")
    println("|-----------+------------------------------------------|")
    println("|  commande | usage                                    |")
    println("|-----------+------------------------------------------|")
    if (parametre.length == 0){
        println("|  env      | affiche les varaibles d'environnement    |")
        println("|  clock    | gestion de la fonction de refresh (*)    |")
        println("|  new      | creation d'un nouvel item (*)            |")
        println("|  kill     | destruction d'un nouvel item (*)         |")
        println("|  cd       | change de niveau vers un item (*)        |")
        println("|  clr      | efface l'ecran                           |")
        println("|  save     | sauvegarde d'item dans un fichier (*)    |")
        println("|  load     | chargement d'item depuis un fichier (*)  |")
        println("|  list     | liste tous les item existants            |")
        println("|  arbre    | affiche les item par descendance         |")
        println("|  supervis | affiche la supervision du monde          |")
        println("|  show     | visualise les données d'un objet (*)     |")
        println("|  help     | cette aide                               |")
        println("|  quit     | fin du programme                         |")
    } else {
        var listeParametres = parametre.split(" ")
        var nbParametres = listeParametres.size
        lateinit var p0:String
        lateinit var p1:String
        lateinit var p2:String
        if (nbParametres > 0) p0 = listeParametres[0]
        if (nbParametres > 1) p1 = listeParametres[1]
        if (nbParametres > 2) p2 = listeParametres[2]
        when (nbParametres){
            1 -> {
                if (p0 == "clock") {
                    println("|  clock    | delai <val> : temps en ms entre refresh  |")
                    println("|           | stop : stop clock                        |")
                    println("|           | delai : start clock                      |")                
                }
                if (p0 == "new") {
                    println("|  new      | <type> .. : creation d'un item (*)       |")
                    println("|           | <animal|continent|individu|pays          |")
                    println("|           | |planete|univers>                        |")
                }
                if (p0 == "kill") {
                    println("|  kill     | <type> <nom> >: suppression d'un item    |")
                    println("|           | <animal|continent|individu|pays          |")
                    println("|           | |planete|univers>                        |")
                }
                if (p0 == "cd") {
                    println("|  cd       | <type> <nom>                             |")           
                }
                if (p0 == "save") {
                    println("|  save     | [nom] fichier monde.data par defaut      |")           
                }
                if (p0 == "load") {
                    println("|  load     | [nom] fichier monde.data par defaut      |")           
                }
                if (p0 == "show") {
                    println("|  show     | <type> <nom>                             |")           
                }
            }
            2 -> {
                if (p0 == "new"){
                    if (p1 == "animal"){
                        println("|  new      | animal <nom> <sexe>                      |")
                    } else if (p1 == "continent"){
                        println("|           | continent <nom>                          |")
                    }else if (p1 == "individu"){
                        println("|           | individu <nom> <sexe>                    |")
                    } else if (p1 == "pays"){
                        println("|           | pays <nom>                               |")
                    } else if (p1 == "planete"){
                        println("|           | planete <nom>                            |")
                    }else if (p1 == "univers"){
                        println("|           | univers <nom>                            |")
                    }
                }
            }
        } 
    }
    println("+===========+==========================================+")
    println(" (*) => aide complementaire disponible")
}

