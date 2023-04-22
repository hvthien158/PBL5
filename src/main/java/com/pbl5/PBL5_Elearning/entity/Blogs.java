package com.pbl5.PBL5_Elearning.entity;

import jakarta.persistence.*;

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

    @Column(name = "description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsers() {
        return users.getId();
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
