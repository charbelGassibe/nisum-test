package cl.charbel.nisumtest.persistence.domain;

import static cl.charbel.nisumtest.jwt.JwtTokenProvider.generateToken;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  private String name;
  private String email;
  private String password;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private List<Phone> phones;

  private LocalDateTime created;
  private LocalDateTime modified;
  private LocalDateTime lastLogin;
  private boolean isActive;
  private String token;

  @PrePersist
  protected void onCreate() {
    this.created = LocalDateTime.now();
    this.modified = this.created;
    this.lastLogin = this.created;
    this.isActive = true;
    this.token = generateToken();
    this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
  }

  @PreUpdate
  protected void onUpdate() {
    this.modified = LocalDateTime.now();
  }
}
