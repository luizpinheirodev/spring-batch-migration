package com.batch.MigracaoDadosJob.writer;

import com.batch.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoasClassifierWriterConfig {
    @Bean
    public ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter(
            JdbcBatchItemWriter<Pessoa> bancoPessoaWriter,
            FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter){
        return new ClassifierCompositeItemWriterBuilder<Pessoa>()
                .classifier(classifer(bancoPessoaWriter, arquivoPessoasInvalidasWriter))
                .build();
    }

    private Classifier<Pessoa, ItemWriter<? super Pessoa>> classifer(JdbcBatchItemWriter<Pessoa> bancoPessoaWriter, FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {
        return new Classifier<Pessoa, ItemWriter<? super Pessoa>>() {
            @Override
            public ItemWriter<? super Pessoa> classify(Pessoa pessoa) {
                if(pessoa.isValida())
                    return bancoPessoaWriter;
                else
                    return arquivoPessoasInvalidasWriter;
            }
        };
    }
}
