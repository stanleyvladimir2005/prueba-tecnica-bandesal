package sv.com.bandesal.pruebatecnica.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.EntityModel;
import sv.com.bandesal.pruebatecnica.model.Blog;
import sv.com.bandesal.pruebatecnica.model.Reader;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogReaderHateoasDTO extends EntityModel<Object> {
	private Blog blog;
	private Reader reader;
}