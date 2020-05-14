//----------------------------------------------
//
//      Main.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde

import Monde.Univers.*

class Monde{  
    var listeItems = ArrayList<Any>()
    var nom:String = "mon monde"

    init{
        println("+=====================+")
        println("|   debut du monde    |")
        println("+---------------------+")
    }

}

var prompt:String = ""
var monde = Monde()?
var node:Any?

fun main(args: Array<String>){

    node = monde
    println(monde.nom)
    println(node.nom)

    // Lancement du shell de pilotage
    shell()
}

private fun shell(){
    var commande:String
    var result = 1
    while (result != 0){
        displayPrompt()
        commande = getCommande()
        result = executeCommande(commande)
    }
} 


private fun displayPrompt(){
    print(prompt + "> ")
}

private fun getCommande():String{
    return readLine()!!
}

private fun executeCommande(commande:String):Int{
    var tmp:String
    if (commande.equals("quit")) return 0
    if (commande.equals("")) return 1
    if (commande.equals("list")) {
        //list()
        return 1
    }
    if (commande.startsWith("cd")){
        tmp = commande.substring(3)
        var listeParametres:List<String> = tmp.split(" ")
        println("acces a l'objet " + listeParametres.toString())
        //cd(listeParametres)
        return 1
    }
    if (commande.startsWith("new")){
        tmp = commande.substring(4)
        var listeParametres:List<String> = tmp.split(" ")
        println("creation d'un objet " + listeParametres.toString())
        //new(listeParametres)
        return 1
    }
    println("commande " + commande + " inconnue")
    return 1
}


private fun new(parametres:List<String>){
    when (parametres[0]){
        "univers" -> {
            var univers = Univers()
            univers.nomUnivers = parametres[1]
            //node.listeItems.add(univers)
        }
        else -> {
            println(" ERREUR : impossible de creer un " + parametres[0] + " a ce niveau")
        }
    }
}
/*
private fun cd(parametres:List<String>){
    println("fonction cd")
    if (parametres.size == 2){
        when (parametres[0]){
            "univers" -> {
                val nom :String = parametres[1]
                println("acces a l'univers " + nom)
                for (item in node.listeItems){
                    if (item.nom == nom){
                        node = item
                    }
                }
            }
            else -> {
                println(" ERREUR : impossible de creer un " + parametres[0] + " a ce niveau")
            }
        }
    } else {
        println("ERREUR : nombre de parametres incorrect pour la commande cd")
    }
}

private fun list(){
    for(item in node.listeItems){
        when (item){
            is Univers -> item.display()
        }
    }
}
*/