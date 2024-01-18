package org.marx.PP_3_1_2_CROD.SPRINBOOT.controller;


import org.marx.PP_3_1_2_CROD.SPRINBOOT.model.User;
import org.marx.PP_3_1_2_CROD.SPRINBOOT.service.UserService;
import org.marx.PP_3_1_2_CROD.SPRINBOOT.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping({"/users", ""})
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String introduceUserUser(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes attributes) {
        userService.createUser(user);
        attributes.addFlashAttribute("flashMessage", "User " + user.getName() +
                " created!");
        return "redirect:/users";
    }


    @GetMapping(value = "/update", params = "id")
    public String updateUserForm(@RequestParam("id") int id,
                                 RedirectAttributes attributes, Model model) {
        try {
            model.addAttribute("user", userService.getUser(id));
        } catch (NumberFormatException | NullPointerException e) {
            attributes.addFlashAttribute("flashMessage", "User not found");
            return "redirect:/users";
        }
        return "/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", defaultValue = "") long id,
                             RedirectAttributes attributes) {
        userService.deleteUser(id);
        attributes.addFlashAttribute("flashMessage", "User " +
                " deleted!");
        return "redirect:/users";
    }
}
