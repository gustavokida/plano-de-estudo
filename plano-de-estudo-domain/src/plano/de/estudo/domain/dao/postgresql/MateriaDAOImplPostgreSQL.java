
package plano.de.estudo.domain.dao.postgresql;
 
import plano.de.estudo.domain.dao.IMateriaDAO;
 import plano.de.estudo.domain.entidades.Materia;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.util.ArrayList;
 import java.util.List;
 
public class MateriaDAOImplPostgreSQL 
 implements IMateriaDAO {
 
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
 
    
 public void inserir(Materia ent) {
 Connection con = criaConexao();
 
 String sql = "insert into materia (nome, horario, cargaHoraria, media, aprovado, observacao)"
         + "VALUES ('" + ent.getNome() + "','"+ ent.getHorario()+ "','"+
         ent.getCargaHoraria()+"','"+ ent.getMedia()+
         "','"+ ent.isAprovado()+"','"+ ent.getObservacao()+ "')";
 
 try{
 con.createStatement().execute(sql);
 } catch (Exception erro) {
 erro.printStackTrace();
 }
 }
 
    
 public void atualizar(Materia ent) {
 Connection con = criaConexao();
 String sql = "update materia set " + "nome = ?," +
        "horario = ?," +
        "cargaHoraria = ?," +
        "media = ?," +
        "aprovado = ?," +
        "observacao = ? where id = ?";
 
 try{
 PreparedStatement ps = con.prepareStatement(sql);
 ps.setString(1, ent.getNome());
 ps.setTime(2, ent.getHorario());
 ps.setInt(3, ent.getCargaHoraria());
 ps.setFloat(4, ent.getMedia());
 ps.setBoolean(5, ent.isAprovado());
 ps.setString(6, ent.getObservacao());
 ps.setInt(7, ent.getId());
 ps.execute();
 ps.close();
 con.close();
 } catch(Exception erro){
 erro.printStackTrace();
 }
 }
 
    
 public void remover(int id) {
 Connection con = criaConexao();
 String sql = "delete from materia where id = "+ id;
 
 try{
 con.createStatement().execute(sql);
 } catch(Exception erro){
 erro.printStackTrace();
 }
 }
 
    
 public List<Materia> consultar() {
 try{
 List<Materia> lista = new ArrayList<>();
 String sql = "select * from materia";
 Connection con = criaConexao();
 
 ResultSet res = con.createStatement().executeQuery(sql);
 
 while(res.next()){
 Materia c = new Materia();
 c.setId(res.getInt("id"));
 c.setNome(res.getString("nome"));
 c.setHorario(res.getTime("horario"));
 c.setCargaHoraria(res.getInt("cargaHoraria"));
 c.setMedia(res.getFloat("media"));
 c.setAprovado(res.getBoolean("aprovado"));
 c.setObservacao(res.getString("observacao"));
 lista.add(c);
 }
 
 return lista;
 }catch(Exception erro){
 erro.printStackTrace();
 }
 return null;
 }
 
 
 }
