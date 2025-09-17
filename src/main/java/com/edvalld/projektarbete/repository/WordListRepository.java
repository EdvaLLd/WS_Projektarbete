package com.edvalld.projektarbete.repository;

import com.edvalld.projektarbete.model.WordList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordListRepository extends JpaRepository<WordList, Long> {

    Optional<WordList> findByName(String name);

}
