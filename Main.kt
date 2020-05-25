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

import Monde.*

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var shell:Shell = Shell()
var clock = Clock(5000)

enum class Sexe{MALE, FEMELLE}

//--------------------------
//      main
//--------------------------
fun main(args: Array<String>){
    log("=====================================")
    log("         Debut de session")
    log("=====================================")
    // initialisation de l'horloge
    clock.start()
    // Lancement du shell de pilotage
    shell.run()
    clock.stop()
    
    log("=====================================")
    log("         fin de session")
    log("=====================================")
    return
}

var logFilename:String="monde.log"

//--------------------------
//      log
//--------------------------
public fun log(message:String){
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    val date = current.format(formatter)
    var file = File(logFilename)

    file.appendText("$date => $message \n")
}

//--------------------------
//      suppEspaces
//--------------------------
fun suppEspaces(chaine:String):String{
    var taille:Int=0
    var tmp:String=chaine.trim()
    do{
        taille = tmp.length
        tmp = tmp.replace("  "," ")
    } while (taille != tmp.length)
    return tmp
}