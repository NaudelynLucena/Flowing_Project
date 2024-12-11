package dev.naulu.flowing.service;

import dev.naulu.flowing.model.MoodRecord;
import dev.naulu.flowing.repository.MoodRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoodRecordService {

    private final MoodRecordRepository repository;

    public MoodRecordService(MoodRecordRepository repository) {
        this.repository = repository;
    }

    public List<MoodRecord> getMoodRecordsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public MoodRecord recordMood(Long userId, String mood) {
        MoodRecord moodRecord = new MoodRecord();
        return repository.save(moodRecord);
    }
}
