package com.example.easyum.controller;

import com.example.easyum.entities.UserEntity;
import com.example.easyum.repo.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/reg")
public class RegController {

    private final UserRepo userRepo;

    public RegController(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String getReg() {
        return "reg";
    }


    @PostMapping
    public String postReg(String username, String password, String repeat, Map<String, Object> model) {
        if (!password.equals(repeat)) {
            model.put("message", "Password is bad");
            return "reg";
        }

        final UserEntity userWithSameName = userRepo.findByUsername(username);
        if (userWithSameName != null) {
            model.put("message", "Such user already exists");
            return "reg";
        }

        userRepo.save(new UserEntity(username, password));

        return "redirect:/hello";
    }


}
