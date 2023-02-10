package sv.com.bandesal.pruebatecnica.service;

import sv.com.bandesal.pruebatecnica.model.Blog;

public interface IBlogService extends ICRUD<Blog, Integer> {

    void createOrUpdateBlog(Blog blog);
}