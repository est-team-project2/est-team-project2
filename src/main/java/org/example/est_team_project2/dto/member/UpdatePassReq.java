package org.example.est_team_project2.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePassReq {
    @Email
    @NotBlank
    private String email;

    @NotBlank(message = "비밀번호를 입력 해주세요")
    @Size(min = 8, max = 15, message = "비밀번호를 제대로 입력 해주세요.")
    private String currentPassword;

    @NotBlank(message = "비밀번호를 입력 해주세요")
    @Size(min = 8, max = 15, message = "비밀번호를 제대로 입력 해주세요.")
    private String newPassword;
}