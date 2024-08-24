package com.example.one_manage.controller;

import com.example.one_manage.entity.User;
import com.example.one_manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        // Use UserService to find user
        Optional<User> optionalUser = userService.getUserByUserName(username);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            User user = optionalUser.get();
            model.addAttribute("username", username);
            model.addAttribute("isShowedNoti", user.isShownNoti());
            return "home";
        }
        else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "home"; // Return home view
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @PostMapping("/updateNotiStatus")
    @ResponseBody
    public ResponseEntity<Void> updateNotiStatus(@RequestBody Map<String, Object> request) {
        String username = (String) request.get("username"); // Get the username from the request
        boolean showedNoti = true; // Set to true when the notification is dismissed

        userService.updateShowedNoti(username, showedNoti); // Call the service method to update the field

        return ResponseEntity.ok().build(); // Respond with 200 OK
    }

}