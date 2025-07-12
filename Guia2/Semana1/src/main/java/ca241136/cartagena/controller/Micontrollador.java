package ca241136.cartagena.controller;

import ca241136.cartagena.service.Miservicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/datos")

public class Micontrollador {

    @Autowired
    private Miservicio miservicio;

    @GetMapping
    public List<String> Obtenerdatos() {
        return miservicio.obtenerdatos();
    }

    @PostMapping
    public String agregardatos(@RequestBody String nuevoDato) {
        miservicio.agregardatos(nuevoDato);
        return "Dato agregado correctamente "+nuevoDato;
    }

}
