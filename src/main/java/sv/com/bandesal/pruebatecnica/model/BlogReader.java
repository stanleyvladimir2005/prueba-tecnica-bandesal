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
public class BlogReader {

   @Id
   private Blog blog;

   @Id
   private Reader reader;
}