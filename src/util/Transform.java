package util;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.CharBuffer;
import java.util.ArrayList;


public class Transform {

	private char[] tudo = new char[5048];
	
	
	public  void ler(String marca){
		
		try {
			
			
			StringBuffer buffer = new StringBuffer();
			
			FileReader  file = new FileReader(marca+".txt");
			
			BufferedReader b = new BufferedReader(file);
			
			while(b.ready())
			buffer.append(b.readLine());
			
			
			tudo = buffer.toString().toCharArray();
			
			file.close();
			b.close();
			
			
			
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public char[] getTudo() {
		return tudo;
	}

	public void setTudo(char[] tudo) {
		this.tudo = tudo;
	}
	
	
	ArrayList<String> getPalavras(){
		
	  ArrayList<String> lista = new ArrayList<String>();
	     
	    int i,j,k,tam;
	    
	    char palavra[];
	    
	    System.out.println("Tamanho de tudo: "+tudo.length);
	    
	    i=0;
	   for(i=0;i<tudo.length;i++)
	   {
	      
	       while(tudo[i]!='>' && i<tudo.length-1)
	       i++;
	       
	       i++;
	      
	       
	       k=0;
	       
	       tam = 0;
	       
	       j=i;
	       while( j<(tudo.length-1) &&(tudo[j]!='<'))
	       {
	    	   j++;
	    	   tam++;
	       }
	       
	       
	       palavra = new char[tam+1];
	       while( i<(tudo.length-1) &&(tudo[i]!='<'))
	       {
	      
	    	   
	       palavra[k]=tudo[i];
	      
	      
	       k++;
	       i++;	
	    
	       }
	       
	       palavra[k]='\0';
	    
	       
	      
	       
	       if(palavra.length>2)
	       lista.add(String.copyValueOf(palavra));
	     
	    
	     
	     
	      
	     
	    }
	  
	    
	       System.out.println("Palavra: "+lista);
		return lista;
	}
	
	
}
