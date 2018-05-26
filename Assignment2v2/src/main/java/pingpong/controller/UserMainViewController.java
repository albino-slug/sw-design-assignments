package pingpong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pingpong.model.Role;
import pingpong.repository.UserRepo;

import javax.servlet.http.HttpSession;

@Controller
public class UserMainViewController {
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = {"/user_main_view"}, method = RequestMethod.GET)
    public String userMainViewIndex(HttpSession httpSession) {
//        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.USER) {
            return "user_main_view";
//        }
//        else {
//            return "redirect:/login";
//        }
    }

    @RequestMapping(value = "/user_main_view", method = RequestMethod.POST, params = "redirect=toUserViewTournaments")
    public String switchToUserViewTournaments() { return "redirect:/user_view_tournaments"; }

    @RequestMapping(value = "/user_main_view", method = RequestMethod.POST, params = "redirect=toUserViewMatches")
    public String switchToUserViewMatches() { return "redirect:/user_view_matches"; }
}
