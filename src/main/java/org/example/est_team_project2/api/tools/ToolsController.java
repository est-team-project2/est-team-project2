package org.example.est_team_project2.api.tools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tools")
public class ToolsController {
    @GetMapping("/map")
    public String map() {
        return "tools/mapsearch";
    }

    @GetMapping("/dog-calculator")
    public String dogCalculator() {
        return "tools/dog-calculator";
    }

}
