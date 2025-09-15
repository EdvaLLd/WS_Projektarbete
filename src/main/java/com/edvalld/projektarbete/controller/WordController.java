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

    //lägger till ord till en lista
    @PostMapping("/{wordListId}")
    public ResponseEntity<Word> addWordToList(@PathVariable Long wordListId, @RequestBody Word word) {
        WordList list = wordListRepository.findById(wordListId)
                .orElseThrow(() -> new RuntimeException("WordList not found"));

        word.createWordListConnection(list);
        return ResponseEntity.ok(wordRepository.save(word));
    }

    //redigerar ett ord i en lista
    @PostMapping("/edit/{wordId}")
    public ResponseEntity<Word> editWordInList(@PathVariable Long wordId, @RequestBody Word newWord){

        //hitta ordet
        Word word = wordRepository.getWordById(wordId);
        WordList wordList = word.getWordList();

        //byta ordet
        word.setWord(newWord.getWord());
        word.setAnswer(newWord.getAnswer());

        return ResponseEntity.ok(wordRepository.save(word));
    }

    @DeleteMapping("/{wordId}")
    public ResponseEntity<String> removeWord(@PathVariable Long wordId){

        //hitta ordet
        Word word = wordRepository.getWordById(wordId);

        word.removeWordListConnection();
        wordRepository.delete(word);
        return ResponseEntity.ok("Word removed");
    }

}