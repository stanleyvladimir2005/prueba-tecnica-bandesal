package sv.com.bandesal.pruebatecnica.service;

import sv.com.bandesal.pruebatecnica.model.BlogReader;
import java.util.List;

public interface IBlogReaderService  extends ICRUD <BlogReader, Integer> {

    List<BlogReader> getBlogReaders();

    void createOrUpdateBlog(BlogReader br);

    void deleteBlogReader(String br);
}