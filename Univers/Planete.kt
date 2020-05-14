//----------------------------------------------
//
//      Planete.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------

import Monde.Univers.*

class Planete:Univers(){
    
    lateinit var nomPlanete:String
    
    override fun display(){
        println("+=====================+")
        println("|   objet Planete     |")
        println("+---------------------+")
        println("| nom    | %10s |".format(this.nomPlanete))
        println("+=====================+")
    }
    
}
