package pingpong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pingpong.model.User;
import pingpong.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminCrudTournamentsController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin_crud_tournaments", method = RequestMethod.GET)
    public String userIndex(Model model, HttpSession httpSession) {
        model.addAttribute("user", new User());
//        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
        return "admin_crud_tournaments";
//        }
//        else {
//            return "redirect:/login";
//        }
    }
}
