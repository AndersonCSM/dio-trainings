import java.io.File;
import java.io.IOException;

public class ClassExemples {

    // CHECKED: O Java me obriga a adicionar "throws IOException" aqui
    // ou usar um try-catch, senão não compila.
    public static void criarArquivo() throws IOException {
        File arquivo = new File("teste.txt");
        arquivo.createNewFile(); // Esse método lança IOException
    }

    // UNCHECKED: ArithmeticException é RuntimeException.
    // O Java não me obriga a colocar nada, mas vai estourar se rodar.
    public static void dividir(int a, int b) {
        System.out.println(a / b);
    }

    public void ex1(){
        // Tratando a Checked (Obrigatório)
        try {
            criarArquivo();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }

        // A Unchecked eu não sou obrigado a tratar, mas deveria
        // para evitar crash do programa
        try {
            dividir(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Não é possível dividir por zero!");
        }
    }

    public void ex2(){
        System.out.println("Início do programa.");

        try {
            System.out.println("1. Abrindo calculadora...");
            int divisao = 10 / 0; // OPA! Erro aqui (ArithmeticException)
            System.out.println("2. Isso nunca será impresso.");

        } catch (ArithmeticException e) {
            System.out.println("3. ERRO CAPTURADO: Não divida por zero!");

        } finally {
            System.out.println("4. FINALLY: Fechando calculadora (Executa sempre).");
        }

        System.out.println("5. Fim do programa.");
    }
}
