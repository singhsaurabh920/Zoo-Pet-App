package org.zoo.pets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pets")
@Data
public class Pet {
    @Id
    private String id;
    private String name;
    private String age;
    private String email;
    private String address;
    private String city;
    private String state;
    private String thumbnailImage;

}
