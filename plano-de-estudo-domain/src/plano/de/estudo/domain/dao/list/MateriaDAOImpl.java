package plano.de.estudo.domain.dao.list;

import plano.de.estudo.domain.dao.IMateriaDAO;
import plano.de.estudo.domain.entidades.Materia;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAOImpl implements IMateriaDAO {

    private static List<Materia> materias = new ArrayList<>();
    private int lastId = 1;

    public void inserir(Materia ent) {
        if (ent == null) {
            throw new NullPointerException();
        }
        if (materias.contains(ent)) {
            throw new RuntimeException("Valor repetido!");
        }
        ent.setId(lastId);
        lastId++;
        materias.add(ent);
    }

    public void atualizar(Materia ent) {
        for (int i = 0; i < materias.size(); i++) {
            Materia get = materias.get(i);
            if (ent.getId() == get.getId()) {
                if (!materias.contains(ent)) {
                    materias.set(i, ent);
                }
            }
        }
    }

    public void remover(int id) {
        for (int i = 0; i < materias.size(); i++) {
            Materia get = materias.get(i);
            if (get.getId() == id) {
                materias.remove(i);
            }
        }
    }

    public List<Materia> consultar() {
        return materias;
    }
}
