package com.edvalld.projektarbete.model;

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
}
