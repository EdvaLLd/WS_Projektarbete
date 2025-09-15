package com.edvalld.projektarbete.repository;

import com.edvalld.projektarbete.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {

    public Word getWordById(Long id);
}
