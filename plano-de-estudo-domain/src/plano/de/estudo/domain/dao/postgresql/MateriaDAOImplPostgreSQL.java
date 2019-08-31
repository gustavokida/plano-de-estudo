
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
 conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/planoestudo", "postgres", "123");
 } catch(Exception erro){
 erro.printStackTrace();
 }
 return conexao;
 }
 
    
 public void inserir(Materia ent) {
 Connection con = criaConexao();
 
 String sql = "insert into materia (nome)" + "VALUES ('" + ent.getNome() + "')" +
         "insert into materia (horario)" + "VALUES ('" + ent.getHorario()+ "')" + 
         "insert into materia (cargaHoraria)" + "VALUES ('" + ent.getCargaHoraria()+ "')" + 
         "insert into materia (media)" + "VALUES ('" + ent.getMedia()+ "')" +
         "insert into materia (aprovado)" + "VALUES ('" + ent.isAprovado()+ "')" +
         "insert into materia (observacao)" + "VALUES ('" + ent.getObservacao()+ "')" +
         "insert into materia (idUsuario)" + "VALUES ('" + ent.getIdUsuario()+ "')" ;
 
 try{
 con.createStatement().execute(sql);
 } catch (Exception erro) {
 erro.printStackTrace();
 }
 }
 
    
 public void atualizar(Materia ent) {
 Connection con = criaConexao();
 String sql = "update materia set " + "nome = ? where id = ?" +
         "update materia set " + "horario = ? where id = ?" +
         "update materia set " + "cargaHoraria = ? where id = ?" +
         "update materia set " + "media = ? where id = ?" +
         "update materia set " + "aprovado = ? where id = ?" +
         "update materia set " + "observacao = ? where id = ?" +
         "update materia set " + "idUsuario = ? where id = ?";
 
 try{
 PreparedStatement ps = con.prepareStatement(sql);
 ps.setString(1, ent.getNome());
 ps.setInt(2, ent.getId());
 ps.setTime(3, ent.getHorario());
 ps.setInt(4, ent.getId());
 ps.setInt(5, ent.getCargaHoraria());
 ps.setInt(6, ent.getId());
 ps.setFloat(7, ent.getMedia());
 ps.setInt(8, ent.getId());
 ps.setBoolean(9, ent.isAprovado());
 ps.setInt(10, ent.getId());
 ps.setString(11, ent.getObservacao());
 ps.setInt(12, ent.getId());
 ps.setInt(13, ent.getIdUsuario());
 ps.setInt(14, ent.getId());
 
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
 c.setIdUsuario(res.getInt("idUsuario"));
 lista.add(c);
 }
 
 return lista;
 }catch(Exception erro){
 erro.printStackTrace();
 }
 return null;
 }
 
 
 }
