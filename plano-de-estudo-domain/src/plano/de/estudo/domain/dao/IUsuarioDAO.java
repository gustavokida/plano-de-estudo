package plano.de.estudo.domain.dao;

import plano.de.estudo.domain.entidades.Usuario;
import java.util.List;
public interface IUsuarioDAO {
    public void inserir(Usuario ent);
    public void atualizar(Usuario ent);
    public void remover(int id);
    public List<Usuario> consultar();
}