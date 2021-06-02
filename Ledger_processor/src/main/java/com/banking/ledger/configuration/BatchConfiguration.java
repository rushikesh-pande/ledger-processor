package com.banking.ledger.configuration;

import javax.management.NotificationListener;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.banking.ledger.ledgers.Ledgers;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	 @Autowired
	    public JobBuilderFactory jobBuilderFactory;

	    @Autowired
	    public StepBuilderFactory stepBuilderFactory;
	    
	    
	    @Bean
	    public FlatFileItemReader<Ledgers> reader() {
	    	 return new FlatFileItemReaderBuilder<Ledgers>()
	                 .name("ledgerReader")
	                 .resource(new ClassPathResource("Ledgers.csv"))
	                 .delimited()
	                 .names(new String[]{"ledger", "time"})
	                 .lineMapper(lineMapper())
	                 .fieldSetMapper(new BeanWrapperFieldSetMapper<Ledgers>() {{
	                     setTargetType(Ledgers.class);
	                 }})
	                 .build();
	    }
	    
	    
	    @Bean
	    public LineMapper<Ledgers> lineMapper() {

	        final DefaultLineMapper<Ledgers> defaultLineMapper = new DefaultLineMapper<>();
	        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	        lineTokenizer.setDelimiter(";");
	        lineTokenizer.setStrict(false);
	        lineTokenizer.setNames(new String[] {"ledger","time"});

	        final LedgerFieldSetMapper fieldSetMapper = new LedgerFieldSetMapper();
	        defaultLineMapper.setLineTokenizer(lineTokenizer);
	        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

	        return defaultLineMapper;
	    }
	    
	    @Bean
	    public LedgerProcessor processor() {
	    	return new LedgerProcessor();
	    }
	    
	    
	    @Bean
	    public JdbcBatchItemWriter<Ledgers> writer(final DataSource dataSource) {
	        return new JdbcBatchItemWriterBuilder<Ledgers>()
	                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	                .sql("INSERT INTO Ledgers (ledgers, time) VALUES (:ledgers, :time)")
	                .dataSource(dataSource)
	                .build();
	    }

	    @Bean
	    public Job importLedgersJob(NotificationListener listener, Step step1) {
	        return jobBuilderFactory.get("importLedgersJob")
	                .incrementer(new RunIdIncrementer())
	                .listener(listener)
	                .flow(step1)
	                .end()
	                .build();
	    }

	    @Bean
	    public Step step1(JdbcBatchItemWriter<Ledgers> writer) {
	        return stepBuilderFactory.get("step1")
	                .<Ledgers, Ledgers> chunk(10)
	                .reader(reader())
	                .processor(processor())
	                .writer(writer)
	                .build();
	    }
	    

}
