package dev.naulu.flowing.repository;

import dev.naulu.flowing.model.MoodRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodRecordRepository extends JpaRepository<MoodRecord, Long> {
    List<MoodRecord> findByUserId(Long userId);
}
