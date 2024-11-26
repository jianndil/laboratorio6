package TICTACTOE;

public class Casilla {
    private int x, y;
    private String estado; // "X", "O" o ""
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.estado = "";
    }
    public String getEstado() {
        return estado;
    }
    public void colocarFicha(String tipo) {
        if (estado.equals("")) {
            estado = tipo;
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String toString() {
        return "Casilla[" + x + "," + y + "] Estado: " + estado;
    }
}
