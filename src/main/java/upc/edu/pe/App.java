package upc.edu.pe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import upc.edu.pe.Entidades.Pedido;
import upc.edu.pe.UI.Controller;

/**
 * Hello world!
 *
 */
public class App {
    static String conf = "";
    static String pedido = "";
    static Controller execute;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static void ejecutar(Controller execute) {
        try {
            System.out.println("Ingrese Numero de Pedido: ");
            pedido = reader.readLine();
            execute.buscarPedido(pedido);

            conf = reader.readLine();

            if (conf.equals("S")) {
                System.out.println("Ingrese las cantidades por tallas segun el ratio de tallas seguida por una ',':  ");
                String confCant = reader.readLine();
                // execute.ingresaCantidades( new ArrayList<>(Arrays.asList(80,78,95)));
                try {
                    String[] arr = confCant.split(",");
                    ArrayList<Integer> listCant = new ArrayList<>();
                    for (String ch : arr) {
                        listCant.add(Integer.parseInt(ch));
                    }

                    execute.ingresaCantidades(listCant);

                    System.out.println("Desea generar los pesos por tallas? Presione S/N ");
                    conf = reader.readLine();
                    if (conf.equals("S")) {

                        System.out
                                .println("Ingrese los pesos por tallas segun el ratio de tallas seguida por una ',' ");
                        String confPesos = reader.readLine();

                        arr = confPesos.split(",");
                        ArrayList<Double> listPesos = new ArrayList<>();
                        for (String ch : arr) {
                            listPesos.add(Double.parseDouble(ch));
                        }
                        // execute.ingresaPesos( new ArrayList<>(Arrays.asList(15.0,20.0,10.0)));
                        execute.ingresaPesos(listPesos);

                        System.out.println("Desea generar la explosión de Pedidos? Presione S/N ");
                        conf = reader.readLine();
                        if (conf.equals("S")) {
                            execute.KgsxCompra();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    static void SingIn() {
        System.out.println("::::Bienvenido::::");
        execute = new Controller();
    }

    static void msgSingIn() {
        System.out.println("0 => Registrar un Pedido::::");
        System.out.println("1 => Buscar y trabajar un Pedido::::");
        System.out.println("2 => Mostrar todos los Pedidos::::");
        System.out.println("3 => Salir de la Aplicacion::::");
        System.out.print("Por favor ingrese una opcion para continuar: ");
    }

    static void recursive(boolean opc) {
        if (opc) {
            SingIn();
            msgSingIn();
        } else {
            msgSingIn();
        }
    }

    public static void main(String[] args) throws IOException {
        recursive(true);
        String opc = null;
        try {
            opc = reader.readLine();
            if (opc.equals("0")) {
                System.out.println("Ingrese Numero de Pedido: ");
                pedido = reader.readLine();
                System.out.println("Ingrese Tallas del Pedido: ");
                String tallas = reader.readLine();
                String[] arr = tallas.split(",");

                ArrayList<String> ratioTallas = new ArrayList<>();
                for (String ch : arr) {
                    ratioTallas.add(ch);
                }

                System.out.println("Ingrese Componente del Pedido: ");
                String cmp = reader.readLine();

                execute.AddPedido(new Pedido(pedido, ratioTallas, cmp));
                System.out.println("::::Pedido Guardado::::");
                System.out.println("............................");
                System.out.println("............................");
                //recursive(false);
                main(args);

            } else if (opc.equals("1")) {
                execute.ejecutar();
                System.out.println("::::Cargando Pedidos::::");
                System.out.println("............................");
                System.out.println("............................");
                System.out.println("....Pedidos Cargados......");
                main(args);
                //ejecutar(execute);

            } else if (opc.equals("2")) {
                execute.ejecutar();
                execute.buscarPedidoAll();
                main(args);
            } else if (opc.equals("3")) {
                System.exit(0);
            } else {
                System.out.println(":( Opcion No Implementada");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
