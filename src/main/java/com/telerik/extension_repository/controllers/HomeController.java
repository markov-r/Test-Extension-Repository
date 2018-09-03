package com.telerik.extension_repository.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("view","/extensions/extensions-table");
        return "base-layout";
    }

//    @GetMapping("/unauthorized")
//    public String getNoAccessPage(Model model) {
//        model.addAttribute("view", "no-access");
//        return "base-layout";
//    }
}