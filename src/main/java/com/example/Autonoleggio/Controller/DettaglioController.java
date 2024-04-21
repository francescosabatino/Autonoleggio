package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.Model.Noleggio;
import com.example.Autonoleggio.Model.Utente;
import com.example.Autonoleggio.Service.AutoService;
import com.example.Autonoleggio.Service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {
    @Autowired
    private AutoService autoService;
    @Autowired
    private UtenteService utenteService;
    private int idAuto;
    @GetMapping
    public String getPage(Model model, @RequestParam("id") int id,
                          @RequestParam(name = "result", required = false) String result,
                          HttpSession session, @RequestParam(name = "confirmed", required = false) String confirmed){
        idAuto = id;
        model.addAttribute("auto", autoService.getDettagliAuto(id));
        model.addAttribute("auto", autoService.getAutoById(id));
        model.addAttribute("result", result);
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente != null){
            model.addAttribute("utente", utente);
        }
        if(session.getAttribute("noleggio") != null){
            model.addAttribute("noleggio", (Noleggio) session.getAttribute("noleggio"));
        }
        model.addAttribute("confirmed", confirmed);
        return "dettaglio";
    }
    //Al posto di prenota fare pulsante controlla. In cui mettiamo la logica di controllo sulle date e calcolargli il totale. Quindi o gli diciamo che
    //l'auto non è disponibile oppure fare tipo preventivo e, in quel caso, dargli la possibilità di confermare. Dividiere autoserviceImpl in due
    @PostMapping("/prenota")
    public String formManager(HttpSession session, @RequestParam(name = "dataInizioNoleggio") String dataInizioNoleggio,
                              @RequestParam("dataFineNoleggio") String dataFineNoleggio) {
       String result = autoService.controlloNoleggio(session, idAuto, dataInizioNoleggio, dataFineNoleggio);
        return "redirect:/dettaglio?id=" + idAuto + "&result=" + result;

    }

    @GetMapping("/conferma")
    public String confermaNoleggio (HttpSession session){
        autoService.confermaNoleggio(session);
        return "redirect:/dettaglio?id=" + idAuto + "&confirmed";
    }

}
