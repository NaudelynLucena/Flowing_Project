package dev.naulu.flowing.controller;

import dev.naulu.flowing.model.MoodRecord;
import dev.naulu.flowing.service.MoodRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood")
public class MoodRecordController {

    private final MoodRecordService service;

    public MoodRecordController(MoodRecordService service) {
        this.service = service;
    }

    // Obtener todos los registros de estado de ánimo de un usuario
    @GetMapping("/user/{userId}")
    public List<MoodRecord> getMoodRecords(@PathVariable Long userId) {
        return service.getMoodRecordsByUser(userId);
    }

    // Registrar un nuevo estado de ánimo
    @PostMapping("/user/{userId}")
    public MoodRecord recordMood(@PathVariable Long userId, @RequestBody String mood) {
        return service.recordMood(userId, mood);
    }
}
