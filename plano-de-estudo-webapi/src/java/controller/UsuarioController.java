
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


@Path("/usuario")
public class UsuarioController {
    
    private IUsuarioDAO
            banco = new UsuarioDAOImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> index(){
        return banco.consultar();
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
    @Path("/cadastrar/{nome}{email}{celular}")
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
}
