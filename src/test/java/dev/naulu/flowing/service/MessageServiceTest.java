package dev.naulu.flowing.service;

import dev.naulu.flowing.model.DailyMessage;
import dev.naulu.flowing.model.User;
import dev.naulu.flowing.repository.DailyMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    private DailyMessageRepository dailyMessageRepository;
    private MessageService messageService;
    private User user;

    @BeforeEach
    void setUp() {
        dailyMessageRepository = mock(DailyMessageRepository.class);
        messageService = new MessageService(dailyMessageRepository);
        user = new User();
        user.setId(1L);
    }

    @Test
    void testGetDailyMessageWhenMessageExists() {
        String existingMessage = "Cree en ti mismo y en todo lo que eres.";
        DailyMessage dailyMessage = new DailyMessage(user, existingMessage, LocalDate.now());
        when(dailyMessageRepository.findByUserIdAndDate(user.getId(), LocalDate.now()))
                .thenReturn(Optional.of(dailyMessage));

        String message = messageService.getDailyMessage(user);

        assertEquals(existingMessage, message);
        verify(dailyMessageRepository, never()).save(any(DailyMessage.class));
    }

    @Test
    void testGetDailyMessageWhenMessageDoesNotExist() {
        when(dailyMessageRepository.findByUserIdAndDate(user.getId(), LocalDate.now()))
                .thenReturn(Optional.empty());

        String message = messageService.getDailyMessage(user);

        assertNotNull(message);
        verify(dailyMessageRepository).save(any(DailyMessage.class));
    }

    @Test
    void testGetRandomMessage() {
        String randomMessage = messageService.getRandomMessage();

        assertNotNull(randomMessage);
        assertTrue(randomMessage.length() > 0);
    }
}