package br.com.dio.persistence;

import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

/**
 * Utilitário para gerenciar conexões com o banco de dados.
 * Lê as configurações do arquivo db.properties e fornece conexões JDBC.
 * 
 * Boas práticas implementadas:
 * - Construtor privado (utility class - não deve ser instanciada)
 * - Método estático para obter conexões
 * - Configurações externalizadas em arquivo .properties
 */
@NoArgsConstructor(access = PRIVATE)
public class ConnectionUtil {
    
    /**
     * Cria e retorna uma nova conexão com o banco de dados.
     * As configurações (URL, usuário, senha) são lidas do arquivo db.properties.
     * 
     * @return Uma nova conexão JDBC ativa
     * @throws SQLException Se não conseguir conectar ao banco
     * @throws RuntimeException Se o arquivo db.properties não for encontrado ou estiver inválido
     */
    public static Connection getConnection() throws SQLException {
        // Objeto para armazenar as propriedades do arquivo
        Properties props = new Properties();

        // Try-with-resources: fecha automaticamente o FileInputStream
        try (FileInputStream fis = new FileInputStream("db.properties")){
            // Carrega as propriedades do arquivo (formato chave=valor)
            props.load(fis);
        } catch (IOException e){
            throw new RuntimeException("Erro ao carregar arquivo de configuração", e);
        }

        // Cria e retorna a conexão usando as propriedades lidas
        return DriverManager.getConnection(
                props.getProperty("db.url"),      // Ex: jdbc:mysql://localhost:3306/test
                props.getProperty("db.user"),     // Ex: root
                props.getProperty("db.password")  // Ex: root
        );

    }
}
