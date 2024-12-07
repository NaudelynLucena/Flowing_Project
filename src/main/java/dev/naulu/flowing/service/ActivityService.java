package dev.naulu.flowing.service;

import dev.naulu.flowing.model.Activity;
import dev.naulu.flowing.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository repository;

    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public List<Activity> getAllActivities() {
        return repository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return repository.findById(id);
    }

    public Activity createActivity(Activity activity) {
        return repository.save(activity);
    }

    public Activity updateActivity(Long id, Activity newActivity) {
        return repository.findById(id)
                .map(activity -> {
                    activity.setName(newActivity.getName());
                    activity.setDescription(newActivity.getDescription());
                    activity.setMood(newActivity.getMood());
                    return repository.save(activity);
                })
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    public void deleteActivity(Long id) {
        repository.deleteById(id);
    }
}
