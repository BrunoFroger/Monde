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

fun main(args: Array<String>){

    // Lancement du shell de pilotage
    shell.run()
}

var logFilename:String="monde.log"

public fun log(message:String){
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    val date = current.format(formatter)
    var file = File(logFilename)

    file.appendText("$date => $message \n")
}
