package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.Activity;
import dev.naulu.flowing.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return service.getAllActivities();
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return service.getActivityById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        return service.createActivity(activity);
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return service.updateActivity(id, activity);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        service.deleteActivity(id);
    }
}
