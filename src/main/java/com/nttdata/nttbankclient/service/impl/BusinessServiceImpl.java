package com.nttdata.nttbankclient.service.impl;

import com.nttdata.nttbankclient.exception.CustomException;
import com.nttdata.nttbankclient.model.BusinessModel;
import com.nttdata.nttbankclient.repository.BusinessRepository;
import com.nttdata.nttbankclient.service.BusinessService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessServiceImpl implements BusinessService {
  private final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

  @Autowired
  BusinessRepository businessRepository;

  @Value("${dup.business.exception}")
  private String message;

  @Override
  public Flux<BusinessModel> findAll() {
    return businessRepository.findAll();
  }

  @Override
  public Mono<BusinessModel> create(BusinessModel business) {
    business.setRegDate(new java.sql.Timestamp(new Date().getTime()));

    logger.info("Verifying business duplicates");

    //Se verifica la no duplicidad de la empresa a registrar
    return businessRepository.findByRuc(business.getRuc())
            .flatMap(er -> Mono.error(new CustomException(message)))
            .switchIfEmpty(Mono.defer(() -> businessRepository.save(business)))
            .cast(BusinessModel.class);
  }

  @Override
  public Mono<BusinessModel> update(BusinessModel business) {
    return businessRepository.save(business);
  }

  @Override
  public Mono<Void> deleteByRuc(String ruc) {
    return businessRepository.deleteByRuc(ruc);
  }

  @Override
  public Mono<BusinessModel> findByRuc(String ruc) {
    return businessRepository.findByRuc(ruc);
  }
}
