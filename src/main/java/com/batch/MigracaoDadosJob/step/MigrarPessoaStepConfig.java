package com.batch.MigracaoDadosJob.step;

import com.batch.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarPessoaStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step migrarPessoaStep(ItemReader<Pessoa> arquivoPessoaReader,
                                 ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
                                 FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {
        return stepBuilderFactory
                .get("migrarPessoaStep")
                .<Pessoa, Pessoa>chunk(10000)
                .reader(arquivoPessoaReader)
                .writer(pessoaClassifierWriter)
                .stream(arquivoPessoasInvalidasWriter) // ClassifierComposite não realiza abertura e fechamento de recurso. Por isso add o stream
                .build();
    }
}
