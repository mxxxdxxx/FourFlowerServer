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
    private Long user_id;
    private String user_name;
    private String user_login_id;
    private String user_pw;
    private Long user_phone;
    private String user_nickname;
    private LocalDateTime user_join_date;
    private String login_type;
    private Long family_token;
    private Long disturb_id;
}
