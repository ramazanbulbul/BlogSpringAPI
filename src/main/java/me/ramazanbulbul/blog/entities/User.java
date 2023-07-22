package me.ramazanbulbul.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
    private Date createdTimestamp;
    private Long createdUserId;

    private Date updatedTimestamp;
    private Long updatedUserId;

    private Date deletedTimestamp;
    private Long deletedUserId;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;



    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}
