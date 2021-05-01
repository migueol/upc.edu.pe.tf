package upc.edu.pe.Interfaces;
import java.util.HashMap;
import java.util.List;
import upc.edu.pe.Entidades.Pedido;
import upc.edu.pe.Entidades.Transaccion;

public interface ProcessInterface {
   public List<Pedido> CargarData();

   public Pedido buscarPedido(String pedido);

   public List<Pedido> buscarPedidoAll();

   public List<Integer> ingresaCantidades(List<Integer> cantidades);

   public List<Double> KgsxCompra(Transaccion Transac);

   public HashMap<Integer, Integer> DetalleTallas();

   public void AddPedido(Pedido entidad);
}
