package com.example.Autonoleggio.Controller;

import com.example.Autonoleggio.Model.Auto;
import com.example.Autonoleggio.Service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dettaglionumeroposti")
public class DettaglioNumeroPostiController {
    @Autowired
    private AutoService autoService;
    @GetMapping
    public String getPage (Model model, @RequestParam("numeroPosti") int numeroPosti){
        List<Auto> auto = autoService.getAutoByNumeroPosti(numeroPosti);
        model.addAttribute("automobili", auto);
        return "dettaglionumeroposti";
    }
}
