package controller;

import java.util.Date;
import plano.de.estudo.domain.dao.ITarefasDAO;
import plano.de.estudo.domain.dao.list.TarefasDAOImpl;
import plano.de.estudo.domain.entidades.Tarefas;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import plano.de.estudo.domain.dao.postgresql.TarefasDAOImplPostgreSQL;

@Path("/tarefas")
public class TarefasController {

    private ITarefasDAO banco = new TarefasDAOImplPostgreSQL();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tarefas> index() {
        return banco.consultar();
    }

    @GET
    @Path("/remover/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remover(@PathParam("id") int id) {
        banco.remover(id);
    }

    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tarefas select(@PathParam("id") int pk) {
        System.out.println("Parametro:" + pk);
        for (Tarefas cat : banco.consultar()) {
            if (cat.getId() == pk) {
                return cat;
            }
        }
        String json = "{\"id\":" + pk + "}";
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/{observacao}&{data}&{feito}&{importante}&{dataEntrega}&{idConteudo}")
    public String cadastrar(@PathParam("observacao") String observacao,
            @PathParam("data") Date data,
            @PathParam("feito") boolean feito,
            @PathParam("importante") boolean importante,
            @PathParam("dataEntrega") Date dataEntrega,
            @PathParam("idMateria") int idMateria) {
        try {
            System.out.println("Cadastrando");
            Tarefas nova = new Tarefas();
            nova.setObservacao(observacao);
            nova.setData(data);
            nova.setFeito(feito);
            nova.setImportante(importante);
            nova.setDataEntrega(dataEntrega);
            nova.setIdConteudo(idMateria);

            banco.inserir(nova);
        } catch (Exception erro) {
            return "{\"status\": 0}";
        }
        String ret = "{\"status\": 1}";
        return ret;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/atualizar/{observacao}&{data}&{feito}&{importante}&{dataEntrega}&{idConteudo}")
    public void atualizar(@PathParam("observacao") String observacao,
            @PathParam("data") Date data,
            @PathParam("feito") boolean feito,
            @PathParam("importante") boolean importante,
            @PathParam("dataEntrega") Date dataEntrega,
            @PathParam("idMateria") int idMateria) {
        Tarefas nova = new Tarefas();
        nova.setObservacao(observacao);
        nova.setData(data);
        nova.setFeito(feito);
        nova.setImportante(importante);
        nova.setDataEntrega(dataEntrega);
        nova.setIdConteudo(idMateria);

        banco.atualizar(nova);
    }

}
