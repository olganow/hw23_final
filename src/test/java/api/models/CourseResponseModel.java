package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем неизвестные поля
public class CourseResponseModel {
    private Meta meta;
    private List<Course> courses;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Course {
        private int id;
        private String summary;
        private String workload;
        private String cover;
        private String intro;
        private String course_format;
        private String target_audience;
        private String certificate_footer;
        private String certificate_cover_org;
        private boolean _certificate_issued;
        private boolean _certificate_auto_issued;
        private Integer certificate_regular_threshold;
        private Integer certificate_distinction_threshold;
        private List<Integer> instructors;
        private String certificate;
        private String requirements;
        private String description;
    }

    @Data
    public static class Meta {
        private int page;
        private boolean has_next;
        private boolean has_previous;
    }
}