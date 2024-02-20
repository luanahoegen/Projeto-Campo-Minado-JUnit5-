package modelo;

import org.example.excecao.ExplosaoException;
import org.example.modelo.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTest {

    private Campo campo;

    @BeforeEach
    void iniciarCampo(){
        campo = new Campo(3, 3);
    }

    @Test
    void testeDistaciaVizinhosEsquerda(){
        Campo vizinho = new Campo(3,2);
        boolean resultado = campo.addVizinhos(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeDistaciaVizinhosDireita(){
        Campo vizinho = new Campo(3,4);
        boolean resultado = campo.addVizinhos(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeDistaciaDiagonal(){
        Campo vizinho = new Campo(2,2);
        boolean resultado = campo.addVizinhos(vizinho);
        assertTrue(resultado);
    }

    @Test
    void alternaMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void valorMarcacao(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirMinadoNaoMarcado(){
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos(){
        Campo vizinho1 = new Campo(1,1);
        Campo vizinho2 = new Campo(2,2);

        vizinho1.addVizinhos(vizinho2);
        campo.addVizinhos(vizinho1);
        campo.abrir();
    }
}