package workshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshop.model.dto.UserRegistrationDTO;
import workshop.service.AuthService;

import javax.validation.Valid;

@Controller
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @ModelAttribute("userRegistrationDTO")
    public void initForm(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegistrationDTO",
                    bindingResult);
            return "redirect:/register";
        }

        this.authService.register(userRegistrationDTO);
        return "redirect:/login";

    }


    @GetMapping("/login")
    public String login(){
        return "/login";
    }
}
