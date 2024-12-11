package dev.naulu.flowing.repository;

import dev.naulu.flowing.model.DailyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyMessageRepository extends JpaRepository<DailyMessage, Long> {
    Optional<DailyMessage> findByUserIdAndDate(Long userId, LocalDate date);
}
