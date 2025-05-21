package kg.attractor.projects.instagram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "followers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="follower_id")
    private User userFollower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="receiver_id")
    private User userReceiver;



}
