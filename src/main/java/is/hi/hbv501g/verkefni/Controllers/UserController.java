package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user){

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            return "redirect:/signup";
        }
        User exists = userService.findByUsername(user.getUsername());

        if (exists != null){
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:/signup";
        }

        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, RedirectAttributes redirectAttributes, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }

        User exists = userService.login(user);

        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("loginFailed", true);
        return "redirect:/login";
    }

    @RequestMapping(value = "/setMoviePreferences", method = RequestMethod.GET)
    public String setMoviePreferencesGET(){

        return "setMoviePreferences";
    }
}
