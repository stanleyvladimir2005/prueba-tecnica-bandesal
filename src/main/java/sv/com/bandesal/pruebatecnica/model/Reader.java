package sv.com.bandesal.pruebatecnica.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reader")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "readerSeqGen")
    @SequenceGenerator(name = "readerSequence",sequenceName = "readerSeqGen",  initialValue = 100, allocationSize = 1)
    @Column(name="id", length = 8)
    private Integer id;

    @Size(min=3, message = "{readers.name.size}")
    @Column(name="name", length = 8)
    private String name;
}