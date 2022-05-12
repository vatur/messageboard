package com.vkt_board.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid")
    private Integer userId;

    @Column(name = "rating", columnDefinition = "0")
    private Integer rating;

    @Column(nullable = false, name = "text")
    private String text;

    @Column(name = "voteduserid")
    private String votedUserId;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getUserId () {
        return userId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId;
    }

    public Integer getRating () {
        return rating;
    }

    public void setRating (Integer rating) {
        this.rating = rating;
    }

    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getVotedUserId () {
        return votedUserId;
    }

    public void setVotedUserId (String votedUserId) {
        this.votedUserId = votedUserId;
    }
}
