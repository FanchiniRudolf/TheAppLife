package mx.itesm.materialsfragments

class Material (val clave: String, val nombre: String, val descripcion: String)
    {
        companion object {
            val arrMaterias = arrayOf(
                Material("TC1014", "Fundamentos de programación",
                    "Objetivo general:\n" +
                            "Al finalizar este curso el alumno sea capaz de aplicar la lógica para generar algoritmos que den solución a problemas de ingeniería."),

                Material("TC1018", "Estructura de datos", "Objetivo general:\n" +
                        "Al finalizar este curso el alumno será capaz de diseñar programas usando estructuras de datos en un lenguaje de programación que den solución a problemas planteados."),

                Material("TC2025", "Programación avanzada", "Objetivo general:\n" +
                        "Al finalizar el curso el alumno tendrá conocimientos avanzados sobre el desarrollo de programas en lenguaje C, su depuración e implementación para el diseño y desarrollo de aplicaciones computacionales que optimicen el aprovechamiento de los recursos del núcleo del sistema operativo. El alumno entenderá a cabalidad la administración de procesos de un sistema operativo y las técnicas de sincronización y comunicación entre ellos, así como las ventajas del desarrollo de algoritmos concurrentes y multihilos simultáneamente con la forma de implementarlos utilizando herramientas que garanticen su eficiencia."),

                Material("TI2011", "Evaluación y administración de proyectos", "Objetivo general:\n" +
                        "Al finalizar el curso el alumno será capaz de manejar el liderazgo y los recursos humanos en la administración de un proyectos en el entorno de las Tecnologías de Información, así como comunicarse en el ámbito interpersonal y grupal para coordinar los esfuerzos dirigidos al logro de proyectos exitosos.")
            )
        }
    }