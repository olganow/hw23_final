package api.models;


import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseModel {
    private Meta meta;
    private List<User> users;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private int id;
        private int profile;
        private boolean is_private;
        private boolean is_active;
        private boolean is_guest;
        private boolean is_organization;
        private boolean is_author;
        private String short_bio;
        private String details;
        private String first_name;
        private String last_name;
        private String full_name;
        private String alias;
        private String avatar;
        private String cover;
        private int city;
        private int knowledge;
        private int knowledge_rank;
        private int reputation;
        private int reputation_rank;
        private String join_date;
        private List<Integer> social_profiles;
        private int solved_steps_count;
        private int created_courses_count;
        private int created_lessons_count;
        private int issued_certificates_count;
        private int followers_count;
    }

    @Data
    public static class Meta {
        private int page;
        private boolean has_next;
        private boolean has_previous;
    }
}