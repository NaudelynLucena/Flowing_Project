package dev.naulu.flowing.service;

import dev.naulu.flowing.model.DailyMessage;
import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.DailyMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class MessageService {
    private final List<String> messages = List.of(
        "Cree en ti mismo y en todo lo que eres.",
        "No tengas miedo de ser diferente.",
        "Eres único, como todos los demás.",
        "Tu viaje es tu destino.",
        "Cada día es un nuevo comienzo.",
        "Eres más fuerte de lo que crees.",
        "Céntrate en el progreso, no en la perfección.",
        "Pequeños pasos conducen a grandes cambios.",
        "La única persona a la que estás destinado a superar es a ti mismo.",
        "No te preocupes por lo que no puedas cambiar, solo por lo que puedas mejorar.",
        "No te preocupes por el fracaso. Preocúpate por las oportunidades que pierdes al no intentarlo.",
        "Una actitud positiva puede cambiar todo.",
        "Los obstáculos son esas cosas espantosas que ves cuando apartas la vista de tu objetivo.",
        "Lo que te distingue no es lo que logras, sino los obstáculos que superas para lograrlo."
    );

    private final DailyMessageRepository dailyMessageRepository;

    public MessageService(DailyMessageRepository dailyMessageRepository) {
        this.dailyMessageRepository = dailyMessageRepository;
    }

    public String getRandomMessage() {
        Random random = new Random();
        return messages.get(random.nextInt(messages.size()));
    }

    public String getDailyMessage(User user) {
        LocalDate today = LocalDate.now();
        return dailyMessageRepository.findByUserIdAndDate(user.getId(), today)
                .map(DailyMessage::getMessage)
                .orElseGet(() -> generateAndSaveDailyMessage(user));
    }

    private String generateAndSaveDailyMessage(User user) {
        String message = getRandomMessage();
        DailyMessage dailyMessage = new DailyMessage(user, message, LocalDate.now());
        dailyMessageRepository.save(dailyMessage);
        return message;
    }
}