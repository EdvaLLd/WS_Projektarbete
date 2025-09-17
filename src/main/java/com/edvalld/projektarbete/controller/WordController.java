package com.edvalld.projektarbete.controller;

import com.edvalld.projektarbete.model.Word;
import com.edvalld.projektarbete.model.WordList;
import com.edvalld.projektarbete.repository.WordListRepository;
import com.edvalld.projektarbete.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private WordListRepository wordListRepository;

    //gör ett ord och lägger till det i en lista
    @PostMapping("/{wordListId}")
    public ResponseEntity<Word> addWordToList(@PathVariable Long wordListId, @RequestBody Word word) {
        Optional<WordList> list = wordListRepository.findById(wordListId);
        if(list.isEmpty()) return ResponseEntity.notFound().build();

        word.createWordListConnection(list.get());

        return ResponseEntity.status(201).body(wordRepository.save(word));
    }

    //redigerar ett ord i en lista
    @PostMapping("/edit/{wordId}")
    public ResponseEntity<Word> editWordInList(@PathVariable Long wordId, @RequestBody Word newWord){

        //hitta ordet
        Optional<Word> word = wordRepository.getWordById(wordId);
        if(word.isEmpty()) return ResponseEntity.notFound().build();
        WordList wordList = word.get().getWordList();

        //byta ordet
        word.get().setWord(newWord.getWord());
        word.get().setAnswer(newWord.getAnswer());

        return ResponseEntity.ok(wordRepository.save(word.get()));
    }

    @DeleteMapping("/{wordId}")
    public ResponseEntity<String> removeWord(@PathVariable Long wordId){

        //hitta ordet
        Optional<Word> word = wordRepository.getWordById(wordId);
        if(word.isEmpty()) return ResponseEntity.notFound().build();

        word.get().removeWordListConnection();
        wordRepository.delete(word.get());

        return ResponseEntity.ok("Word removed");
    }

}