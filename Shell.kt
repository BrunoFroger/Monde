//----------------------------------------------
//
//      shell.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde

import java.awt.*;
import java.awt.event.*; 
import java.util.Scanner

class Shell{
    
    var listeItems = ArrayList<Any>()
    var prompt:String = ""
    lateinit var node:Any
    var nextId:Int = 0 
    var commandLine:String=""
    var parentId:Int=0

    fun run(){
        var commande:String
        var result = 1
        while (result != 0){
            displayPrompt()
            commande = getCommande()
            log("commande <" + commande + "> saisie")
            if (commande != null){
                result = executeCommande(commande)
            }
        }
    } 

    private fun displayPrompt(){
        print(prompt + ": $parentId > ")
    }

    private fun getCommande():String{

        //var scanner = Scanner(System.`in`)
        //var car = scanner.readByte()
        //if (car != '')
            //log("caraactere lu : $car")
        /*
        var car = readByte()
        when (car){
            '\n' -> {
                println("")
            }
        }
        */
        //return ""
        return readLine()!!
    }

    private fun executeCommande(commande:String):Int{
        var tmp:String
        if (commande.equals("quit")){
            return 0
        } else if (commande.equals("")) {
            return 1
        } else if (commande.equals("list")) {
            list()
        } else if (commande.startsWith("clock")) {
            if (commande.length > 5){
                clock.commande(commande.substring(6))
            } else {
                println("ERREUR : syntaxe incorrecte")
            }
        } else if (commande.startsWith("cd")){
            if (commande.length > 2){
                tmp = commande.substring(3)
                var listeParametres:List<String> = tmp.split(" ")
                //println("acces a l'objet " + listeParametres.toString())
                cd(listeParametres)
            } else {
                cd (null)
            }
        } else if (commande.startsWith("kill")){
            tmp = commande.substring(5)
            var listeParametres:List<String> = tmp.split(" ")
            println("kill d'un objet " + listeParametres.toString())
            kill(listeParametres)
        } else if (commande.startsWith("new")){
            tmp = commande.substring(4)
            var listeParametres:List<String> = tmp.split(" ")
            //println("creation d'un objet " + listeParametres.toString())
            new(listeParametres)
        } else {
            println("commande " + commande + " inconnue")
        }
        return 1
    }

    private fun kill(parametres:List<String>){
        if (parametres.size == 0){
            println("ERREUR : pas assez de parametres")
            return
        }
        val type = parametres[0]
        if (parametres.size != 2){
            println("ERREUR : nombre de parametres incorrect pour la commande : new $type <nom>")
        } else {
            val nom = parametres[1]
            log("on essaie de supprimer $nom de type $type")
            for (item in listeItems){
                when (item){
                    is Univers -> {
                        if (item.nom == nom){
                            log("on supprime ce $type ($nom)")
                            listeItems.remove(item)
                            return
                        }
                        println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                    }
                    else -> println("impossible de supprimer un $type")
                }
            }
            println("$type ($nom) non truve")
        }
    }

    private fun new(parametres:List<String>){
        if (parametres.size == 0){
            println("ERREUR : pas assez de parametres")
            return
        }
        val type = parametres[0]
        if (parametres.size != 2){
            println("ERREUR : nombre de parametres incorrect pour la commande : new $type <nom>")
        } else {
            val nom = parametres[1]
            when (type){
                "univers" -> {
                    var item = Univers(++nextId,nom, parentId)
                    listeItems.add(item)
                }
                "planete" -> {
                    var item = Planete(++nextId,nom, parentId)
                    listeItems.add(item)
                }
                "continent" -> {
                    var item = Continent(++nextId,nom,parentId)
                    listeItems.add(item)
                }
                else -> {
                    println(" ERREUR : impossible de creer un $type a ce niveau")
                }
            }
        }
    }
    
    private fun cd(parametres:List<String>?){
        if (parametres == null){
            prompt = ""
            parentId = 0
            return
        }
        val type = parametres[0]
        if (parametres.size != 2){
            println("ERREUR : nombre de parametres incorrect pour la commande : new $type <nom>")
            return
        }
        val nom = parametres[1]
        log("on essaie de changer vers le niveau de $nom de type $type")
        for (item in listeItems){
            when (item){
                is Univers -> {
                    if (item.nom == nom){
                        log("on change de niveau ($nom)")
                        parentId = item.id
                        prompt = item.type + "." + item.nom
                        return
                    }
                    println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                }
                else -> println("impossible de chnager vers $type")
            }
        }
        println("$type ($nom) non truve")
    }
    

    private fun list(){
        println("list " + listeItems.size + " items ")
        println("+===========================================+")
        println("|   liste d'objets                          |")
        println("+--------+--------+------------+------------+")
        println("|   id   | parent | type       | nom        |")
        println("+--------+--------+------------+------------+")
        for(item in listeItems){
            when (item){
                is Univers -> {
                    //println("Display item de type Univers")
                    item.display()
                }
                is Planete -> {
                    //println("Display item de type Planete")
                    item.display()
                }
                is Continent -> {
                    //println("Display item de type Planete")
                    item.display()
                }
                else -> {
                    //println("type d'item inconnu ")
                    println("|   ---  |   ---  | inconnu    |            |")
                }
            }
        }
        println("+===========================================+")
    }
    
}