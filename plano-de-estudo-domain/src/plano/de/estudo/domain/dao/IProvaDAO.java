package plano.de.estudo.domain.dao;

import plano.de.estudo.domain.entidades.Prova;
import java.util.List;
public interface IProvaDAO {
    public void inserir(Prova ent);
    public void atualizar(Prova ent);
    public void remover(int id);
    public List<Prova> consultar();
}