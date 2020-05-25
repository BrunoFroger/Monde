//----------------------------------------------
//
//      pageWeb.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------
package Monde

import Monde.Shell.*
import java.io.*

fun pageWeb(){
    println("creation de la page web")
    initWebPage()
    writeWebLigne("<table>")
    writeWebLigne("<tr><th colspan=\"2\">variables d'environnement</th></tr>")
    writeWebLigne("<tr>")
    writeWebLigne("<th>variable</th>")
    writeWebLigne("<th>valeur</th>")
    writeWebLigne("</tr>")
    writeWebLigne("<tr>")
    writeWebLigne("<td> nextId </td>")
    writeWebLigne("<td>" + nextId + "</td>")
    writeWebLigne("</tr>")
    writeWebLigne("<tr>")
    writeWebLigne("<td> parentId </td>")
    writeWebLigne("<td>" + parentId + "</td>")
    writeWebLigne("</tr>")
    writeWebLigne("<tr>")
    writeWebLigne("<td> clock delai </td>")
    writeWebLigne("<td>" + clock.delai + "</td>")
    writeWebLigne("</tr>")
    writeWebLigne("</table>")
    writeWebLigne("<table>")
    writeWebLigne("<tr>")
    writeWebLigne("<th colspan=\"6\">liste des items</th>")
    writeWebLigne("</tr>")
    writeWebLigne("<tr>")
    writeWebLigne("<th>type</th>")
    writeWebLigne("<th>nom</th>")
    writeWebLigne("<th>id</th>")
    writeWebLigne("<th>parent</th>")
    writeWebLigne("<th>age</th>")
    writeWebLigne("<th>sexe</th>")
    writeWebLigne("</tr>")    
    for (item in listeItems){
        writeWebLigne("<tr>")    
        var sexeString:String=""
        when (item){
            is Animal ->{
                if (item.sexe == Sexe.MALE){
                    sexeString="male"
                }
                else{
                    sexeString="feml"
                }
                writeWebLigne("<td>" + item.type + "</td>")
                writeWebLigne("<td>" + item.nom + "</td>")
                writeWebLigne("<td>" + item.id + "</td>")
                writeWebLigne("<td>" + item.parentId + "</td>")
                writeWebLigne("<td>" + item.age + "</td>")
                writeWebLigne("<td>" + sexeString + "</td>")
            }
            is Individu ->{
                if (item.sexe == Sexe.MALE){
                    sexeString="M"
                }
                else{
                    sexeString="F"
                }
                writeWebLigne("<td>" + item.type + "</td>")
                writeWebLigne("<td>" + item.nom + "</td>")
                writeWebLigne("<td>" + item.id + "</td>")
                writeWebLigne("<td>" + item.parentId + "</td>")
                writeWebLigne("<td>" + item.age + "</td>")
                writeWebLigne("<td>" + sexeString + "</td>")
            }
            is Continent ->{
                sexeString=""
                writeWebLigne("<td>" + item.type + "</td>")
                writeWebLigne("<td>" + item.nom + "</td>")
                writeWebLigne("<td>" + item.id + "</td>")
                writeWebLigne("<td>" + item.parentId + "</td>")
                writeWebLigne("<td>" + item.age + "</td>")
                writeWebLigne("<td>" + sexeString + "</td>")
            } 
            is Pays ->{
                sexeString=""
                writeWebLigne("<td>" + item.type + "</td>")
                writeWebLigne("<td>" + item.nom + "</td>")
                writeWebLigne("<td>" + item.id + "</td>")
                writeWebLigne("<td>" + item.parentId + "</td>")
                writeWebLigne("<td>" + item.age + "</td>")
                writeWebLigne("<td>" + sexeString + "</td>")
            } 
            is Planete ->{
                sexeString=""
                writeWebLigne("<td>" + item.type + "</td>")
                writeWebLigne("<td>" + item.nom + "</td>")
                writeWebLigne("<td>" + item.id + "</td>")
                writeWebLigne("<td>" + item.parentId + "</td>")
                writeWebLigne("<td>" + item.age + "</td>")
                writeWebLigne("<td>" + sexeString + "</td>")
            } 
            is Univers ->{
                sexeString=""
                writeWebLigne("<td>" + item.type + "</td>")
                writeWebLigne("<td>" + item.nom + "</td>")
                writeWebLigne("<td>" + item.id + "</td>")
                writeWebLigne("<td>" + item.parentId + "</td>")
                writeWebLigne("<td>" + item.age + "</td>")
                writeWebLigne("<td>" + sexeString + "</td>")
            } 
        }
        writeWebLigne("</tr>")   
    } 
    writeWebLigne("</table>")
    closeWebPage()
}

var webFile = File("supervision.html")
//--------------------------
//      initWebLPage
//--------------------------
fun initWebPage(){
    webFile.writeText("<html>\n")
    webFile.appendText("<head>")
    webFile.appendText("<title>")
    webFile.appendText("Supervision du monde")
    webFile.appendText("</title>")
    webFile.appendText("<style>")
    webFile.appendText("    table, th, td {")
    webFile.appendText("    padding: 10px;")
    webFile.appendText("    border: 1px solid black;")
    webFile.appendText("    border-collapse: collapse;")
    webFile.appendText("  }")
    webFile.appendText("</style>")
    webFile.appendText("<meta http-equiv=\"refresh\" content=\"1\">")
    webFile.appendText("<head>")
    webFile.appendText("<body>\n")
}

//--------------------------
//      closeWebLPage
//--------------------------
fun closeWebPage(){
    webFile.appendText("</body\n>")
    webFile.appendText("</html>\n")
}

//--------------------------
//      writeWebLigne
//--------------------------
fun writeWebLigne(message:String){
    webFile.appendText("$message \n")
}