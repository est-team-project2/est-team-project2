package org.example.est_team_project2.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNicknameReq {
    @Email
    @NotBlank
    private String email;

    @NotBlank(message = "닉네임을 입력 해주세요")
    @Size(min = 2, max = 20, message = "닉네임을 제대로 입력 해주세요")
    private String newNickname;
}
