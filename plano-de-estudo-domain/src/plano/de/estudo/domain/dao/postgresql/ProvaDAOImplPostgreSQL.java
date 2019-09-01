package plano.de.estudo.domain.dao.postgresql;

import plano.de.estudo.domain.dao.IProvaDAO;
import plano.de.estudo.domain.entidades.Prova;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProvaDAOImplPostgreSQL
        implements IProvaDAO {

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

    public void inserir(Prova ent) {
        Connection con = criaConexao();

        String sql = "insert into prova (nota, data, observacao, idConteudo)"
                + "VALUES ('" + ent.getNota() + "','" + ent.getData() + "','"
                + ent.getObservacao() + "','" + ent.getIdConteudo() + " '),";

        try {
            con.createStatement().execute(sql);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void atualizar(Prova ent) {
        Connection con = criaConexao();
        String sql = "update prova set " + "nota = ?,"
                + "data = ?,"
                + "observacao = ?,"
                + "idConteudo = ? where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, ent.getNota());
            ps.setDate(2, (Date) ent.getData());
            ps.setString(3, ent.getObservacao());
            ps.setInt(4, ent.getIdConteudo());
            ps.setInt(5, ent.getId());
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void remover(int id) {
        Connection con = criaConexao();
        String sql = "delete from prova where id = " + id;

        try {
            con.createStatement().execute(sql);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public List<Prova> consultar() {
        try {
            List<Prova> lista = new ArrayList<>();
            String sql = "select * from prova";
            Connection con = criaConexao();

            ResultSet res = con.createStatement().executeQuery(sql);

            while (res.next()) {
                Prova c = new Prova();
                c.setId(res.getInt("id"));
                c.setData(res.getDate("data"));
                c.setNota(res.getFloat("nota"));
                c.setObservacao(res.getString("nome"));
                c.setIdConteudo(res.getInt("idConteudo"));
                lista.add(c);
            }

            return lista;
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return null;
    }

}
