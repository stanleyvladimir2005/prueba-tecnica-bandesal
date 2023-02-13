package sv.com.bandesal.pruebatecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.com.bandesal.pruebatecnica.model.Blog;
import sv.com.bandesal.pruebatecnica.service.IBlogService;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/blogs")
public class BlogsControllerRest {

   @Autowired
   private IBlogService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> findAll() {
        List<Blog> blogs = service.findAll();
        return new ResponseEntity<>(blogs, OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> findById(@PathVariable("id") Integer id) {
        Blog blog = service.findById(id);
        return new ResponseEntity<>(blog, OK);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<Blog> findByIdHateoas(@PathVariable("id") Integer id) {
        Blog blog = service.findById(id);
        EntityModel<Blog> resource = EntityModel.of(blog);
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
        resource.add(link1.withRel("blog-info1"));
        resource.add(link2.withRel("blog-full"));
        return resource;
    }
}