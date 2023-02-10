package sv.com.bandesal.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @SequenceGenerator(name = "blogSequence", sequenceName = "blogSequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", length = 8)
    private Integer id;

    @Size(min=3, message = "{blogs.title.size}")
    @Column(name="name", length = 50)
    private String title;

    @Size(min=3, message = "{blogs.description.size}")
    @Column(name="description", length = 400)
    private String description;
}