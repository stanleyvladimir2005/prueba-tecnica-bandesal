package sv.com.bandesal.pruebatecnica.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(BlogReaderPK.class)
@Table(name = "blog_reader")
public class BlogReader {

   @Id
   @ManyToOne
   @JoinColumn(name="b_id")
   private Blog blog;

   @Id
   @ManyToOne
   @JoinColumn(name="r_id")
   private Reader reader;
}