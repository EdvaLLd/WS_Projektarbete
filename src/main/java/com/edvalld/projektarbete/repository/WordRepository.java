package com.edvalld.projektarbete.repository;

import com.edvalld.projektarbete.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {

    public Optional<Word> getWordById(Long id);
}
