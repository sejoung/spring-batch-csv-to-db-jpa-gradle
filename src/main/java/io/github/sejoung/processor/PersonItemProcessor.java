package io.github.sejoung.processor;

import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;

import io.github.sejoung.dto.CSVFile;
import io.github.sejoung.entity.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<CSVFile, Person> {

  @Override
  public Person process(CSVFile item) throws Exception {
    return Person.builder()
      .createDate(LocalDateTime.now())
      .firstName(item.getFirstName())
      .lastName(item.getLastName())
      .build();
  }
}
