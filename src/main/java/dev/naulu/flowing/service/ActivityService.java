package dev.naulu.flowing.service;

import dev.naulu.flowing.model.Activity;
import dev.naulu.flowing.repository.ActivityRepository;
import dev.naulu.flowing.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository repository;

    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public List<Activity> getAllActivities() {
        return repository.findAll();
    }

    public List<Activity> getActivitiesByMood(String mood) {
        return repository.findByMood(mood);
    }

    public List<Activity> getActivitiesByMoodAndUser(String mood, Long userId) {
        return repository.findByMoodAndUserId(mood, userId);
    }

    public Activity createActivity(String name, String description, String mood, String benefits, User user) {
        Activity activity = new Activity(name, description, mood, benefits, user);
        return repository.save(activity);
    }

    public Activity updateActivity(Long id, String name, String description, String mood, String benefits) {
        Activity activity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        activity.setName(name);
        activity.setDescription(description);
        activity.setMood(mood);
        activity.setBenefits(benefits);
        return repository.save(activity);
    }

    public void deleteActivity(Long id) {
        repository.deleteById(id);
    }
}
