// dto/DogAgeRequestDto.java
@Getter @Setter
@NoArgsConstructor
public class DogAgeRequestDto {
    private LocalDate birthDate;
}

// dto/DogAgeResponseDto.java
@Getter @Setter 
@NoArgsConstructor
public class DogAgeResponseDto {
    private int years;
    private int months;
    private int humanAge;
}

// dto/VaccinationRequestDto.java
@Getter @Setter
@NoArgsConstructor
public class VaccinationRequestDto {
    private LocalDate birthDate;
    private String vaccineType;
    private LocalDate lastVaccinationDate;
}

// dto/VaccinationResponseDto.java
@Getter @Setter
@NoArgsConstructor
public class VaccinationResponseDto {
    private String vaccineName;
    private String status;
    private LocalDate vaccinationDate;
    private String description;
}