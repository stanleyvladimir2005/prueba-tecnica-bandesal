package sv.com.bandesal.pruebatecnica.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode( onlyExplicitlyIncluded = true )
@Embeddable
public class BlogReaderPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name="b_id")
    private Blog blog;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name="r_id")
    private Reader reader;
}