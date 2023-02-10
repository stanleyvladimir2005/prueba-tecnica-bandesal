package sv.com.bandesal.pruebatecnica.service;

import org.springframework.data.jpa.repository.Query;
import sv.com.bandesal.pruebatecnica.model.BlogReader;

import java.util.List;

public interface IBlogReaderService  extends ICRUD <BlogReader, Integer> {

    void saveTransactional(BlogReader br);

    List<BlogReader> getBlogReaders();
}
