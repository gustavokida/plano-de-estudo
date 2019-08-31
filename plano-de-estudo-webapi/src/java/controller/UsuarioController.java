
package controller;

import plano.de.estudo.domain.dao.IUsuarioDAO;
import plano.de.estudo.domain.dao.list.UsuarioDAOImpl;
import plano.de.estudo.domain.entidades.Usuario;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import plano.de.estudo.domain.dao.postgresql.UsuarioDAOImplPostgreSQL;


@Path("/usuario")
public class UsuarioController {
    
    private IUsuarioDAO
            banco = new UsuarioDAOImplPostgreSQL();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> index(){
        return banco.consultar();
    }
    
    @GET
    @Path("/remover/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remover(@PathParam("id") int id){
        banco.remover(id);
    }
 
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario select(@PathParam("id") int pk){
        System.out.println("Parametro:"+pk);
        for(Usuario cat: banco.consultar()){
            if(cat.getId() == pk){
                return cat;
            }
        }
        String json = "{\"id\":"+pk+"}";
        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/{nome}&{email}&{celular}")
    public String cadastrar(@PathParam("nome") String nome,
            @PathParam("email") String email,
            @PathParam("celular") String celular){
        try{
            System.out.println("Cadastrando");
            Usuario nova = new Usuario();
            nova.setNome(nome);
            nova.setEmail(email);
            nova.setCelular(celular);
            
            banco.inserir(nova);
        } catch(Exception erro){
            return "{\"status\": 0}";
        }
        String ret = "{\"status\": 1}";
        return ret;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/atualiar/{nome}&{email}&{celular}")
    public void atualizar(@PathParam("nome") String nome,
            @PathParam("email") String email,
            @PathParam("celular") String celular){    
            Usuario nova = new Usuario();
            nova.setNome(nome);
            nova.setEmail(email);
            nova.setCelular(celular);
            banco.atualizar(nova);
    }
    
}
