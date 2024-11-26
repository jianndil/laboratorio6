package TICTACTOE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {
    private Juego juego;
    private JButton[][] botones;
    private JLabel labelTurno;
    private Timer timer;
    private int startX, startY;  
    private int targetX, targetY;  
    public InterfazGrafica(Juego juego) {
        this.juego = juego;
        botones = new JButton[3][3];
        labelTurno = new JLabel("Turno de: " + juego.getTurno(), JLabel.CENTER);
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));
        JPanel panelTablero = new JPanel(new GridLayout(3, 3));
        panelTablero.setPreferredSize(new Dimension(300, 300));
        panelTablero.setBackground(new Color(255, 255, 255));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("");
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                botones[i][j].setFocusPainted(false);
                botones[i][j].setPreferredSize(new Dimension(100, 100));
                botones[i][j].setBackground(new Color(204, 204, 255));
                botones[i][j].setBorder(BorderFactory.createLineBorder(new Color(102, 102, 255), 3));
                botones[i][j].setForeground(new Color(0, 0, 0));
                botones[i][j].setOpaque(true);
                int finalI = i;
                int finalJ = j;
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moverFicha(finalI, finalJ); 
                    }
                });
                panelTablero.add(botones[i][j]);
            }
        }
        labelTurno.setFont(new Font("Arial", Font.BOLD, 20));
        labelTurno.setForeground(new Color(102, 102, 255));
        labelTurno.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(labelTurno, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    public void moverFicha(int x, int y) {
        if (juego.getGanador() == null && !juego.isEmpate()) {
            Casilla casilla = juego.getTablero().getCasilla(x, y);
            if (casilla.getEstado().equals("")) { 
                deshabilitarBotones();
                startX = botones[x][y].getX(); 
                startY = botones[x][y].getY();
                targetX = botones[x][y].getX(); 
                targetY = botones[x][y].getY();
                timer = new Timer(20, new ActionListener() {
                    private int steps = 0;
                    private int totalSteps = 20;  
                    public void actionPerformed(ActionEvent e) {
                        if (steps < totalSteps) {

                            double progress = (double) steps / totalSteps;
                            int xPos = (int) (startX + (targetX - startX) * progress);
                            int yPos = (int) (startY + (targetY - startY) * progress);
                            botones[x][y].setLocation(xPos, yPos);
                            if (steps > totalSteps / 2) {
                                botones[x][y].setLocation(xPos, yPos + 10);  // Un pequeño "rebote"
                            }
                            steps++;
                        } else {
                            timer.stop();
                            casilla.colocarFicha(juego.getTurno());
                            botones[x][y].setText(juego.getTurno());
                            botones[x][y].setForeground(juego.getTurno().equals("X") ? new Color(255, 0, 0) : new Color(0, 0, 255)); // Color de texto: Rojo para "X" y Azul para "O"
                            System.out.println("Casilla seleccionada: " + casilla);
                            if (juego.chequearGanador()) {
                                if (juego.getGanador() != null) {
                                    labelTurno.setText("¡" + juego.getGanador() + " ha ganado!");
                                } else {
                                    labelTurno.setText("¡Empate!");
                                }
                            } else {
                                juego.cambiarTurno();
                                labelTurno.setText("Turno de: " + juego.getTurno());
                            }
                            habilitarBotones();
                        }
                    }
                });
                timer.start();
            }
        }
    }

    private void deshabilitarBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }

    private void habilitarBotones() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(true);
            }
        }
    }
    public static void main(String[] args) {
        Juego juego = new Juego();
        new InterfazGrafica(juego);
    }
}

