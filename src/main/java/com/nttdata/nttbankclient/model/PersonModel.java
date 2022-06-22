package com.nttdata.nttbankclient.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "person")
public class PersonModel {
  @Id
  private String dni;

  private String name;

  private String lastName;

  private String phone;

  private String email;

  private Date regDate;
}
