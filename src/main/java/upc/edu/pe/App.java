package upc.edu.pe;

import java.io.Console;
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

    public static void main(String[] args) {

        Main execute = new Main();
        execute.ejecutar();
        System.out.println("Ingrese Numero de Pedido: ");
        pedido = System.console().readLine();

        execute.buscarPedido(pedido);
        // execute.buscarPedidoAll();

        System.out.println("Desea generar las cantidades por tallas? Presione S/N ");
        conf = System.console().readLine();

        if (conf.equals("S")) {
            String confCant = System.console()
                    .readLine("Ingrese las cantidades por tallas segun el ratio de tallas seguida por una ',' ");
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
                conf = System.console().readLine();
                if (conf.equals("S")) {
                    String confPesos = System.console()
                            .readLine("Ingrese los pesos por tallas segun el ratio de tallas seguida por una ',' ");

                    arr = confPesos.split(",");
                    ArrayList<Double> listPesos = new ArrayList<>();
                    for (String ch : arr) {
                        listPesos.add(Double.parseDouble(ch));
                    }
                    // execute.ingresaPesos( new ArrayList<>(Arrays.asList(15.0,20.0,10.0)));
                    execute.ingresaPesos(listPesos);

                    System.out.println("Desea generar la explosión de Pedidos? Presione S/N ");
                    conf = System.console().readLine();
                    if (conf.equals("S")) {
                        execute.KgsxCompra();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // TODO: handle exception
            }

        }

        // execute.ingresaPesos( new ArrayList<>(Arrays.asList(15.0,20.0,10.0)));

        // execute.KgsxCompra();
    }
}
