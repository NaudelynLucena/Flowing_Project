package dev.naulu.flowing.model;

import org.junit.jupiter.api.Test;

public class ActivityTest {

    @Test
    void testGetBenefits() {
        Activity activity = new Activity();
        activity.setBenefits("Benefits");
        assert activity.getBenefits().equals("Benefits");
    }

    @Test
    void testGetDescription() {
        Activity activity = new Activity();
        activity.setDescription("Description");
        assert activity.getDescription().equals("Description");
    }

    @Test
    void testGetId() {
        Activity activity = new Activity();
        activity.setId(1L);
        assert activity.getId().equals(1L);
    }

    @Test
    void testGetMood() {
        Activity activity = new Activity();
        activity.setMood("Mood");
        assert activity.getMood().equals("Mood");
    }

    @Test
    void testGetName() {
        Activity activity = new Activity();
        activity.setName("Name");
        assert activity.getName().equals("Name");
    }

    @Test
    void testGetUser() {
        Activity activity = new Activity();
        activity.setUser(new User());
        assert activity.getUser() != null;
    }

    @Test
    void testSetBenefits() {
        Activity activity = new Activity();
        activity.setBenefits("Benefits");
        assert activity.getBenefits().equals("Benefits");
    }

    @Test
    void testSetDescription() {
        Activity activity = new Activity();
        activity.setDescription("Description");
        assert activity.getDescription().equals("Description");
    }

    @Test
    void testSetId() {
        Activity activity = new Activity();
        activity.setId(1L);
        assert activity.getId().equals(1L);
    }

    @Test
    void testSetMood() {
        Activity activity = new Activity();
        activity.setMood("Mood");
        assert activity.getMood().equals("Mood");
    }

    @Test
    void testSetName() {
        Activity activity = new Activity();
        activity.setName("Name");
        assert activity.getName().equals("Name");
    }

    @Test
    void testSetUser() {
        Activity activity = new Activity();
        activity.setUser(new User());
        assert activity.getUser() != null;
    }
}
