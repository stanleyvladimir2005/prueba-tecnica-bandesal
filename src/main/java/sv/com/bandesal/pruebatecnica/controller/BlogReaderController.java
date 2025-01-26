package sv.com.bandesal.pruebatecnica.controller;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sv.com.bandesal.pruebatecnica.dto.BlogReaderDto;
import sv.com.bandesal.pruebatecnica.model.Blog;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.model.Reader;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;
import sv.com.bandesal.pruebatecnica.service.IBlogService;
import sv.com.bandesal.pruebatecnica.service.IReaderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BlogReaderController {

    @Autowired
    private IBlogReaderService service;

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IReaderService readerService;

    @Autowired
    private ModelMapper mapper;

    @RequestMapping("/blogReaders/")
    public String getAllBlogReaders(Model model) {
        var list = service.getBlogReaders().stream().map(this::convertToDto).collect(Collectors.toList());
        model.addAttribute("blogReaders", list);
        return "list-blogReaders";
    }

    @RequestMapping(path = {"/blogReaders/edit"})
    public String editBlogReadersById(Model model ){
            var entity = new BlogReader();
            var list11 = blogService.findAll();
            var list2 = readerService.findAll();
            model.addAttribute("blogReader", entity);
            model.addAttribute("itemsBlog", list11);
            model.addAttribute("itemsReader", list2);
        return "add-edit-blogReaders";
    }

    @RequestMapping(path = "/blogReaders/delete/{br}")
    public String deleteBlogReaders(Model model, @PathVariable("br") String dto)  {
        service.deleteBlogReader(dto);
        return "redirect:/blogReaders/";
    }

    @PostMapping (path = "/blogReaders/createBlogReaders")
    public String createOrUpdateBlogReaders(@Valid BlogReaderDto dto) {
        var br = convertToEntity(dto);
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