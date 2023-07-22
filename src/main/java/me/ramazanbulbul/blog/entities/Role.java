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

    @ManyToOne
    @JoinColumn(name="created_user_id")
    private User createdUser;
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createdTimestamp;

    @ManyToOne
    @JoinColumn(name="updated_user_id")
    private User updatedUser;
    private Date updatedTimestamp;

    @ManyToOne
    @JoinColumn(name="deleted_user_id")
    private User deletedUser;
    private Date deletedTimestamp;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}
