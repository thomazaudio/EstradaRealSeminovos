package Controle;

import javax.faces.bean.ManagedBean;

import Modelo.ItemDAO;
import util.Item;

@ManagedBean
public class ItemBean {

	
	 private String item;
     private String tipo="0";
     
	
      public String getItem() {
		return item;
	}
     
	public void setItem(String item) {
		this.item = item;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void upcase(){
		
		this.item = this.item.toUpperCase();
	}
	
	public void cadastra(){
		
		Item it =  new Item();
		item  = item.toUpperCase();
		it.setItem(item);
		it.setTipo(Integer.parseInt(tipo));
		
		new ItemDAO().insert(it);
		
	}
	
}
