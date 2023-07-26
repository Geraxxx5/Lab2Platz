class Hobby(titulo:String, descripcion:String, urlPhoto:String?) {
    val titulo = titulo
    val descripcion = descripcion
    val urlPhoto = urlPhoto
    override fun toString(): String {
        return "Photo: " + (if (urlPhoto == null) "No existe" else urlPhoto)+
                "\nTitulo: $titulo\nDescripcion: $descripcion"
    }
}