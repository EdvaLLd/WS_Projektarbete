package com.edvalld.projektarbete.repository;

import com.edvalld.projektarbete.controller.WordListController;
import com.edvalld.projektarbete.model.Word;
import com.edvalld.projektarbete.model.WordList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordListRepository extends JpaRepository<WordList, Long> {

    WordList findByName(String name);

}
