package upc.edu.pe.UI;

import java.util.HashMap;
import java.util.List;
import upc.edu.pe.Entidades.Pedido;
import upc.edu.pe.Entidades.Transaccion;
import upc.edu.pe.Interfaces.CommonInterface;
import upc.edu.pe.Interfaces.ProcessInterface;
import upc.edu.pe.Services.CommonService;
import upc.edu.pe.Services.ProcessSerivce;
import upc.edu.pe.common.utilies;

public class Controller {
    ProcessInterface service = new ProcessSerivce();
    private Pedido entidad;
    private double kgsTotal;
    private HashMap<Integer, Integer> collect;
    private Transaccion Transac;

    public List<Pedido> ejecutar() {
        System.out.println(Titulo("COMERCIAL"));
        return service.CargarData();
    }

    public void AddPedido(Pedido objecto) {
        service.AddPedido(objecto);
    }

    public void buscarPedido(String pedido) {
        entidad = service.buscarPedido(pedido);
        utilies<Pedido> x = new utilies<Pedido>(entidad);
        if (entidad.pedido == "xxx") {
            System.out.println("Pedido No Encontrado");
            x.write(x.datos());
        } else {
            System.out.println("Pedido Encontrado con los siguientes datos:");
            x.write(x.datos());
            System.out.println("Desea generar las cantidades por tallas? Presione S/N ");
        }

    }

    public void buscarPedidoAll() {
        System.out.println("Listado de todos los pedidos");
        service.buscarPedidoAll().stream().forEach((a) -> {
            CommonInterface<Pedido> Commonservice = new CommonService<Pedido>(a);
            Commonservice.write(Commonservice.datos());
        });
    }

    public void ingresaCantidades(List<Integer> cantidades) {

        List<Integer> cant = service.ingresaCantidades(cantidades);
        Transac = new Transaccion(entidad.pedido, entidad.getTalla(), entidad.componente, cantidades, cant, null, null);
        CommonInterface<Transaccion> Commonservice = new CommonService<Transaccion>(Transac);
        Commonservice.write(Commonservice.datos());
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
        try {

            System.out.println(Titulo("LOGISTICA"));
            List<Double> cantxKilo = service.KgsxCompra(Transac);
            cantxKilo.stream().forEach((a) -> {
                kgsTotal += a;
            });
            Transac.kgsxCompra = cantxKilo;

            CommonInterface<Transaccion> Commonservice = new CommonService<Transaccion>(Transac);
            Commonservice.write(Commonservice.datos());

            System.out.println("Los kilogramos toles para la compra de tela del Pedido: " + entidad.pedido + " es:"
                    + kgsTotal + " kilos");
            System.out.println("============================================");
            System.out.println("Los kilogramos por talla son: ");

            collect = service.DetalleTallas();
            collect.forEach((k, v) -> {
                System.out.println(
                        "Talla: " + Transac.Talla.get(k) + " :: Cantidad 105% es: " + Transac.cantidad100.get(k)
                                + " :: Cantidad 108% es: " + Transac.cantidad108.get(k) + " :: Peso(kgs):"
                                + Transac.pesos.get(k) / 1000 + " :: KgsxCompra es: " + Transac.kgsxCompra.get(k));
            });

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}