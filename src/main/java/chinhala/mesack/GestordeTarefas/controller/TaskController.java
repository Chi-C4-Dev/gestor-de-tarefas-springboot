package chinhala.mesack.GestordeTarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import chinhala.mesack.GestordeTarefas.model.ModelTask;
import chinhala.mesack.GestordeTarefas.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class TaskController {
	

	@Autowired
	TaskRepository repo;
	

	@GetMapping("/create")
	public ModelAndView home() {
		
		ModelAndView mv = new ModelAndView("criartarefa");
		mv.addObject("task", new ModelTask());
		return mv;
	}
	
	
	@PostMapping("/create")
	public String criar(ModelTask task, RedirectAttributes attributes) {
		var currentDate = LocalDateTime.now();
		var existentTask = repo.findByName(task.getName());
	
	if(task.getName().equals("") || task.getDate() == null)
	{
		attributes.addFlashAttribute("mensagemError", "Verifique os campos do formulário.");
		 return "redirect:/create";
	}
	
	if(existentTask != null) {
		attributes.addFlashAttribute("mensagemError", "Já existe uma tarefa com este nome.");
		return "redirect:/create";
	}
	
	if(currentDate.isAfter(task.getDate())) {
		
		// a variavel attributes é um objecto da classe redirectattributes que permite pegar uma mensagem de erro personalizada para ser renderizado de alguma forma na view
		 attributes.addFlashAttribute("mensagemError", "A data de execução deve ser maior que a data actual.");
		 return "redirect:/create";
	} 
	
	repo.save(task);
	return "redirect:/list";
	
	} 
	
	  
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("listartarefas");
		List<ModelTask> tasks = repo.findAll();
		mv.addObject("tasks", tasks);
	return mv;
	}
	  
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("editartarefa");
		ModelTask task = repo.findById(id);
	
		mv.addObject("task", task);	
		
		return mv;
	} 
	 
	@PostMapping("/edit")
	public String saveEdit( ModelTask task, RedirectAttributes attributes) {
		var currentDate = LocalDateTime.now();
		var existentTask = repo.findByName(task.getName());
	
	if(task.getName().equals("") || task.getDate() == null)
	{
		attributes.addFlashAttribute("mensagemError", "Verifique os campos do formulário.");
		 return "redirect:/create";
	}
	
	if(existentTask != null) {
		attributes.addFlashAttribute("mensagemError", "Já existe uma tarefa com este nome.");
		return "redirect:/create";
	} 
	
	if(currentDate.isAfter(task.getDate())) {
		
		// a variavel attributes é um objecto da classe redirectattributes que permite pegar uma mensagem de erro personalizada para ser renderizado de alguma forma na view
		 attributes.addFlashAttribute("mensagemError", "A data de execução deve ser maior que a data actual.");
		 return "redirect:/create";
	} 
		
		repo.save(task); 
		
		return "redirect:/list";
	}
	
 
	@GetMapping("/delete/{name}")
	public String delete(@PathVariable("name") String name, ModelTask task) {
		task = repo.findByName(name);
		 
		if(task != null) {
			repo.delete(task);
		}
		 
		return "redirect:/list";
	}
	
}
