package com.koniukhov.tictactoe.util;

public class Util {
    public static String getIndexesBySymbol(char symbol, String boardState){
        StringBuilder indexes = new StringBuilder();
        for (int index = boardState.indexOf(symbol);
             index >= 0;
             index = boardState.indexOf(symbol, index + 1))
        {
            indexes.append(index);
        }
        return indexes.toString();
    }
}
