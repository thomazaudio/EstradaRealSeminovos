package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean(name="beanUtil")
public class BeanUtil {

	
	private String page;

	public String getPage() {
		
		page=  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page_pos");

		return page;
	}

	public void setPagepos(String pagepos) {
		
		this.page = pagepos;
	}
	
  
	
	
}
