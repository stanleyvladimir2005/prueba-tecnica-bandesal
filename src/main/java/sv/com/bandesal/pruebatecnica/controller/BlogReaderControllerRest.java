package sv.com.bandesal.pruebatecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.com.bandesal.pruebatecnica.dto.BlogReaderHateoasDTO;
import sv.com.bandesal.pruebatecnica.model.BlogReader;
import sv.com.bandesal.pruebatecnica.service.IBlogReaderService;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/blog-reader-rest")
public class BlogReaderControllerRest {

   @Autowired
   private IBlogReaderService service;

    @GetMapping(value = "/hateoas",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogReaderHateoasDTO> listHateoas() {
        List<BlogReader> blogReaders;
        List<BlogReaderHateoasDTO> blogReaderDTO = new ArrayList<>();
        blogReaders = service.getBlogReaders();

        for (BlogReader c : blogReaders) {
            BlogReaderHateoasDTO d = new BlogReaderHateoasDTO();
            d.setBlog(c.getBlog());
            d.setReader(c.getReader());

            WebMvcLinkBuilder linkTo = linkTo(methodOn(BlogController.class).findById((c.getBlog().getId())));
            d.add(linkTo.withSelfRel());
            WebMvcLinkBuilder linkTo1 = linkTo(methodOn(ReaderController.class).findById((c.getReader().getId())));
            d.add(linkTo1.withSelfRel());

            blogReaderDTO.add(d);
        }
        return blogReaderDTO;
    }
}