package by.academy.it.pojo;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "t_messages")
@Data
public class Messages implements Serializable {

    static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "recipient_id")
    private String recipientId;

    @Column(name = "value")
    private String value;

    @Column(name = "timestamp")
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messages messages = (Messages) o;
        return Objects.equals(id, messages.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Messages{");
        sb.append("id='").append(id).append('\'');
        sb.append(", recipientId='").append(recipientId).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}

