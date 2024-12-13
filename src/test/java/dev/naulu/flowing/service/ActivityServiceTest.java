package dev.naulu.flowing.service;

import dev.naulu.flowing.model.Activity;
import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ActivityServiceTest {

    private ActivityRepository activityRepository;
    private ActivityService activityService;

    private User user;
    private Activity activity;

    @BeforeEach
    public void setUp() {
        activityRepository = mock(ActivityRepository.class);
        activityService = new ActivityService(activityRepository);

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");

        activity = new Activity("Running", "Morning run", "Happy", "Boosts energy", user);
    }

    @Test
    public void testGetAllActivities() {
        when(activityRepository.findByUserId(user.getId())).thenReturn(Arrays.asList(activity));

        List<Activity> activities = activityService.getAllActivities(user.getId());

        assertNotNull(activities);
        assertEquals(1, activities.size());
        assertEquals(activity.getName(), activities.get(0).getName());
        verify(activityRepository).findByUserId(user.getId());
    }

    @Test
    public void testGetActivitiesByMood() {
        when(activityRepository.findByMood("Happy")).thenReturn(Arrays.asList(activity));

        List<Activity> activities = activityService.getActivitiesByMood("Happy");

        assertNotNull(activities);
        assertEquals(1, activities.size());
        assertEquals(activity.getMood(), activities.get(0).getMood());
        verify(activityRepository).findByMood("Happy");
    }

    @Test
    public void testGetActivitiesByMoodAndUser() {
        when(activityRepository.findByMoodAndUserId("Happy", user.getId())).thenReturn(Arrays.asList(activity));

        List<Activity> activities = activityService.getActivitiesByMoodAndUser("Happy", user.getId());

        assertNotNull(activities);
        assertEquals(1, activities.size());
        assertEquals(activity.getMood(), activities.get(0).getMood());
        assertEquals(user.getId(), activities.get(0).getUser().getId());
        verify(activityRepository).findByMoodAndUserId("Happy", user.getId());
    }

@Test
public void testCreateActivity() {
    Activity newActivity = new Activity("Running", "Morning run", "Happy", "Boosts energy", user);
    
    when(activityRepository.save(any(Activity.class))).thenReturn(newActivity);

    Activity createdActivity = activityService.createActivity("Running", "Morning run", "Happy", "Boosts energy", user);

    assertNotNull(createdActivity);
    assertEquals("Running", createdActivity.getName());
    assertEquals("Morning run", createdActivity.getDescription());
    assertEquals("Happy", createdActivity.getMood());
    assertEquals(user.getId(), createdActivity.getUser().getId());

    verify(activityRepository).save(any(Activity.class));
}

    @Test
    public void testUpdateActivity() {
        when(activityRepository.findById(activity.getId())).thenReturn(Optional.of(activity));
        when(activityRepository.save(activity)).thenReturn(activity);

        Activity updatedActivity = activityService.updateActivity(activity.getId(), "Running", "Evening run", "Energetic", "Improves health");

        assertNotNull(updatedActivity);
        assertEquals("Evening run", updatedActivity.getDescription());
        assertEquals("Energetic", updatedActivity.getMood());
        assertEquals("Improves health", updatedActivity.getBenefits());
        verify(activityRepository).findById(activity.getId());
        verify(activityRepository).save(updatedActivity);
    }

    @Test
    public void testUpdateActivityNotFound() {
        when(activityRepository.findById(activity.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            activityService.updateActivity(activity.getId(), "Running", "Evening run", "Energetic", "Improves health");
        });

        assertEquals("Activity not found", exception.getMessage());
        verify(activityRepository).findById(activity.getId());
    }

    @Test
    public void testDeleteActivity() {
        doNothing().when(activityRepository).deleteById(activity.getId());

        activityService.deleteActivity(activity.getId());

        verify(activityRepository).deleteById(activity.getId());
    }
}
