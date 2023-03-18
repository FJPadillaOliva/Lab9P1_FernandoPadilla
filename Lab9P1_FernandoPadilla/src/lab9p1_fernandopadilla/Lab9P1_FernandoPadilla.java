package lab9p1_fernandopadilla;
import java.util.*;
public class Lab9P1_FernandoPadilla {

    static Scanner read = new Scanner(System.in);
    static Random random = new Random();
    static Ventanilla vent = new Ventanilla();
    static ArrayList<Integer> colaPrioridad = new ArrayList<>();
    static ArrayList<Integer> colaRegular = new ArrayList<>();
    
    public static void main(String[] args) {
        int op = 0;
        do{
            System.out.println("1.Pase a la ventanilla: ");
            System.out.println("2.Salida");
            op = read.nextInt();
            switch (op){
                case 1:
                    System.out.println("Ingrese el numero de clientes: ");
                    int clientes = read.nextInt();
                    System.out.println("Ingrese el numero de escritorios disponibles: ");
                    int escritorios = read.nextInt();
                    int n1 = 0;
                    int nclientep = 0;
                    int nclienter = 0;
                    int tiempo = 0;
                    vent = new Ventanilla(escritorios);
                    for (int i = 0; i < clientes; i++) {
                        n1 = random.nextInt(2);
                        if (n1 == 0){
                            nclientep = 1+random.nextInt(5);
                            tiempo += nclientep;
                            vent.agregarClientePreferencial(nclientep);
                        }else if (n1 == 1){
                            nclienter = 1+random.nextInt(5);
                            tiempo += nclienter;
                            vent.agregarClienteRegular(nclienter);
                        }
                    }
                    vent.correrSimulacion(tiempo,escritorios);
                    break;
                    
                case 2:
                    op = 2;
                    break;
                    
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while (op!= 2);
        
    }
    
}
    
