package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
//		PreparedStatement stmt;
		try {
//			stmt = connection.prepareStatement(sql);
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
