package util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Transform {

	private char[] tudo = new char[5048];
	
	
	public Transform(String marca){
		this.ler(marca);		
	}
	
	
	public  void ler(String marca){
		
		try {
			
			
			StringBuffer buffer = new StringBuffer();
			
			FileReader  file = new FileReader(marca);
			
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
	
	
	public String getSQLInsertModeloCarro(long marca){
		
		String sql ="INSERT INTO modelo_moto(nome, marca) VALUES ";
		
		ArrayList<String> palavras =  getPalavras();
		
		for(int i=0;i<palavras.size();i++){
			
			String palavra = palavras.get(i);
			
			if(!palavras.get(i).contains("Escolha") && palavra.length()>1)
			{
			sql+="('"+palavra+"',"+marca+")";
			
			if(i<(palavras.size()-2))
				sql+=",";
			}
			
			System.out.println("I:"+i);
			System.out.println("Tamanho: "+palavras.size());
			
			
			
		
		}
		
		sql+=";";
		
		return sql;
		
	}
	
}
