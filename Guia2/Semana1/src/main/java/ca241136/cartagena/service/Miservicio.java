package ca241136.cartagena.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class Miservicio {
    private final List<String> datos = new ArrayList<>();
    public Miservicio() {
        datos.add("elemento1");
        datos.add("elemento2");
    }
    public List<String> obtenerdatos() {
        return datos;
    }

    public void agregardatos(String nuevoDato){
        datos.add(nuevoDato);
    }
}
