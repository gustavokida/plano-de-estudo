package plano.de.estudo.domain.dao;

import plano.de.estudo.domain.entidades.Tarefas;
import java.util.List;
public interface ITarefasDAO {
    public void inserir(Tarefas ent);
    public void atualizar(Tarefas ent);
    public void remover(int id);
    public List<Tarefas> consultar();
}