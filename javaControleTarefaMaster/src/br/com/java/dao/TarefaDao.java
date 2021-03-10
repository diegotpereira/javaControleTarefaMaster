package br.com.java.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.ResultSet;
import br.com.java.connection.ConnectionFactory;
import br.com.java.model.Tarefa;

public class TarefaDao {
    
	private Connection connection;
	
	public TarefaDao() {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public void adiciona(Tarefa tarefa) {
		// TODO Auto-generated method stub
		String sql = "insert into tarefas (descricao, finalizado) values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Tarefa> getListaTarefa(){
		
		try {
			List<Tarefa> listaTarefa = new ArrayList<>();
			
			String sql = "SELECT * FROM Tarefas";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.beforeFirst();
			
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				
				if (rs.getString("dataFinalizacao") != null) {
					String dataFinalizacaoString = rs.getString("dataFinalizacao");
					DateFormat dataFinalizacaoDate = new SimpleDateFormat("yyy-MM-dd");
					Calendar dataFinalizacao = Calendar.getInstance();
					dataFinalizacao.setTime(dataFinalizacaoDate.parse(dataFinalizacaoString));
					
					tarefa.setDataFinalizacao(dataFinalizacao);
				}
				listaTarefa.add(tarefa);
			}
			
			rs.close();
			stmt.close();
			
			return listaTarefa;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public void remove(Tarefa tarefa) {
		
		if (tarefa.getId() == null) {
			throw new IllegalStateException("Id da tarefa não pode ser nulo.");
		}
		try {
			String sql = "DELETE FROM tarefas where id = ?";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public void altera(Tarefa tarefa) {
		String sql = "UPDATE tarefas SET descricao = ?, finalizado = ?, dataFinalizacao = ? WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, tarefa.getDataFinalizacao() != null ? new Date(tarefa.getDataFinalizacao().getTimeInMillis()): null);
			stmt.setLong(4, tarefa.getId());
			stmt.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public Tarefa buscaPorId(Long id) {
		
		String sql = "SELECT * FROM tarefas WHERE id = ?";
		if (id == null) {
			throw new IllegalStateException("id da tarefa não deve ser nulo.");
		}
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return populaTarefa(rs);
			}
			rs.close();
			stmt.close();
			
			return null;
		} catch (SQLException e2) {
			// TODO: handle exception
			throw new RuntimeException(e2);
		}
	}
	private Tarefa populaTarefa(ResultSet rs) throws SQLException{
		Tarefa tarefa = new Tarefa();
		
		tarefa.setId(rs.getLong("id"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setFinalizado(rs.getBoolean("finalizado"));
		
		Date data = rs.getDate("dataFinalizacao");
		
		if (data != null) {
			Calendar dataFinalizacao = Calendar.getInstance();
			dataFinalizacao.setTime(data);tarefa.setDataFinalizacao(dataFinalizacao);
		}
		return tarefa;
	}
	public void finaliza(Long id) {
		String sql = "UPDATE tarefas SET finalizado = ?, dataFinalizacao = ? WHERE id = ?";
		
		if (id == null) {
			throw new IllegalStateException("Id da tarefa não deve ser nulo.");
		}
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
