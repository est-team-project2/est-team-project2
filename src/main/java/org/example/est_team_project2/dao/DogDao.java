@Repository
@RequiredArgsConstructor
public class VaccinationDao {
    private final List<VaccinationSchedule> standardSchedule = Arrays.asList(
        new VaccinationSchedule(6, "DHPPL 1차"),
        new VaccinationSchedule(9, "DHPPL 2차"),
        new VaccinationSchedule(12, "DHPPL 3차"),
        new VaccinationSchedule(16, "DHPPL 4차"),
        new VaccinationSchedule(8, "코로나 1차"),
        new VaccinationSchedule(11, "코로나 2차"),
        new VaccinationSchedule(12, "광견병 1차"),
        new VaccinationSchedule(52, "1년차 추가접종 (종합)")
    );

    public List<VaccinationSchedule> findAllSchedules() {
        return standardSchedule;
    }

    private final Map<String, VaccineInfo> vaccineInfoMap = Map.of(
        "dhppl", VaccineInfo.builder()
            .code("dhppl")
            .name("종합백신 (DHPPL)")
            .description("매년 1회 접종이 필요합니다.")
            .interval(365)
            .isRequired(true)
            .startWeek(6)
            .endWeek(16)
            .build(),
        "corona", VaccineInfo.builder()
            .code("corona")
            .name("코로나")
            .description("매년 1회 접종이 필요합니다.")
            .interval(365)
            .isRequired(true)
            .startWeek(8)
            .endWeek(12)
            .build(),
        "rabies", VaccineInfo.builder()
            .code("rabies")
            .name("광견병")
            .description("매년 1회 접종이 필요합니다.")
            .interval(365)
            .isRequired(true)
            .startWeek(12)
            .endWeek(16)
            .build(),
        "kennel", VaccineInfo.builder()
            .code("kennel")
            .name("켄넬코프")
            .description("6개월마다 접종이 필요합니다.")
            .interval(180)
            .isRequired(false)
            .startWeek(8)
            .endWeek(16)
            .build()
    );

    public VaccineInfo findVaccineInfoByCode(String code) {
        return Optional.ofNullable(vaccineInfoMap.get(code))
            .orElseThrow(() -> new IllegalArgumentException("잘못된 백신 타입입니다"));
    }
}