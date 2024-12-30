// dao/VaccinationDao.java
@Repository
@RequiredArgsConstructor
public class VaccinationDao {
    private final List<VaccinationSchedule> schedules = Arrays.asList(
        createSchedule(1L, 6, "DHPPL 1차"),
        createSchedule(2L, 9, "DHPPL 2차"),
        createSchedule(3L, 12, "DHPPL 3차"),
        createSchedule(4L, 16, "DHPPL 4차")
        // ... 더 많은 스케줄
    );

    private final Map<String, VaccineInfo> vaccines = createVaccineMap();

    public List<VaccinationSchedule> findAllSchedules() {
        return schedules;
    }

    public Optional<VaccineInfo> findVaccineByCode(String code) {
        return Optional.ofNullable(vaccines.get(code));
    }

    private VaccinationSchedule createSchedule(Long id, int weeks, String name) {
        VaccinationSchedule schedule = new VaccinationSchedule();
        schedule.setId(id);
        schedule.setWeeks(weeks);
        schedule.setVaccineName(name);
        return schedule;
    }

    private Map<String, VaccineInfo> createVaccineMap() {
        Map<String, VaccineInfo> map = new HashMap<>();
        
        VaccineInfo dhppl = new VaccineInfo();
        dhppl.setVaccineCode("DHPPL");
        dhppl.setVaccineName("종합백신");
        dhppl.setDescription("매년 1회 접종이 필요합니다.");
        dhppl.setIntervalDays(365);
        dhppl.setRequired(true);
        map.put("DHPPL", dhppl);
        
        // ... 다른 백신 정보들
        
        return map;
    }
}