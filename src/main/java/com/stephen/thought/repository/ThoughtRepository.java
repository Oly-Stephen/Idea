package com.stephen.thought.repository;

import com.stephen.thought.models.Thought;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThoughtRepository extends JpaRepository <Thought, Long>{
}
