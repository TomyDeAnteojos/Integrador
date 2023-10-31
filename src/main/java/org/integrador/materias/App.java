package org.integrador.materias;

import Entidades.*;
import java.util.ArrayList;
import java.util.List;
import Servicios.LectorCSV;

public class App 
{
    public static void main( String[] args )
    {

        Materia programacionI = new Materia("Programación I");
        Materia programacionII = new Materia("Programación II");
        Materia baseDeDatosI = new Materia("Base de datos I");

        //Agregamos una materia correlativa
        programacionII.agregarMateriaCorrelativa(programacionI);

        List<Materia> materias = new ArrayList<Materia>();
        materias.add(programacionI);
        materias.add(programacionII);
        materias.add(baseDeDatosI);

        // Inicializar los alumnos, crear 2 o 3 alumnos, con y sin materias aprobadas.
        //Creo un alumno con una materia aprobada
        Alumno alumno1 = new Alumno("Vanesa Sosa","138000-0");
        //alumno1.agregarMateriaAprobada(programacionI);

        Alumno alumno2 = new Alumno("José Rodríguez","150000-0");

        List<Alumno> alumnos = new ArrayList<Alumno>();
        alumnos.add(alumno1);
        alumnos.add(alumno2);

        LectorCSV archivoLeido = new LectorCSV("src\\main\\java\\resources\\inscripcion.csv");

        archivoLeido.listarInscripciones(alumnos, materias);
    }
}
