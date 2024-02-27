package org.example.modelo;

import org.example.excecao.ExplosaoException;
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

    public boolean addVizinhos(Campo vizinho){
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

    public void alternarMarcacao(){
        if (!aberto){
            marcado = !marcado;
        }
    }

    public boolean abrir(){
        if (!marcado && !aberto){
            aberto = true;

            if (minado){
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar(){
        minado = true;
    }

    public boolean isMarcado(){
        return marcado;
    }

    public boolean isAberto(){
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public boolean isFechado(){
        return !isAberto();
    }

    public boolean isMinado(){
        return minado;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long minasNavizinhanca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        aberto =  false;
        minado =  false;
        marcado = false;
    }

    public String toString(){
        if (marcado){
            return "X";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNavizinhanca() > 0) {
            return Long.toString(minasNavizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }
}