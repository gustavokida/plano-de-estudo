
package plano.de.estudo.domain.dao.postgresql;
 
import plano.de.estudo.domain.dao.IConteudoDAO;
 import plano.de.estudo.domain.entidades.Conteudo;
 import java.sql.Connection;
import java.sql.Date;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.util.ArrayList;
 import java.util.List;
 
public class ConteudoDAOImplPostgreSQL 
 implements IConteudoDAO {
 
 private Connection criaConexao(){
 Connection conexao = null;
 try{
 Class.forName("org.postgresql.Driver");
 conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/plano", "postgres", "123");
 } catch(Exception erro){
 erro.printStackTrace();
 }
 return conexao;
 }
 
    
 public void inserir(Conteudo ent) {
 Connection con = criaConexao();
 
 String sql = "insert into conteudo (nome, observacao, data, idMateria)"
         + "VALUES ('" + ent.getNome() +"','"+ ent.getObservacao() +
         "','"+ ent.getData()+"','" + ent.getIdMateria()+ "')";
 
 try{
 con.createStatement().execute(sql);
 } catch (Exception erro) {
 erro.printStackTrace();
 }
 }
 
    
 public void atualizar(Conteudo ent) {
 Connection con = criaConexao();
 String sql = "update conteudo set " + "nome = ?," + 
        "observacao = ?," + 
        "data = ?," +
        "idMateria = ? where id = ?"; 
 
 try{
 PreparedStatement ps = con.prepareStatement(sql);
 ps.setString(1, ent.getNome());
 ps.setString(2, ent.getObservacao());
 ps.setDate(3, (Date) ent.getData());
 ps.setInt(4, ent.getIdMateria());
 ps.setInt(5, ent.getId());
 ps.execute();
 ps.close();
 con.close();
 } catch(Exception erro){
 erro.printStackTrace();
 }
 }
 
    
 public void remover(int id) {
 Connection con = criaConexao();
 String sql = "delete from conteudo where id = "+ id;
 
 try{
 con.createStatement().execute(sql);
 } catch(Exception erro){
 erro.printStackTrace();
 }
 }
 
    
 public List<Conteudo> consultar() {
 try{
 List<Conteudo> lista = new ArrayList<>();
 String sql = "select * from conteudo";
 Connection con = criaConexao();
 
 ResultSet res = con.createStatement().executeQuery(sql);
 
 while(res.next()){
 Conteudo c = new Conteudo();
 c.setId(res.getInt("id"));
 c.setNome(res.getString("nome"));
 c.setData(res.getDate("data"));
 c.setObservacao(res.getString("observacao"));
 c.setIdMateria(res.getInt("idMateria"));
 lista.add(c);
 }
 
 return lista;
 }catch(Exception erro){
 erro.printStackTrace();
 }
 return null;
 }
 
 
 }
