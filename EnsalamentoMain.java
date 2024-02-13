public class EnsalamentoMain{
    public static void main(String[] args) {
        Ensalamento e1 = new Ensalamento();
        Sala s1 = new Sala(6, 101, 50, true);
        Sala s2 = new Sala(6, 102, 100, true);
        Sala s3 = new Sala(6, 203, 50, false);
        Sala s4 = new Sala(6, 204, 100, false);
        e1.addSala(s1);
        e1.addSala(s2);
        e1.addSala(s3);
        e1.addSala(s4);
        Turma t1 = new Turma("Técnicas de Programação", "Horácio Fernandes", 50, false);
        t1.addHorario(11);
        t1.addHorario(25);
        t1.addHorario(32);
        e1.addTurma(t1);
        Turma t2 = new Turma("Laboratório de Programação C", "Edson Nascimento", 25, true);
        t2.addHorario(1);
        t2.addHorario(15);
        t2.addHorario(29);
        e1.addTurma(t2);
        Turma t3 = new Turma("Algoritmos e Estrutura de Dados I", "Edleno Silva", 60, false);
        t3.addHorario(1);
        t3.addHorario(15);
        t3.addHorario(29);
        e1.addTurma(t3);
        e1.alocarTodas();
        System.out.println(e1.relatorioTurmasPorSala());

    }
}
