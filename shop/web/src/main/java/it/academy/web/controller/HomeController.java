package it.academy.web.controller;

import it.academy.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class.getName());
    @Autowired
    PromoService promoService;

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView,
                             @RequestParam(value = "param", required = false) String param) {
        log.info("Calling home()");
        modelAndView.addObject("userName", "Guest");
        modelAndView.addObject("paramValue", param);
        modelAndView.setViewName("index");

        modelAndView.addObject("promoList", promoService.findAllPromo());

        return modelAndView;
    }

}
