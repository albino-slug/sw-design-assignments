//package pingpong.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import pingpong.model.User;
//import pingpong.service.UserService;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class UserViewAccountBalanceController {
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(value = "user_view_account_balance", method = RequestMethod.GET)
//    public String userIndex(Model model, HttpSession httpSession) {
//        model.addAttribute("user", new User());
////        if (Role.valueOf(httpSession.getAttribute("userRole").toString()) == Role.ADMIN) {
//        return "user_view_account_balance";
////        }
////        else {
////            return "redirect:/login";
////        }
//    }
//
//    private void updateUserList(Model model, List<User> userList) {
//        model.addAttribute("user", new User());
//        model.addAttribute("userList", userList);
//    }
//
//    @RequestMapping(value = "/user_view_account_balance", method = RequestMethod.POST, params = "action=getCurrentUserAccountBalance")
//    public String getCurrentUserAccountBalance(Model model, HttpSession httpSession){
//        Optional<Integer> id = Optional.ofNullable(Integer.valueOf(httpSession.getAttribute("userId").toString()));
//        List<User> users = new ArrayList<User>();
//        users.add(userService.findById(id.get()).get());
//        updateUserList(model, users);
//        return "user_view_account_balance";
//    }
//
//    @RequestMapping(value = "/user_view_account_balance", method = RequestMethod.POST, params = "action=addSumToUserAccount")
//    public String addToAccount(@RequestParam("account") Integer sum, Model model, HttpSession httpSession){
//        Boolean transactionResult = userService.addSumToUserAccountById(Integer.valueOf(httpSession.getAttribute("userId").toString()), sum);
//        if (transactionResult == Boolean.FALSE){
//            model.addAttribute("message", "Error while adding money to user account");
//        }
//        Optional<Integer> id = Optional.ofNullable(Integer.valueOf(httpSession.getAttribute("userId").toString()));
//        List<User> users = new ArrayList<User>();
//        users.add(userService.findById(id.get()).get());
//        updateUserList(model, users);
//        return "user_view_account_balance";
//    }
//
//    @RequestMapping(value = "/user_view_account_balance", method = RequestMethod.POST, params = "action=withdrawSumFromUserAccount")
//    public String withdrawFromAccount( @RequestParam("account") Integer sum, Model model, HttpSession httpSession){
//        Boolean transactionResult = userService.withdrawSumFromUserAccountById(Integer.valueOf(httpSession.getAttribute("userId").toString()), sum);
//        if (transactionResult == Boolean.FALSE){
//            model.addAttribute("message", "Error while withdrawing money from user account");
//        }
//        Optional<Integer> id = Optional.ofNullable(Integer.valueOf(httpSession.getAttribute("userId").toString()));
//        List<User> users = new ArrayList<User>();
//        users.add(userService.findById(id.get()).get());
//        updateUserList(model, users);
//        return "user_view_account_balance";
//    }
//}
