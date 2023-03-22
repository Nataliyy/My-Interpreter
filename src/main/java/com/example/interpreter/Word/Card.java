package com.example.interpreter.Word;

import javax.persistence.*;

@Entity

public class Card {
    public int timesViewed;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String EnWord;
    public String RuWord;
    private boolean fromRtoE = true;

    public Card() {

    }

    public Card(String EnWord, String RuWord, boolean fromRtoE) {
        this.EnWord = EnWord;
        this.RuWord = RuWord;
        this.fromRtoE = fromRtoE;

    }

    // Не пугайтесь. Здесь вместо void стоит Definition,
    // чтобы можно было добавлять значения в одну строчку:
    // (new Definition("яблоко")).addDefinition(new WordDefinition())


    public String getEnWord() {
        return EnWord;
    }

    public String getRuWord() {
        return RuWord;
    }

}

