package org.example;

import org.example.modelo.Tabuleiro;
import org.example.visao.TabuleroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(5,5,2);
        new TabuleroConsole(tabuleiro);
    }
}
