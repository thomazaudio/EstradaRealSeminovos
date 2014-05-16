package util;

import java.util.ArrayList;
import java.util.List;

public class Paginacao {

	
	private long total_registros;
    private int n_registro_por_pagina;
	private List<Object> lista;
	
	public long getTotal_registros() {
		return total_registros;
	}

	public void setTotal_registros(long total_registros) {
		this.total_registros = total_registros;
	}

	public int getN_registro_por_pagina() {
		return n_registro_por_pagina;
	}

	public void setN_registro_por_pagina(int n_registro_por_pagina) {
		this.n_registro_por_pagina = n_registro_por_pagina;
	}
    

	/**
	 * 
	 * @param lista
	 * @param total_registros
	 * @param pagina_index
	 * @param n_registro_por_pagina
	 */

	
	//Pré-teste->>OK
	public Paginacao(List<Object> lista,int n_registro_por_pagina) {

		this.lista = lista;
		this.setTotal_registros(lista.size());
		this.setN_registro_por_pagina(n_registro_por_pagina);

	}
	
	//Contrutor mais aprimorado
	public Paginacao(long total_registros,int n_registro_por_pagina){
		
		this.setTotal_registros(total_registros);
		this.setN_registro_por_pagina(n_registro_por_pagina);
	}

	
	//OK
	public long getTotalPaginas(){
		
		
		

		long total=0;
		
		if( ( this.getTotal_registros()<this.getN_registro_por_pagina()) && this.getTotal_registros()!=0 )
		return 1;	
		
		else
		{
		long resto = this.getTotal_registros()%this.getN_registro_por_pagina();	
		total= this.getTotal_registros()/this.getN_registro_por_pagina();
		if(resto!=0)
		total++;
		return total;
		
		
		}
	
		
		
	}

	
	//OK
	public int getIndexIni(int pagina){

		int index = ( (pagina * n_registro_por_pagina) - (n_registro_por_pagina -1))-1;

		
	
		return index;
	}
	
	
	//Se não funcionar retirar o "@SuppressWarnings("rawtypes")"
	public List getPaginaElements(int pagina){
		
		ArrayList<Object> n_lista =  new ArrayList<Object>();
		
		int count = 0;
		int index = getIndexIni(pagina);

		for(int i= index;(count<total_registros && i!=total_registros && count<n_registro_por_pagina);i++)
		{   
			
            n_lista.add(lista.get(i));
			count++;
		}
  
		return n_lista;
	}
	
	
	public ArrayList<Pagina> refactorPaginacao(ArrayList<Pagina> paginas,int atual_page){
		
		
		ArrayList<Pagina> n_lista =  new ArrayList<Pagina>();
		
		int j=0;
		int pos_ini = atual_page -5;
		
			
		
		if(pos_ini<0)
			pos_ini=0;
		
		//Elementos anteriores
		for(int i=atual_page;(i>=0 && j<5 && pos_ini<paginas.size());i--)
		{
				
		n_lista.add(paginas.get(pos_ini)); 	
		j++;
		pos_ini++;
					
		}
					
		j=0;
		//Elementos posteriores
		for(int i=atual_page+1;(i<paginas.size() && (j<5 && j<paginas.size() ));i++)
		{
		n_lista.add(paginas.get(i)); 	
		j++;
		}
		
		
		
			
		
		return n_lista;
	}

	

}