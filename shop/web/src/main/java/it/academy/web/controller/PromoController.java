package it.academy.web.controller;

import it.academy.dto.PromoDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PromoController {

    public static final Logger log =
            Logger.getLogger(PromoController.class.getName());


    @GetMapping("/promo")
    public String showPromoPage(Model model) {
        model.addAttribute("promoDto", new PromoDto());
        return "add-promo";
    }

    @PostMapping("/promo/add")
    public String addNewPromo(
            @Valid @ModelAttribute("promoDto") PromoDto promoDto,
            BindingResult bindingResult
    ) {
        log.info("Call: addNewPromo");
        if (bindingResult.hasErrors()) {
            log.log(Level.SEVERE, "Form has errors: " + bindingResult.getFieldErrors());
            return "add-promo";
        }
        return "index";
    }
}
