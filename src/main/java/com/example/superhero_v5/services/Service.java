package com.example.superhero_v5.services;

import com.example.superhero_v5.Model.Superhero;
import com.example.superhero_v5.Repository.HeroRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    //Database & mysql implementation

    private final HeroRepository repository;

    public Service(HeroRepository repository){
        this.repository = repository;
    }

    public List<Superhero> getAllHeroes(){
        return repository.getAllHeroes();
    }

}
