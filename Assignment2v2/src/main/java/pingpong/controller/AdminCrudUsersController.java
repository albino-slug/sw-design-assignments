package pingpong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pingpong.model.User;
import pingpong.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminCrudUsersController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin_crud_users", method = RequestMethod.GET)
    public String userIndex(Model model, HttpSession httpSession) {
        model.addAttribute("user", new User());
//        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
            return "admin_crud_users";
//        }
//        else {
//            return "redirect:/login";
//        }
    }

    private void updateUserList(Model model, List<User> userList) {
        model.addAttribute("user", new User());
        model.addAttribute("userList", userList);
    }

    @RequestMapping(value = "/admin_crud_users", method = RequestMethod.POST, params = "action=addUser")
    public String addNewUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Boolean saveResult = userService.save(user);
            if (saveResult == Boolean.FALSE){
                model.addAttribute("message", "Error while saving user");
            }
            updateUserList(model, userService.findAll());
        }
        return "admin_crud_users";
    }

    @RequestMapping(value = "/admin_crud_users", method = RequestMethod.POST, params = "action=deleteUser")
    public String deleteUser(@RequestParam("id") Integer id, Model model){
        Boolean deleteResult = userService.deleteById(id);
        if (deleteResult == Boolean.FALSE){
            model.addAttribute("message", "Error while deleting user");
        }
        updateUserList(model, userService.findAll());
        return "admin_crud_users";
    }

    @RequestMapping(value = "/admin_crud_users", method = RequestMethod.POST, params = "action=listAllUsers")
    public String listAllUsers(Model model){
        updateUserList(model, userService.findAll());
        return "admin_crud_users";
    }

    @RequestMapping(value = "/admin_crud_users", method = RequestMethod.POST, params = "action=addSumToUserAccount")
    public String addToAccount(@RequestParam("id") Integer id, @RequestParam("account") Integer sum, Model model){
        Boolean transactionResult = userService.addSumToUserAccountById(id, sum);
        if (transactionResult == Boolean.FALSE){
            model.addAttribute("message", "Error while adding money to user account");
        }
        updateUserList(model, userService.findAll());
        return "admin_crud_users";
    }

    @RequestMapping(value = "/admin_crud_users", method = RequestMethod.POST, params = "action=withdrawSumFromUserAccount")
    public String withdrawFromAccount(@RequestParam("id") Integer id, @RequestParam("account") Integer sum, Model model){
        Boolean transactionResult = userService.withdrawSumFromUserAccountById(id, sum);
        if (transactionResult == Boolean.FALSE){
            model.addAttribute("message", "Error while withdrawing money from user account");
        }
        updateUserList(model, userService.findAll());
        return "admin_crud_users";
    }
}
