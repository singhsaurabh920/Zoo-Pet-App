package org.zoo.pets.controller;

import org.zoo.pets.domain.Pet;
import org.zoo.pets.repository.PetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Controller
public class PetController {
    private static final Logger logger = LogManager.getLogger(PetController.class);

    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @PostMapping(value = "/pet")
    @ResponseBody
    Mono<Pet> createPet(@RequestBody Pet pet){
        return petRepository.save(pet);
    }

    @GetMapping(value = "/pet/{id}")
    @ResponseBody
    Mono<Pet> findPet(@PathVariable String id){
        return petRepository.findPetById(id);
    }

    @GetMapping(value = "/pets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    Flux<Pet> findPets() {
        logger.info("Called - "+new Date());
        return petRepository.findWithTailableCursorBy().delayElements(Duration.ofMillis(2500));
    }

    @GetMapping(value = "/pet/name/{name}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    Flux<Pet> findPetsByName(@PathVariable String name){
        return petRepository.findPetWithTailableCursorByName(name).delayElements(Duration.ofMillis(2500));
    }

    @GetMapping("/")
    Mono<String> home() {
        return Mono.just("pet");
    }



}
