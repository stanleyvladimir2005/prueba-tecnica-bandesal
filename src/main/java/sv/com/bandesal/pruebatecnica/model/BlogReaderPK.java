package sv.com.bandesal.pruebatecnica.model;

import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode( onlyExplicitlyIncluded = true )
@Embeddable
public class BlogReaderPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Integer blog;

    @EqualsAndHashCode.Include
    private Integer reader;
}