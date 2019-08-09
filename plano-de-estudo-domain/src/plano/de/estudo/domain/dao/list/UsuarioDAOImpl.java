package plano.de.estudo.domain.dao.list;

import plano.de.estudo.domain.dao.IUsuarioDAO;
import plano.de.estudo.domain.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAOImpl implements IUsuarioDAO {
    private static List <Usuario> usuarios = new ArrayList <> ();
    private int lastId = 1;

    public void inserir(Usuario ent) {
        if(ent == null){
            throw new NullPointerException();
        }
        if(usuarios.contains(ent)) {
            throw new RuntimeException("Valor repetido!");
        }
        ent.setId(lastId);
        lastId++;
        usuarios.add(ent);
    }
    public void atualizar(Usuario ent) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario get = usuarios.get(i);
            if(ent.getId() == get.getId()){
                if(!usuarios.contains(ent)){
                    usuarios.set(i, ent);
                }
            }
        }
    }
    public void remover(int id){
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario get = usuarios.get(i);
            if(get.getId()== id){
                usuarios.remove(i);
            }
        }
    }
    public List<Usuario> consultar() {
        return usuarios;
        }
    }
