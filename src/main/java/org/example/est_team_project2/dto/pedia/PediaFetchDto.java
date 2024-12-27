package org.example.est_team_project2.dto.pedia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PediaFetchDto {
    private String code;
    private String memberEmail;
}