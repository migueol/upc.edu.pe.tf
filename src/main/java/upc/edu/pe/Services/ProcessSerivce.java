package upc.edu.pe.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import upc.edu.pe.Interfaces.ProcessInterface;
import upc.edu.pe.Entidades.Pedido;
import upc.edu.pe.Entidades.Transaccion;

public class ProcessSerivce implements ProcessInterface {

    private static List<Pedido> entidad;
    private HashMap<Integer, Integer> collect;

    public void AddPedido(Pedido objeto) {

        try {
            if (entidad == null) {
                entidad = new ArrayList<>();
            }
            entidad.add(objeto);
        } catch (Exception e) {
            throw new ExceptionService(e.getMessage());
        }

    }

    public List<Pedido> CargarData() {
        if (entidad == null) {
            entidad = new ArrayList<>();
            entidad.add(new Pedido("123456", new ArrayList<>(Arrays.asList("X", "XS", "M")), "TCU"));
            entidad.add(new Pedido("123454", new ArrayList<>(Arrays.asList("L", "M", "S")), "PUÑ"));
            entidad.add(new Pedido("123457", new ArrayList<>(Arrays.asList("4", "6", "8")), "TCU"));
            entidad.add(new Pedido("123458", new ArrayList<>(Arrays.asList("2", "10", "14")), "PUÑ"));
        }
        return entidad;
    }

    public Pedido buscarPedido(String pedido) {
        try {
            // == pedido
            return (Pedido) entidad.stream().filter((x) -> x.pedido.equals(pedido)).collect(Collectors.toList()).get(0);

        } catch (Exception e) {
            return new Pedido("xxx", new ArrayList<>(Arrays.asList("-", "-", "-")), "xxx");
            // e.getMessage();
        }
    }

    public List<Pedido> buscarPedidoAll() {
        return entidad.stream().collect(Collectors.toList());
    }

    public void insertar(List<Integer> a, Object o) {
        a.add((Integer) o);
    }

    public List<Integer> ingresaCantidades(List<Integer> cantidades) {

        List<Integer> cant = new ArrayList<Integer>();
        HashMap<Integer, Integer> collect = cantidades.stream().collect(HashMap<Integer, Integer>::new,
                (map, streamValue) -> map.put(map.size(), streamValue), (map, map2) -> {
                });

        collect.forEach((k, v) -> {
            Integer valor = (int) (v * 1.08);
            insertar(cant, valor);
        });

        return cant;
    }

    public List<Double> KgsxCompra(Transaccion Transac) throws NullPointerException {
        List<Double> cantxKilo = new ArrayList<Double>();
        collect = Transac.cantidad108.stream().collect(HashMap<Integer, Integer>::new,
                (map, streamValue) -> map.put(map.size(), streamValue), (map, map2) -> {
                });

        collect.forEach((k, v) -> {
            try {
                double peso = Transac.pesos.get(k) / 1000;
                // System.out.println(peso);
                double kgs = ((peso * v) * 1.05);
                // System.out.println(kgs);
                cantxKilo.add(kgs);
            } catch (Exception e) {
                throw new ExceptionService("Error por división");
            } finally {
                System.out.println("Ejecutado con exito");
            }

        });

        return cantxKilo;

    }

    public HashMap<Integer, Integer> DetalleTallas() {
        return collect;
    }

}
