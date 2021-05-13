package io.github.sejoung.fieldsetmapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import io.github.sejoung.dto.CSVFile;

public class PersonFieldSetMapper implements FieldSetMapper<CSVFile> {
  @Override
  public CSVFile mapFieldSet(FieldSet fieldSet) {
    return new CSVFile(fieldSet.readString(0), fieldSet.readString(1));
  }
}
