import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private int TOTAL_CARTAS = 10;
    private int MARGEN = 10;
    private int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];




    private Random r = new Random(); // la suerte del jugador

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN + (TOTAL_CARTAS - 1) * DISTANCIA;
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN);
            posicion -= DISTANCIA;
        }
        pnl.repaint();
    }



    public String getGrupos() {
        String mensaje = "No se encontraron figuras";
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador > 1) {
                hayGrupos = true;
                break;
            }
        }

        if (hayGrupos) {
            mensaje = "Se encontraron los siguientes grupos:\n";
            int fila = 0;
            for (int contador : contadores) {
                if (contador > 1) {
                    mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[fila] + "\n";
                


                }
                fila++;
            }
        }
           
        return mensaje;
    }
    public String getEscaleras() {
    StringBuilder mensaje = new StringBuilder();
    // Listas separadas por pinta
    List<NombreCarta> treboles = new ArrayList<>();
    List<NombreCarta> picas = new ArrayList<>();
    List<NombreCarta> corazones = new ArrayList<>();
    List<NombreCarta> diamantes = new ArrayList<>();

    // Clasificar cartas por su pinta
    for (Carta c : cartas) {
        switch (c.getPinta()) {
            case TREBOL -> treboles.add(c.getNombre());
            case PICA -> picas.add(c.getNombre());
            case CORAZON -> corazones.add(c.getNombre());
            case DIAMANTE -> diamantes.add(c.getNombre());
        }
    }
    
    // Ordenar las cartas dentro de cada pinta
    Collections.sort(treboles);
    Collections.sort(picas);
    Collections.sort(corazones);
    Collections.sort(diamantes);

    // Buscar escaleras en cada lista
    String resultado = buscarEscaleras(treboles, Pinta.TREBOL) +
                       buscarEscaleras(picas, Pinta.PICA) +
                       buscarEscaleras(corazones, Pinta.CORAZON) +
                       buscarEscaleras(diamantes, Pinta.DIAMANTE);

    if (!resultado.isBlank()) {
    mensaje.append("<html><b>Se encontraron las siguientes escaleras:</b><br><br></html>");
    mensaje.append(resultado);
    } else {
        mensaje.append("No se encontraron escaleras.");
    }
  
    return mensaje.toString();
}
public String buscarEscaleras(List<NombreCarta> cartas, Pinta pinta) {

    StringBuilder resultado = new StringBuilder();
    List<NombreCarta> secuencia = new ArrayList<>();
    
    for (int i = 0; i < cartas.size(); i++) {
        if (i == 0 || cartas.get(i).ordinal() == cartas.get(i - 1).ordinal() + 1) {
            secuencia.add(cartas.get(i));
        } else {
            if (secuencia.size() >= 3) {
                resultado.append(mostrarEscalera(secuencia, pinta));
            }
            secuencia.clear();
            secuencia.add(cartas.get(i));
        }
    }
    
    if (secuencia.size() >= 3) {
        resultado.append(mostrarEscalera(secuencia, pinta));
    }
    
    return resultado.toString();
}
private String mostrarEscalera(List<NombreCarta> secuencia, Pinta pinta) {
    String grupo = switch (secuencia.size()) {
        case 3 -> "TERNA";
        case 4 -> "CUARTA";
        case 5 -> "QUINTA";
        case 6 -> "SEXTA";
        case 7 -> "SÉPTIMA";
        case 8 -> "OCTAVA";
        case 9 -> "NOVENA";
        case 10 -> "DÉCIMA";
        default -> throw new IllegalStateException("Tamaño inesperado de escalera: " + secuencia.size());
    };
    String simbolo = Carta.getSimboloPinta(pinta);
    String colorSimbolo = Carta.getColorSimbolo(pinta);
    return "<html><b>" + grupo + " de " + pinta +
           " <span style='color:" + colorSimbolo + "; font-size:18px;'>" + simbolo + "</span></b>: " +
           secuencia + "</html>\n";

}
public List<Carta> getCartasEnGrupos() {
    List<Carta> cartasEnGrupos = new ArrayList<>();
    int[] contadores = new int[NombreCarta.values().length];
    for (Carta c : cartas) {
        contadores[c.getNombre().ordinal()]++;
    }
    boolean hayGrupos = false;
    for (int contador : contadores) {
        if (contador > 1) {
            hayGrupos = true;
            break;
        }
    }
    if (hayGrupos){
        for (Carta c : cartas) {
            if (contadores[c.getNombre().ordinal()] > 1 && !cartasEnGrupos.contains(c)) {
                cartasEnGrupos.add(c);
            }
        }
    }
    return cartasEnGrupos;
}
private List<Carta> getCartasPorPinta(Pinta pinta) {
    List<Carta> cartasPinta = new ArrayList<>();
    for (Carta c : cartas) {
        if (c.getPinta() == pinta) {
            cartasPinta.add(c);
        }
    }
    cartasPinta.sort(Comparator.comparingInt(c -> c.getNombre().ordinal()));
    return cartasPinta;
}
public List<Carta> getCartasEnEscaleras(List<Carta> cartas, Pinta pinta) {
    List<Carta> secuencia = new ArrayList<>();
    List<Carta> resultado = new ArrayList<>();
    
    for (int i = 0; i < cartas.size(); i++) {
        if (i == 0 || cartas.get(i).getNombre().ordinal() == cartas.get(i - 1).getNombre().ordinal() + 1) {
            secuencia.add(cartas.get(i));
        } else {
            if (secuencia.size() >= 3) {
                resultado.addAll(secuencia);
            }
            secuencia.clear();
            secuencia.add(cartas.get(i));
        }
    }

    if (secuencia.size() >= 3) {
        resultado.addAll(secuencia);
    }
    
    return resultado;
}


public List<Carta> getSinGrupos() {
    List<Carta> sinGrupos = new ArrayList<>();
    List<Carta> cartasEnGrupos = getCartasEnGrupos();
    List<Carta> cartasEnEscaleras = new ArrayList<>();
    for (Pinta pinta : Pinta.values()) {
        cartasEnEscaleras.addAll(getCartasEnEscaleras(getCartasPorPinta(pinta), pinta));
    }
    for (Carta c : cartas) {
        if (!cartasEnEscaleras.contains(c) && !cartasEnGrupos.contains(c)) {
            sinGrupos.add(c);
        }
    }

    return sinGrupos;
}




public int calcularPuntaje() {
    List<Carta> cartassingrupos = getSinGrupos();
    int puntaje = 0;

    for (Carta carta : cartassingrupos) {
        switch (carta.getNombre()) {
            case AS, JACK, QUEEN, KING -> puntaje += 10;  // As y figuras valen 10
            default -> puntaje += carta.getNombre().ordinal() + 1;  // El resto vale su número (ordinal + 1)
        }
    }

    return puntaje;
}






}
