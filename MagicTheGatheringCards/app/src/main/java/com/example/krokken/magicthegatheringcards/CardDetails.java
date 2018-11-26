package com.example.krokken.magicthegatheringcards;

import java.util.List;

public class CardDetails {

    private String cardName;
    private List<String> language;
    private String cardCMC;

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public List<String> getLanguage() {
        return this.language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public String getCardCMC() {
        return this.cardCMC;
    }

    public void setCardCMC(String cmc) {
        this.cardCMC = cmc;
    }
}
