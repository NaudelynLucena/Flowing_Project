package dev.naulu.flowing.repository;

import dev.naulu.flowing.model.JournalEntry;
import dev.naulu.flowing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findByUser(User user);
}
