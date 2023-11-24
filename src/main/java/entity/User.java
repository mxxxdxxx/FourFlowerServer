// User.java
package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;             // changed from user_id to id
    private String name;         // changed from user_name to name
    private String loginId;      // changed from user_login_id to loginId
    private String password;     // changed from user_pw to password
    private Long phone;          // changed from user_phone to phone
    private String nickname;     // changed from user_nickname to nickname
    private LocalDateTime joinDate;  // changed from user_join_date to joinDate
    private String loginType;    // changed from login_type to loginType
    private Long familyToken;    // changed from family_token to familyToken
    private Long disturbId;      // changed from disturb_id to disturbId
}