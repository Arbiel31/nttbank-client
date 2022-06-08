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

    //Tipo de transacción 1=retiro / 2=deposito
    private String type;

    //Fondos de la cuenta
    private float funds;

    //Monto a retirar/depositar
    private float amount;

    //Saldo tras operación
    private float balance;

    //Número de cuenta en que se realiza la operación
    private String account;

    //DNI para persona, RUC para empresa
    private String client;
}
