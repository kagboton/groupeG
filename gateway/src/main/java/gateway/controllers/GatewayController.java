package gateway.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("session")
public class GatewayController {




    @RequestMapping("/")
    public String index(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping("/creerEvenement")
    public String creerEvenement(){
        return "creerEvenement";
    }

    @RequestMapping("/inscription")
    public String inscription(){
        return "inscription";
    }

    @RequestMapping("/connexion")
    public String connexion(){
        return "connexion";
    }

    @GetMapping("/accueil")
    public String accueil(@RequestParam String pseudo,HttpServletRequest request){
        request.getSession().setAttribute("pseudo",pseudo);
        return "index";
    }

    @RequestMapping("/accueilP")
    public String accueilP(){
        return "index";
    }

    @RequestMapping("/ajouterAmis")
    public String ajouterAmis(){
        return "ajouterAmis";
    }

    @RequestMapping("/invitationAmis")
    public String invitationAmis(){
        return "invitationAmis";
    }

    @RequestMapping("/invitationEvenement")
    public String invitationEvenement(){
        return "invitationEvenement";
    }

    @RequestMapping("/mesEvenementsPublics")
    public String mesEvenementsPublics(){
        return "mesEvenementsPublics";
    }

    @RequestMapping("/mesEvenementsPrives")
    public String mesEvenementsPrives(){
        return "mesEvenementsPrives";
    }

    @RequestMapping("/profile")
    public String profile(){
        return "profile";
    }

    @RequestMapping("/modifierProfile")
    public String modifierProfile(){
        return "modifierProfile";
    }

    @RequestMapping("/creerSoiree")
    public String creerSoiree(){
        return "creerSoiree";
    }

    @RequestMapping("/afficherSoiree")
    public String afficherSoiree(){
        return "soiree";
    }




}
