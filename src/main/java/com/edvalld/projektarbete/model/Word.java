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


    //de här används för kopplingen mellan word och wordlist.
    //@JsonBackReference gör så att det inte blir en oändlighets-loop i returvärdet där ordet
    //returnerar listan och listan returnerar ordet.
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

    public void createWordListConnection (WordList wordList){
        this.wordList = wordList;
        wordList.getWords().add(this);
    }

    public void removeWordListConnection (){
        wordList.getWords().remove(this);
        this.wordList = null;
    }
}
