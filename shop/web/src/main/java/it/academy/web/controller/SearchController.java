package it.academy.web.controller;

import it.academy.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class SearchController {

    private static final Logger log =
            Logger.getLogger(SearchController.class.getName());

    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "searchParam", required = false) String param,
            Model model) {
        log.info("Calling search(): searchParam: " + param);
        if (param != null && !"".equals(param.trim())) {
            model.addAttribute("searchResult", searchService.searchProducts(param));

        }
        return "search-result";
    }
}
