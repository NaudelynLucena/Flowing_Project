package dev.naulu.flowing.repository;

import dev.naulu.flowing.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByMood(String mood);
    List<Activity> findByMoodAndUserId(String mood, Long userId);
}
