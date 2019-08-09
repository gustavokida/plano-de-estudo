package plano.de.estudo.domain.dao;

import plano.de.estudo.domain.entidades.Conteudo;
import java.util.List;
public interface IConteudoDAO {
    public void inserir(Conteudo ent);
    public void atualizar(Conteudo ent);
    public void remover(int id);
    public List<Conteudo> consultar();
}