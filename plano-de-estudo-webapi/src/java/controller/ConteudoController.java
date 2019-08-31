
package controller;

import java.util.Date;
import plano.de.estudo.domain.dao.IConteudoDAO;
import plano.de.estudo.domain.dao.list.ConteudoDAOImpl;
import plano.de.estudo.domain.entidades.Conteudo;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/conteudo")
public class ConteudoController {
    
    private IConteudoDAO
            banco = new ConteudoDAOImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conteudo> index(){
        return banco.consultar();
    }
 
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conteudo select(@PathParam("id") int pk){
        System.out.println("Parametro:"+pk);
        for(Conteudo cat: banco.consultar()){
            if(cat.getId() == pk){
                return cat;
            }
        }
        String json = "{\"id\":"+pk+"}";
        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/{nome}{observacao}{data}{idMateria}")
    public String cadastrar(@PathParam("nome") String nome,
            @PathParam("observacao") String observacao,
            @PathParam("data") Date data,
            @PathParam("idMateria") int idMateria){
        try{
            System.out.println("Cadastrando");
            Conteudo nova = new Conteudo();
            nova.setNome(nome);
            nova.setObservacao(observacao);
            nova.setData(data);
            nova.setIdMateria(idMateria);
            banco.inserir(nova);
        } catch(Exception erro){
            return "{\"status\": 0}";
        }
        String ret = "{\"status\": 1}";
        return ret;
    }
}
