package com.batch.MigracaoDadosJob.writer;

import com.batch.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoPessoasInvalidasWriterConfig {
    @Bean
    public FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter() {
        return new FlatFileItemWriterBuilder<Pessoa>()
                .name(this.getClass().getName())
                .resource(new FileSystemResource("files/pessoas_invalidas.csv"))
                .delimited()
                .names("id")
                .build();
    }
}
