package org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha;
    private final int coluna;
    private boolean aberto;
    private boolean minado;
    private boolean marcado;

    private List<Campo> vizinhos = new ArrayList<Campo>();

    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    private boolean addVizinhos(Campo vizinho){
        boolean retornoFuncao  = false;
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int somaLinhaColuna = deltaLinha + deltaColuna;

        if (somaLinhaColuna == 1 && !diagonal){
            vizinhos.add(vizinho);
            retornoFuncao = true;
        } else if (somaLinhaColuna ==  2  && diagonal) {
            vizinhos.add(vizinho);
            retornoFuncao = true;
        }
        return retornoFuncao;
    }
}