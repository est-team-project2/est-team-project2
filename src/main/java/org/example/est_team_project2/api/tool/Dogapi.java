// api/DogApiController.java
@RestController
@RequestMapping("/api/v1/dogs")
@RequiredArgsConstructor
@Slf4j
public class DogApiController {
    private final DogAgeService dogAgeService;
    private final VaccinationService vaccinationService;

    @PostMapping("/age")
    public ResponseEntity<DogAgeResponseDto> calculateAge(
            @RequestBody @Valid DogAgeRequestDto request) {
        log.info("나이 계산 요청: birthDate={}", request.getBirthDate());
        DogAgeResponseDto response = dogAgeService.calculateAge(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/vaccinations/schedule")
    public ResponseEntity<List<VaccinationResponseDto>> getVaccinationSchedule(
            @RequestBody @Valid VaccinationRequestDto request) {
        log.info("예방접종 일정 요청: birthDate={}", request.getBirthDate());
        List<VaccinationResponseDto> schedule = vaccinationService.getVaccinationSchedule(request);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/vaccinations/next")
    public ResponseEntity<VaccinationResponseDto> getNextVaccination(
            @RequestBody @Valid VaccinationRequestDto request) {
        log.info("다음 접종일 계산 요청: type={}, lastDate={}", 
                request.getVaccineType(), request.getLastVaccinationDate());
        VaccinationResponseDto nextVaccination = vaccinationService.getNextVaccination(request);
        return ResponseEntity.ok(nextVaccination);
    }
}

// api/DogViewController.java
@Controller
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogViewController {
    
    @GetMapping
    public String dogManagementPage() {
        return "dogs/index";
    }
    
    @GetMapping("/age")
    public String ageCalculatorPage() {
        return "dogs/age-calculator";
    }
    
    @GetMapping("/vaccinations")
    public String vaccinationPage() {
        return "dogs/vaccination";
    }
}