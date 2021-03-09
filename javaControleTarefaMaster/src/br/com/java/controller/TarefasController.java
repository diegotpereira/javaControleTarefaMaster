package br.com.java.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.java.dao.TarefaDao;
import br.com.java.model.Tarefa;

@Controller
public class TarefasController {
	
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		TarefaDao dao = new TarefaDao();
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}
	@RequestMapping("listaTarefa")
	public String lista(Model model) {
		TarefaDao dao = new TarefaDao();
		model.addAttribute("tarefas", dao.getListaTarefa());
		return "tarefa/lista";
	}
	
}
