package com.nttdata.nttbankclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model.
 *
 * @author Percy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transaction")
public class TransactionModel {
  @Id
  private String id;

  //Tipo de transacción 1=retiro / 2=deposito / 3=transferencia
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

  //Contador de transacciones para habilitar cobro de comisión
  private int counter;

  //Monto de comisión a cobrar
  private float commission;

  //Flag para identificar si es una transacción a un tercero: 1=personal/2=tercero
  private String thirdParty;

  private String destinationAccount;

  private String dateTransaction;
}
