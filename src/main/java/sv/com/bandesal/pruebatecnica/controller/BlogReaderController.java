package sv.com.bandesal.pruebatecnica.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sv.com.bandesal.pruebatecnica.dto.BlogReaderDto;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.model.Reader;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogReaderController {

    @Autowired
    private IBlogReaderService service;

    @RequestMapping("/blogReaders/")
    public String getAllBlogReader(Model model) {
        List<BlogReader> list = service.getBlogReaders();
        model.addAttribute("blogReaders", list);
        return "list-blogReaders";
    }

    @RequestMapping(path = {"/blogReaders/edit", "/blogReader/edit/{id}"})
    public String editBlogReaderById(Model model, @PathVariable("id") Optional<Integer> id){
        if (id.isPresent()) {
            BlogReader entity = service.findById(id.get());
            model.addAttribute("blogReader", entity);
        } else {
            model.addAttribute("blogReader", new Reader());
        }
        return "add-edit-blogReaders";
    }

    @PostMapping(path = "/blogReaders/createBlogReader")
    public String createBlogReader (@Valid @RequestBody BlogReaderDto dto) {
        BlogReader blogReader = new BlogReader(dto.getBlog(), dto.getReader());
        service.saveTransactional(blogReader);
        return "redirect:/blogsReaders/";
    }
}