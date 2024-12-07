package dev.naulu.flowing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.naulu.flowing.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
