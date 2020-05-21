//----------------------------------------------
//
//      Individu.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde

import java.io.File
import Monde.Shell.*
import com.google.common.collect.Lists

class Individu{
    
    lateinit var nom:String
    var id:Int = 0
    lateinit var type:String
    var parentId:Int = 0
    var sexe:Sexe = Sexe.MALE
    var age:Int = 0
    var comportementFilename:String?=""
    var comportementFile:File? =null
    var listeComportements = ArrayList<ArrayList<String>>()
    var marie:Boolean = false
    var dureeMariage:Int=0
    var listeEnfants = ArrayList<Individu>()
    var conjoint:Individu?=null
    var tauxMaladie:Int=0

    //--------------------------
    //      constructor
    //--------------------------
    constructor(id:Int, nom:String, parent:Int, sexe:Sexe){
        init(id, nom, parent, sexe)
    }

    constructor(id:Int, nom:String, parent:Int, age:Int){
        this.age = age
        init(id, nom, parent, sexe)
    }

    fun init(id:Int, nom:String, parent:Int, sexe:Sexe){
        this.type = "Individu"
        this.id = id
        this.nom = nom
        this.parentId = parent
        this.sexe = sexe
        log("$type $nom($id) cree")
        initComportement()
    }

    //--------------------------
    //      initComportement
    //--------------------------
    private fun initComportement(){
        comportementFilename = "config/comportement.$type.conf"
        try {
            comportementFile = File(comportementFilename)
            loadComportement(comportementFile)
        } finally {
            log("lecture fichier configuration $type $nom ok")
        }
    }

    //--------------------------
    //      loadComportement
    //--------------------------
    private fun loadComportement(file:File?){
        //log("$type : loadComportement => chargement du fichier $comportementFilename")
        try{
            file!!.forEachLine{
                //log("$type : loadComportement => ligne lue = <$it>")
                if (it.length == 0) return@forEachLine
                if (it[0] == '#') return@forEachLine
                var ligne:String = suppEspaces(it)
                var tmp = ligne.split(" ")
                println(tmp.toString())
                listeComportements.add(ArrayList(tmp))
            }
        } finally {
        }
    }
    
    //--------------------------
    //      evalueComportement
    //--------------------------
    fun evalueComportement(){
        var comportement = ArrayList<String>()

        val numbers = listOf("one", "two", "three", "four")
        val iterator = listeComportements.iterator()
        do {
            var tmp = ArrayList<String>()
            tmp = Lists.newArrayList(iterator)
            if (tmp[0] == "set"){
                // traitement d'une affectation
                println("traitement d'une action <$tmp>")
            } else if (tmp[0] == "calcul"){
                // traitement d'un calcul
                println("traitement d'un calcul <$tmp>")
            } else if (tmp[0] == "if"){
                // traitement d'un test
                println("traitement d'un test <$tmp>")
            }
        } while (iterator.hasNext())

        /* 
        for ((index,comportement) in listeComportements.withIndex()){
            if (comportement[0] == "test"){
                // traitement particulier d'un test
                log("evalueComportement ($nom) => ligne de test")
                while (comportement[0] != "finTest"){
                    var idx = listeComportements.lastIndex
                    listeComportements.get(idx+1)
                    log("ligne incluse dans le test <$comportement>")
                }
            } else if (comportement[0] == "set"){
                // ligne d'affectaion
            }
        }
        */
    }

    //--------------------------
    //      display
    //--------------------------
    fun display(){
        println("| %5d  | %5d  | %10s | %10s |".format(this.id,this.parentId,this.type,this.nom))
    }

    //--------------------------
    //      refresh
    //--------------------------
    fun refresh(){
        //log("refresh de " + this.type + "(" + this.nom + ")")
        this.age++
        evalueComportement()
    }

    //--------------------------
    //      sexeToString
    //--------------------------
    fun sexeToString():String{
        when(this.sexe) {
            Sexe.MALE -> return "Male"
            Sexe.FEMELLE -> return "Femelle"
            else -> return "Male"
        }
    }

    //--------------------------
    //      show
    //--------------------------
    fun show(){
        println("+-----------------------+")
        println("|    %10s         |".format(this.type))
        println("+--------+--------------+")
        println("| var    |   valeur     |")
        println("+--------+--------------+")
        println("| id     |          %3d |".format(this.id))
        println("| nom    |   %10s |".format(this.nom))
        println("| parent |          %3d |".format(this.parentId))
        println("| sexe   |   %10s |".format(this.sexeToString()))
        println("| age    |          %3d |".format(this.parentId))
        println("+--------+--------------+")
    }
    
    //--------------------------
    //      new
    //--------------------------
    fun new(parametres:List<String>){
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
                else -> {
                    println(" ERREUR : impossible de creer un $type a ce niveau")
                }
            }
        }
    }

    //--------------------------
    //      save
    //--------------------------
    fun save(file:File){
        log("Sauveagrde d'un " + this.type)
        var ligne = StringBuilder()
        ligne.append(this.type)
        ligne.append(";")
        ligne.append(this.id.toString())
        ligne.append(";")
        ligne.append(this.parentId.toString())
        ligne.append(";")
        ligne.append(this.nom)
        ligne.append(";")
        if (this.sexe == Sexe.MALE) ligne.append("Male")
        else ligne.append("Femelle")
        ligne.append(";")
        ligne.append(this.age.toString())
        ligne.append("\n")
        log ("ligne sauvegardee : " + ligne.toString())
        file.appendText(ligne.toString())
    }
}