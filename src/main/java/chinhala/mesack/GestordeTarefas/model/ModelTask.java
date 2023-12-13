package chinhala.mesack.GestordeTarefas.model;
import java.time.LocalDateTime;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="tarefas")
public class ModelTask {
//	
//	public ModelTask(Long id, String name, Date date) {
//		this.id = id;
//		this.name = name;
//		this.date = date;
//	}
//	
//	public ModelTask() {
//		 
//	}  
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	//private Date date;
	private LocalDateTime date;

}
 