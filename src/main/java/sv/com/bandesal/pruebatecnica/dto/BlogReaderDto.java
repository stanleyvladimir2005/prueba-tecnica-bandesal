package sv.com.bandesal.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.com.bandesal.pruebatecnica.model.Blog;
import sv.com.bandesal.pruebatecnica.model.Reader;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogReaderDto {

    private Blog blog;

    private Reader reader;
}