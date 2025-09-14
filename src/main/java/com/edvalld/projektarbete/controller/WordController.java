package com.edvalld.projektarbete.controller;

import com.edvalld.projektarbete.model.Word;
import com.edvalld.projektarbete.model.WordList;
import com.edvalld.projektarbete.repository.WordListRepository;
import com.edvalld.projektarbete.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private WordListRepository wordListRepository;

    @PostMapping("/{wordListId}")
    public ResponseEntity<Word> addWordToList(@PathVariable Long wordListId, @RequestBody Word word) {
        WordList list = wordListRepository.findById(wordListId)
                .orElseThrow(() -> new RuntimeException("WordList not found"));

        word.setWordList(list);          // koppla ordet till listan
        list.getWords().add(word);       // lägg till ordet i listan
        return ResponseEntity.ok(wordRepository.save(word));
    }

}
