package sv.com.bandesal.pruebatecnica.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sv.com.bandesal.pruebatecnica.dto.BlogReaderDto;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BlogReaderController {

    @Autowired
    private IBlogReaderService service;

    @Autowired
    private ModelMapper mapper;

    @RequestMapping("/blogReaders/")
    public String getAllBlogReaders(Model model) {
        List<BlogReaderDto> list = service.getBlogReaders().stream().map(this::convertToDto).collect(Collectors.toList());
        model.addAttribute("blogReaders", list);
        return "list-blogReaders";
    }

     @RequestMapping(path = {"/blogReaders/edit", "/blogReader/edit/"})
    public String editBlogReadersById(Model model){
            BlogReader entity = new BlogReader();
            model.addAttribute("blogReader", entity);
        return "add-edit-blogReaders";
    }

    @RequestMapping(path = "/blogReaders/delete/{br}")
    public String deleteBlogReaders(Model model, @PathVariable("br") String dto)  {
        service.deleteBlogReader(dto);
        return "redirect:/blogReaders/";
    }

    @PostMapping (path = "/blogReaders/createBlogReaders")
    public String createOrUpdateBlogReaders(@Valid BlogReaderDto dto) {
        BlogReader br = convertToEntity(dto);
        service.createOrUpdateBlog(br);
        return "redirect:/blogReaders/";
    }

    private BlogReaderDto convertToDto(BlogReader obj){
        return mapper.map(obj, BlogReaderDto.class);
    }

    private BlogReader convertToEntity(BlogReaderDto dto){
        return mapper.map(dto, BlogReader.class);
    }
}