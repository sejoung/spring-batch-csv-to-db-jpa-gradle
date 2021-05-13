package io.github.sejoung.jobs;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import io.github.sejoung.dto.CSVFile;
import io.github.sejoung.entity.Person;
import io.github.sejoung.fieldsetmapper.PersonFieldSetMapper;
import io.github.sejoung.processor.PersonItemProcessor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class FileToDatabaseJob {

  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final EntityManagerFactory entityManagerFactory;

  @Bean
  public Job csvToDatabaseJob() {
    return jobBuilderFactory.get("csvToDatabaseJob")
      .start(step1()).build();
  }

  private FlatFileItemReader<CSVFile> reader() {
    return new FlatFileItemReaderBuilder<CSVFile>()
      .name("personItemReader")
      .resource(new ClassPathResource("sample-data.csv"))
      .delimited().names(new String[] {"firstName", "lastName"})
      .fieldSetMapper(new PersonFieldSetMapper()).build();
  }

  private PersonItemProcessor processor() {
    return new PersonItemProcessor();
  }

  private JpaItemWriter<Person> writer() {
    return new JpaItemWriterBuilder<Person>().entityManagerFactory(entityManagerFactory).build();
  }

  private Step step1() {
    return stepBuilderFactory.get("step1")
      .<CSVFile, Person>chunk(10)
      .reader(reader())
      .processor(processor())
      .writer(writer())
      .build();
  }

}
