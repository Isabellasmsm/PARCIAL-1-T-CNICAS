import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

    private JButton btnRepartir;
    private JButton btnVerificar;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JPanel pnlPuntaje;
    private JTabbedPane tpJugadores;
    private JLabel lblNombreJugador1;
    private JLabel lblNombreJugador2;
    private JLabel lblPuntaje1;
    private JLabel lblPuntaje2;

    public FrmJuego() {
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();
        pnlPuntaje = new JPanel();

        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlJugador1.setBackground(new Color(153, 255, 51));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);
        pnlPuntaje.setBackground(new Color(255, 182, 193));
        pnlPuntaje.setLayout(null);

        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raul Vidal", pnlJugador2);
        tpJugadores.addTab("Puntaje de cada jugador", pnlPuntaje);

        lblNombreJugador1 = new JLabel();
        lblNombreJugador1.setBounds(50, 110, 200, 25);
        lblNombreJugador1.setHorizontalAlignment(SwingConstants.CENTER);
        pnlPuntaje.add(lblNombreJugador1);

        lblNombreJugador2 = new JLabel();
        lblNombreJugador2.setBounds(220, 110, 200, 25);
        lblNombreJugador2.setHorizontalAlignment(SwingConstants.CENTER);
        pnlPuntaje.add(lblNombreJugador2);

        lblPuntaje1 = new JLabel("0");
        lblPuntaje1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPuntaje1.setBackground(new Color(255, 255, 255)); // color de fondo
        lblPuntaje1.setOpaque(true); // hacer opaco para ver el color de fondo
        // lblPuntaje1.setForeground(new Color(51, 255, 0));
        lblPuntaje1.setForeground(Color.decode("#800080")); // color de la letra
        lblPuntaje1.setFont(new Font("Tahoma", 1, 72)); // cambiar el tipo de letra
        lblPuntaje1.setBounds(98, 10, 100, 100);
        pnlPuntaje.add(lblPuntaje1);

        lblPuntaje2 = new JLabel("0");
        lblPuntaje2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPuntaje2.setBackground(new Color(255, 255, 255)); // color de fondo
        lblPuntaje2.setOpaque(true); // hacer opaco para ver el color de fondo
        // lblPuntaje2.setForeground(new Color(51, 255, 0));
        lblPuntaje2.setForeground(Color.decode("#800080")); // color de la letra
        lblPuntaje2.setFont(new Font("Tahoma", 1, 72)); // cambiar el tipo de letra
        lblPuntaje2.setBounds(270, 10, 100, 100);
        pnlPuntaje.add(lblPuntaje2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);
    }

    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();

    private void btnRepartirClick(ActionEvent evt) {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);

        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
        lblNombreJugador1.setText("Martín Estrada Contreras");
        lblNombreJugador2.setText("Raul Vidal");
        lblPuntaje1.setText(String.valueOf(jugador1.calcularPuntaje()));
        lblPuntaje2.setText(String.valueOf(jugador2.calcularPuntaje()));
    }

    private void btnVerificarClick(ActionEvent evt) {
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                JOptionPane.showMessageDialog(null, jugador1.getEscaleras());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                JOptionPane.showMessageDialog(null, jugador2.getEscaleras());
                break;
        }
    }

}