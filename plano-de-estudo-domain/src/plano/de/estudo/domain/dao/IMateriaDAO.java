package plano.de.estudo.domain.dao;

import plano.de.estudo.domain.entidades.Materia;
import java.util.List;
public interface IMateriaDAO {
    public void inserir(Materia ent);
    public void atualizar(Materia ent);
    public void remover(int id);
    public List<Materia> consultar();
}