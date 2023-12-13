package chinhala.mesack.GestordeTarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import chinhala.mesack.GestordeTarefas.model.ModelTask;

public interface TaskRepository extends JpaRepository<ModelTask, Long> {
	
	//listar tarefas
	List<ModelTask> findAll();
	  
	
	//pesquisar produto por id
	ModelTask findById(long id);
	
	//pesquisar pelo nome da tarefa
	ModelTask findByName(String name);
	
	//remover
	void delete(ModelTask task);
	
	//salvar/alterar
	//ModelTask save(ModelTask task);
	<TM extends ModelTask> TM save(TM task);

	@Override
	default void deleteById(Long id) {
		
		
	}
}
