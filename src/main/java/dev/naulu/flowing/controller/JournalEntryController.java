package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.JournalEntry;
import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.UserRepository;
import dev.naulu.flowing.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<JournalEntry> getUserJournalEntries(@RequestHeader("Authorization") String authorizationHeader) {
        User user = extractUserFromAuth(authorizationHeader);
        return journalEntryService.getAllEntriesByUser(user);
    }

    @PostMapping
    public JournalEntry createJournalEntry(@RequestHeader("Authorization") String authorizationHeader, @RequestBody JournalEntry journalEntry) {
        User user = extractUserFromAuth(authorizationHeader);
        journalEntry.setUser(user);
        return journalEntryService.createEntry(journalEntry);
    }

    @PutMapping("/{id}")
    public JournalEntry updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry updatedEntry) {
        return journalEntryService.updateEntry(id, updatedEntry);
    }

    @DeleteMapping("/{id}")
    public void deleteJournalEntry(@PathVariable Long id) {
        journalEntryService.deleteEntry(id);
    }

    private User extractUserFromAuth(String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic ".length());
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes);
        String email = decodedCredentials.split(":")[0];
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
