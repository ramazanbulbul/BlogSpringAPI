package me.ramazanbulbul.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String username;
    private String nameSurname;
    @Column(unique = true)
    private String email;
    private String password;
    private Date deletedTimezone;
    private boolean isDeleted;
}
