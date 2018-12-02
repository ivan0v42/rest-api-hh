import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vacancies {

    public VacancyInfo[] items;

    public VacancyInfo[] getAllVacancies() {
        return this.items;
    }
}