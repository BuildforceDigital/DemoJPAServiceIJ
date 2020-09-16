package com.vladmihalcea.OneToMany;

import jakarta.persistence. Entity;
import jakarta.persistence. FetchType;
import jakarta.persistence. GeneratedValue;
import jakarta.persistence. GenerationType;
import jakarta.persistence. Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity(name = "PostComment")
@Table(name = "post_comment", schema = "OLINGO")
public class PostComment {

    public PostComment(String review) {
        this.review = review;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PostComment() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    private String review;

    //Constructors, getters and setters removed for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostComment )) return false;
        return id != null && id.equals(((PostComment) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}