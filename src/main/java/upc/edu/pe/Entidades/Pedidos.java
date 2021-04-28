package upc.edu.pe.Entidades;
import java.util.List;

public class Pedidos {
    public String pedido;
    public  List<String> Talla;
    public String componente;

    public Pedidos(String pedido,  List<String> Talla, String componente) {
    	super();
        this.pedido = pedido;
        this.Talla = Talla;
        this.componente = componente;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public List<String> getTalla() {
        return Talla;
    }

    public void setTalla(List<String> Talla) {
        this.Talla = Talla;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    // @Override
    // public String toString() {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append(getClass().getName());
    //     sb.append(": ");
    //     for (Field f : getClass().getDeclaredFields()) {
    //         sb.append(f.getName());
    //         sb.append("=");
    //         try {
	// 			sb.append(f.get(this));
	// 		} catch (IllegalArgumentException | IllegalAccessException e) {
	// 			// TODO Auto-generated catch block
	// 			e.printStackTrace();
	// 		}
    //         sb.append(", ");
    //     }
    //     return sb.toString();
    // }

}
