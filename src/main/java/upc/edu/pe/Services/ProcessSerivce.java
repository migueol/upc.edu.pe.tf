package upc.edu.pe.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import upc.edu.pe.Interfaces.ProcessInterface;
import upc.edu.pe.Entidades.Pedidos;

public class ProcessSerivce implements ProcessInterface {

    private List<Pedidos> entidad;

    public List<Pedidos> CargarData() {
        entidad = new ArrayList<>();
        // List<String> gfg = new ArrayList<>( List.of("S", "M", "L"));
        entidad.add(new Pedidos("123456", new ArrayList<>(Arrays.asList("X", "XS", "M")), "TCU"));
        entidad.add(new Pedidos("123454", new ArrayList<>(Arrays.asList("L", "M", "S")), "PUÑ"));
        entidad.add(new Pedidos("123457", new ArrayList<>(Arrays.asList("4", "6", "8")), "TCU"));
        entidad.add(new Pedidos("123458", new ArrayList<>(Arrays.asList("2", "10", "14")), "PUÑ"));
        return entidad;
    }

    public Pedidos buscarPedido(String pedido) {
        try {
            // == pedido
            return (Pedidos) entidad.stream().filter((x) -> x.pedido.equals(pedido) ).collect(Collectors.toList()).get(0);

        } catch (Exception e) {
            return new Pedidos("xxx", new ArrayList<>(Arrays.asList("-", "-", "-")), "xxx");
            // e.getMessage();
        }
    }

    public List<Pedidos> buscarPedidoAll() {
        return entidad.stream().collect(Collectors.toList());
    }

}
