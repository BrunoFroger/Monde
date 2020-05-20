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
data class Comportement(val variable:String, val operateur:String, val valeur:String, val action:String)

//--------------------------
//      main
//--------------------------
fun main(args: Array<String>){
    log("=====================================")
    log("         Debut de session")
    log("=====================================")
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

