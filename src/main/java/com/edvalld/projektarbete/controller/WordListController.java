package com.edvalld.projektarbete.controller;

import com.edvalld.projektarbete.model.Word;
import com.edvalld.projektarbete.model.WordList;
import com.edvalld.projektarbete.repository.WordListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/wordList")
public class WordListController {
    @Autowired
    private WordListRepository wordListRepository;

    //skapar ny tom lista
    @PostMapping
    public ResponseEntity<WordList> createNewWordList(@RequestBody WordList wordList) {
        return ResponseEntity.status(201).body(wordListRepository.save(wordList));
    }

    //hämtar en ordlista
    @GetMapping("/{wordListId}")
    public ResponseEntity<WordList> getWordList(@PathVariable Long wordListId) {
        Optional<WordList> list = wordListRepository.findById(wordListId);
        if(list.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(list.get());
    }

    //hämtar ett slumpat ord från ordlistan
    @GetMapping("/random/{wordListId}")
    public ResponseEntity<Word> getRandomWord(@PathVariable Long wordListId){
        Optional<WordList> list = wordListRepository.findById(wordListId);
        if(list.isEmpty()) return ResponseEntity.notFound().build();

        Random random = new Random();
        int setCount = (int)list.get().getWords().size();

        return ResponseEntity.ok(list.get().getWords().stream().toList()
                .get(random.nextInt(setCount)));
    }
}
