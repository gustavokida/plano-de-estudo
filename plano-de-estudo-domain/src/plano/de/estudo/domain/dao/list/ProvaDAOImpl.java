package plano.de.estudo.domain.dao.list;

import plano.de.estudo.domain.dao.IProvaDAO;
import plano.de.estudo.domain.entidades.Prova;
import java.util.ArrayList;
import java.util.List;

public class ProvaDAOImpl implements IProvaDAO {

    private static List<Prova> provas = new ArrayList<>();
    private int lastId = 1;

    public void inserir(Prova ent) {
        if (ent == null) {
            throw new NullPointerException();
        }
        if (provas.contains(ent)) {
            throw new RuntimeException("Valor repetido!");
        }
        ent.setId(lastId);
        lastId++;
        provas.add(ent);
    }

    public void atualizar(Prova ent) {
        for (int i = 0; i < provas.size(); i++) {
            Prova get = provas.get(i);
            if (ent.getId() == get.getId()) {
                if (!provas.contains(ent)) {
                    provas.set(i, ent);
                }
            }
        }
    }

    public void remover(int id) {
        for (int i = 0; i < provas.size(); i++) {
            Prova get = provas.get(i);
            if (get.getId() == id) {
                provas.remove(i);
            }
        }
    }

    public List<Prova> consultar() {
        return provas;
    }
}
