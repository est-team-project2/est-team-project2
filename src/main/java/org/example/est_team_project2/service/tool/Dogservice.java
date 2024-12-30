// service/DogAgeService.java
@Service
@RequiredArgsConstructor
public class DogAgeService {
    
    public DogAgeResponseDto calculateAge(DogAgeRequestDto request) {
        DogAge dogAge = new DogAge(request.getBirthDate());
        
        DogAgeResponseDto response = new DogAgeResponseDto();
        response.setYears(dogAge.getYears());
        response.setMonths(dogAge.getMonths());
        response.setHumanAge(dogAge.getHumanAge());
        
        return response;
    }
}

// service/VaccinationService.java
@Service
@RequiredArgsConstructor
public class VaccinationService {
    private final VaccinationDao vaccinationDao;

    public List<VaccinationResponseDto> getVaccinationSchedule(VaccinationRequestDto request) {
        List<VaccinationSchedule> schedules = vaccinationDao.findAllSchedules();
        LocalDate birthDate = request.getBirthDate();
        LocalDate today = LocalDate.now();

        return schedules.stream()
            .map(schedule -> {
                VaccinationResponseDto response = new VaccinationResponseDto();
                LocalDate vaccineDate = birthDate.plusWeeks(schedule.getWeeks());
                
                response.setVaccineName(schedule.getVaccineName());
                response.setVaccinationDate(vaccineDate);
                response.setStatus(determineStatus(vaccineDate, today));
                
                return response;
            })
            .collect(Collectors.toList());
    }

    public VaccinationResponseDto getNextVaccination(VaccinationRequestDto request) {
        VaccineInfo vaccineInfo = vaccinationDao.findVaccineByCode(request.getVaccineType())
            .orElseThrow(() -> new IllegalArgumentException("Invalid vaccine type"));

        LocalDate nextDate = request.getLastVaccinationDate()
            .plusDays(vaccineInfo.getIntervalDays());

        VaccinationResponseDto response = new VaccinationResponseDto();
        response.setVaccineName(vaccineInfo.getVaccineName());
        response.setVaccinationDate(nextDate);
        response.setDescription(vaccineInfo.getDescription());
        response.setStatus(determineStatus(nextDate, LocalDate.now()));

        return response;
    }

    private String determineStatus(LocalDate targetDate, LocalDate today) {
        if (targetDate.isBefore(today)) return "PAST";
        if (targetDate.isEqual(today)) return "TODAY";
        return "FUTURE";
    }
}