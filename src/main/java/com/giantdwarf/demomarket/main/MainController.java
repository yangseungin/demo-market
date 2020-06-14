package com.giantdwarf.demomarket.main;

import com.giantdwarf.demomarket.account.CurrentUser;
import com.giantdwarf.demomarket.account.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(@CurrentUser Member member, Model model) {
        if(member !=null){
            model.addAttribute(member);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
