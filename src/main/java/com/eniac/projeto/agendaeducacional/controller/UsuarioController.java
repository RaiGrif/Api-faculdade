@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService_) {
        this.usuarioService = usuarioService_
    }

    @PostMapping
    String create(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @GetMapping{"{id}"}
    String list(@PathVariabel("id") Long id){
        return usuarioService.delete(id);
    }


    @GetMapping
    @PutMapping
    @DeleteMapping
}
