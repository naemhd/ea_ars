package edu.miu.cs.cs544.ea_ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    @Enumerated(value=EnumType.STRING)
    @Column(name="role",columnDefinition = "text",length = 15)
    private UserRole role;

    private boolean isEnabled;

    public User(String username, String password, UserRole role, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
    }
}
