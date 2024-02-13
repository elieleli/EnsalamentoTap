class Sala{
    int bloco;
    int sala;
    int capacidade;
    boolean acessivel;

    Sala(){
        this(0,0,0,false); 
    }

    Sala(int bloco, int sala, int capacidade, boolean acessivel){
        this.bloco = bloco; this.sala = sala;
        this.capacidade = capacidade; this.acessivel = acessivel;
    }

    String getAcessibilidade(){
        String z;
        if(acessivel){
            z = "acessível";
        }
        else{
            z = "não acessível";
        }
        return z;
    }

    String getDescricao(){
        String z;
        z = "Bloco "+bloco+", Sala "+sala+" ("+capacidade+" lugares, "+getAcessibilidade()+")";
        return z;
    }
}

class peido{
    public static void main(String[] args) {
        Sala pum = new Sala();
        String z = pum.getDescricao();
        System.out.println(z);
    }
}