package Bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;



@ManagedBean(name="uploadBean")
public class UploadBean {

	
    public void uploadLogo(FileUploadEvent event) {
        try {
        	
        	
        	
        	System.out.println("Chegou aqui");
        	
        	FacesMessage f = new FacesMessage();
        	f.setSeverity(FacesMessage.SEVERITY_ERROR);
        	f.setSummary("Id: "+new UsuarioBean().getUser().getId());
        	
        	
        	FacesContext.getCurrentInstance().addMessage(null,f);
        	
        	
        	
            File targetFolder = new File("C:/images");
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
