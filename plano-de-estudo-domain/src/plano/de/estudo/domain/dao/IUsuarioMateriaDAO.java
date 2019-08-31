package plano.de.estudo.domain.dao;

import java.util.List;
import plano.de.estudo.domain.entidades.UsuarioMateria;
public interface IUsuarioMateriaDAO {
    public void inserir(UsuarioMateria ent);
    public void atualizar(UsuarioMateria ent);
    public void remover(int id);
    public List<UsuarioMateria> consultar();
}