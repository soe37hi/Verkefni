package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(Model model, HttpSession session){

        if (session.getAttribute("LoggedInUser") != null) {
            User loggedInUser = (User) session.getAttribute("LoggedInUser");
            model.addAttribute("user", loggedInUser);
        }

        return "home";
    }



    @RequestMapping("/CRUD")
    public String CRUD(){
        return "CRUD";
    }

}
