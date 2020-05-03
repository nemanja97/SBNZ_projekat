package rs.ac.uns.ftn.sbnz.models;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbnz.models.enums.Role;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
        name = "users",
        indexes = { @Index(name = "idx_user_email", columnList = "email") }
)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }
}
