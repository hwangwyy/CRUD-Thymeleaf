package vn.iostar.model;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    private String username;
    private String name;
    private String email;
    private String role;      // ADMIN / USER / khác
    private String firstName;
    private String lastName;

    public boolean isAdmin() { return "ADMIN".equals(role); }
}
