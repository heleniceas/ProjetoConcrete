package br.com.concrete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import br.com.concrete.repository.PhoneRepository;


@RestController
public class PhoneController {
    PhoneRepository repository;

    @Autowired
    public PhoneController(PhoneRepository repository) {
        this.repository = repository;
    }
}
