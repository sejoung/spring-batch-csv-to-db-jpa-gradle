package io.github.sejoung.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CSVFile {

  private final String lastName;
  private final String firstName;

  public CSVFile(String lastName, String firstName) {
    this.lastName = lastName;
    this.firstName = firstName;
  }

}
