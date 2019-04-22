package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "post_types")
public class PostTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "post_type")
    private String postType;

    @Setter
    @Getter
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Posts> posts;

    public PostTypes(String postType) {
        this.postType = postType;
        this.posts = new HashSet<>();
    }

    public void addPost(Posts post) {
        posts.add(post);
    }

    public void removePost(Posts post) {
        posts.remove(post);
    }

}
