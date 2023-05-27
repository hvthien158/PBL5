package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "blogs")
public class Blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDate created_at;

    @OneToMany(mappedBy = "blogs")
    private Set<Bookmarks> listBookmarks;

    @ManyToOne
    @JoinColumn(name = "user_post_id")
    private Users users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public int getUsers() {
        return users.getId();
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
