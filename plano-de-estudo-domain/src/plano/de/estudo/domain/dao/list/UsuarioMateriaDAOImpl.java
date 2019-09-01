package plano.de.estudo.domain.dao.list;

import plano.de.estudo.domain.entidades.UsuarioMateria;
import java.util.ArrayList;
import java.util.List;
import plano.de.estudo.domain.dao.IUsuarioMateriaDAO;
import plano.de.estudo.domain.entidades.UsuarioMateria;

public class UsuarioMateriaDAOImpl implements IUsuarioMateriaDAO {

    private static List<UsuarioMateria> usuarioMaterias = new ArrayList<>();
    private int lastId = 1;

    public void inserir(UsuarioMateria ent) {
        if (ent == null) {
            throw new NullPointerException();
        }
        if (usuarioMaterias.contains(ent)) {
            throw new RuntimeException("Valor repetido!");
        }
        ent.setId(lastId);
        lastId++;
        usuarioMaterias.add(ent);
    }

    public void atualizar(UsuarioMateria ent) {
        for (int i = 0; i < usuarioMaterias.size(); i++) {
            UsuarioMateria get = usuarioMaterias.get(i);
            if (ent.getId() == get.getId()) {
                if (!usuarioMaterias.contains(ent)) {
                    usuarioMaterias.set(i, ent);
                }
            }
        }
    }

    public void remover(int id) {
        for (int i = 0; i < usuarioMaterias.size(); i++) {
            UsuarioMateria get = usuarioMaterias.get(i);
            if (get.getId() == id) {
                usuarioMaterias.remove(i);
            }
        }
    }

    public List<UsuarioMateria> consultar() {
        return usuarioMaterias;
    }
}
