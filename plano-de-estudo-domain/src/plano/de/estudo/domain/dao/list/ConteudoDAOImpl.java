package plano.de.estudo.domain.dao.list;

import plano.de.estudo.domain.dao.IConteudoDAO;
import plano.de.estudo.domain.entidades.Conteudo;
import java.util.ArrayList;
import java.util.List;

public class ConteudoDAOImpl implements IConteudoDAO {

    private static List<Conteudo> conteudos = new ArrayList<>();
    private int lastId = 1;

    public void inserir(Conteudo ent) {
        if (ent == null) {
            throw new NullPointerException();
        }
        if (conteudos.contains(ent)) {
            throw new RuntimeException("Valor repetido!");
        }
        ent.setId(lastId);
        lastId++;
        conteudos.add(ent);
    }

    public void atualizar(Conteudo ent) {
        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo get = conteudos.get(i);
            if (ent.getId() == get.getId()) {
                if (!conteudos.contains(ent)) {
                    conteudos.set(i, ent);
                }
            }
        }
    }

    public void remover(int id) {
        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo get = conteudos.get(i);
            if (get.getId() == id) {
                conteudos.remove(i);
            }
        }
    }

    public List<Conteudo> consultar() {
        return conteudos;
    }
}
