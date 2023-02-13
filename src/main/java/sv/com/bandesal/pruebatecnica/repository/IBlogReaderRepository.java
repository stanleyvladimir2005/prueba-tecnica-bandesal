package sv.com.bandesal.pruebatecnica.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import java.util.List;

public interface IBlogReaderRepository extends IGenericRepository<BlogReader, Integer> {

    @Query(value = "SELECT * FROM blog_reader", nativeQuery = true)
    List<BlogReader> getBlogReaders();

    @Modifying
    @Query(value = "DELETE FROM BLOG_READER WHERE b_id =:b_id AND r_id=:r_id", nativeQuery = true)
    Integer deleteTransactional(@Param("b_id") Integer b_id, @Param("r_id") Integer r_id);
}