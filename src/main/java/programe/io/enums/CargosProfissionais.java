package programe.io.enums;

public enum CargosProfissionais {
    
    DIRETOR("Diretor(a)"),
    COORDENADOR("Coordenador(a)"),
    AUXILIAR_ADMINISTRATIVO("Auxiliar Administrativo"),
    PROFESSOR("Professor(a)"),
    AUXILIAR_DE_SERVICOS_GERAIS("Auxiliar de Serviços Gerais"),
    CUIDADOR("Cuidador(a)"),
    PORTEIRO("Porteiro(a)");

    private final String cargo;

    CargosProfissionais(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
    
     public String setCargo() {
        return cargo;
    }
   
    @Override
    public String toString() {
        return cargo;
    }
  
    
    
    
    public static CargosProfissionais fromString(String value) {
        for (CargosProfissionais cargo : CargosProfissionais.values()) {
            if (cargo.cargo.equalsIgnoreCase(value)) {
                return cargo;
            }
        }
        return null; // Retorna null se não encontrar um cargo correspondente
       }

  
 

    
}
