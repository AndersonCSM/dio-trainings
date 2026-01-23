import java.util.ArrayList;
import java.util.List;

public class ClassGenerics {
    class Caixa<T> {
        private T conteudo;

        public void guardar(T conteudo) {
            this.conteudo = conteudo;
        }

        public T pegar() {
            return conteudo;
        }
    }

    public void ex1(){
        // Caixa especificamente de String
        Caixa<String> caixaTexto = new Caixa<>();
        caixaTexto.guardar("Segredo");
        // caixaTexto.guardar(123); // ERRO DE COMPILAÇÃO! (Segurança)

        String item = caixaTexto.pegar(); // Não precisa de cast (String)
        System.out.println(item);
    }

    // Método Genérico: Aceita Lista de qualquer coisa que seja "filha" de Number
    // T extends Number: Aceita Integer, Double, Float, Long...
    // O '?' (Wildcard) também é comum, mas o T permite referenciar o tipo.
    public static <T extends Number> double calcularMedia(List<T> numeros) {
        if (numeros.isEmpty()) return 0.0;

        double soma = 0.0;
        for (T n : numeros) {
            soma += n.doubleValue(); // Garante que tem .doubleValue()
        }
        return soma / numeros.size();


    }

    public void ex2(){
        List<Integer> notasInteiras = new ArrayList<>();
        notasInteiras.add(8);
        notasInteiras.add(10);

        List<Double> notasDecimais = new ArrayList<>();
        notasDecimais.add(7.5);
        notasDecimais.add(9.2);

        // O mesmo método funciona para listas de tipos diferentes!
        System.out.println("Média Inteiros: " + calcularMedia(notasInteiras));
        System.out.println("Média Decimais: " + calcularMedia(notasDecimais));

        // List<String> textos = new ArrayList<>();
        // calcularMedia(textos); // ERRO: String não estende Number
    }
}
