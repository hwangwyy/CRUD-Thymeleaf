package vn.iostar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.iostar.model.*;

@Controller
public class DemoController {

    private final List<Product> products = List.of(
            new Product(1L, "Laptop", 1200, true),
            new Product(2L, "Chuột không dây", 25, true),
            new Product(3L, "Màn hình 4K", 480, false));

    private final List<User> users = List.of(
            new User("trung", "Nguyễn Hữu Trung", "trungnh@hcmute.edu.vn", "ADMIN", "Hữu", "Trung"),
            new User("an", "Trần An", "an@example.com", "USER", "An", "Trần"),
            new User("guest", "Khách", "guest@example.com", "GUEST", "Khách", "Vãng lai"));

    @GetMapping("/")
    public String index() { return "index"; }

    // ---- Biến + Standard Expression ----
    @GetMapping("/variables")
    public String variables(Model model) {
        model.addAttribute("name", "Nguyễn Hữu Trung");   // ${name}
        model.addAttribute("person", users.get(0));        // th:object
        model.addAttribute("user", users.get(0));          // form + th:value
        model.addAttribute("product", products.get(2));    // @{...}
        return "variables";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, RedirectAttributes ra) {
        ra.addFlashAttribute("message", user.getUsername());
        return "redirect:/variables";
    }

    // ---- th:if / th:switch / th:each / toán tử ----
    @GetMapping("/logic")
    public String logic(Model model) {
        model.addAttribute("users", users);
        model.addAttribute("products", products);
        model.addAttribute("age", 20);
        model.addAttribute("hasPermission", true);
        model.addAttribute("active", false);
        model.addAttribute("admin", true);
        return "logic";
    }

    // ---- th:each + Info (slide 18-19) ----
    @GetMapping("/profile")
    public String profile(Model model) {
        List<Info> profile = new ArrayList<>();
        profile.add(new Info("fullname", "Hữu Trung"));
        profile.add(new Info("nickname", "HuuTrung"));
        profile.add(new Info("email", "trungnhspkt@gmail.com"));
        profile.add(new Info("website", "https://iotstar.vn"));
        model.addAttribute("profile", profile);
        return "profile";
    }

    // ---- Endpoint để các link @{...} bấm được ----
    @GetMapping("/product/details")
    @ResponseBody
    public String detailsQuery(@RequestParam Long id) { return "Query param: id = " + id; }

    @GetMapping("/product/{id}/details")
    @ResponseBody
    public String detailsPath(@PathVariable Long id) { return "Path variable: id = " + id; }
}
