package com.example.superhero_v5.Controller;

import com.example.superhero_v5.DTO.PowerTypeDTO;
import com.example.superhero_v5.Model.Superhero;
import com.example.superhero_v5.Repository.HeroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping
public class HeroController {

    HeroRepository heroRepository;

    public HeroController(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    //Modificér endpointet: /superhelte, således at html siden index.html vises og renderes med data fra endpointet.
    @GetMapping({"/", ""})
    public String index(Model model){
        model.addAttribute("allHeroes", heroRepository.getAllHeroes());
        return "index";
    }

    ////Modificér endpointet: /superpower/{name} fra superheltev4, sådan at controlleren returnerer powers.html der renderer powers:
    @GetMapping("/superpower/{name}")
    public String getPowerTypeByName(@PathVariable String name, Model model) {
        List <PowerTypeDTO> superheroesList = heroRepository.getPowerTypeByName(name);
        model.addAttribute("powerTypeByName", superheroesList);
        return "powers";
    }

//Lav et nyt endpoint, /add,  til oprettelse af en superhelt. Der skal både laves en getmapping, der returnerer form til oprettelse,
   /* @GetMapping("/add")
    public String addHero(Model model){
        model.addAttribute("addHero", )
    }*/


// en postmapping der tilføjer data fra formens modelattribut til backenden.
    //@PostMapping("/add")




}
