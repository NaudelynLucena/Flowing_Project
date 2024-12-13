package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.Activity;
import dev.naulu.flowing.service.ActivityService;
import dev.naulu.flowing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ActivityController activityController;

    private Activity activity;
    private Long userId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        activity = new Activity();
        activity.setName("Jogging");
        activity.setDescription("Morning exercise");
        activity.setMood("Happy");
        activity.setBenefits("Health");

        userId = 1L;
    }

    @Test
    public void testGetAllActivitiesForAuthenticatedUser() {
        when(authentication.getName()).thenReturn("user@example.com");
        when(userService.getUserIdByUsername("user@example.com")).thenReturn(userId);
        when(activityService.getAllActivities(userId)).thenReturn(Arrays.asList(activity));

        List<Activity> activities = activityController.getAllActivitiesForAuthenticatedUser(authentication);

        assertNotNull(activities);
        assertEquals(1, activities.size());
        assertEquals("Jogging", activities.get(0).getName());
    }

    @Test
    public void testGetAllActivitiesByUserId() {
        when(activityService.getAllActivities(userId)).thenReturn(Arrays.asList(activity));

        List<Activity> activities = activityController.getAllActivities(userId);

        assertNotNull(activities);
        assertEquals(1, activities.size());
        assertEquals("Jogging", activities.get(0).getName());
    }

    @Test
    public void testGetActivitiesByMood() {
        String mood = "Happy";
        when(authentication.getName()).thenReturn("user@example.com");
        when(userService.getUserIdByUsername("user@example.com")).thenReturn(userId);
        when(activityService.getActivitiesByMoodAndUser(mood, userId)).thenReturn(Arrays.asList(activity));

        List<Activity> activities = activityController.getActivitiesByMood(mood, authentication);

        assertNotNull(activities);
        assertEquals(1, activities.size());
        assertEquals("Jogging", activities.get(0).getName());
    }

    @Test
    public void testCreateActivity() {
        when(authentication.getName()).thenReturn("user@example.com");
        when(userService.getUserIdByUsername("user@example.com")).thenReturn(userId);
        when(userService.findUserById(userId)).thenReturn(null);  // Suponemos que el usuario existe
        when(activityService.createActivity(any(), any(), any(), any(), any())).thenReturn(activity);

        Activity createdActivity = activityController.createActivity(activity, authentication);

        assertNotNull(createdActivity);
        assertEquals("Jogging", createdActivity.getName());
    }

    @Test
    public void testUpdateActivity() {
        Activity updatedActivity = new Activity();
        updatedActivity.setName("Updated Jogging");

        when(activityService.updateActivity(eq(1L), any(), any(), any(), any())).thenReturn(updatedActivity);

        Activity result = activityController.updateActivity(1L, updatedActivity);

        assertNotNull(result);
        assertEquals("Updated Jogging", result.getName());
    }

    @Test
    public void testDeleteActivity() {
        doNothing().when(activityService).deleteActivity(1L);

        activityController.deleteActivity(1L);

        verify(activityService, times(1)).deleteActivity(1L);
    }
}
