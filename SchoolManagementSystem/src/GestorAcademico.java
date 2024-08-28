import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Curso> cursos = new ArrayList<>();
    private HashMap<Integer, ArrayList<Estudiante>> estudiantesPorCurso = new HashMap<>();

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
            System.out.println("Estudiante matriculado: " + estudiante);
        } else {
            System.out.println("El estudiante ya está matriculado.");
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            System.out.println("Curso agregado: " + curso);
        } else {
            System.out.println("El curso ya existe.");
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        // Buscar la lista de estudiantes inscritos en el curso por su id
        ArrayList<Estudiante> inscritos = estudiantesPorCurso.get(idCurso);

        // Si no existe, inicializar la lista de inscritos para ese curso
        if (inscritos == null) {
            inscritos = new ArrayList<>();
            estudiantesPorCurso.put(idCurso, inscritos);
        }

        // Verificar si el estudiante ya está inscrito
        if (inscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en este curso.");
        }

        // Inscribir al estudiante en el curso
        inscritos.add(estudiante);
        System.out.println("Estudiante inscrito en el curso " + idCurso + ": " + estudiante);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        ArrayList<Estudiante> inscritos = estudiantesPorCurso.get(idCurso);

        if (inscritos == null || inscritos.isEmpty()) {
            throw new EstudianteNoInscritoEnCursoException("No hay estudiantes inscritos en este curso.");
        }

        // Buscar al estudiante por ID y eliminarlo si existe
        boolean removed = inscritos.removeIf(estudiante -> estudiante.getId() == idEstudiante);

        if (!removed) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante con ID " + idEstudiante + " no está inscrito en el curso " + idCurso + ".");
        } else {
            System.out.println("Estudiante con ID " + idEstudiante + " desinscrito del curso " + idCurso + ".");
        }
    }
}