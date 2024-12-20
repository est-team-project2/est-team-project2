package org.example.est_team_project2.api.pedia;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.example.est_team_project2.dto.pedia.VersionRequestDetails;
import org.example.est_team_project2.service.pedia.VersionRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedia")
public class PediaVersionRequestController {

    private final VersionRequestService versionRequestService;

    @GetMapping("/edit/create")
    public String showEditRequestCreatePage() {
        return "/pedia/edit/create_request";
    }

    @PostMapping("/edit/create")
    public String createEditRequest(@ModelAttribute VersionRequestDetails versionRequestDetails,
        @ModelAttribute PediaContentDto pediaContentDto) {

        System.out.println("VersionRequestDetails: " + versionRequestDetails);
        System.out.println("PediaContentDto: " + pediaContentDto);

        VersionRequestDetails output = versionRequestService.createNewEditRequest(
            versionRequestDetails, pediaContentDto);

        return "redirect:/";
    }
}
