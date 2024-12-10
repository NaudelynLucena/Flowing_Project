package dev.naulu.flowing.model;

import jakarta.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String mood; // Estado de ánimo asociado

    @Column(nullable = false)
    private String benefits;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Usuario que creó la actividad (puede ser null si es actividad general)

    public Activity() {}

    public Activity(String name, String description, String mood, String benefits, User user) {
        this.name = name;
        this.description = description;
        this.mood = mood;
        this.benefits = benefits;
        this.user = user;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
