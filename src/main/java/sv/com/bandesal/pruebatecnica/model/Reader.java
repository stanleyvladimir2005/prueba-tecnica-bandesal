package sv.com.bandesal.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "readers")
public class Reader {

    @Id
    @SequenceGenerator(name = "readerSequence", sequenceName = "readerSequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", length = 8)
    private Integer id;

    @Size(min=3, message = "{readers.name.size}")
    @Column(name="name", length = 8)
    private String name;
}