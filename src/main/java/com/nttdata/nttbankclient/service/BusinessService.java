package com.nttdata.nttbankclient.service;

import com.nttdata.nttbankclient.model.BusinessModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessService {

  Flux<BusinessModel> findAll();

  Mono<BusinessModel> create(BusinessModel business);

  Mono<BusinessModel> update(BusinessModel business);

  Mono<Void> deleteByRuc(String ruc);

  Mono<BusinessModel> findByRuc(String ruc);
}
