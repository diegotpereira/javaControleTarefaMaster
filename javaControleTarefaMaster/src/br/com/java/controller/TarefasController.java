package br.com.java.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa, HttpServletResponse response) {
		TarefaDao dao = new TarefaDao();
		dao.remove(tarefa);
		response.setStatus(200);
		return "redirect:listaTarefa";	
	}
	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		TarefaDao dao = new TarefaDao();
		dao.altera(tarefa);
		return "redirect:listaTarefa";
	}
	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		TarefaDao dao = new TarefaDao();
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra";
	}
	@ResponseBody
	@RequestMapping("finalizaTarefa")
	public void finalizar(Long id) {
		TarefaDao  dao = new TarefaDao();
		dao.finaliza(id);
	}
}
