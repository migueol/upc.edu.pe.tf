package upc.edu.pe.UI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;
import javax.swing.TransferHandler.TransferSupport;

import upc.edu.pe.Entidades.Pedidos;
import upc.edu.pe.Entidades.Transaccion;
import upc.edu.pe.Interfaces.CommonInterface;
import upc.edu.pe.Interfaces.ProcessInterface;
import upc.edu.pe.Services.CommonService;
import upc.edu.pe.Services.ProcessSerivce;
import upc.edu.pe.common.utilies;

public class Main {
    ProcessInterface service = new ProcessSerivce();
    private Pedidos entidad;
    private double kgsTotal;

    private Transaccion Transac;

    public List<Pedidos> ejecutar() {
        System.out.println(Titulo("COMERCIAL"));
        return service.CargarData();
    }

    public void buscarPedido(String pedido) {
        entidad = service.buscarPedido(pedido);
        utilies<Pedidos> x = new utilies<Pedidos>(entidad);
        if ( entidad.pedido == "xxx" ) {
        	 System.out.println("Pedido No Encontrado");
        	  x.write(x.datos());
         }
        else {
        	  System.out.println("Pedido Encontrado con los siguientes datos:");
        	  x.write(x.datos());
        	  System.out.println("Desea generar las cantidades por tallas? Presione S/N ");
        }
      
     
    }

    public void buscarPedidoAll() {
        System.out.println("Listado de todos los pedidos");
        service.buscarPedidoAll().stream().forEach((a) -> {
            CommonInterface<Pedidos> Commonservice = new CommonService<Pedidos>(a);
            Commonservice.write(Commonservice.datos());
        });
    }

    void insertar(List<Integer> a, Object o) {
        a.add((Integer) o);
    }

    public void ingresaCantidades(List<Integer> cantidades) {

        List<Integer> cant = new ArrayList<Integer>();
        HashMap<Integer, Integer> collect = cantidades.stream().collect(HashMap<Integer, Integer>::new,
                (map, streamValue) -> map.put(map.size(), streamValue), (map, map2) -> {
                });

        collect.forEach((k, v) -> {
            Integer valor = (int) (v * 1.08);
            insertar(cant, valor);
            // cant.add( (Integer) valor);
            // System.out.println(k + ":" + v);
            // Transac = new Transaccion(entidad.pedido, entidad.getTalla().get(k),v,0);
            // CommonInterface<Transaccion> Commonservice = new
            // CommonService<Transaccion>(Transac);
            // Commonservice.write(Commonservice.datos());
        });

        Transac = new Transaccion(entidad.pedido, entidad.getTalla(), entidad.componente, cantidades, cant, null, null);
        CommonInterface<Transaccion> Commonservice = new CommonService<Transaccion>(Transac);
        Commonservice.write(Commonservice.datos());

        // cantidades
        // .forEach((a) -> {
        // //CommonInterface<Transaccion> Commonservice = new
        // CommonService<Transaccion>(a);
        // Transac.add( new Transaccion(entidad.pedido, entidad.getTalla().get(0) ,a,0
        // ));
        // System.err.println(System.out.in);
        // });

        // System.err.println(Transac);
    }

    public void ingresaPesos(List<Double> pesos) {
        System.out.println(Titulo("PLANEAMIENTO TEXTIL"));
        Transac.pesos = pesos; //

        CommonInterface<Transaccion> Commonservice = new CommonService<Transaccion>(Transac);
        Commonservice.write(Commonservice.datos());
    }

    public String Titulo(String titulo) {

        return String.format("::::::::::::::::::::::::::::::::::::::::::::::::") + "\n"
                + String.format("::::::::::AREA DE  %s ::::::::::: ", titulo) + "\n"
                + String.format("::::::::::::::::::::::::::::::::::::::::::::::::");

    }

    public void KgsxCompra() {
        System.out.println(Titulo("LOGISTICA"));
        List<Double> cantxKilo = new ArrayList<Double>();

        HashMap<Integer, Integer> collect = Transac.cantidad108.stream().collect(HashMap<Integer, Integer>::new,
                (map, streamValue) -> map.put(map.size(), streamValue), (map, map2) -> {
                });

        collect.forEach((k, v) -> {
            double peso = Transac.pesos.get(k) / 1000;
            // System.out.println(peso);
            double kgs = ((peso * v) * 1.05);
            // System.out.println(kgs);
            cantxKilo.add(kgs);
        });
        cantxKilo.stream().forEach((a) -> {
            kgsTotal += a;
        });

        Transac.kgsxCompra = cantxKilo;

        CommonInterface<Transaccion> Commonservice = new CommonService<Transaccion>(Transac);
        Commonservice.write(Commonservice.datos());

        System.out.println("Los kilogramos toles para la compra de tela del Pedido: " + entidad.pedido + " es:"
                + kgsTotal + " kilos");
        System.out.println("============================================");
        System.out.println("Los kilogramos por cada talla son: ");

        collect.forEach((k, v) -> {
            System.out.println("Talla: " + Transac.Talla.get(k) + " :: Cantidad 105% es: " + Transac.cantidad100.get(k)
                    + " :: Cantidad 108% es: " + Transac.cantidad108.get(k) + " :: Peso(kgs):"
                    + Transac.pesos.get(k) / 1000 + " :: KgsxCompra es: " + Transac.kgsxCompra.get(k));
        });
    }

}