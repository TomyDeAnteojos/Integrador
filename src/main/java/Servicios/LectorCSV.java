package Servicios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.bean.CsvToBeanBuilder;

import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.LineasCsvInscripcion;
import Entidades.Materia;
import Excepciones.NoExisteAlumnoExcepcion;
import Excepciones.NoExisteMateriaExcepcion;

public class LectorCSV {
    private String rutaArchivo;
    private List<LineasCsvInscripcion> lineasArchivo;

    public LectorCSV(String ruta) {
        this.rutaArchivo = ruta;
        lineasArchivo = new ArrayList<>();
    }

    public LectorCSV() {
        lineasArchivo = new ArrayList<>();
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void leerElArchivo() {
        List<LineasCsvInscripcion> parsedLines = null;

        try (FileReader fileReader = new FileReader(this.rutaArchivo, StandardCharsets.UTF_8)) {
            parsedLines = new CsvToBeanBuilder<LineasCsvInscripcion>(fileReader)
                    .withSkipLines(1) // Salta primer linea
                    .withType(LineasCsvInscripcion.class)
                    .build()
                    .parse();
        } catch (IllegalStateException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.lineasArchivo = parsedLines;
    }

    public void listarInscripciones(List<Alumno> alumnos, List<Materia> materias) {
        List<Inscripcion> inscripciones = new ArrayList<>();

        for (LineasCsvInscripcion lineaLeida : this.lineasArchivo)
        {
            try {
                Inscripcion inscripcion =
                        validarInscripcion(alumnos, materias, lineaLeida);
                imprimirEnPantalla(
                        lineaLeida.getNombreAlumno(),
                        lineaLeida.getNombreMateria(),
                        inscripcion.aprobadaString());
            } catch (NoExisteAlumnoExcepcion e) {
                imprimirEnPantalla(
                        lineaLeida.getNombreAlumno(),
                        lineaLeida.getNombreMateria(),
                        "No existe el/la alumno/a");
            } catch (NoExisteMateriaExcepcion e) {
                imprimirEnPantalla(
                        lineaLeida.getNombreAlumno(),
                        lineaLeida.getNombreMateria(),
                        "No existe la materia");
            }
        }
    }

    public Inscripcion validarInscripcion(List<Alumno> alumnos, List<Materia> materias,
                                          LineasCsvInscripcion lineaLeida) throws NoExisteAlumnoExcepcion, NoExisteMateriaExcepcion {
        Alumno alumnoLeido = null;
        for (Alumno alumnoDeLaLista : alumnos) {
            if (alumnoDeLaLista.getNombre().equalsIgnoreCase(lineaLeida.getNombreAlumno())) {
                alumnoLeido = alumnoDeLaLista;
            }
        }

        Materia materiaLeida = null;
        for (Materia materiaDeLaLista : materias) {
            if (materiaDeLaLista.getNombre().equalsIgnoreCase(lineaLeida.getNombreMateria())) {
                materiaLeida = materiaDeLaLista;
            }
        }

        if (alumnoLeido == null) {
            throw new NoExisteAlumnoExcepcion();
        }
        if (materiaLeida == null) {
            throw new NoExisteMateriaExcepcion();
        }

        return new Inscripcion(alumnoLeido, materiaLeida);
    }

    public void imprimirEnPantalla(String nombreAlumno, String nombreMateria, String resultado) {
        System.out.printf("%-20s%-20s%-20s\n", nombreAlumno, nombreMateria, resultado);
    }
}
