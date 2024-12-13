package dev.naulu.flowing.service;

import dev.naulu.flowing.model.MoodRecord;
import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.MoodRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoodRecordServiceTest {

    @Mock
    private MoodRecordRepository repository;

    @InjectMocks
    private MoodRecordService moodRecordService;

    private MoodRecord moodRecord;

    @BeforeEach
    void setUp() {
        moodRecord = new MoodRecord();
        moodRecord.setMood("Happy");
        moodRecord.setUser(new User());
    }

    @Test
    void testGetMoodRecordsByUser() {
        when(repository.findByUserId(1L)).thenReturn(Arrays.asList(moodRecord));

        List<MoodRecord> moodRecords = moodRecordService.getMoodRecordsByUser(1L);

        assertNotNull(moodRecords);
        assertEquals(1, moodRecords.size());
        verify(repository).findByUserId(1L);
    }

    @Test
    void testRecordMood() {
        when(repository.save(any(MoodRecord.class))).thenReturn(moodRecord);

        MoodRecord recordedMood = moodRecordService.recordMood(1L, "Happy");

        assertNotNull(recordedMood);
        assertEquals("Happy", recordedMood.getMood());
        verify(repository).save(any(MoodRecord.class));
    }
}
