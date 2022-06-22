package com.nttdata.nttbankclient.service.impl;

import com.nttdata.nttbankclient.exception.CustomException;
import com.nttdata.nttbankclient.model.TransactionModel;
import com.nttdata.nttbankclient.repository.BusinessRepository;
import com.nttdata.nttbankclient.repository.PersonRepository;
import com.nttdata.nttbankclient.repository.TransactionRepository;
import com.nttdata.nttbankclient.service.TransactionService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service.
 *
 * @author Percy
 */
@Service
public class TransactionServiceImpl implements TransactionService {
  private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  PersonRepository personRepository;

  @Autowired
  BusinessRepository businessRepository;

  @Value("${type.transaction.exception}")
  private String message;

  @Value("${balance.transaction.exception}")
  private String messageBalance;

  @Override
  public Flux<TransactionModel> findAll() {
    return transactionRepository.findAll();
  }

  @Override
  public Mono<TransactionModel> create(TransactionModel transactionAux) {
    TransactionModel transaction = calculateCommission(transactionAux);

    float balance = 0;
    logger.info("Calculating balance");

    //Se calcula el saldo tras la operación
    switch (transaction.getType()) {
      case "1":
        balance = transaction.getFunds() - transaction.getAmount() - transaction.getCommission();
        logger.info("Withdraw, calculating balance");
        break;
      case "2":
        balance = transaction.getFunds() + transaction.getAmount() - transaction.getCommission();
        logger.info("Deposit, calculating balance");
        break;
      case "3":
        balance = transaction.getFunds() - transaction.getAmount() - transaction.getCommission();
        logger.info("Transfer, calculating balance");
        break;
      default:
        logger.error(message);
        //Si se recibe un tipo de operación diferente, devolverá la excepcion
        throw new CustomException(message);
    }

    if (balance < 0) {
      logger.error(messageBalance);

      //La operación no se completará si no hay fondos suficientes en la cuenta

      throw new CustomException(messageBalance);
    }

    transaction.setBalance(balance);

    if (transaction.getType() == "3") {
      calculateTransferBalance(transaction);
    }

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = formatter.format(date);

    transaction.setDateTransaction(strDate);

    return transactionRepository.save(transaction);
  }

  @Override
  public Mono<TransactionModel> update(TransactionModel transaction) {
    return transactionRepository.save(transaction);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return transactionRepository.deleteById(id);
  }

  @Override
  public Mono<TransactionModel> findById(String id) {
    return transactionRepository.findById(id);
  }

  @Override
  public Flux<TransactionModel> findByClient(String doc) {
    return transactionRepository.findByClient(doc);
  }

  @Override
  public Flux<TransactionModel> findByAccount(String number) {
    return transactionRepository.findByAccount(number);
  }

  @Override
  public Flux<TransactionModel> reportCommission(String startDate, String endDate, String account) {
    return transactionRepository.reportCommission(startDate, endDate, account);
  }

  private TransactionModel calculateCommission(TransactionModel transaction) {
    if (transaction.getCounter() > 100) {
      transaction.setCommission(5.0F);
    }
    return transaction;
  }

  private void calculateTransferBalance(TransactionModel transaction) {
    TransactionModel destAccount = new TransactionModel();
    destAccount.setType("4");
    destAccount.setAmount(transaction.getAmount());
    destAccount.setAccount(transaction.getDestinationAccount());
    if (transaction.getThirdParty() == "1") {
      destAccount.setClient(transaction.getClient());
    }

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = formatter.format(date);

    destAccount.setDateTransaction(strDate);

    transactionRepository.save(destAccount);
  }
}
