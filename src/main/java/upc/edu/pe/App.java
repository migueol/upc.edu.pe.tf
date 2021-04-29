package upc.edu.pe;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import upc.edu.pe.UI.Main;

/**
 * Hello world!
 *
 */
public class App {
    static String conf = "";
    static String pedido = "";
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static void ejecutar(Main execute) {
        try {
            System.out.println("Ingrese Numero de Pedido: ");
            pedido = reader.readLine();
            execute.buscarPedido(pedido);

            conf = reader.readLine();

            if (conf.equals("S")) {
                System.out.println("Ingrese las cantidades por tallas segun el ratio de tallas seguida por una ',' ");
                String confCant = reader.readLine();
                // execute.ingresaCantidades( new ArrayList<>(Arrays.asList(80,78,95)));
                try {
                    String[] arr = confCant.split(",");
                    ArrayList<Integer> listCant = new ArrayList<>();
                    for (String ch : arr) {
                        listCant.add(Integer.parseInt(ch));
                    }
                    // confCant.chars().forEach(i -> execute.ingresaCantidades(new
                    // ArrayList<>(Arrays.asList(i))));
                    // Stream<String> stringStream = confCant.codePoints() .mapToObj(c ->
                    // String.valueOf((char) c));
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
                    // TODO: handle exception
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        Main execute = new Main();
        System.out.println("::::Bienvenido::::");
        System.out.println("1 => Buscar y trabajar un Pedido::::");
        System.out.println("2 => Mostrar todos los Pedidos::::");
        System.out.println("3 => Salir de la Aplicacion::::");
        System.out.print("Por favor ingrese una opcion para continuar: ");
        String opc = null;
        try {
            opc = reader.readLine();
            if (opc.equals("1")) {
                execute.ejecutar();
                System.out.println("::::Cargando Pedidos::::");
                System.out.println("............................");
                System.out.println("............................");
                System.out.println("....Pedidos Cargados......");
                ejecutar(execute);

            } else if (opc.equals("2")) {
            	execute.ejecutar();
                execute.buscarPedidoAll();
            } else if (opc.equals("3")) {
                System.exit(0);
            } else {
                System.out.println(":( Opcion No Implementada");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.exit(0);

        // Main execute = new Main();
        // execute.ejecutar();
        // System.out.println("Ingrese Numero de Pedido: ");
        // pedido = reader.readLine();
        // execute.buscarPedido("123456");
        // execute.buscarPedidoAll();

        // execute.ingresaPesos( new ArrayList<>(Arrays.asList(15.0,20.0,10.0)));

        // execute.KgsxCompra();
    }
}
