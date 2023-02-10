package sv.com.bandesal.pruebatecnica.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.com.bandesal.pruebatecnica.model.BlogReader;

import java.util.List;

public interface IBlogReaderRepository extends IGenericRepository<BlogReader, Integer> {

    @Modifying
    @Query(value = "INSERT INTO blog_reader(b_id, r_id) VALUES (:id, :id)", nativeQuery = true)
    Integer register(@Param("id") Integer idBlog, @Param("id") Integer idReader);

    @Query(value = "SELECT * FROM blog_reader", nativeQuery = true)
    List<BlogReader> getBlogReaders();

    @Query(value = "DELETE FROM BLOG_READER WHERE b_id =:id AND r_id=:id", nativeQuery = true)
    Void delete(@Param("id") Integer idBlog, @Param("id") Integer idReader);



}