package dev.naulu.flowing.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class MoodRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String mood;

    @Column(nullable = false)
    private LocalDate date;

    public MoodRecord() {}

    public MoodRecord(User user, String mood, LocalDate date) {
        this.user = user;
        this.mood = mood;
        this.date = date;
    }

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

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MoodRecord that = (MoodRecord) o;
    return Objects.equals(user, that.user) &&
            Objects.equals(mood, that.mood) &&
            Objects.equals(date, that.date);
}

@Override
public int hashCode() {
    return Objects.hash(user, mood, date);
}

}