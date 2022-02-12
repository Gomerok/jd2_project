package by.academy.it.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "t_news")
@NoArgsConstructor
@Data
public class UserNews implements Serializable {

    static final long serialVersionUID = 5L;

    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "header", length = 50)
    private String header;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "creation_time")
    private Date creationTime;

    public UserNews(User user, String header, String description, Date creationTime) {
        this.user = user;
        this.header = header;
        this.description = description;
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNews userNews = (UserNews) o;
        return Objects.equals(id, userNews.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserNews{");
        sb.append("id='").append(id).append('\'');
        sb.append(", user=").append(user);
        sb.append(", header='").append(header).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", creationTime=").append(creationTime);
        sb.append('}');
        return sb.toString();
    }
}