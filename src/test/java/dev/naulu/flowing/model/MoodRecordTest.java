package dev.naulu.flowing.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MoodRecordTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
    }

    @Test
    void testMoodRecordCreation() {
        String mood = "Happy";
        LocalDate date = LocalDate.now();
        MoodRecord moodRecord = new MoodRecord(user, mood, date);

        assertNotNull(moodRecord);
        assertEquals(user, moodRecord.getUser());
        assertEquals(mood, moodRecord.getMood());
        assertEquals(date, moodRecord.getDate());
    }

    @Test
    void testSettersAndGetters() {
        MoodRecord moodRecord = new MoodRecord();
        String mood = "Sad";
        LocalDate date = LocalDate.now().minusDays(1);

        moodRecord.setUser(user);
        moodRecord.setMood(mood);
        moodRecord.setDate(date);

        assertEquals(user, moodRecord.getUser());
        assertEquals(mood, moodRecord.getMood());
        assertEquals(date, moodRecord.getDate());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDate date = LocalDate.now();
        MoodRecord moodRecord1 = new MoodRecord(user, "Happy", date);
        MoodRecord moodRecord2 = new MoodRecord(user, "Happy", date);

        assertEquals(moodRecord1, moodRecord2);
        assertEquals(moodRecord1.hashCode(), moodRecord2.hashCode());

        moodRecord2.setMood("Sad");
        assertNotEquals(moodRecord1, moodRecord2);
    }
}