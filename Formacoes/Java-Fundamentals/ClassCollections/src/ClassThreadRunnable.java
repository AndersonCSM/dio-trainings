import java.util.concurrent.atomic.AtomicInteger;

public class ClassThreadRunnable {
    // FORMA 1: Estendendo Thread (Menos recomendada)
    class MinhaThread extends Thread {
        @Override
        public void run() {
            System.out.println("Sou a Thread 1 rodando!");
        }
    }

    // FORMA 2: Implementando Runnable (Recomendada)
    class MinhaTarefa implements Runnable {
        @Override
        public void run() {
            System.out.println("Sou a Runnable rodando na Thread: " + Thread.currentThread().getName());
        }
    }

    public void ex1(){
        System.out.println("Início do Main (Thread Principal)");

        // Iniciando Forma 1
        MinhaThread t1 = new MinhaThread();
        t1.start(); // ATENÇÃO: Nunca chame .run() direto! Use .start()

        // Iniciando Forma 2
        MinhaTarefa tarefa = new MinhaTarefa();
        Thread t2 = new Thread(tarefa); // Colocamos a tarefa dentro da Thread
        t2.start();

        // Lambda (Jeito moderno de usar Runnable)
        Thread t3 = new Thread(() -> System.out.println("Sou uma Lambda Runnable!"));
        t3.start();

        System.out.println("Fim do Main");
    }

    class ContadorSite {
        private int acessos = 0;

        // JEITO ERRADO: Sem sincronização
        // Duas threads podem ler o valor "10", somar 1 e ambas gravarem "11", perdendo uma contagem.
        public void incrementarInseguro() {
            acessos++;
        }

        // JEITO CERTO (Opção 1): synchronized
        // O Java "tranca" o método. Só uma thread entra por vez. É seguro, mas mais lento.
        public synchronized void incrementarSeguro() {
            acessos++;
        }

        public int getAcessos() {
            return acessos;
        }
    }

    public void ex2(){
        ContadorSite contador = new ContadorSite();

        // Criando uma tarefa que simula 1000 cliques
        Runnable tarefaClicar = () -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementarInseguro(); // Tente mudar para incrementarSeguro()
            }
        };

        // Criando duas threads para simular acesso simultâneo
        Thread t1 = new Thread(tarefaClicar);
        Thread t2 = new Thread(tarefaClicar);

        t1.start();
        t2.start();

        try {
            // A Thread Main vai pausar aqui e esperar a T1 e T2
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // OPA! Alguém interrompeu a espera!

            System.err.println("A espera pelas threads foi interrompida!");

            // Boa prática: Restaurar o estado de interrupção
            // (explicarei isso abaixo se tiver curiosidade, é um conceito avançado)
            Thread.currentThread().interrupt();

            // Decisão de negócio: Parar o programa ou continuar mesmo sem os resultados?
            return;
        }

        System.out.println("Total de Acessos: " + contador.getAcessos());
    }


}
