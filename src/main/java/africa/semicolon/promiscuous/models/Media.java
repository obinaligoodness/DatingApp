package africa.semicolon.promiscuous.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Column( unique = true,columnDefinition = "MEDIUMTEXT")
    private String url;
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<Reaction> reactions;
    @ManyToOne
    private User user;
}
