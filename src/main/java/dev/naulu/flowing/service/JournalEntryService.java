package dev.naulu.flowing.service;

import dev.naulu.flowing.model.JournalEntry;
import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> getAllEntriesByUser(User user) {
        return journalEntryRepository.findByUser(user);
    }

    public Optional<JournalEntry> getEntryById(Long id) {
        return journalEntryRepository.findById(id);
    }

    public JournalEntry createEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    public JournalEntry updateEntry(Long id, JournalEntry updatedEntry) {
        return journalEntryRepository.findById(id)
            .map(existingEntry -> {
                existingEntry.setDate(updatedEntry.getDate());
                existingEntry.setTitle(updatedEntry.getTitle());
                existingEntry.setContent(updatedEntry.getContent());
                return journalEntryRepository.save(existingEntry);
            })
            .orElseThrow(() -> new RuntimeException("Entry not found"));
    }

    public void deleteEntry(Long id) {
        journalEntryRepository.deleteById(id);
    }
}
