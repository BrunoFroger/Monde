//----------------------------------------------
//
//      Supervision.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde


import Monde.Shell.*


fun supervision(){
    // clear the screen
    print("\u001b[H\u001b[2J")
    println("+-----------------------------------------+")
    println("|      supervision du monde               |")
    println("+-----------------------------------------+")
    println("|  variables d'environnement              |")
    println("+--------------+--------------------------+")
    println("|  Variables   |   Valeur                 |")
    println("+--------------+--------------------------+")
    println("|   nextId     |   %3d                    |".format(nextId))
    println("|   parentId   |   %3d                    |".format(parentId))
    println("|  clock delai |   %5d                  |".format(clock.delai))
    println("+--------------+--------------------------+-----------+")
    println("|  liste des items                                    |")
    println("+------------+------------+-----+--------+-----+------+")
    println("| type       |  nom       | id  | parent | age | sexe |")
    println("+------------+------------+-----+--------+-----+------+")
    for (item in listeItems){
        var sexeString:String=""
        when (item){
            is Animal ->{
                if (item.sexe == Sexe.MALE){
                    sexeString="male"
                }
                else{
                    sexeString="feml"
                }
                println("| %10s | %10s | %3d |  %3d   | %3d | %4s |"
                    .format(item.type, item.nom, item.id, 
                    item.parentId, item.age, sexeString))
            }
            is Individu ->{
                if (item.sexe == Sexe.MALE){
                    sexeString="M"
                }
                else{
                    sexeString="F"
                }
                println("| %10s | %10s | %3d |  %3d   | %3d | %4s |"
                    .format(item.type, item.nom, item.id, 
                    item.parentId, item.age, sexeString))
            }
            is Continent ->{
                sexeString=""
                println("| %10s | %10s | %3d |  %3d   | %3d | %4s |"
                    .format(item.type, item.nom, item.id, 
                    item.parentId, item.age, sexeString))
            } 
            is Pays ->{
                sexeString=""
                println("| %10s | %10s | %3d |  %3d   | %3d | %4s |"
                    .format(item.type, item.nom, item.id, 
                    item.parentId, item.age, sexeString))
            } 
            is Planete ->{
                sexeString=""
                println("| %10s | %10s | %3d |  %3d   | %3d | %4s |"
                    .format(item.type, item.nom, item.id, 
                    item.parentId, item.age, sexeString))
            } 
            is Univers ->{
                sexeString=""
                println("| %10s | %10s | %3d |  %3d   | %3d | %4s |"
                    .format(item.type, item.nom, item.id, 
                    item.parentId, item.age, sexeString))
            } 
        }
    }
    println("+------------+------------+-----+--------+-----+------+")
    println("arborescence des objets")
    arbre()
}
