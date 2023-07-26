fun main(args: Array<String>) {
    var usuarios = mutableListOf<PerfilUsuario>()
    var flag = true
    createProfiles(usuarios)
    var contID = usuarios.size
    println("Bienvenido a la aplicacion de usuarios")

    while (flag){
        println("Seleccione la opcion que desea:\n0) Salir del Programa" +
                "\n1) Crear nuevo Perfil\n2) Buscar Perfil de usuario(s)" +
                "\n3) Eliminar perfil\n4) Agregar un Hobby")
        var opc = readln()
        println(when (opc){
            "0" -> {
                flag = false
                "se termino el programa"
            }
            "1" -> {
                var biografia: String? = null
                var urlPhoto: String? = null
                var edad: Int? = null
                var estado: String? = null
                println("Ingrese los nombres del usuario")
                val nombres = readln()
                println("Ingrese los apellidos del usuario")
                val apellidos = readln()
                println("Desea colocar Foto?\n1) Si\n2) No\nSeleccione la opcion (Si ingresa una opcion invalida se considerara como no):")
                var flagFoto = readln()
                when(flagFoto){
                    "1"-> {
                        println("Ingrese el url de la foto")
                        urlPhoto = readln()
                    }
                }
                println("Ingrese la edad del usuario:")
                var flagEdad = true
                while(flagEdad){
                    edad = readln()?.toInt()
                    if(edad != null) flagEdad = false
                }
                println("Ingrese el correo del usuario:")
                var correo = readln()
                println("Desea colocar biografia?\n1) Si\n2) No\nSeleccione la opcion (Si ingresa una opcion invalida se considerara como no): ")
                var flagBiografia = readln()
                when(flagBiografia){
                    "1"-> {
                        println("Ingrese su Biografia")
                        biografia = readln()
                    }
                }
                println("Ingrese el estado en el que se encuentra el usuario\n1) Activo" +
                        "\n2) Pendiente\n3) Inactivo\n Selecciona la opcion (Si selecciona una opcion invalida se tomara como inactivo):")
                estado = readln()
                when (estado){
                    "1"->estado = "Activo"
                    "2"->estado = "Pendiente"
                    else -> estado = "Inactivo"
                }
                usuarios.add(PerfilUsuario(contID,nombres, apellidos, urlPhoto,edad!!,correo,biografia,estado))
                contID+=1
                "Perfil Creado"
            }
            "2"->{
                println("Ingrese el ID, los nombres o los apellidos del usuario que desea buscar")
                var busqueda:Any = readln()
                var isInteger:Any? = busqueda.toString().toIntOrNull()
                var usuariosEncontrados:List<PerfilUsuario>?= null
                when (isInteger){

                    is Int -> {
                        usuariosEncontrados = usuarios.filter { it.id == busqueda.toString().toInt() }
                    }
                    else -> {
                        usuariosEncontrados = usuarios.filter { it.nombres == busqueda.toString() }
                        if(usuariosEncontrados.size == 0){
                            usuariosEncontrados = usuarios.filter { it.apellidos == busqueda.toString() }
                        }
                    }
                }
                if(usuariosEncontrados.size != 0){
                    println("Usuarios Encontrados: \n")
                    for(usuario in usuariosEncontrados!!){
                        println(usuario.toString())
                    }
                    "Fin de los usuarios encontrados\n"
                }else{
                    "\nNo se encontro ningun usuario\n"
                }

            }
            "3"->{
                println("Ingrese el ID del usuario que desea eliminar")
                val id = readln().toIntOrNull()
                if(id!=null){
                    val usuarioBorrar = usuarios.filter { it.id == id }
                    if(usuarioBorrar.size != 0){
                        var tempUsuario = usuarioBorrar[0]
                        usuarios.remove(usuarioBorrar[0])
                        "El usuario con id ${tempUsuario.id} ,nombres ${tempUsuario.nombres} y apellidos ${tempUsuario.apellidos} fue borrado"
                    }else{
                        "No se encontro usuarios con ese ID"
                    }

                }else{
                    "Unicamente numeros enteros para el ID"
                }
            }
            "4"->{
                println("Ingrese el ID, los nombres o los apellidos del usuario que desea agregar un hobby")
                var busqueda:Any = readln()
                var isInteger:Any? = busqueda.toString().toIntOrNull()
                var usuariosEncontrados:List<PerfilUsuario>?= null
                when (isInteger){

                    is Int -> {
                        usuariosEncontrados = usuarios.filter { it.id == busqueda.toString().toInt() }
                    }
                    else -> {
                        usuariosEncontrados = usuarios.filter { it.nombres == busqueda.toString() }
                        if(usuariosEncontrados.size == 0){
                            usuariosEncontrados = usuarios.filter { it.apellidos == busqueda.toString() }
                        }
                    }
                }
                if (usuariosEncontrados.size != 0){
                    println("Ingrese el nombre del hobby")
                    val titulo = readln()
                    println("Ingrese la descripcion del hobby")
                    val descripcion = readln()
                    println("Desea incluir una foto?\n1) Si\n2) No\nSeleccione una opcion (Si selecciona una opcion invalida se tomara como no):")
                    var urlPhoto:String? = readln()
                    when (urlPhoto){
                        "1"->{
                            println("Ingrese el url de la foto")
                            urlPhoto = readln()
                        }
                        "2"-> urlPhoto = null
                    }
                    if(usuariosEncontrados.size > 1){
                        println("A cual de los siguientes usuarios quiere agregarle el hobby")
                        var contUsuarios = 1
                        for (usuario in usuariosEncontrados!!){
                            println("$contUsuarios) ID: ${usuario.id}. ${usuario.nombres},${usuario.apellidos}")
                            contUsuarios+=1
                        }
                        println("Ingrese la opcion: ")
                        var opcUsuario = readln().toIntOrNull()
                        if(opcUsuario != null) {
                            opcUsuario-=1
                            if(opcUsuario < 0 || opcUsuario > usuariosEncontrados.size){
                                "Selecciono una opcion invalida"
                            }else{
                                usuariosEncontrados[opcUsuario].addHobby(Hobby(titulo,descripcion,urlPhoto))
                                "Se agrego el hobby"
                            }
                        }else{
                            "Ingrese una opcion invalida"
                        }
                    }else{
                        usuariosEncontrados[0].addHobby(Hobby(titulo,descripcion,urlPhoto))
                        "Se agrego el hobby"
                    }
                }else{
                    "No se encontro ningun usuario con esas especificaciones"
                }

            }
            else -> "Seleccione una opcion correcta"
        })

    }
}

