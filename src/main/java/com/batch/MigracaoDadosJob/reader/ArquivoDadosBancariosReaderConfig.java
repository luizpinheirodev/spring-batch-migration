package com.batch.MigracaoDadosJob.reader;

import com.batch.MigracaoDadosJob.dominio.DadosBancarios;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoDadosBancariosReaderConfig {
    @Bean
    public FlatFileItemReader<DadosBancarios> arquivoDadosBancariosReader() {
        return new FlatFileItemReaderBuilder<DadosBancarios>()
                .name(this.getClass().getName())
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("pessoasId", "agencia", "conta", "banco", "id")
                .addComment("--") //Ignorar linha com comentário
                .targetType(DadosBancarios.class) // Quando possui objetos simples de mapear
                .build();
    }
}
