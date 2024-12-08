package dev.naulu.flowing.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class MoodRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relacionado con el usuario

    @Column(nullable = false)
    private String mood; // El estado de ánimo del usuario

    @Column(nullable = false)
    private LocalDate date; // Fecha del registro del estado de ánimo

    public MoodRecord() {}

    public MoodRecord(User user, String mood, LocalDate date) {
        this.user = user;
        this.mood = mood;
        this.date = date;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
