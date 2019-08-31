
package controller;

import java.util.Date;
import plano.de.estudo.domain.dao.IProvaDAO;
import plano.de.estudo.domain.dao.list.ProvaDAOImpl;
import plano.de.estudo.domain.entidades.Prova;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/prova")
public class ProvaController {
    
    private IProvaDAO
            banco = new ProvaDAOImpl();
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prova> index(){
        return banco.consultar();
    }
 
    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prova select(@PathParam("id") int pk){
        System.out.println("Parametro:"+pk);
        for(Prova cat: banco.consultar()){
            if(cat.getId() == pk){
                return cat;
            }
        }
        String json = "{\"id\":"+pk+"}";
        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cadastrar/{nota}{data}{observacao}{idConteudo}")
    public String cadastrar(@PathParam("nota") float nota,
            @PathParam("data") Date data,
            @PathParam("observacao") String observacao,
            @PathParam("idConteudo") int idConteudo){
        try{
            System.out.println("Cadastrando");
            Prova nova = new Prova();
            nova.setNota(nota);
            nova.setData(data);
            nova.setObservacao(observacao);
            nova.setIdConteudo(idConteudo);
            
            banco.inserir(nova);
        } catch(Exception erro){
            return "{\"status\": 0}";
        }
        String ret = "{\"status\": 1}";
        return ret;
    }
}
