//----------------------------------------------
//
//      Univers.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde

import java.io.File
import Monde.Shell.*

class Univers{

    lateinit var nom:String
    var id:Int = 0
    lateinit var type:String
    var parentId:Int = 0
    var age:Int = 0
    var comportementFilename:String?=""
    var comportementFile:File? =null
    var listeComportements = ArrayList<ArrayList<String>>()

    //--------------------------
    //      constructor
    //--------------------------
    constructor(id:Int, nom:String, parent:Int){
        init(id,nom,parent)
    }

    constructor(id:Int, nom:String, parent:Int, age:Int){
        this.age = age
        init(id,nom,parent)
    }

    fun init(id:Int, nom:String, parent:Int){
        this.type = "Univers"
        this.id = id
        this.nom = nom
        this.parentId = parent
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
            println("lecture fichier configuration $type ok")
        }
    }

    //--------------------------
    //      loadComportement
    //--------------------------
    private fun loadComportement(file:File?){
        log("$type : loadComportement => chargement du fichier $comportementFilename")
        try{
            file!!.forEachLine{
                println("$type : loadComportement => ligne lue = <$it>")
            }
        } finally {
        }
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
        println("| age    |          %3d |".format(this.parentId))
        println("+--------+--------------+")
    }

    //--------------------------
    //      new
    //--------------------------
    fun new(parametres:List<String>){
        log ("Univers : new")
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
                "planete" -> {
                    var item = Planete(++nextId,nom, this.id)
                    listeItems.add(item)
                }
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
        ligne.append(this.age.toString())
        
        ligne.append("\n")
        
        log ("ligne sauvegardee : " + ligne.toString())
        file.appendText(ligne.toString())
    }

}