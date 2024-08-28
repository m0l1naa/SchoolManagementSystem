public class Main {
    public static void main(String[] args) {
        // Crear instancias de Estudiante y Curso
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez", "2000-01-01", "matriculado");
        Estudiante estudiante2 = new Estudiante(2, "Ana", "García", "1999-02-02", "matriculado");

        Curso curso1 = new Curso(101, "Matemáticas", "Curso de matemáticas básicas", 3, "v1");
        Curso curso2 = new Curso(102, "Historia", "Curso de historia mundial", 2, "v1");

        // Instanciar GestorAcademico y probar los métodos
        GestorAcademico gestor = new GestorAcademico();
        gestor.matricularEstudiante(estudiante1);
        gestor.matricularEstudiante(estudiante2);
        gestor.agregarCurso(curso1);
        gestor.agregarCurso(curso2);

        try {
            gestor.inscribirEstudianteCurso(estudiante1, 101);
            gestor.inscribirEstudianteCurso(estudiante2, 102);
            gestor.inscribirEstudianteCurso(estudiante2, 101); // Prueba de inscripción múltiple

            gestor.desinscribirEstudianteCurso(2, 102);
            gestor.desinscribirEstudianteCurso(3, 101); // Estudiante no inscrito
        } catch (EstudianteYaInscritoException | EstudianteNoInscritoEnCursoException e) {
            System.out.println(e.getMessage());
        }
    }
}