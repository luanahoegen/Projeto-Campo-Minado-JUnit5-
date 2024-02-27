package org.example.visao;

import org.example.excecao.ExplosaoException;
import org.example.excecao.SairException;
import org.example.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleroConsole {
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar){
                loopJogo();
                System.out.println("Outra partida? (S/n)");
                String resposta = entrada.nextLine();
                if ("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (SairException e){
            System.out.println("TCHAUUU!!");
        } finally {
            entrada.close();
        }
    }

    private void loopJogo() {
        try {
            while (tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro.toString());

                String digitado = capturarValorDigitado("Digite (x,y)");
                Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e)).iterator();
                digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)marcar");
                if ("1".equals(digitado)){
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(digitado)) {
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Você ganhou :)");
        } catch (ExplosaoException e){
            System.out.println(tabuleiro);
            System.out.println("Você perdeu :(");
        }
    }

    private String capturarValorDigitado(String texto){
        System.out.println(texto);
        String digitado = entrada.nextLine();
        if ("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
