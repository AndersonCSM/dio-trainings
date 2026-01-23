import java.util.Date;
import java.util.Calendar;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ClassTime {

    public void ex1(){
        // --- JEITO ANTIGO (LEGADO) ---
        // Criar uma data específica: 25 de Dezembro de 2025
        Calendar calendario = Calendar.getInstance();
        calendario.set(2025, Calendar.DECEMBER, 25); // Note o uso da constante, pois Dezembro seria 11
        Date dataLegada = calendario.getTime();
        System.out.println("Legado: " + dataLegada);

        // --- JEITO MODERNO (JAVA 8+) ---

        // 1. LocalDate (Apenas Data)
        LocalDate dataHoje = LocalDate.now();
        LocalDate natal = LocalDate.of(2025, 12, 25); // Mês 12 é Dezembro!

        // 2. LocalTime (Apenas Hora)
        LocalTime horaAlmoco = LocalTime.parse("12:30:00");

        // 3. LocalDateTime (Data e Hora sem fuso)
        LocalDateTime reuniao = LocalDateTime.of(natal, horaAlmoco);
        System.out.println("Reunião (Local): " + reuniao);

        // 4. OffsetDateTime (Data e Hora com fuso para APIs)
        // Adiciona o offset de -03:00 (Brasília padrão)
        OffsetDateTime dataComOffset = OffsetDateTime.of(reuniao, ZoneOffset.of("-03:00"));
        System.out.println("Para API (Offset): " + dataComOffset);
    }

    public void ex2(){
        // 1. Simulando dado vindo de sistema legado (java.util.Date)
        Date dataCriacaoLegada = new Date(); // Data atual "antiga"

        System.out.println("--- Sistema de Logística ---");

        // 2. Conversão: Date -> Instant -> LocalDateTime
        // Instant é um ponto na linha do tempo UTC (ponte entre antigo e novo)
        LocalDateTime dataCriacao = LocalDateTime.ofInstant(
                dataCriacaoLegada.toInstant(),
                ZoneId.systemDefault() // Pega o fuso do seu computador
        );

        // 3. Lógica de Negócio: Prazo de 5 dias
        // Vantagem da imutabilidade: 'dataCriacao' permanece intacta
        LocalDateTime dataVencimento = dataCriacao.plusDays(5).withHour(23).withMinute(59);

        // 4. Tratando Fusos Diferentes (OffsetDateTime)
        // O servidor está no Brasil (-03:00)
        ZoneOffset offsetBrasil = ZoneOffset.of("-03:00");
        OffsetDateTime dataVencimentoBR = OffsetDateTime.of(dataVencimento, offsetBrasil);

        // Mas o banco de dados exige UTC (Z ou +00:00)
        OffsetDateTime dataVencimentoUTC = dataVencimentoBR.withOffsetSameInstant(ZoneOffset.UTC);

        // 5. Formatação para o Usuário (String)
        DateTimeFormatter formatadorBR = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

        System.out.println("Pedido criado em: " + dataCriacao.format(formatadorBR));
        System.out.println("Vencimento (Cliente BR): " + dataVencimentoBR);
        System.out.println("Vencimento (Banco UTC):  " + dataVencimentoUTC);

        // Comparação robusta
        if (dataVencimentoBR.isAfter(OffsetDateTime.now(offsetBrasil))) {
            System.out.println("Status: Dentro do prazo.");
        } else {
            System.out.println("Status: Vencido.");
        }
    }


}
