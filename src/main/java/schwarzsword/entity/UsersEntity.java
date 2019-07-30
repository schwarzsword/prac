package schwarzsword.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "users", schema = "public", catalog = "documents")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "email", unique = true)
    private String email;
    @Basic
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Basic
    @Column(name = "password")
    private String password;

    protected UsersEntity(){}

    public UsersEntity(String username, String password, String mail) {
        this.email = mail;
        this.username = username;
        this.password = password;
    }
}
