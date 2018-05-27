package pingpong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pingpong.model.Tournament;
import pingpong.service.TournamentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminCrudTournamentsController {
    @Autowired
    private TournamentService tournamentService;

    @RequestMapping(value = "admin_crud_tournaments", method = RequestMethod.GET)
    public String tournamentIndex(Model model, HttpSession httpSession) {
        model.addAttribute("tournament", new Tournament());
//        if (Role.valueOf(httpSession.getAttribute("tournamentRole").toString()) == Role.ADMIN) {
        return "admin_crud_tournaments";
//        }
//        else {
//            return "redirect:/login";
//        }
    }

    private void updateTournamentList(Model model, List<Tournament> tournamentList) {
        model.addAttribute("tournament", new Tournament());
        model.addAttribute("tournamentList", tournamentList);
    }

    @RequestMapping(value = "/admin_crud_tournaments", method = RequestMethod.POST, params = "action=addTournament")
    public String addNewTournament(@Validated @ModelAttribute("tournament") Tournament tournament, BindingResult bindingResult, Model model)
    {
        if (!bindingResult.hasErrors()){
            Boolean saveResult = tournamentService.save(tournament);
            if (saveResult == Boolean.FALSE){
                model.addAttribute("message", "Error while saving tournament");
            }
            updateTournamentList(model, tournamentService.findAll());
        }
        return "admin_crud_tournaments";
    }

    @RequestMapping(value = "/admin_crud_tournaments", method = RequestMethod.POST, params = "action=deleteTournament")
    public String deleteTournament(@RequestParam("id") Integer id, Model model){
        Boolean deleteResult = tournamentService.deleteById(id);
        if (deleteResult == Boolean.FALSE){
            model.addAttribute("message", "Error while deleting tournament");
        }
        updateTournamentList(model, tournamentService.findAll());
        return "admin_crud_tournaments";
    }

    @RequestMapping(value = "/admin_crud_tournaments", method = RequestMethod.POST, params = "action=listAllTournaments")
    public String listAllTournaments(Model model){
        updateTournamentList(model, tournamentService.findAll());
        return "admin_crud_tournaments";
    }
}
