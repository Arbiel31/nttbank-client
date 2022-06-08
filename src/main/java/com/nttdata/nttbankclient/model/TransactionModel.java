package com.nttdata.nttbankclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transaction")
public class TransactionModel {
    @Id
    private String id;

    private String type;

    private float funds;

    private float amount;

    private float balance;

    private String account;

    private String client;
}
