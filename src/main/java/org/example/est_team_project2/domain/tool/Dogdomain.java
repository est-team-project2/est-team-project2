// domain/VaccinationSchedule.java
@Entity
@Getter @Setter
@NoArgsConstructor
public class VaccinationSchedule {
    @Id
    private Long id;
    private int weeks;
    private String vaccineName;
}

// domain/VaccineInfo.java
@Entity
@Getter @Setter
@NoArgsConstructor
public class VaccineInfo {
    @Id
    private String vaccineCode;
    private String vaccineName;
    private String description;
    private int intervalDays;
    private boolean required;
}

// domain/DogAge.java
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DogAge {
    private int years;
    private int months;
    private int humanAge;

    public DogAge(LocalDate birthDate) {
        calculateAge(birthDate);
    }

    private void calculateAge(LocalDate birthDate) {
        // 나이 계산 로직
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        
        this.years = period.getYears();
        this.months = period.getMonths();
        
        double dogAge = years + (months / 12.0);
        if (dogAge <= 1) {
            this.humanAge = (int) Math.round(15 * dogAge);
        } else if (dogAge <= 2) {
            this.humanAge = (int) Math.round(15 + (dogAge - 1) * 9);
        } else {
            this.humanAge = (int) Math.round(24 + (dogAge - 2) * 4);
        }
    }
}