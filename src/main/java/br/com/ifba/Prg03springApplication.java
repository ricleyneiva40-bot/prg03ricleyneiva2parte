package br.com.ifba;

import br.com.ifba.curso.view.CursoList;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Prg03springApplication {

	public static void main(String[] args) {
            new SpringApplicationBuilder(Prg03springApplication.class)
                .headless(false)
                .run(args);
    }

    @Bean
    public ApplicationRunner init(CursoList tela) {
        return args -> {
            javax.swing.SwingUtilities.invokeLater(() -> {
                tela.setVisible(true);
            });
        };
	}

}
