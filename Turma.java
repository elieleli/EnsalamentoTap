import java.util.ArrayList;

public class Turma {
    String nome;
    String professor;
    int numAlunos;
    boolean acessivel;
    ArrayList<Integer> horarios;

    Turma(){
        this(null, null, 0, false);
        horarios = new ArrayList<Integer>();
        addHorario(1); addHorario(15); addHorario(29);
    }
    Turma(String nome, String professor, int numAlunos, boolean acessivel){
        this.nome = nome; this.professor = professor; this.numAlunos = numAlunos;
        this.acessivel = acessivel;  horarios = new ArrayList<Integer>();
    }

    

    void addHorario(int horario){
        horarios.add(horario);
    }
    String getAcessibilidade(){
        String z;
        if(acessivel == true){
            z = "sim";
        }
        else{z="não";}
        return z;
    }

    String getHorariosString(){
        String z ="";
        if(horarios != null){
            for(Integer hora:horarios){
                int horaCorreta = 8;
                if(hora<=7){
                    int i = 7;
                    while(horaCorreta-i!=hora){
                        horaCorreta +=2;
                        i++;
                    }
                    z += "segunda "+horaCorreta+"hs";
                }
                else if(hora<=14){
                    int i = 0;
                    while(horaCorreta-i!=hora){
                        horaCorreta += 2;
                        i++;
                    }
                    z += "terça "+horaCorreta+"hs";
                }
                else if(hora<=21){
                    int i = 7;
                    while(horaCorreta+i!=hora){
                        horaCorreta += 2;
                        i--;
                    }
                    z += "quarta "+horaCorreta+"hs";
                }
                else if(hora<=28){
                    int i = 14;
                    while(horaCorreta+i!=hora){
                        i--;
                        horaCorreta+=2;
                    }
                    z += "quinta "+horaCorreta+"hs";
                }
                else{
                    int i = 21;
                    while(horaCorreta+i!=hora){
                        i--;
                        horaCorreta+=2;
                    }
                    z += "sexta "+horaCorreta+"hs";
                }
                z+=", ";
            }
            z = z.substring(0, z.length() - 1);
            z = z.substring(0, z.length() - 1);
            return z;
        }
        else{return "nao ha nada";}
    }

    String getDescricao(){
        String s;
        s = "Turma: "+nome+"\n"+
        "Professor: "+professor+"\n"+
        "Número de Alunos: "+numAlunos+"\n"+
        "Horário: "+ getHorariosString()+"\n"+
        "Acessível: "+getAcessibilidade()+"\n";

        return s;
    }


}
class teste{
    public static void main(String[] args) {
        Turma turma = new Turma();
        String z = turma.getDescricao();
        System.out.println(z);
    }
}
