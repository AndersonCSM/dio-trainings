import java.math.BigDecimal;

public class ClassBigDecimal {
    public void ex1(){

        BigDecimal preco = new BigDecimal("10.50");
        BigDecimal quantidade = new BigDecimal("3");
        BigDecimal total = preco.multiply(quantidade); // 31.50 exato
    }

    public void ex2(){
        BigDecimal saldoConta = new BigDecimal("100.00");
        BigDecimal valorSaque = new BigDecimal("100.0000"); // Escala diferente, mesmo valor
        BigDecimal taxaBanco = new BigDecimal("1.50");

        // ERRO COMUM: Usar equals
        if (saldoConta.equals(valorSaque)) {
            System.out.println("Equals diz: Iguais");
        } else {
            System.out.println("Equals diz: Diferentes (por causa das casas decimais)");
        }

        // JEITO CORRETO: Usar compareTo
        // Verifica se saldo >= saque
        // saldo.compareTo(saque) >= 0
        if (saldoConta.compareTo(valorSaque) >= 0) {
            System.out.println("Saque autorizado (Numericamente iguais ou saldo maior)");
        }

        // Cenário de subtração com verificação
        BigDecimal saldoAposSaque = saldoConta.subtract(valorSaque);

        // Verifica se o saldo ficou negativo
        // Se saldoAposSaque < ZERO
        if (saldoAposSaque.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Conta negativada!");
        } else if (saldoAposSaque.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Conta zerada.");
        }
    }
}
