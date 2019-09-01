package plano.de.estudo.domain.dao.postgresql;

import plano.de.estudo.domain.dao.ITarefasDAO;
import plano.de.estudo.domain.entidades.Tarefas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TarefasDAOImplPostgreSQL
        implements ITarefasDAO {

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

    public void inserir(Tarefas ent) {
        Connection con = criaConexao();

        String sql = "insert into tarefas (observacao,data,feito,importante,dataEntrega,idConteudo)"
                + "VALUES ('" + ent.getObservacao() + "','" + ent.getData() + "','" + ent.isFeito()
                + "','" + ent.isImportante() + "','" + ent.getDataEntrega()
                + "','" + ent.getIdConteudo() + "')";

        try {
            con.createStatement().execute(sql);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void atualizar(Tarefas ent) {
        Connection con = criaConexao();
        String sql = "update tarefas set " + "observacao = ?,"
                + "data = ?,"
                + "feito = ?,"
                + "importante = ?,"
                + "dataEntrega = ?,"
                + "idConteudo = ? where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ent.getObservacao());
            ps.setDate(2, (Date) ent.getData());
            ps.setBoolean(3, ent.isFeito());
            ps.setBoolean(4, ent.isImportante());
            ps.setDate(5, (Date) ent.getDataEntrega());
            ps.setInt(6, ent.getIdConteudo());
            ps.setInt(7, ent.getId());
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void remover(int id) {
        Connection con = criaConexao();
        String sql = "delete from tarefas where id = " + id;

        try {
            con.createStatement().execute(sql);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public List<Tarefas> consultar() {
        try {
            List<Tarefas> lista = new ArrayList<>();
            String sql = "select * from tarefas";
            Connection con = criaConexao();

            ResultSet res = con.createStatement().executeQuery(sql);

            while (res.next()) {
                Tarefas c = new Tarefas();
                c.setId(res.getInt("id"));
                c.setObservacao(res.getString("observacao"));
                c.setData(res.getDate("data"));
                c.setFeito(res.getBoolean("feito"));
                c.setImportante(res.getBoolean("importante"));
                c.setDataEntrega(res.getDate("dataEntrega"));
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
