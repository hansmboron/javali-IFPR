package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Model.Estaciona;

public class EstacionamentoDAO {
	public List<Estaciona> getEstaciona(boolean allVeicles) throws ClassNotFoundException, SQLException {
		Connection conexao = ConexaoJDBC.getConexao();
		String sql = "";
				
		if (allVeicles) {
			sql = "SELECT id, entrada, placa, saida, pagamento, livre FROM vagas ORDER BY id DESC";
		} else {
			sql = "SELECT id, entrada, placa, saida, pagamento, livre FROM vagas WHERE livre = 0 ORDER BY id DESC";
		}
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Estaciona> estacionamentos = new ArrayList<Estaciona>();
		while (rs.next()) {
			estacionamentos.add(new Estaciona(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getTimestamp(4),
					rs.getInt(5), rs.getInt(6)));
		}
		return estacionamentos;
	}

	public static Estaciona getEstacionaById(Integer id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConexaoJDBC.getConexao();
		PreparedStatement ps = conexao.prepareStatement(
				"select id, entrada, placa, saida, pagamento, livre from estacionamento.vagas WHERE id = ? LIMIT 1");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Estaciona estaciona = new Estaciona();
		while (rs.next()) {
			estaciona = new Estaciona(rs.getInt(1), rs.getTimestamp(2), rs.getString(3), rs.getTimestamp(4),
					rs.getInt(5), rs.getInt(6));
		}
		return estaciona;
	}

	public static boolean salvar(Estaciona estaciona) throws ClassNotFoundException, SQLException {
		Connection conexao = ConexaoJDBC.getConexao();
		String sql = "INSERT INTO vagas (placa, pagamento, livre) VALUES (?, ?, ?)";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, estaciona.getPlaca());
		ps.setInt(2, 0);
		ps.setInt(3, 0);
		return ps.execute();
	}
	
	public static boolean updatePrice(Estaciona estaciona) throws ClassNotFoundException, SQLException {
		Connection conexao = ConexaoJDBC.getConexao();
		String sql = "UPDATE vagas SET pagamento = ? WHERE id = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, estaciona.getPagamento());
		ps.setInt(2, estaciona.getId());
		return ps.execute();
	}
	
	public static boolean confirmOut(Estaciona estaciona) throws ClassNotFoundException, SQLException {
		Connection conexao = ConexaoJDBC.getConexao();
		String sql = "UPDATE vagas SET saida = current_timestamp(), livre = ? WHERE id = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, 1);
		ps.setInt(2, estaciona.getId());
		return ps.execute();
	}
}
