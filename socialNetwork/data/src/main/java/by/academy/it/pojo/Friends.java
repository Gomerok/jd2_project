package by.academy.it.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_friends")
@Data
public class Friends implements Serializable {

    static final long serialVersionUID = 2L;

    @Id
    @Column(name = "user_friends_id")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "friend_id")
    private String friendId;

    @Column(name = "status")
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friends friends = (Friends) o;
        return Objects.equals(id, friends.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Friends{");
        sb.append("id='").append(id).append('\'');
        sb.append(", user=").append(user);
        sb.append(", friendId='").append(friendId).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
