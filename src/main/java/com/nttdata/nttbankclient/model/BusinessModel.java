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
@Document(collection = "business")
public class BusinessModel {
  @Id
  private String ruc;

  private String name;

  private String managerName;

  private String phone;

  private String email;

  private Date regDate;
}
