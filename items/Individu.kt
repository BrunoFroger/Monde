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

class itemVariable(val nom:String, val valeur:String)

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
    var listeVariables = ArrayList<itemVariable>()

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
                log(tmp.toString())
                listeComportements.add(ArrayList(tmp))
            }
        } finally {
        }
    }
    
    //--------------------------
    //      evalueComportement
    //--------------------------
    fun evalueComportement(){
        var evalueBloc = true

        for (item in listeComportements){
            //println("ligne a analyser <$item>")
            var tmp = item.subList(1,item.size)
            if (item[0]!! == "set"){
                // traitement d'une affectation
                if (evalueBloc) evalueSet(tmp)
            } else if (item[0] == "calcul"){
                // traitement d'un calcul
                if (evalueBloc) evalueCalcul(tmp)
                //println("traitement d'un calcul <$item>")
            } else if (item[0] == "if"){
                // traitement d'un test
                //println("traitement d'un test <$item>")
                evalueBloc = evalueIf(tmp)
            } else if (item[0] == "fi"){
                // traitement d'un test
                //println("traitement d'un test <$item>")
                evalueBloc = false
            } else if (item[0] == "print"){
                // traitement d'un affichage a l'ecran
                if (evalueBloc) println(tmp)
            } else {
                // commande inconnue
                println("la ligne de commande du fichier comportement n'est pas valide => " + item)
            }
        }
    }

    //--------------------------
    //      evalueCalcul
    //--------------------------
    fun evalueCalcul(items: MutableList<String>){
        log("Analyse de la ligne de calcul <$items>")
        log("A traiter")
    }

    //--------------------------
     //      evalueSet
     //--------------------------
    fun evalueSet(items: MutableList<String>){
        //println("Analyse de la ligne de set <$items>")
        var variable = items[0]
        try {
            if (variable == "age"){
                var valeur:Int = items[1].toInt()
                this.age = valeur
                //println("Age modifie avec <$valeur>")
            } else if (variable == "marie"){
                var valeur:Boolean = items[1].toBoolean()
                this.marie = valeur
                //println("marie modifie avec <$valeur>")
            } else {
                loop@for (item in listeVariables){
                    if (item.nom == variable){
                        listeVariables.remove(item)
                        break@loop
                    }
                }
                // viaraible non trouvée, creation d'une variable
                var tmp = itemVariable(variable, items[1])
                listeVariables.add(tmp)
            }
        } catch (e:Throwable){
            println("Erreur de syntaxe dans la ligne set $items")
        }
     }

    //--------------------------
    //      getVariableString
    //--------------------------
    fun getVariableString(variable:String):String?{
        if (variable == "age") return this.age.toString()
        for (item in listeVariables){
            if (item.nom == variable){
                return item.valeur
            }
        }
        println("<$variable> inconnu")
        return null
    }

    //--------------------------
    //      getVariableInt
    //--------------------------
    fun getVariableInt(variable:String):Int{
        return getVariableString(variable)!!.toInt()
    }

    //--------------------------
    //      getVariableBoolean
    //--------------------------
    fun getVariableBoolean(variable:String):Boolean{
        if (getVariableString(variable)!! == "true"){
            return true
        }
        return false
    }

    //--------------------------
    //      evalueIf
    //--------------------------
    fun evalueIf(items: MutableList<String>):Boolean{
        var result=false
        var variable = items[0]
        var operateur = items[1]
        var valeur = items[2]
        var valeurInt = 0
        var type = "entier"
        //log("Analyse de la ligne de test <$items>")
        try {
            try {
                valeurInt = valeur.toInt()
            } catch (e: NumberFormatException){
                type = "chaine"
            }
            if (type == "entier"){
                // traitement du test d'un entier
                //log("evaluation d'un entier")
                getVariableInt(variable)
                return (valeurInt == getVariableInt(variable))
            } else if (type == "boolean"){
                // traitement du test d'un booleen
                //log("evaluation d'une booleen")
                getVariableBoolean(variable)
            } else if (type == "chaine"){
                // traitement du test d'une chaine
                //log("evaluation d'une chaine")
                getVariableString(variable)
                return (valeur == getVariableString(variable))
            } else {
                println("Type de variable inconnu <$type>")
                log("Type de variable inconnu <$type>")
            }
        } catch (e:Throwable){
            println("Erreur de syntaxe dans le bloc if $items")
            println("<$variable> <$operateur> <$valeur> : <$type>")
        }
        return result
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
        println("| age    |   %10s |".format(this.age.toString()))
        println("| marie  |   %10s |".format(this.marie.toString()))
        println("| dur Mar|          %3d |".format(this.dureeMariage))
        println("| txMalad|          %3d |".format(this.tauxMaladie))
        println("+--------+--------------+")
        println("|var cree|   valeur     |")
        println("+--------+--------------+")
        for (item in listeVariables){
            println("| %7s|   %10s |".format(item.nom, item.valeur))
        }
        println("+--------+--------------+")
        /* 
        var marie:Boolean = false
        var dureeMariage:Int=0
        var listeEnfants = ArrayList<Individu>()
        var conjoint:Individu?=null
        var tauxMaladie:Int=0
        */
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