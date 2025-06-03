package sv.com.bandesal.pruebatecnica.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sv.com.bandesal.pruebatecnica.dto.BlogReaderDto;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;
import sv.com.bandesal.pruebatecnica.service.IBlogService;
import sv.com.bandesal.pruebatecnica.service.IReaderService;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BlogReaderController {

    private final IBlogReaderService service;

    private final IBlogService blogService;

    private final IReaderService readerService;

    private final ModelMapper mapper;

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