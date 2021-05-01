package upc.edu.pe.Entidades;

import java.util.List;

public class Transaccion extends Pedido {
    public List<Integer> cantidad100;
    public List<Integer> cantidad108;
    public List<Double> pesos;
    public List<Double> kgsxCompra;

    public Transaccion(String pedido, List<String> Talla, String componente, List<Integer> cantidad100,
            List<Integer> cantidad108, List<Double> pesos, List<Double> kgsxCompra) {
        super(pedido, Talla, componente);
        this.pedido = pedido;
        this.Talla = Talla;
        this.cantidad100 = cantidad100;
        this.cantidad108 = cantidad108;
        this.pesos = pesos;
        this.kgsxCompra = kgsxCompra;
    }

}