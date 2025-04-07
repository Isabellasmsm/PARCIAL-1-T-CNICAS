import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carta {

    private int indice;

    // Metodo constructor
    public Carta(Random r) {
        // Generar un numero al azar entre 1 y 52
        indice = r.nextInt(52) + 1;
    }

    public void mostrar(JPanel pnl, int x, int y) {
        String nombreArchivo = "/imagenes/CARTA" + indice + ".jpg";
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(nombreArchivo));

        JLabel lblCarta = new JLabel();
        lblCarta.setIcon(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());

        lblCarta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String simbolo = getSimboloPinta(getPinta());
                String colorSimbolo = getColorSimbolo(getPinta());

                // Mensaje con HTML para cambiar el color del símbolo
                String mensaje = "<html><body style='font-family: Arial; font-size: 10px;'>" +
                                 "<b>" + getNombre() + "</b> de " +
                                 "<b>" + getPinta() + " <span style='color:" + colorSimbolo + "; font-size:20px;'>" + simbolo + "</span></b>" +
                                 "</body></html>";

                JOptionPane.showMessageDialog(null, mensaje);
            }
        });

        pnl.add(lblCarta);
    }

    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre() {
        int residuo = indice % 13;
        if (residuo == 0) {
            residuo = 13;
        }
        return NombreCarta.values()[residuo - 1];
    }

    public static String getSimboloPinta(Pinta pinta) {
        switch (pinta) {
            case TREBOL:
                return "♣";
            case PICA:
                return "♠";
            case CORAZON:
                return "♥";
            case DIAMANTE:
                return "♦";
            default:
                return "";
        }
    }

    public static String getColorSimbolo(Pinta pinta) {
        switch (pinta) {
            case TREBOL:
                return "green";  // Verde para Trébol
            case PICA:
                return "black";  // Negro para Pica
            case CORAZON:
                return "red";    // Rojo para Corazón
            case DIAMANTE:
                return "blue";   // Azul para Diamante
            default:
                return "black";
        }
    }
    
}