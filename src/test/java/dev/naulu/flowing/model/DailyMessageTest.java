package dev.naulu.flowing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DailyMessageTest {

    private User user;
    private DailyMessage dailyMessage1;
    private DailyMessage dailyMessage2;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);

        dailyMessage1 = new DailyMessage(user, "Test message", LocalDate.now());
        dailyMessage2 = new DailyMessage(user, "Test message", LocalDate.now());
    }

    @Test
    public void testConstructor() {
        assertNotNull(dailyMessage1);
        assertEquals(user, dailyMessage1.getUser());
        assertEquals("Test message", dailyMessage1.getMessage());
        assertEquals(LocalDate.now(), dailyMessage1.getDate());
    }

    @Test
    public void testEquals() {
        assertEquals(dailyMessage1, dailyMessage2);
    }

    @Test
    public void testNotEqualsWithDifferentUser() {
        User differentUser = new User();
        differentUser.setId(2L);
        dailyMessage2.setUser(differentUser);

        assertNotEquals(dailyMessage1, dailyMessage2);
    }

    @Test
    public void testNotEqualsWithDifferentMessage() {
        dailyMessage2.setMessage("Different message");

        assertNotEquals(dailyMessage1, dailyMessage2);
    }

    @Test
    public void testNotEqualsWithDifferentDate() {
        dailyMessage2.setDate(LocalDate.of(2024, 12, 31));

        assertNotEquals(dailyMessage1, dailyMessage2);
    }

    @Test
    public void testHashCode() {
        assertEquals(dailyMessage1.hashCode(), dailyMessage2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentValues() {
        dailyMessage2.setMessage("Different message");
        assertNotEquals(dailyMessage1.hashCode(), dailyMessage2.hashCode());
    }
}