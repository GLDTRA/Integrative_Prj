package com.group3.projecteducation.Controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.group3.projecteducation.Repository.CategoriasRepository;
import com.group3.projecteducation.Repository.CursosRepository;
import com.group3.projecteducation.model.Categoria;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private CursosRepository cursosRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok(categoriasRepository.findAll());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Optional<Categoria>> getByTitulo(@PathVariable String titulo){
        Optional<Categoria> categoria = categoriasRepository.findAllByTituloContainingIgnoreCase(titulo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id){
        return  categoriasRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            }

    @PostMapping
    public ResponseEntity<Categoria>post(@Valid @RequestBody Categoria categoria ){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> Put(@Valid @RequestBody Categoria categoria){
        return categoriasRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                .body(categoriasRepository.save(categoria))).orElse(ResponseEntity
                .status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id){
        Optional<Categoria> categoria = categoriasRepository.findById(id);
        if(categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        categoriasRepository.deleteById(id);
    }


}
