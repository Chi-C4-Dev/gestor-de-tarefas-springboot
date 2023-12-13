package chinhala.mesack.GestordeTarefas.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;



public class Utils {
	
	// pega tuda propiedade nulla e atribue dentro do beanutis copyproperty para mesclar os dados
	public static void copyNonNulllProperties(Object source, Object target) {
		
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}
	
	
//este método cria um objecto e pega todos as propriedades nulas, as propriedades que neste caso não foram editadas pelo usuário
public static String[] getNullPropertyNames(Object source) {
	final BeanWrapper src = new BeanWrapperImpl(source);
	
	PropertyDescriptor[] pds = src.getPropertyDescriptors();
	
	Set<String> emptyNames = new HashSet<>();
	
	for(PropertyDescriptor pd: pds) {
		
		Object srcValue = src.getPropertyValue(pd.getName());
		if(srcValue == null) {
			emptyNames.add(pd.getName());
		}
	}
	
	
	String [] result = new String[emptyNames.size()];
	
	return emptyNames.toArray(result);
}
	
}
