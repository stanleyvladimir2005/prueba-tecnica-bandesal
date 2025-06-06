package sv.com.bandesal.pruebatecnica.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sv.com.bandesal.pruebatecnica.model.Blog;
import sv.com.bandesal.pruebatecnica.service.IBlogService;
import java.util.Optional;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final IBlogService blogService;

    @RequestMapping("/blogs/")
    public String getAllBlogs(Model model) {
        var list = blogService.findAll();
        model.addAttribute("blogs", list);
        return "list-blogs";
    }

    @RequestMapping(path = {"/blogs/edit", "/blogs/edit/{id}"})
    public String editBlogById(Model model, @PathVariable("id") Optional<Integer> id){
        if (id.isPresent()) {
           var entity = blogService.findById(id.get());
           model.addAttribute("blog", entity);
        } else
            model.addAttribute("blog", new Blog());
        return "add-edit-blogs";
    }

    @RequestMapping(path = "/blogs/delete/{id}")
    public String deleteBlog(Model model, @PathVariable("id") int id)  {
        blogService.delete(id);
        return "redirect:/blogs/";
    }

    @PostMapping (path = "/blogs/createBlogs")
    public String createOrUpdateBlog(@Valid Blog blog) {
        blogService.createOrUpdateBlog(blog);
        return "redirect:/blogs/";
    }

    @GetMapping(value = "/blogs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> findById(@PathVariable("id") Integer id) {
        var blog = blogService.findById(id);
        return new ResponseEntity<>(blog, OK);
    }
}