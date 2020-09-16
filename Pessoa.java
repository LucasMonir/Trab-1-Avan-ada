public class Pessoa {
    private static int matricula;
    private static int cpf;
    private static String nome;
    private static String materia;
    private static String turno;

    public static int getMatricula() {
        return matricula;
    }

    public static String getTurno() {
        return turno;
    }

    public static void setTurno(String turno) {
        Pessoa.turno = turno;
    }

    public static String getMateria() {
        return materia;
    }

    public static void setMateria(String materia) {
        Pessoa.materia = materia;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Pessoa.nome = nome;
    }

    public static int getCpf() {
        return cpf;
    }

    public static void setCpf(int cpf) {
        Pessoa.cpf = cpf;
    }

    public static void setMatricula(int matricula) {
        Pessoa.matricula = matricula;
    }
}
