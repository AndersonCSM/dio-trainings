import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class ClassStream {

    public void ex0(){
        List<Integer> numeros = Arrays.asList(1, 5, 8, 10, 15, 3);

        // Imprimir apenas números pares
        numeros.stream()
                .filter(n -> n % 2 == 0) // Filtro (Intermediário)
                .forEach(System.out::println); // Terminal
    }

    class Produto {
        String nome;
        String categoria;
        double preco;

        public Produto(String nome, String categoria, double preco) {
            this.nome = nome;
            this.categoria = categoria;
            this.preco = preco;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public void ex2(){
        List<Produto> estoque = Arrays.asList(
                new Produto("Notebook", "Eletrônico", 3000.00),
                new Produto("Cadeira", "Móveis", 500.00),
                new Produto("Smartphone", "Eletrônico", 2000.00),
                new Produto("Mesa", "Móveis", 800.00)
        );

        // Pipeline do Stream
        List<String> promocaoEletronicos = estoque.stream()
                // 1. Filter: Apenas Eletrônicos
                .filter(p -> p.categoria.equals("Eletrônico"))

                // 2. Peek: Debug (opcional, mostra o fluxo passando)
                .peek(p -> System.out.println("Filtrado: " + p.nome))

                // 3. Map: Transforma Produto -> String (Nome em Capslock)
                .map(p -> p.nome.toUpperCase() + " PROMOÇÃO")

                // 4. Sorted: Ordena alfabeticamente
                .sorted()

                // 5. Collect: Fecha o stream e retorna uma Lista
                .collect(Collectors.toList());

        System.out.println("\nResultado Final: " + promocaoEletronicos);
    }
}
