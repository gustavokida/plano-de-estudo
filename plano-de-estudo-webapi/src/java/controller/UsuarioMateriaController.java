package controller;

import plano.de.estudo.domain.dao.IUsuarioMateriaDAO;
import plano.de.estudo.domain.dao.list.UsuarioMateriaDAOImpl;
import plano.de.estudo.domain.entidades.UsuarioMateria;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import plano.de.estudo.domain.dao.postgresql.UsuarioMateriaDAOImplPostgreSQL;

@Path("/usuariomateria")
public class UsuarioMateriaController {

    private IUsuarioMateriaDAO banco = new UsuarioMateriaDAOImplPostgreSQL();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioMateria> index() {
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
    public UsuarioMateria select(@PathParam("id") int pk) {
        System.out.println("Parametro:" + pk);
        for (UsuarioMateria cat : banco.consultar()) {
            if (cat.getId() == pk) {
                return cat;
            }
        }
        String json = "{\"id\":" + pk + "}";
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/{idUsuario}&{idMateria}")
    public String cadastrar(@PathParam("idUsuario") int idUsuario,
            @PathParam("idMateria") int idMateria) {
        try {
            System.out.println("Cadastrando");
            UsuarioMateria nova = new UsuarioMateria();
            nova.setIdUsuario(idUsuario);
            nova.setIdMateria(idMateria);

            banco.inserir(nova);
        } catch (Exception erro) {
            return "{\"status\": 0}";
        }
        String ret = "{\"status\": 1}";
        return ret;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/atualizar/{idUsuario}&{idMateria}")
    public void atualizar(@PathParam("idUsuario") int idUsuario,
            @PathParam("idMateria") int idMateria) {
        UsuarioMateria nova = new UsuarioMateria();
        nova.setIdUsuario(idUsuario);
        nova.setIdMateria(idMateria);
        banco.atualizar(nova);
    }
}
