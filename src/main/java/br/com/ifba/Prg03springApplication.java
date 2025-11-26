package br.com.ifba;

import br.com.ifba.curso.view.CursoList;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Classe principal da aplicação Spring Boot.
 * 
 * - Configura a aplicação para rodar em modo gráfico (headless = false),
 *   permitindo uso de interfaces Swing.
 * - O método init() abre a tela CursoList assim que a aplicação inicializa.
 */

@SpringBootApplication
public class Prg03springApplication {

	public static void main(String[] args) {
            // Inicia o Spring Boot e permite uso de UI Swing
            new SpringApplicationBuilder(Prg03springApplication.class)
                .headless(false)
                .run(args);
    }

    @Bean
    public ApplicationRunner init(CursoList tela) {
        // Exibe a interface gráfica após o carregamento do Spring
        return args -> {
            javax.swing.SwingUtilities.invokeLater(() -> {
                tela.setVisible(true);
            });
        };
	}

}
