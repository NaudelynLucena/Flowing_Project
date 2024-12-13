package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.Activity;
import dev.naulu.flowing.service.ActivityService;
import dev.naulu.flowing.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService service;
    private final UserService userService;

    public ActivityController(ActivityService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public List<Activity> getAllActivitiesForAuthenticatedUser(Authentication authentication) {
        String email = authentication.getName();
        Long userId = userService.getUserIdByUsername(email);
        return service.getAllActivities(userId);
    }

    @GetMapping("/{userId}")
    public List<Activity> getAllActivities(@PathVariable Long userId) {
        return service.getAllActivities(userId);
    }

    @GetMapping("/mood/{mood}")
    public List<Activity> getActivitiesByMood(@PathVariable String mood, Authentication authentication) {
        String email = authentication.getName();
        Long userId = userService.getUserIdByUsername(email);
        return service.getActivitiesByMoodAndUser(mood, userId);
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity, Authentication authentication) {
        String email = authentication.getName();
        Long userId = userService.getUserIdByUsername(email);
        return service.createActivity(
            activity.getName(),
            activity.getDescription(),
            activity.getMood(),
            activity.getBenefits(),
            userService.findUserById(userId)
        );
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return service.updateActivity(
            id,
            activity.getName(),
            activity.getDescription(),
            activity.getMood(),
            activity.getBenefits()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        service.deleteActivity(id);
    }
}
