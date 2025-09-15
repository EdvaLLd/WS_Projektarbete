package com.edvalld.projektarbete.controller;

import com.edvalld.projektarbete.model.WordList;
import com.edvalld.projektarbete.repository.WordListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wordList")
public class WordListController {
    @Autowired
    private WordListRepository wordListRepository;

    //skapar ny tom lista
    @PostMapping
    public ResponseEntity<WordList> createNewWordList(@RequestBody WordList wordList) {
        return ResponseEntity.ok(wordListRepository.save(wordList));
    }

    //hämtar en ordlista
    @GetMapping("/{wordListId}")
    public ResponseEntity<WordList> getWordList(@PathVariable Long wordListId) {
        WordList list = wordListRepository.findById(wordListId)
                .orElseThrow(() -> new RuntimeException("WordList not found"));
        return ResponseEntity.ok(list);
    }
}
