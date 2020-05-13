//----------------------------------------------
//
//      Vegetaux.kt
//
//----------------------------------------------
//
//      Copyright B. Froger 2020
//
//----------------------------------------------


class Vegetaux():Especes{
    
    private var nom:String
    
    constucteur(nom:String){
        this.nom = nom
    }

    fun getNom():String{
        return this.nom
    }
}