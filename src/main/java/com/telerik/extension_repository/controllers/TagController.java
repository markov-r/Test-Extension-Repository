package com.telerik.extension_repository.controllers;

import com.telerik.extension_repository.entities.Tag;
import com.telerik.extension_repository.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tag/{name}")
    public String extensionsWithTag(Model model, @PathVariable String name){
        Tag tag=this.tagRepository.findByName(name);

        if(tag == null){
            return "redirect:/extensions/all";
        }

        model.addAttribute("view", "/tags/extensions");
        model.addAttribute("tag", tag);

        return "base-layout";
    }
}
