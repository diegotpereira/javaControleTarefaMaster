package br.com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.java.connection.ConnectionFactory;
import br.com.java.model.Usuario;

public class UsuarioDao {
	
private Connection connection;
	
	public UsuarioDao() {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	public boolean existeUsuario(Usuario usuario) {
		
		String sql = "SELECT * FROM usuarios WHERE login = ? and senha = ?";
		if (usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo!.");
		}
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			
			ResultSet rs = stmt.executeQuery();
			
			boolean encontrado = rs.next();
			rs.close();
			stmt.close();
			
			return encontrado;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
