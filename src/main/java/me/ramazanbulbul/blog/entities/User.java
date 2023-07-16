package me.ramazanbulbul.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String username;
    private String nameSurname;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createdTimezone;
    private Date deletedTimezone;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Collection<Role> role;

}
