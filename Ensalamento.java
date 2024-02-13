import java.util.ArrayList;

public class Ensalamento {
    ArrayList<Sala> salas;
    ArrayList<Turma> turmas;
    ArrayList<TurmaEmSala> ensalamento;

    Ensalamento(){
        salas = new ArrayList<Sala>();
        turmas = new ArrayList<Turma>();
        ensalamento = new ArrayList<TurmaEmSala>();
    }

    void addTurma(Turma turma){
        this.turmas.add(turma);
    }

    void addSala(Sala sala){
        this.salas.add(sala);
    }
    
    Sala getSala(Turma turma){
        for(TurmaEmSala turmas:ensalamento){
            if(turma.nome.equals(turmas.turma.nome)){
                return turmas.sala;
            }
        }
        return null;
    }

    boolean salaDisponivel(Sala sala, int horario){
        for(TurmaEmSala tes: ensalamento){
            if(tes.sala == sala){
                for(Integer hora: tes.turma.horarios){
                    if (hora==horario){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    boolean salaDisponivel(Sala sala, ArrayList<Integer> horarios){
        for(TurmaEmSala tes: ensalamento){
            if(tes.sala == sala){
                for(Integer hora: tes.turma.horarios){
                    for(Integer horab:horarios){
                        if (hora==horab){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean naoPertence(TurmaEmSala z){
        for(TurmaEmSala tes:ensalamento){
            if(tes.turma == z.turma){
                return false;
            }
        }
        return true;
    }

    boolean alocar(Turma turma, Sala sala){
            if(possoAlocar(turma, sala)){
                TurmaEmSala z = new TurmaEmSala(turma, sala);
                if(naoPertence(z)){
                    this.ensalamento.add(z);
                }
                return true;
            }
            else{return false;}
    }

    boolean alocarbruto(Turma turma, Sala sala){
        if((turma.acessivel == sala.acessivel) && (turma.numAlunos<=sala.capacidade) && salaDisponivel(sala, turma.horarios)){
            TurmaEmSala z = new TurmaEmSala(turma, sala);
            if(naoPertence(z)){
                return true;
            }
            else{return false;}
        }
        else{return false;}
    }

        
    boolean possoAlocar(Turma turma, Sala sala){
        if(turma.acessivel){
            if((turma.acessivel == sala.acessivel) && (turma.numAlunos<=sala.capacidade) && salaDisponivel(sala, turma.horarios)){
                TurmaEmSala z = new TurmaEmSala(turma, sala);
                if(naoPertence(z)){
                    return true;
                }
                else{return false;}
            }
            else if((turma.acessivel == sala.acessivel) && (turma.numAlunos<=sala.capacidade) && 
            salaDisponivel(sala, turma.horarios) == false){

            }
        }
        else{
            if((turma.numAlunos<=sala.capacidade) && salaDisponivel(sala, turma.horarios)){
                TurmaEmSala z = new TurmaEmSala(turma, sala);
                if(naoPertence(z)){
                    return true;
                }
                else{return false;}
            }
            else{return false;}
        }
        return false;
    }

    void alocarTodas(){
        for(Turma turma: turmas){
            if(turma.acessivel){
                Sala salaIdeal = null;
                int j = 0;
                while((salaIdeal == null)&&(j<salas.size())){
                    if(possoAlocar(turma, salas.get(j))){salaIdeal = salas.get(j);}
                    else{j++;}
                    
                }
                if(salaIdeal!=null){
                    for(int i=j; i<salas.size(); i++){
                        if((salas.get(i).capacidade < salaIdeal.capacidade) && 
                        (salas.get(i).capacidade>=turma.numAlunos) &&
                        possoAlocar(turma, salas.get(i))){
                            salaIdeal = salas.get(i);
                        }
                    }
                    alocar(turma, salaIdeal);
                }
            }

        }
        for(Turma turma: turmas){
            if(turma.acessivel == false){
                Sala salaIdeal = null;
                int j = 0;
                while((salaIdeal == null)&&(j<salas.size())){
                    if(possoAlocar(turma, salas.get(j))){salaIdeal = salas.get(j);}
                    else{j++;}
                    
                }
                if(salaIdeal!=null){
                    for(int i=j; i<salas.size(); i++){
                        if((salas.get(i).capacidade < salaIdeal.capacidade) && 
                        (salas.get(i).capacidade>=turma.numAlunos) &&
                        possoAlocar(turma, salas.get(i))){
                            salaIdeal = salas.get(i);
                        }
                    }
                    alocar(turma, salaIdeal);
                }
            }

        }
    }


    int getTotalTurmasAlocadas(){
        int i = 0;
        for(TurmaEmSala tes: ensalamento){
            if(tes.sala != null){i++;}
        }
        return i;
    }

    int getTotalEspacoLivre(){
        int total = 0;
        for(TurmaEmSala tes: ensalamento){
            total += tes.sala.capacidade -tes.turma.numAlunos;
        }
        return total;
    }

    String relatorioResumoEnsalamento(){
        String s;
        s = "Total de Salas: "+salas.size()+"\n"+
        "Total de Turmas: "+turmas.size()+"\n"+
        "Turmas Alocadas: "+getTotalTurmasAlocadas()+"\n"+
        "Espaços Livres: "+getTotalEspacoLivre()+"\n";

        return s;
    }

    String relatorioTurmasPorSala(){
        String s = relatorioResumoEnsalamento()+"\n";
        for(Sala sala: salas){
            Turma turma = getTurma(sala);
            s += "--- "+sala.getDescricao()+" ---\n";
            s+= "\n";
            if(turma!= null){
                for(TurmaEmSala tes:ensalamento){
                    if(sala == tes.sala){
                        s += tes.turma.getDescricao()+"\n";
                    }
                }
            }
           
        }
        return s;
    }

    Turma getTurma(Sala sala){
        for(TurmaEmSala turmas:ensalamento){
            if(sala == turmas.sala){
                return turmas.turma;
            }
        }
        return null;
    }

    String relatorioSalasPorTurma(){
        String s = relatorioResumoEnsalamento();
        for(Turma turma: turmas){
            Sala sala = getSala(turma);
            s +="\n";
            if(sala!= null){
                s += turma.getDescricao()+"Sala: "+sala.getDescricao();
            }
            else{
                s += turma.getDescricao()+"Sala: SEM SALA \n";
            }
        }
        return s;
    }
}

class peido{
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
