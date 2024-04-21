package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.Model.Admin;
import com.example.Autonoleggio.Model.Auto;
import com.example.Autonoleggio.Model.Utente;
import com.example.Autonoleggio.Service.AutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private AutoService autoService;
    @GetMapping
    public String getPage(Model model, HttpSession session){

        List<Auto> auto = autoService.getAllAuto();
        model.addAttribute("auto", auto);
        Utente utente = (Utente) session.getAttribute("utente");
        Admin admin = (Admin) session.getAttribute("admin");
        if(utente != null){
            model.addAttribute("utente", utente);
        }
        if (admin != null) {
            model.addAttribute("admin", admin);
        }
        return "index";


    }
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("utente");
        return "redirect:/";
    }
}
