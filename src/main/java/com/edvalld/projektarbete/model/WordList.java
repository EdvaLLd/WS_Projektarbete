package com.edvalld.projektarbete.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class WordList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String name;


    public WordList(){  }
    public WordList(String name){
        this.name = name;
    }

    @OneToMany(mappedBy = "wordList")
    @JsonManagedReference
    private Set<Word> words= new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }


    //går igenom alla ord i listan och hämtar det som är mest sökt på
    //detta är mer av en front end-funktion för att den ska fungera som tänkt, men
    //nu är back end-funktionaliteten implementerad
    @JsonIgnore
    public Word getMostSelectedWord(){
        if(words.isEmpty())return null;
        Word w = null;

        for (Word word : words) {
            if (w == null || word.getWordSelectedCount() > w.getWordSelectedCount()) {
                w = word;
            }
        }
        return w;
    }
}
