package lab9p1_fernandopadilla;

import java.util.*;

public class Ventanilla {

    private ArrayList<Integer> colaPrioridad = new ArrayList<>();
    private ArrayList<Integer> colaRegular = new ArrayList<>();
    private int[] ventanilla;
    private char[] clientesEnVentanilla;

    public Ventanilla() {

    }

    public Ventanilla(int escritorio) {
        this.ventanilla = new int[escritorio];
        this.clientesEnVentanilla = new char[escritorio];
    }

    public ArrayList<Integer> getColaPrioridad() {
        return colaPrioridad;
    }

    public ArrayList<Integer> getColaRegular() {
        return colaRegular;
    }

    public int[] getVentanilla() {
        return ventanilla;
    }

    public char[] getClientesEnVentanilla() {
        return clientesEnVentanilla;
    }

    public void setColaPrioridad(ArrayList<Integer> colaPrioridad) {
        this.colaPrioridad = colaPrioridad;
    }

    public void setColaRegular(ArrayList<Integer> colaRegular) {
        this.colaRegular = colaRegular;
    }

    public void setVentanilla(int[] ventanilla) {
        this.ventanilla = ventanilla;
    }

    public void setClientesEnVentanilla(char[] clientesEnVentanilla) {
        this.clientesEnVentanilla = clientesEnVentanilla;
    }

    public void correrSimulacion(int tiempo, int escritorios) {
        int vent = 0;
        System.out.println(imprimirclp());
        System.out.println(imprimirclr());
        for (int i = 0; i < tiempo; i++) {
            actualizarVentanillas();
            vent = encontrarVentanillaDisponible();
            while (vent != -1 && colaPrioridad.isEmpty() == false) {
                //Mientras haya clientes en la cola preferencial evaluar ventanillas disponibles y pasar los clientes
                ventanilla[encontrarVentanillaDisponible()] = colaPrioridad.get(0);
                colaPrioridad.remove(0);
                clientesEnVentanilla[vent] = 'P';
                vent = encontrarVentanillaDisponible();
            }
            while (vent != -1 && colaPrioridad.isEmpty() == true && colaRegular.isEmpty() == false) {
                ventanilla[encontrarVentanillaDisponible()] = colaRegular.get(0);
                colaRegular.remove(0);
                clientesEnVentanilla[vent] = 'R';
                vent = encontrarVentanillaDisponible();
            }
            System.out.println("Iteracion #" + i);
            for (int j = 0; j < clientesEnVentanilla.length; j++) {
                System.out.print("[" + clientesEnVentanilla[j] + "]" + " ");
            }
            System.out.println();
            for (int j = 0; j < clientesEnVentanilla.length; j++) {
                System.out.print("[" + ventanilla[j] + "]" + " ");
            }
            System.out.println();
            if (colaPrioridad.isEmpty() && colaRegular.isEmpty()) {
                break;
            }
        }
    }

    public void agregarClienteRegular(int cliente) {
        colaRegular.add(cliente);
    }

    public void agregarClientePreferencial(int cliente) {
        colaPrioridad.add(cliente);
    }

    public int encontrarVentanillaDisponible() {
        int ventdisp = 0;
        for (int i = 0; i < clientesEnVentanilla.length; i++) {
            if (ventanilla[i] == 0) {
                ventdisp = i;
                break;
            } else {
                ventdisp = -1;
            }
        }
        return ventdisp;
    }

    public void actualizarVentanillas() {
        for (int i = 0; i < clientesEnVentanilla.length; i++) {
            if (ventanilla[i] > 0) {
                ventanilla[i] -= 1;
            }
        }
    }

    public String imprimirclp() {
        String temp = "[";
        System.out.println("Cola Prioridad");
        for (int i = 0; i < colaPrioridad.size(); i++) {
            temp += colaPrioridad.get(i) + " ";
        }
        temp += "]";
        System.out.println();
        return temp;
    }

    public String imprimirclr() {
        String temp = "[";
        System.out.println("Cola Regular");
        for (int i = 0; i < colaRegular.size(); i++) {
            temp += colaRegular.get(i) + " ";
        }
        temp += "]";
        return temp;
    }
}
