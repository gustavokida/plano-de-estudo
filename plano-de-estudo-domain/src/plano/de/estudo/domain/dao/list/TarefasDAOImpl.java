package plano.de.estudo.domain.dao.list;

import plano.de.estudo.domain.dao.ITarefasDAO;
import plano.de.estudo.domain.entidades.Tarefas;
import java.util.ArrayList;
import java.util.List;
public class TarefasDAOImpl implements ITarefasDAO {
    private static List <Tarefas> tarefass = new ArrayList <> ();
    private int lastId = 1;

    public void inserir(Tarefas ent) {
        if(ent == null){
            throw new NullPointerException();
        }
        if(tarefass.contains(ent)) {
            throw new RuntimeException("Valor repetido!");
        }
        ent.setId(lastId);
        lastId++;
        tarefass.add(ent);
    }
    public void atualizar(Tarefas ent) {
        for (int i = 0; i < tarefass.size(); i++) {
            Tarefas get = tarefass.get(i);
            if(ent.getId() == get.getId()){
                if(!tarefass.contains(ent)){
                    tarefass.set(i, ent);
                }
            }
        }
    }
    public void remover(int id){
        for (int i = 0; i < tarefass.size(); i++) {
            Tarefas get = tarefass.get(i);
            if(get.getId()== id){
                tarefass.remove(i);
            }
        }
    }
    public List<Tarefas> consultar() {
        return tarefass;
        }
    }
