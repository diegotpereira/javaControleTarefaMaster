package br.com.java.dao;

import java.sql.Connection;
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

}
