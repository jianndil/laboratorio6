package TICTACTOE;

public class Tablero {
    private Casilla[][] casillas;
    public Tablero() {
        casillas = new Casilla[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }
    public Casilla getCasilla(int x, int y) {
        return casillas[x][y];
    }
}
