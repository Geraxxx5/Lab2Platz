class PerfilUsuario(id: Int, nombres:String, apellidos:String, urlPhoto:String?,
                    edad:Int, correo:String, biografia:String?,
                    estado: String, hobbies: MutableList<Hobby> = mutableListOf()) {
    val id:Int = id
    val nombres = nombres
    val apellidos = apellidos
    val urlPhoto = urlPhoto
    val edad = edad
    val correo = correo
    val estado = estado
    val biografia = biografia
    var hobbies = hobbies

    fun addHobby(hobby: Hobby){
        hobbies.add(hobby)
    }

    override fun toString(): String{
        var msg = "ID: $id" +
                "\nFoto: " + (if (urlPhoto == null) "No existe" else "$urlPhoto") +
                "\nNombres: $nombres" +
                "\nApellidos: $apellidos" +
                "\nEdad: $edad" +
                "\nBiografia: " + (if (biografia == null) "No existe" else "$biografia") +
                "\nCorreo: $correo" +
                "\nEstado: $estado" +
                "\nHobbies: \n"
        if (hobbies.size != 0){
            for(hobby in hobbies){
                msg+=hobby.toString()+"\n"
            }
        }else{
            msg+="No tiene"
        }
        return msg
    }
}