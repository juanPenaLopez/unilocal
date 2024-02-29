package co.edu.uniquindio.unilocal.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("ciudades")
public class Ciudad implements Serializable {

    @Id
    private String id;

    private String nombre;
}
