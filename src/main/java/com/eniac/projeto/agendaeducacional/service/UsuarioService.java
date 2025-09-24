@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public usuarioService (UsuarioRepository usuarioRepository_) {
        this.usuarioRepository = usuarioRepository_;
    }

    public String create(Usuario usuario ) {
        Return usuarioRepository.save(usuario);
    }

    public String update (Usuario usuario) {
        Return usuarioRepository.save(usuario);
    }

    public String delete(Long id){
        if (id) {
        Return usuarioRepository.deleteById(id);
        } 
}
