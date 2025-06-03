package sv.com.bandesal.pruebatecnica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
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
@RequiredArgsConstructor
public class BlogsControllerRest {

    private final IBlogService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Blog>> findAll() {
        var blogs = service.findAll();
        return new ResponseEntity<>(blogs, OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> findById(@PathVariable("id") Integer id) {
        var blog = service.findById(id);
        return new ResponseEntity<>(blog, OK);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<Blog> findByIdHateoas(@PathVariable("id") Integer id) {
        var blog = service.findById(id);
        var resource = EntityModel.of(blog);
        var link1 = linkTo(methodOn(this.getClass()).findById(id));
        var link2 = linkTo(methodOn(this.getClass()).findAll());
        resource.add(link1.withRel("blog-info1"));
        resource.add(link2.withRel("blog-full"));
        return resource;
    }
}