package me.ramazanbulbul.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String shortDescr;
    @Column(columnDefinition = "text")
    private String descr;

    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createdTimestamp;
    @ManyToOne
    @JoinColumn(name="created_user_id")
    private User createdUser;

    private Date updatedTimestamp;
    @ManyToOne
    @JoinColumn(name="updated_user_id")
    private User updatedUser;

    private Date deletedTimestamp;
    @ManyToOne
    @JoinColumn(name="deleted_user_id")
    private User deletedUser;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}