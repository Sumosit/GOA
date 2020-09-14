package com.example.java.lab3.vcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Lab1")
public class AspNetCoreLab1Controller {

    @Autowired
    AspAccountRepository aspAccountRepository;

    @GetMapping("/home")
    public String Lab1_Home(Model model) {

        return "Lab1_Home";
    }

    @GetMapping("/homeError")
    public String Lab1_HomeError(Model model) {
        model.addAttribute("error", "Wrong PIN typed");
        return "Lab1_Home";
    }

    @PostMapping("/checkPIN")
    public String Lab1_CheckPIN(@RequestParam int pin) {
        AspAccount aspAccount = aspAccountRepository.findAspAccountByPin(pin);
        if (aspAccount != null) {
            return "redirect:/Lab1/profile?id="+aspAccount.getId();
        } else {
            return "redirect:/Lab1/homeError";
        }
    }

    @GetMapping("/profile")
    public String Lab1_profile(Model model,
                               @RequestParam long id) {
        AspAccount aspAccount = aspAccountRepository.getOne(id);
        model.addAttribute("id", aspAccount.getId());
        model.addAttribute("name", aspAccount.getName());
        model.addAttribute("money", aspAccount.getMoney());
        return "Lab1_Profile";
    }

    @PostMapping("/upBalance")
    public String Lab1_upBalance(@RequestParam long id,
                                 @RequestParam int up) {
        AspAccount aspAccount = aspAccountRepository.getOne(id);
        aspAccount.setMoney(aspAccount.getMoney() + up);
        aspAccountRepository.save(aspAccount);
        return "redirect:/Lab1/profile?id="+aspAccount.getId();
    }

    @PostMapping("/downBalance")
    public String Lab1_downBalance(@RequestParam long id,
                                 @RequestParam int down) {
        AspAccount aspAccount = aspAccountRepository.getOne(id);
        aspAccount.setMoney(aspAccount.getMoney() - down);
        aspAccountRepository.save(aspAccount);
        return "redirect:/Lab1/profile?id="+aspAccount.getId();
    }
}
