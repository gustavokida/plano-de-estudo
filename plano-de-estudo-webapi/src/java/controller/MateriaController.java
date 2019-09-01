package controller;

import java.sql.Time;
import plano.de.estudo.domain.dao.IMateriaDAO;
import plano.de.estudo.domain.dao.list.MateriaDAOImpl;
import plano.de.estudo.domain.entidades.Materia;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import plano.de.estudo.domain.dao.postgresql.MateriaDAOImplPostgreSQL;

@Path("/materia")
public class MateriaController {

    private IMateriaDAO banco = new MateriaDAOImplPostgreSQL();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> index() {
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
    public Materia select(@PathParam("id") int pk) {
        System.out.println("Parametro:" + pk);
        for (Materia cat : banco.consultar()) {
            if (cat.getId() == pk) {
                return cat;
            }
        }
        String json = "{\"id\":" + pk + "}";
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/{nome}&{horario}&{cargaHoraria}&{media}&{aprovado}&{observacao}")
    public String cadastrar(@PathParam("nome") String nome,
            @PathParam("horario") Time horario,
            @PathParam("cargaHoraria") int cargaHoraria,
            @PathParam("media") float media,
            @PathParam("aprovado") boolean aprovado,
            @PathParam("observacao") String observacao) {
        try {
            System.out.println("Cadastrando");
            Materia nova = new Materia();
            nova.setNome(nome);
            nova.setHorario(horario);
            nova.setCargaHoraria(cargaHoraria);
            nova.setMedia(media);
            nova.setAprovado(aprovado);
            nova.setObservacao(observacao);

            banco.inserir(nova);
        } catch (Exception erro) {
            return "{\"status\": 0}";
        }
        String ret = "{\"status\": 1}";
        return ret;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/atualizar/{nome}&{horario}&{cargaHoraria}&{media}&{aprovado}&{observacao}")
    public void atualizar(@PathParam("nome") String nome,
            @PathParam("horario") Time horario,
            @PathParam("cargaHoraria") int cargaHoraria,
            @PathParam("media") float media,
            @PathParam("aprovado") boolean aprovado,
            @PathParam("observacao") String observacao) {
        Materia nova = new Materia();
        nova.setNome(nome);
        nova.setHorario(horario);
        nova.setCargaHoraria(cargaHoraria);
        nova.setMedia(media);
        nova.setAprovado(aprovado);
        nova.setObservacao(observacao);

        banco.atualizar(nova);
    }

}
