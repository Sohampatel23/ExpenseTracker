package com.example.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UAuthentication")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    public UserToken(User user) {
        this.user = user;
        this.tokenCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }

    @OneToOne
    @JoinColumn(name = "fk_userId")
    User user;

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public LocalDateTime getTokenCreationTime() {
        return tokenCreationTime;
    }

    public void setTokenCreationTime(LocalDateTime tokenCreationTime) {
        this.tokenCreationTime = tokenCreationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
