package com.nttdata.nttbankclient.service.impl;

import com.nttdata.nttbankclient.model.BusinessModel;
import com.nttdata.nttbankclient.repository.BusinessRepository;
import com.nttdata.nttbankclient.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    @Override
    public Flux<BusinessModel> findAll() {
        return businessRepository.findAll();
    }

    @Override
    public Mono<BusinessModel> create(BusinessModel business) {
        return businessRepository.save(business);
    }

    @Override
    public Mono<BusinessModel> update(BusinessModel business) {
        return businessRepository.save(business);
    }

    @Override
    public void deleteByRuc(String ruc) {
        businessRepository.deleteByRuc(ruc);
    }

    @Override
    public Mono<BusinessModel> findByRuc(String ruc) {
        return businessRepository.findByRuc(ruc);
    }
}
