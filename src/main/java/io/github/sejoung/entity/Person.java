package io.github.sejoung.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Person")
@Entity
@ToString
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "person_id")
  private Long personId;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "create_dt")
  private LocalDateTime createDate;

  @Builder
  private Person(Long personId, String lastName, String firstName, LocalDateTime createDate) {
    this.personId = personId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.createDate = createDate;
  }
}
