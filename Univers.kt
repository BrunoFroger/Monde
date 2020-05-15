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

import Monde.Shell.*

class Univers{

    var nom:String
    var id:Int
    val type:String = "Univers"
    val parentId:Int

    constructor(id:Int, nom:String, parent:Int){
        this.id = id
        this.nom = nom
        this.parentId = parent
        log("$type $nom($id) cree")
    }

    fun display(){
        println("| %5d  | %5d  | %10s | %10s |".format(this.id,this.parentId,this.type,this.nom))
    }
}