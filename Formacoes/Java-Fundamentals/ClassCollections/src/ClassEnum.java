public class ClassEnum {
    enum NivelAcesso {
        COMUM, ADMIN, SUPERVISOR
    }
    public void ex1(){
    NivelAcesso meuNivel = NivelAcesso.ADMIN;

        if(meuNivel == NivelAcesso.ADMIN) {
        System.out.println("Acesso liberado ao painel.");
        }
    }

    public enum StatusPedido {

        AGUARDANDO_PAGAMENTO(1, "Aguardando Pgto") {
            @Override
            public boolean podeCancelar() { return true; }
        },
        PROCESSANDO(2, "Em Processamento") {
            @Override
            public boolean podeCancelar() { return true; }
        },
        ENVIADO(3, "Enviado a Transportadora") {
            @Override
            public boolean podeCancelar() { return false; } // Não cancela se já saiu!
        },
        ENTREGUE(4, "Entregue ao Cliente") {
            @Override
            public boolean podeCancelar() { return false; }
        };

        // Atributos do Enum
        private final int codigo;
        private final String descricao;

        // Construtor (sempre private ou package-private)
        StatusPedido(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        // Método abstrato: Obriga cada constante a definir sua regra
        public abstract boolean podeCancelar();

        public String getDescricao() {
            return descricao;
        }
    }
    public void ex2(){
        StatusPedido atual = StatusPedido.ENVIADO;

        System.out.println("Status: " + atual.getDescricao());

        if (atual.podeCancelar()) {
            System.out.println("Cancelamento efetuado.");
        } else {
            System.out.println("Erro: Não é possível cancelar neste estágio.");
        }
    }
}
