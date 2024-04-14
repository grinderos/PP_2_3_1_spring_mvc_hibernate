package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/users";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping("/users/new")
    public String addNewUserToBD(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

//    @GetMapping("/users/{id}/edit")
//    public String editUser(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "users/edit";
//    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

//    @PatchMapping("/users/{id}")
//        public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
//        userService.updateUser(id, user);
//        return "redirect:/users/";
//    }

    @PatchMapping("/users/update")
    public String updateUser(@RequestParam("id") long id, @ModelAttribute("user") User user) {
        userService.updateUser(id, user);
        return "redirect:/users/";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
