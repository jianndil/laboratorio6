package TICTACTOE;

public class Juego {
    private Tablero tablero;
    private String turno;
    private String ganador;
    private boolean empate;
    public Juego() {
        tablero = new Tablero();
        turno = "X"; 
        ganador = null;
        empate = false;
    }
    public void cambiarTurno() {
        if (turno.equals("X")) {
            turno = "O";
        } else {
            turno = "X";
        }
    }
    public String getTurno() {
        return turno;
    }
    public boolean chequearGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero.getCasilla(i, 0).getEstado().equals(tablero.getCasilla(i, 1).getEstado()) && 
                tablero.getCasilla(i, 1).getEstado().equals(tablero.getCasilla(i, 2).getEstado()) && 
                !tablero.getCasilla(i, 0).getEstado().equals("")) {
                ganador = tablero.getCasilla(i, 0).getEstado();
                return true;
            }
            if (tablero.getCasilla(0, i).getEstado().equals(tablero.getCasilla(1, i).getEstado()) && 
                tablero.getCasilla(1, i).getEstado().equals(tablero.getCasilla(2, i).getEstado()) && 
                !tablero.getCasilla(0, i).getEstado().equals("")) {
                ganador = tablero.getCasilla(0, i).getEstado();
                return true;
            }
        }
        if (tablero.getCasilla(0, 0).getEstado().equals(tablero.getCasilla(1, 1).getEstado()) && 
            tablero.getCasilla(1, 1).getEstado().equals(tablero.getCasilla(2, 2).getEstado()) && 
            !tablero.getCasilla(0, 0).getEstado().equals("")) {
            ganador = tablero.getCasilla(0, 0).getEstado();
            return true;
        }
        if (tablero.getCasilla(0, 2).getEstado().equals(tablero.getCasilla(1, 1).getEstado()) && 
            tablero.getCasilla(1, 1).getEstado().equals(tablero.getCasilla(2, 0).getEstado()) && 
            !tablero.getCasilla(0, 2).getEstado().equals("")) {
            ganador = tablero.getCasilla(0, 2).getEstado();
            return true;
        }
        boolean lleno = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero.getCasilla(i, j).getEstado().equals("")) {
                    lleno = false;
                    break;
                }
            }
        }
        if (lleno) {
            empate = true;
            return true;
        }

        return false;
    }
    public String getGanador() {
        return ganador;
    }

    public boolean isEmpate() {
        return empate;
    }
    public Tablero getTablero() {
        return tablero;
    }
}
