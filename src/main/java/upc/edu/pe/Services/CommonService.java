package upc.edu.pe.Services;

import java.lang.reflect.Field;

import upc.edu.pe.Interfaces.CommonInterface;

public class CommonService<T> implements CommonInterface<T> {

    private T t;

    public CommonService(T t) {
        this.t = t;
    }

    public void write(Object x) {
        System.out.println(x);
    }

    public String datos() {

        StringBuilder sb = new StringBuilder();
        sb.append(t.getClass().getName());
        sb.append(": ");
        for (Field f : t.getClass().getDeclaredFields()) {
            sb.append(f.getName());
            sb.append("=");
            try {
                sb.append(f.get(t));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sb.append(", ");
        }
        return sb.toString();
    }

}
