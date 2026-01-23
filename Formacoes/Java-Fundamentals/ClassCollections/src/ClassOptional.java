import java.util.Optional;

public class ClassOptional {
    // Simulando um método que busca no banco de dados
    public Optional<String> buscarUsuario(int id) {
        if (id == 1) return Optional.of("João");
        return Optional.empty(); // Retorno explícito de vazio
    }

    public void ex1(){
        // Uso correto
        Optional<String> resultado = buscarUsuario(99);

        // "Se existir, imprima. Se não, use 'Desconhecido'"
        System.out.println(resultado.orElse("Usuário Desconhecido"));

        // "Se existir, execute este bloco lambda"
        resultado.ifPresent(nome -> System.out.println("Encontrado: " + nome));
    }

    // Simula um banco de dados
    public static Optional<String> buscarNomeNoBanco(int id) {
        if (id == 1) return Optional.of("Carlos Drummond");
        if (id == 2) return Optional.of("Clarice Lispector");
        return Optional.empty(); // Usuário não existe
    }

    public void ex2(){
        Optional<String> resultado = buscarNomeNoBanco(99);

        // 1. orElse: Se não tiver nada, use um valor padrão
        String nome = resultado.orElse("Visitante Anônimo");
        System.out.println("Nome: " + nome);

        // 2. orElseThrow: Se não tiver nada, lance um erro (muito usado em APIs)
        try {
            // resultado.orElseThrow(() -> new IllegalArgumentException("ID não encontrado!"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // 3. ifPresent: Se tiver, faça algo (Programação Funcional)
        buscarNomeNoBanco(1).ifPresent(n -> {
            System.out.println("Enviando email para: " + n.toUpperCase());
        });

        // 4. map: Se tiver valor, transforme-o (sem tirar da caixa)
        Optional<Integer> tamanhoDoNome = buscarNomeNoBanco(2)
                .map(n -> n.length()); // Transforma String em Integer

        System.out.println("Tamanho do nome da Clarice: " + tamanhoDoNome.get());
    }
}
