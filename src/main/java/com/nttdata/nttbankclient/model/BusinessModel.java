package com.nttdata.nttbankclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "business")
public class BusinessModel {
    @Id
    private String ruc;

    private String name;

    private String manager_name;

    private String phone;

    private String email;

    private Date reg_date;
}
