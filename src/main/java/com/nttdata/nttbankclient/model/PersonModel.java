package com.nttdata.nttbankclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "person")
public class PersonModel {
    @Id
    private String dni;

    private String name;

    private String last_name;

    private String phone;

    private String email;

    private Date reg_date;
}
