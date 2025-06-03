package sv.com.bandesal.pruebatecnica.controller;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sv.com.bandesal.pruebatecnica.dto.UserDto;
import sv.com.bandesal.pruebatecnica.service.IUserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        var user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result,Model model){
        var existing = userService.findByEmail(user.getEmail());
        if (existing != null)
            result.rejectValue("email", null, "There is already an account registered with that email");

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        var users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}