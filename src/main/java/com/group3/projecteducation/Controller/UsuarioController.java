package com.group3.projecteducation.Controller;

import com.group3.projecteducation.Repository.UsuarioRepository;
import com.group3.projecteducation.TipoUsuario;
import com.group3.projecteducation.model.Categoria;
import com.group3.projecteducation.model.Curso;
import com.group3.projecteducation.model.Usuario;
import com.group3.projecteducation.model.UsuarioLogin;
import com.group3.projecteducation.repository.CategoriasRepository;
import com.group3.projecteducation.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        return usuarioRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<Optional<Usuario>> getByUsuario(@PathVariable String usuario){
        return ResponseEntity.ok(usuarioRepository.findByUsuario(usuario));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Usuario>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @GetMapping("/{tipoUsuario}")
    public ResponseEntity<List<Usuario>> getByTipoUsuario(@PathVariable String tipoUsuario){
        return ResponseEntity.ok(usuarioRepository.findAllByTipoUsuario(tipoUsuario));
    }

    @GetMapping("/logar")
    public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuarioLogin){
        return usuarioService.autenticarUsuario(usuarioLogin)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/{cursos}")
    public ResponseEntity<List<Usuario>> getByCurso(@PathVariable Usuario usuario){
        return ResponseEntity.ok(usuarioRepository.findAllByCursosMatriculadosIn(usuario.getCursosMatriculados()));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }




}