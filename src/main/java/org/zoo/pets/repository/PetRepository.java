package org.zoo.pets.repository;

import org.zoo.pets.domain.Pet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetRepository extends ReactiveMongoRepository<Pet, String> {

    Mono<Pet> findPetById(String id);
    @Tailable
    Flux<Pet> findWithTailableCursorBy();
    Flux<Pet> findPetByName(String name);
    @Tailable
    Flux<Pet> findPetWithTailableCursorByName(String name);
}
