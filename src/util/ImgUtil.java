package util;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;


public class ImgUtil {
	
	
	public static final int WIDTH_TUMB=240;
	public static final int HEIGHT_TUMB=220;
	
	
	//Banner destaque
	public static final int MIN_WIDTH_BANNER=900;
	public static final int MIN_HEIGHT_BANNER=450;
	
	
	
	//Altera o tamanho de uma imagem
	
	public InputStream alteraTamanho(InputStream img_original,int w, int h) throws IOException{
		
		
		
		
		 BufferedImage img = Scalr.resize(ImageIO.read(img_original), Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC,
                             w, h, Scalr.OP_ANTIALIAS);
		 
		 
		
		
		 
		 ByteArrayOutputStream os = new ByteArrayOutputStream();
		 ImageIO.write(img, "jpg", os);
		 InputStream is = new ByteArrayInputStream(os.toByteArray());
		 
	  
	   return   is;
	}
	
	
	
	
	
	
	
	//Valida a resolu��o minima de uma imagem de banner
	public boolean validaResolucaoImgBanner(InputStream img) throws IOException{
		
		 BufferedImage buffe_img = ImageIO.read(img);
		 
		 int h = buffe_img.getHeight();
		 int w = buffe_img.getWidth();
		 
		 //Teste h
		 if(h<MIN_HEIGHT_BANNER)
		 return false;	 
		 
		 //Teste w
		 if(w<MIN_WIDTH_BANNER)
		 return false;
		 
		 return true;
		 
		 
		
	}
	
	
	
	
	
	

}
