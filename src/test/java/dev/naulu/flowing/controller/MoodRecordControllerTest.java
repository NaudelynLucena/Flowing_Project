package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.MoodRecord;
import dev.naulu.flowing.service.MoodRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoodRecordControllerTest {

    @Mock
    private MoodRecordService moodRecordService;

    @InjectMocks
    private MoodRecordController moodRecordController;

    private MoodRecord moodRecord;
    private Long userId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        moodRecord = new MoodRecord();
        moodRecord.setMood("Happy");
        moodRecord.setId(1L);

        userId = 1L;
    }

    @Test
    public void testGetMoodRecords() {
        when(moodRecordService.getMoodRecordsByUser(userId)).thenReturn(Arrays.asList(moodRecord));

        List<MoodRecord> moodRecords = moodRecordController.getMoodRecords(userId);

        assertNotNull(moodRecords);
        assertEquals(1, moodRecords.size());
        assertEquals("Happy", moodRecords.get(0).getMood());
    }

    @Test
    public void testRecordMood() {
        String newMood = "Excited";
        when(moodRecordService.recordMood(userId, newMood)).thenReturn(moodRecord);

        MoodRecord recordedMood = moodRecordController.recordMood(userId, newMood);

        assertNotNull(recordedMood);
        assertEquals("Happy", recordedMood.getMood());
        assertEquals(userId, recordedMood.getId());
    }

}
