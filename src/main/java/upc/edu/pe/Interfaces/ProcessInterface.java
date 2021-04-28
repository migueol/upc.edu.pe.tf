package upc.edu.pe.Interfaces; 
import upc.edu.pe.Entidades.Pedidos;
import java.util.List;

public interface ProcessInterface {
   public List<Pedidos>  CargarData() ;
   public Pedidos buscarPedido(String pedido); 
   public List<Pedidos>  buscarPedidoAll() ;
}
