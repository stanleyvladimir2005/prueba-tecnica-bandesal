package sv.com.bandesal.pruebatecnica.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sv.com.bandesal.pruebatecnica.model.Reader;
import sv.com.bandesal.pruebatecnica.service.IReaderService;
import java.util.List;
import java.util.Optional;

@Controller
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    
    @RequestMapping("/readers/")
    public String getAllreaders(Model model) {
        List<Reader> list = readerService.findAll();
        model.addAttribute("readers", list);
        return "list-readers";
    }

    @RequestMapping(path = {"/readers/edit", "/readers/edit/{id}"})
    public String editReaderById(Model model, @PathVariable("id") Optional<Integer> id){
        if (id.isPresent()) {
            Reader entity = readerService.findById(id.get());
            model.addAttribute("reader", entity);
        } else {
            model.addAttribute("reader", new Reader());
        }
        return "add-edit-readers";
    }

    @RequestMapping(path = "/readers/delete/{id}")
    public String deleteReader(Model model, @PathVariable("id") int id)  {
        readerService.delete(id);
        return "redirect:/readers/";
    }

    @PostMapping (path = "/readers/createReaders")
    public String createOrUpdateReader(@Valid Reader reader) {
        readerService.createOrUpdateReader(reader);
        return "redirect:/readers/";
    }
}