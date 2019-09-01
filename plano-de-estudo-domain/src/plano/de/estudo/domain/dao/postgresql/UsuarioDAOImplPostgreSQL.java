
package plano.de.estudo.domain.dao.postgresql;
 
import plano.de.estudo.domain.dao.IUsuarioDAO;
 import plano.de.estudo.domain.entidades.Usuario;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.util.ArrayList;
 import java.util.List;
 
public class UsuarioDAOImplPostgreSQL 
 implements IUsuarioDAO {
 
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
 
    
 public void inserir(Usuario ent) {
 Connection con = criaConexao();
 
 String sql = "insert into usuario (nome, email, celular)" + "VALUES ('"
         + ent.getNome() +"','"+ ent.getEmail()+ "','" + ent.getCelular()+ "');";
 
 try{
 con.createStatement().execute(sql);
 } catch (Exception erro) {
 erro.printStackTrace();
 }
 }
 
    
 public void atualizar(Usuario ent) {
 Connection con = criaConexao();
 String sql = "update usuario set " + "nome = ?," +
        " email = ?," +
        " celular = ? where id = ?" ;
 
 try{
 PreparedStatement ps = con.prepareStatement(sql);
 ps.setString(1, ent.getNome());
 ps.setString(2, ent.getEmail());
 ps.setString(3, ent.getCelular());
 ps.setInt(4, ent.getId());
 ps.execute();
 ps.close();
 con.close();
 } catch(Exception erro){
 erro.printStackTrace();
 }
 }
 
    
 public void remover(int id) {
 Connection con = criaConexao();
 String sql = "delete from usuario where id = "+ id;
 
 try{
 con.createStatement().execute(sql);
 } catch(Exception erro){
 erro.printStackTrace();
 }
 }
 
    
 public List<Usuario> consultar() {
 try{
 List<Usuario> lista = new ArrayList<>();
 String sql = "select * from usuario";
 Connection con = criaConexao();
 
 ResultSet res = con.createStatement().executeQuery(sql);
 
 while(res.next()){
 Usuario c = new Usuario();
 c.setId(res.getInt("id"));
 c.setNome(res.getString("nome"));
 c.setEmail(res.getString("email"));
 c.setCelular(res.getString("celular"));
 lista.add(c);
 }
 
 return lista;
 }catch(Exception erro){
 erro.printStackTrace();
 }
 return null;
 }
 
 
 }
