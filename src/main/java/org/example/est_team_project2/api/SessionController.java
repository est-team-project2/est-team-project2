package org.example.est_team_project2.api;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class SessionController {

    @GetMapping("/session-info")
    public String sessionInfo(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("authentication = {}", authentication);
        log.info("authentication.getAuthorities() = {}", authentication.getAuthorities());
        log.info("authentication.getPrincipal() = {}", authentication.getPrincipal());

        String username = authentication.getName();

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        model.addAttribute("username", username);
        model.addAttribute("roles", roles);

        return "testPage/profile";
    }
}
