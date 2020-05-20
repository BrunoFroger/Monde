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

import java.io.File
import java.awt.*;
import java.awt.event.*; 
import java.util.Scanner
import Monde.Clock.*

var listeItems = ArrayList<Any>()
var nextId:Int = 0 
var isActiveSupervision=false
var parentId:Int=0
var prompt:String = ""

class Shell{
    
    //lateinit var node:Any
    var commandLine:String=""

    //--------------------------
    //      run
    //--------------------------
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

    //--------------------------
    //      getCommande
    //--------------------------
    private fun getCommande():String{
        var tmp = readLine()!!
        tmp = tmp.replace("    ", " ") 
        tmp = tmp.replace("   ", " ") 
        tmp = tmp.replace("  ", " ") 
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
        //println("commande saisie <$tmp>")
        return tmp
    }

    //--------------------------
    //      executeCommande
    //--------------------------
    private fun executeCommande(commande:String):Int{
        var tmp:String
        if (commande.equals("quit")){
            return 0
        } else if (commande.equals("")) {
            return 1
        } else if (commande.equals("env")) {
            env()
        } else if (commande.equals("list")) {
            list()
        } else if (commande.equals("arbre")) {
            arbre()
        } else if (commande.equals("clr")) {
            // clear the screen
            print("\u001b[H\u001b[2J")
        } else if (commande.startsWith("supervis")) {
            if (isActiveSupervision){
                println("supervision desactivee")
                isActiveSupervision = false
            } else {
                println("supervision activee")
                isActiveSupervision = true
            }
        } else if (commande.startsWith("help")) {
            if (commande.length > 5){
                help(commande.substring(5))
            } else {
                help("")
            }
        } else if (commande.startsWith("save")) {
            if (commande.length > 5){
                save(commande.substring(5))
            } else {
                save("monde.data")
            }
        } else if (commande.startsWith("load")) {
            if (commande.length > 5){
                load(commande.substring(5))
            } else {
                load("monde.data")
            }
        } else if (commande.startsWith("clock")) {
            if (commande.length > 5){
                clock.commande(commande.substring(6))
            } else {
                println("ERREUR : syntaxe incorrecte")
            }
        } else if (commande.startsWith("show")) {
            show(commande.substring(4))
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
            //println("kill d'un objet " + listeParametres.toString())
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


    
    //--------------------------
    //      cd
    //--------------------------
    private fun cd(parametres:List<String>?){
        if (parametres == null){
            prompt = ""
            parentId = 0
            return
        }
        val type = parametres[0]
        if (parametres.size != 2){
            println("ERREUR : nombre de parametres incorrect pour la commande : cd $type <nom>")
            return
        }
        val nom = parametres[1]
        log("on essaie de changer vers le niveau de $nom de type $type")
        for (item in listeItems){
            when (item){
                is Animal -> {
                    if (item.nom == nom){
                        log("on change de niveau ($nom)")
                        parentId = item.id
                        prompt = item.type + "." + item.nom
                        return
                    }
                    //println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                }
                is Continent -> {
                    if (item.nom == nom){
                        log("on change de niveau ($nom)")
                        parentId = item.id
                        prompt = item.type + "." + item.nom
                        return
                    }
                    //println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                }
                is Individu -> {
                    if (item.nom == nom){
                        log("on change de niveau ($nom)")
                        parentId = item.id
                        prompt = item.type + "." + item.nom
                        return
                    }
                    //println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                }
                is Pays -> {
                    if (item.nom == nom){
                        log("on change de niveau ($nom)")
                        parentId = item.id
                        prompt = item.type + "." + item.nom
                        return
                    }
                    //println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                }
                is Planete -> {
                    if (item.nom == nom){
                        log("on change de niveau ($nom)")
                        parentId = item.id
                        prompt = item.type + "." + item.nom
                        return
                    }
                    //println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                }
                is Univers -> {
                    if (item.nom == nom){
                        log("on change de niveau ($nom)")
                        parentId = item.id
                        prompt = item.type + "." + item.nom
                        return
                    }
                    //println("c'est bien un $type mais pas le bon nom ($nom/$item.nom")
                }
                else -> println("impossible de chnager vers $type")
            }
        }
        println("$type ($nom) non truve")
    }
    

    //--------------------------
    //      list
    //--------------------------
    private fun list(){
        println("list " + listeItems.size + " items ")
        println("+===========================================+")
        println("|   liste d'objets                          |")
        println("+--------+--------+------------+------------+")
        println("|   id   | parent | type       | nom        |")
        println("+--------+--------+------------+------------+")
        for(item in listeItems){
            when (item){
                is Animal -> item.display()
                is Continent -> item.display()
                is Individu -> item.display()
                is Pays -> item.display()
                is Planete -> item.display()
                is Univers -> item.display()
                else -> {
                    println("|   ---  |   ---  | inconnu    |            |")
                }
            }
        }
        println("+===========================================+")
    }

    //--------------------------
    //      show
    //--------------------------
    fun show(cde:String){
        var tmp:String=""
        if (cde.length > 1){
            tmp = cde.substring(1)
        } else {
            println("show : manque de parametres")
        }
        log("Shell : show => <" + tmp + ">")
        var listeParametres:List<String> = tmp.split(" ")
        var nom = listeParametres[1]
        for (item in listeItems){
            when (item){
                is Animal -> {
                    if (item.nom == nom){
                        item.show()
                    }
                }
                is Continent -> {
                    if (item.nom == nom){
                        item.show()
                    }
                }
                is Individu -> {
                    if (item.nom == nom){
                        item.show()
                    }
                }
                is Pays -> {
                    if (item.nom == nom){
                        item.show()
                    }
                }
                is Planete -> {
                    if (item.nom == nom){
                        item.show()
                    }
                }
                is Univers -> {
                    if (item.nom == nom){
                        item.show()
                    }
                }
                else -> println("Show : type d'item inconnu")
            }
        }
    }
    
    //--------------------------
    //      save
    //--------------------------
    fun save(filename:String){
        var file = File(filename)
        var nbItem = 0
        if (file != null){
            log("sauveagarde des items cournats dans " + filename)
            for (item in listeItems){
                when (item){
                    is Animal -> item.save(file)
                    is Continent -> item.save(file)
                    is Individu -> item.save(file)
                    is Pays -> item.save(file)
                    is Planete -> item.save(file)
                    is Univers -> item.save(file)
                }
                nbItem++
            }  
            log ("$nbItem sauvegardes")
        } else {
            println("nom de fichier invalide")
        }
    }


    //--------------------------
    //      checkExist
    //--------------------------
    fun checkExist(id:Int):Boolean{
        for (item in listeItems){
            when (item){
                is Animal -> if (item.id == id) return true
                is Continent -> if (item.id == id) return true
                is Individu -> if (item.id == id) return true
                is Pays -> if (item.id == id) return true
                is Planete -> if (item.id == id) return true
                is Univers -> if (item.id == id) return true
            }
        }
        return false
    }

    //--------------------------
    //      load
    //--------------------------
    fun load(filename:String){
        var type:String = ""
        var nom:String = ""
        var id:Int = 0
        var parent:Int = 0
        var age:Int = 0
        var sexe:String = ""

        File(filename).forEachLine{
            log ("load : => lecture de la ligne <" + it + ">")
            var listeParametres:List<String> = it.split(";")
            type = listeParametres[0]
            if (type.equals("Animal")) {
                if (listeParametres.size != 6){
                    println("Load : ligne incorrecte (nb parametres incorrect) <" + it + ">")
                } else {
                    id = listeParametres[1].toInt()
                    if (!checkExist(id)){
                        parent = listeParametres[2].toInt()
                        nom = listeParametres[3]
                        age = listeParametres[5].toInt()
                        sexe = listeParametres[4]
                        if (sexe == "Male"){
                            var item = Animal(id.toInt(), nom, parent.toInt(),Sexe.MALE)
                            listeItems.add(item)
                        } else {
                            var item = Animal(id.toInt(), nom, parent.toInt(),Sexe.FEMELLE)
                            listeItems.add(item)
                        }
                        nextId++
                    }else{
                        println("Cet id ($id) existe deja => impossible de l'ajouter")
                        log("Cet id ($id) existe deja => impossible de l'ajouter")
                    }
                }
            } else if (type.equals("Individu")) {
                if (listeParametres.size != 6){
                    println("Load : ligne incorrecte (nb parametres incorrect) <" + it + ">")
                } else {
                    id = listeParametres[1].toInt()
                    if (!checkExist(id)){
                        parent = listeParametres[2].toInt()
                        nom = listeParametres[3]
                        age = listeParametres[5].toInt()
                        sexe = listeParametres[4]
                        if (sexe == "Male"){
                            var item = Individu(id.toInt(), nom, parent.toInt(),Sexe.MALE)
                            listeItems.add(item)
                        } else {
                            var item = Individu(id.toInt(), nom, parent.toInt(),Sexe.FEMELLE)
                            listeItems.add(item)
                        }
                        nextId++
                    }else{
                        println("Cet id ($id) existe deja => impossible de l'ajouter")
                        log("Cet id ($id) existe deja => impossible de l'ajouter")
                    }
                }
            } else if (type.equals("Continent")) {
                if (listeParametres.size != 5){
                    println("Load : ligne incorrecte (nb parametres incorrect) <" + it + ">")
                } else {
                    id = listeParametres[1].toInt()
                    if (!checkExist(id)){
                        parent = listeParametres[2].toInt()
                        nom = listeParametres[3]
                        age = listeParametres[4].toInt()
                        var item = Continent(id.toInt(), nom, parent.toInt(), age.toInt())
                        listeItems.add(item)
                        nextId++
                    }else{
                        println("Cet id ($id) existe deja => impossible de l'ajouter")
                        log("Cet id ($id) existe deja => impossible de l'ajouter")
                    }
                }
            } else if (type.equals("Pays")) {
                if (listeParametres.size != 5){
                    println("Load : ligne incorrecte (nb parametres incorrect) <" + it + ">")
                } else {
                    id = listeParametres[1].toInt()
                    if (!checkExist(id)){
                        parent = listeParametres[2].toInt()
                        nom = listeParametres[3]
                        age = listeParametres[4].toInt()
                        var item = Pays(id.toInt(), nom, parent.toInt(), age.toInt())
                        listeItems.add(item)
                        nextId++
                    }else{
                        println("Cet id ($id) existe deja => impossible de l'ajouter")
                        log("Cet id ($id) existe deja => impossible de l'ajouter")
                    }
                }
            } else if (type.equals("Planete")) {
                if (listeParametres.size != 5){
                    println("Load : ligne incorrecte (nb parametres incorrect) <" + it + ">")
                } else {
                    id = listeParametres[1].toInt()
                    if (!checkExist(id)){
                        parent = listeParametres[2].toInt()
                        nom = listeParametres[3]
                        age = listeParametres[4].toInt()
                        var item = Planete(id.toInt(), nom, parent.toInt(), age.toInt())
                        listeItems.add(item)
                        nextId++
                    }else{
                        println("Cet id ($id) existe deja => impossible de l'ajouter")
                        log("Cet id ($id) existe deja => impossible de l'ajouter")
                    }
                }
            } else if (type.equals("Univers")) {
                if (listeParametres.size != 5){
                    println("Load : ligne incorrecte (nb parametres incorrect) <" + it + ">")
                } else {
                    id = listeParametres[1].toInt()
                    if (!checkExist(id)){
                        parent = listeParametres[2].toInt()
                        nom = listeParametres[3]
                        age = listeParametres[4].toInt()
                        var item = Univers(id.toInt(), nom, parent.toInt(), age.toInt())
                        listeItems.add(item)
                        nextId++
                    }else{
                        println("Cet id ($id) existe deja => impossible de l'ajouter")
                        log("Cet id ($id) existe deja => impossible de l'ajouter")
                    }
                }
            } else {
                println("Load : ligne incorrecte (type inconnu) <" + it + ">")
            }
        }
    }

    //--------------------------
    //      env
    //--------------------------
    fun env(){
        println("nextId = " + nextId)
        println("parentId = " + parentId)
    }
}

//--------------------------
//      displayPrompt
//--------------------------
fun displayPrompt(){
    print(prompt + ": $parentId > ")
}