fun createProfiles(usuarios: MutableList<PerfilUsuario>){
    usuarios.add(PerfilUsuario(0,"Gabriel Gerardo","Pineda Riveiro","https://th.bing.com/th/id/R.7b88b15c8b7ada49a6401eae9a2907b3?rik=pFqzYElBhpNxNQ&riu=http%3a%2f%2fwww.servidor-alicante.com%2ffotos-divertidas%2fAnimales%2fMonos%2fMonito_pensativo.jpg&ehk=nHdkSFMrg16B2v72LnYdnNelhVD8qclaf5AXMw6RuK4%3d&risl=&pid=ImgRaw&r=0",20,"gerax.pineda@gmail.com",null,"Activo"))
    usuarios[0].addHobby(Hobby("Dormir","Dormir en una cama muy comoda","https://MiCama"))
    usuarios[0].addHobby(Hobby("Salir a correr","Salir a correr","https://PersonaCorriendo"))
    usuarios.add(PerfilUsuario(1,"Fernando Enrique","Echeverria Leal","https://th.bing.com/th/id/R.1cfc8fb6464cc4b62866eb5da9fe565e?rik=HzvroG1E7mq9Sw&riu=http%3a%2f%2fwww.abc.es%2fMedia%2f201407%2f16%2ftailandia-elefante-fotos--644x362.jpg&ehk=ObGceZqtXrTEy0cGls%2fbRKe1LKIJks33QzLVSLCDsEc%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1",19,"fer2002@gmail.com","Amo a taylor","Activo"))
    usuarios[1].addHobby(Hobby("Escuchar Musica","Escuchar musica todo el dia",null))
    usuarios.add(PerfilUsuario(2,"Angela Rosana","Garcia Donis",null,19,"angelagrd@outlook.com",":)","Inactivo"))
    usuarios[2].addHobby(Hobby("Escribir","Escribir mucha que mas","https://LibrosCool"))
    usuarios.add(PerfilUsuario(3,"Francis Gabriela","Aguilar Leal","https://Taylor",19,"fra2003@gmail.com","Amo a mis perritos","Pendiente"))
    usuarios.add(PerfilUsuario(4,"Nathalie Johanna","Fajardo Garrido","https://th.bing.com/th/id/R.ebfa9b930d87f3dcbd0534fa91800baf?rik=OhjtGiYcXeSa%2fA&riu=http%3a%2f%2f3.bp.blogspot.com%2f-zwsv-2fpaBY%2fUJcSOB5BwOI%2fAAAAAAAAAAc%2ftVL7mflL-Wg%2fs1600%2fOAKLAND_RAIN__LEONID_AFREMOV_by_Leonidafremov.jpg&ehk=ic66Khzi1RhWHnykqttZMbQ9FVIVfMYiU1j8l6rlUo8%3d&risl=&pid=ImgRaw&r=0",19,"njfg2003@gmail.com","Me gusta el arte y el sueño es mi vida","Inactivo"))
    usuarios[4].addHobby(Hobby("Dormir","Dormir en una cama muy comoda","https://MiCama"))
    usuarios[4].addHobby(Hobby("Pintar","Pintar Cuadros","https://Canvas"))
    usuarios.add(PerfilUsuario(5,"Ludving Gerardo","Pineda Molina",null,45,"ludvingge@outlook.com",null,"Inactivo"))
    usuarios.add(PerfilUsuario(6,"Gabriel Gerardo","Riveiro Pineda",null,20,"gerax.pineda@gmail.com",null,"Activo"))
    usuarios.add(PerfilUsuario(7,"Johanna","Fajardo Garrido",null,19,"njfg2003@gmail.com","Me gusta el arte y el sueño es mi vida","Inactivo"))
}