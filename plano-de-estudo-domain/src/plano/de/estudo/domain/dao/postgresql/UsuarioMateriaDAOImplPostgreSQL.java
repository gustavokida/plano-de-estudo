package plano.de.estudo.domain.dao.postgresql;

import plano.de.estudo.domain.dao.IUsuarioMateriaDAO;
import plano.de.estudo.domain.entidades.UsuarioMateria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioMateriaDAOImplPostgreSQL
        implements IUsuarioMateriaDAO {

    private Connection criaConexao() {
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/plano", "postgres", "123");
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return conexao;
    }

    public void inserir(UsuarioMateria ent) {
        Connection con = criaConexao();

        String sql = "insert into usuarioMateria (idUsuario, idMateria)"
                + "VALUES ('" + ent.getIdUsuario() + "','" + ent.getIdMateria() + "')";

        try {
            con.createStatement().execute(sql);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void atualizar(UsuarioMateria ent) {
        Connection con = criaConexao();
        String sql = "update usuarioMateria set " + "idUsuario = ?,"
                + "idMateria = ? where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ent.getIdUsuario());
            ps.setInt(2, ent.getIdMateria());
            ps.setInt(3, ent.getId());
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void remover(int id) {
        Connection con = criaConexao();
        String sql = "delete from usuarioMateria where id = " + id;

        try {
            con.createStatement().execute(sql);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public List<UsuarioMateria> consultar() {
        try {
            List<UsuarioMateria> lista = new ArrayList<>();
            String sql = "select * from usuarioMateria";
            Connection con = criaConexao();

            ResultSet res = con.createStatement().executeQuery(sql);

            while (res.next()) {
                UsuarioMateria c = new UsuarioMateria();
                c.setId(res.getInt("id"));
                c.setIdUsuario(res.getInt("idUsuario"));
                c.setIdMateria(res.getInt("idMateria"));

                lista.add(c);
            }

            return lista;
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return null;
    }

}
