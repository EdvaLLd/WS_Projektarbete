package com.edvalld.projektarbete.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;
    private String answer;


    @ManyToOne
    @JoinColumn(name = "word_list_id")  // foreign key i Post-tabellen
    @JsonBackReference
    private WordList wordList;

    public Word() {}
    public Word(String word, String answer) {
        this.word = word;
        this.answer = answer;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public WordList getWordList() {
        return wordList;
    }

    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }
}
