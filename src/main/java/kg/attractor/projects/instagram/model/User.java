package kg.attractor.projects.instagram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;


    private String username;
    private String password;
    private String avatar;

    @Column(name="user_info")
    private String info;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @OneToMany(fetch = FetchType.EAGER,  mappedBy = "user")
    private List<Post> posts;
}
