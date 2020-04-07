package com.example.java.lab3.controllers;

import com.example.java.lab3.entities.Roles;
import com.example.java.lab3.entities.User;
import com.example.java.lab3.repositories.RolesRepository;
import com.example.java.lab3.repositories.UserRepository;
import com.example.java.lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class FriendController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("friends/{tab}")
    public String friends(Model model,
                          Authentication auth,
                          @PathVariable String tab) {
        User user = userService.findByUsername(auth.getName());
        Roles role = rolesRepository.findByRole("ROLE_USER");
        List<User> users = userRepository.findByRoles(role);
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("tab", tab);
        return "friends";
    }

    @GetMapping("addfriend/{id_user}")
    public String addFriend(Authentication auth,
                            @PathVariable Long id_user) {
        User user = userService.findByUsername(auth.getName());
        Set<User> friends = user.getFriends();
        User friend = new User();
        friend.setId(id_user);
        friends.add(friend);
        user.setFriends(friends);
        userRepository.save(user);
        return "redirect:/friends/peoples";
    }

    @GetMapping("deletefriend/{id_user}")
    public String deleteFriend(Authentication auth,
                               @PathVariable Long id_user) {
        User user = userService.findByUsername(auth.getName());
        Set<User> friends = user.getFriends();
        User friend = userRepository.getOne(id_user);
        friends.remove(friend);
        user.setFriends(friends);
        userRepository.save(user);
        return "redirect:/friends/peoples";
    }

}
