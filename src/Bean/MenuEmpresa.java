package Bean;

import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;  

import org.primefaces.model.menu.DefaultMenuItem;  
import org.primefaces.model.menu.DefaultMenuModel;  
import org.primefaces.model.menu.DefaultSubMenu;  
import org.primefaces.model.menu.MenuModel;  

@ManagedBean(name="menuEmpresa")
public class MenuEmpresa {

	private MenuModel model;  

    
	public MenuEmpresa() {  
		model = new DefaultMenuModel();  

		//First submenu  
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Opções para concessionária");  

		DefaultMenuItem item = new DefaultMenuItem("External");  
		item.setUrl("http://www.primefaces.org");  
		item.setIcon("ui-icon-home");  
		firstSubmenu.addElement(item);  

		model.addElement(firstSubmenu);  

		//Second submenu  
		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");  

		item = new DefaultMenuItem("Save");  
		item.setIcon("ui-icon-disk");  
		item.setCommand("#{menuBean.save}");  
		item.setUpdate("messages");  
		secondSubmenu.addElement(item);  

		item = new DefaultMenuItem("Delete");  
		item.setIcon("ui-icon-close");  
		item.setCommand("#{menuBean.delete}");  
		item.setAjax(false);  
		secondSubmenu.addElement(item);  

		item = new DefaultMenuItem("Redirect");  
		item.setIcon("ui-icon-search");  
		item.setCommand("#{menuBean.redirect}");  
		secondSubmenu.addElement(item);  

		model.addElement(secondSubmenu);  
	}  

	public MenuModel getModel() {  
		return model;  
	}     

	public void save() {  
		addMessage("Data saved");  
	}  

	public void update() {  
		addMessage("Data updated");  
	}  

	public void delete() {  
		addMessage("Data deleted");  
	}  

	public String redirect() {  
		return "home?faces-redirect=true";  
	}  

	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	} 
}
