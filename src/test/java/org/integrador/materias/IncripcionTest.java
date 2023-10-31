package org.integrador.materias;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import org.junit.Test;

public class IncripcionTest
{


    @Test
    public void validarInscripcionDeMateriaSinCorrelativas() {

        //Creamos materias
        Materia programacionI = new Materia("Programacion I");
        Materia programacionII = new Materia("Programacion II");
        Materia baseDeDatosI = new Materia("Base de datos I");

        //Agregamos una materia correlativa
        programacionII.agregarMateriaCorrelativa(programacionI);

        //Creamos un alumno
        Alumno alumno = new Alumno("Nahuel Ramirez","138000-0");

        //Creamos una inscripción SIN CORRELATIVAS
        Inscripcion inscripcion = new Inscripcion(alumno, baseDeDatosI);

        assertTrue(inscripcion.aprobada());
    }

    @Test
    public void validarInscripcionDeMateriaConCorrelativasYQueElAlumnoLasTenga() {

        //Creamos materias
        Materia programacionI = new Materia("Programacion I");
        Materia programacionII = new Materia("Programacion II");
        Materia baseDeDatosI = new Materia("Base de datos I");

        //Agregamos una materia correlativa
        programacionII.agregarMateriaCorrelativa(programacionI);

        //Creamos un alumno
        Alumno alumno = new Alumno("Nahuel Ramirez","138000-0");

        alumno.agregarMateriaAprobada(programacionI);

        //Creamos una inscripcion CON CORRELATIVAS APROBADAS
        Inscripcion inscripcion = new Inscripcion(alumno, programacionII);

        assertTrue(inscripcion.aprobada());
    }


    @Test
    public void validarInscripcionDeMateriaConCorrelativasYQueElAlumnoNoLasTenga() {

        //Creamos materias
        Materia programacionI = new Materia("Programacion I");
        Materia programacionII = new Materia("Programacion II");
        Materia baseDeDatosI = new Materia("Base de datos I");

        //Agregamos una materia correlativa
        programacionII.agregarMateriaCorrelativa(programacionI);

        //Creamos un alumno
        Alumno alumno = new Alumno("Tomas Medel","13-0");

        //Creamos una inscripción SIN CORRELATIVAS
        Inscripcion inscripcion = new Inscripcion(alumno, programacionII);

        assertFalse(inscripcion.aprobada());
    }
}
