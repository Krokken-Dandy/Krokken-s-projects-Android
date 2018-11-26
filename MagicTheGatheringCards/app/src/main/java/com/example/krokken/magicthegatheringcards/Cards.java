package com.example.krokken.magicthegatheringcards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class Cards {

    private String mCardLayout;

    private String mCardManaCost;

    private String mCardName;

    // Card's Converted Mana Cost
    private String mCardCMC;

    private String mCardType;

    private String[] mCardTypes;

    private String[] mCardSubtypes;

    private String mCardText;

    private String mCardPower;

    private String mCardToughness;

    Cards(String cardLayout, String cardName, String cardManaCost,
          String cardCMC, String cardType, String[] cardTypes,
          String[] cardSubtypes, String cardText, String cardPower,
          String cardToughness) {
        mCardLayout = cardLayout;
        mCardName = cardName;
        mCardManaCost = cardManaCost;
        mCardCMC = cardCMC;
        mCardType = cardType;
        mCardTypes = cardTypes;
        mCardSubtypes = cardSubtypes;
        mCardText = cardText;
        mCardPower = cardPower;
        mCardToughness = cardToughness;
    }

    public String getCardLayout() {
        return mCardLayout;
    }

    String getCardName() {
        return mCardName;
    }

    String[] getCardManaCost() {
        return getConvertManaCostSymbols(mCardManaCost);
    }

    // Card's Converted Mana Cost
    String getCardCMC() {
        return mCardCMC;
    }

    String getCardType() {
        return mCardType;
    }

    String[] getCardTypes(){
        return mCardTypes;
    }

    String[] getCardSubtypes(){
        return mCardSubtypes;
    }

    String getCardText() {
        return mCardText;
    }

    String getCardPower() {
        return mCardPower;
    }

    String getCardToughness() {
        return mCardToughness;
    }

    String getCardPNT() {
        return mCardPower + "/" + mCardToughness;
    }

    private String[] getConvertManaCostSymbols(String cardManaCost) {
        String cardManaCostReplaced = cardManaCost.replaceAll("[{]", "");
        String[] cost = cardManaCostReplaced.split("[}]");
        return cost;
    }

    public Drawable getManaCostSymbol(Context context,  String manaSymbol) {
        Drawable manaCostSymbol;
            switch (manaSymbol) {
                case "U":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.blue);
                    break;
                case "B":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.black);
                    break;
                case "G":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.green);
                    break;
                case "W":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.white);
                    break;
                case "R":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.red);
                    break;
                case "U/B":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.blue_black);
                    break;
                case "U/R":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.blue_red);
                    break;
                case "2/U":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c2_blue);
                    break;
                case "U/P":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.blue_life);
                    break;
                case "B/G":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.black_green);
                    break;
                case "B/R":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.black_red);
                    break;
                case "2/B":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c2_black);
                    break;
                case "B/P":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.black_life);
                    break;
                case "G/U":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.green_blue);
                    break;
                case "G/W":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.green_white);
                    break;
                case "2/G":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c2_green);
                    break;
                case "G/P":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.green_life);
                    break;
                case "W/B":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.white_black);
                    break;
                case "W/U":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.white_blue);
                    break;
                case "2/W":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c2_white);
                    break;
                case "W/P":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.white_life);
                    break;
                case "R/G":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.red_green);
                    break;
                case "R/W":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.red_white);
                    break;
                case "2/R":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c2_red);
                    break;
                case "R/P":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.red_life);
                    break;
                case "0":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c0);
                    break;
                case "1":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c1);
                    break;
                case "2":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c2);
                    break;
                case "3":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c3);
                    break;
                case "4":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c4);
                    break;
                case "5":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c5);
                    break;
                case "6":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c6);
                    break;
                case "7":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c7);
                    break;
                case "8":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c8);
                    break;
                case "9":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c9);
                    break;
                case "10":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c10);
                    break;
                case "11":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c11);
                    break;
                case "12":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c12);
                    break;
                case "13":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c13);
                    break;
                case "14":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c14);
                    break;
                case "15":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c15);
                    break;
                case "16":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c16);
                    break;
                case "17":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c17);
                    break;
                case "18":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c18);
                    break;
                case "19":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c19);
                    break;
                case "20":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.c20);
                    break;
                case "X":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.cx);
                    break;
                case "S":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.snow);
                    break;
                case "C":
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.colorless);
                    break;
                default:
                    manaCostSymbol = ContextCompat.getDrawable(context, R.drawable.colorless);
            }
        return manaCostSymbol;
    }
}
