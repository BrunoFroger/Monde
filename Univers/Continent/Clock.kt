//----------------------------------------------
//
//      Clock.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde

import Monde.Shell.*

import java.util.Timer
import kotlin.concurrent.schedule



class Clock{
    var timer = Timer()
    var delai:Long

    constructor(delai:Long){
        this.delai = delai
    }

    fun start(){
        timer.schedule(delai){
            clockTask()
        }
    }

    fun clockTask(){
        log("Clock : timerTask => ")
        start()
    }

    fun stop(){
        timer.cancel()
        timer.purge()    
    }

    fun delai(delai:Long){
        this.delai=delai
    }

    fun delai():Long{
        return this.delai
    }

    fun commande(cde:String){
        log("Clock : commande <$cde>")
        var listeParametres:List<String> = cde.split(" ")
        if (listeParametres.size  == 0){
            println("ERREUR : nombre de parametres incorrect pour la commande : clock <commande> [parametre]")
        } else {
            var commande = listeParametres[0]
            when (commande){
                "start" -> {
                    log ("Clock : timer started")
                    timer = Timer()
                    start()
                }
                "stop" -> {
                    log ("Clock : timer sropped")
                    stop()
                }
                "delai" -> {
                    if (listeParametres.size == 1){
                        println("Clock : delai = " + this.delai.toString())
                    } else {
                        this.delai = listeParametres[1].toLong()
                        log ("Clock : timer delai updated $this.delai")
                    }
                }
            }
        }
    }
}