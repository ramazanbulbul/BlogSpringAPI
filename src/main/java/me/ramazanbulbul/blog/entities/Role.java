package me.ramazanbulbul.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;
    private String roleDetail;
    private Integer roleLevel;
    @OneToMany(mappedBy="role")
    private Set<User> users;
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createdTimezone;
    private Date deletedTimezone;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}
