package pingpong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pingpong.model.Tournament;
import pingpong.model.TournamentCategory;
import pingpong.model.TournamentFee;
import pingpong.model.User;
import pingpong.service.TournamentService;
import pingpong.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserViewTournamentsController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user_view_tournaments", method = RequestMethod.GET)
    public String tournamentIndex(Model model, HttpSession httpSession) {
        model.addAttribute("tournament", new Tournament());
//        if (Role.valueOf(httpSession.getAttribute("tournamentRole").toString()) == Role.ADMIN) {
        return "user_view_tournaments";
//        }
//        else {
//            return "redirect:/login";
//        }
    }

    private void updateTournamentList(Model model, List<Tournament> tournamentList) {
        model.addAttribute("tournament", new Tournament());
        model.addAttribute("tournamentList", tournamentList);
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=listAllTournaments")
    public String listAllTournaments(Model model){
        updateTournamentList(model, tournamentService.findAll());
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=getCurrentUserAccountBalance")
    public String getCurrentUserAccountBalance(Model model, HttpSession httpSession){
        updateTournamentList(model, tournamentService.findAll());
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=listUpcomingTournaments")
    public String listUpcomingTournaments(Model model){
        updateTournamentList(model, tournamentService.findByTournamentCategory(TournamentCategory.UPCOMING));
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=listOngoingTournaments")
    public String listOngoingTournaments(Model model){
        updateTournamentList(model, tournamentService.findByTournamentCategory(TournamentCategory.ONGOING));
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=listFinishedTournaments")
    public String listFinishedTournaments(Model model){
        updateTournamentList(model, tournamentService.findByTournamentCategory(TournamentCategory.FINISHED));
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=listFreeTournaments")
    public String listFreeTournaments(Model model){
        updateTournamentList(model, tournamentService.findByTournamentFee(TournamentFee.FREE));
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=listPaidTournaments")
    public String listPaidTournaments(Model model){
        updateTournamentList(model, tournamentService.findByTournamentFee(TournamentFee.PAID));
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=enrollInTournament")
    public String enrollInTournament(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("userId").toString())).get();
        if(tournamentService.addUserById(id, user.getId())){
            userService.addTournamentById(user.getId(), id);
        }
        else {
            model.addAttribute("message", "tournament already full or user already enrolled");
        }
        updateTournamentList(model, tournamentService.findAll());
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=disenrollFromTournament")
    public String disenrollFromTournament(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("userId").toString())).get();
        tournamentService.removeUserById(id, user.getId());
        userService.removeTournamentById(user.getId(), id);
        updateTournamentList(model, tournamentService.findTournamentsByUserId(user.getId()));
        return "user_view_tournaments";
    }

    @RequestMapping(value = "/user_view_tournaments", method = RequestMethod.POST, params = "action=findTournamentsByUserId")
    public String findTournamentsByUserId(@RequestParam("id") Integer id, Model model, HttpSession httpSession){
        updateTournamentList(model, tournamentService.findTournamentsByUserId(Integer.parseInt(httpSession.getAttribute("userId").toString())));
        return "user_view_tournaments";
    }

}
