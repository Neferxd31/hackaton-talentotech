package com.whatsboard.repository;

import com.whatsboard.model.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<Conversacion, Long> {
}
