package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;




import org.hibernate.criterion.Restrictions;

import util.Carro;
import util.Debug;
import util.ItemVeiculo;
import util.Pagamento;
import util.Paginacao;
import util.Veiculo;


public class VeiculoDAO {


	private SessionFactory factory;


	public VeiculoDAO(){


		factory = HibernateUtil.getSessaoV();
	}

	public void insert(Veiculo v){

		Session sessao =  factory.openSession();
		sessao.save(v);
		sessao.flush();
		sessao.close();

	}
	
	public ArrayList<Veiculo> getAllVeiculos(){
		

		ArrayList<Veiculo> vs;
			
			Session sessao = HibernateUtil.getSessaoV().openSession();
			
			vs = (ArrayList<Veiculo>) sessao.createCriteria(Veiculo.class).list();
			
			sessao.flush();
			sessao.close();
			
	 return vs;
	}
	
	public void saveOrUpdate(Veiculo v){
		
		Session sessao =  factory.openSession();
		sessao.saveOrUpdate(v);
		sessao.flush();
		sessao.close();
		
	}
	
	

	public boolean update(Veiculo veiculo) {


		try{
			Session sessao =  factory.openSession();
			sessao.merge(veiculo);
			sessao.flush();
			sessao.close();
			return true;
		}catch(Exception e){

			Debug.gerar("util","VeiculoDAO","update", e.getMessage());	  
			return false;	  
		}
	}

	/**
	 * 
	 * @param veiculo
	 */
	public boolean delete(Veiculo veiculo) {

		try{
			Session sessao =  factory.openSession();
			sessao.delete(veiculo);
			sessao.flush();
			sessao.close();	
            return true;
		}catch(Exception e){
   
			Debug.gerar("Modelo","VeiculoIO","delete", e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param pagina
	 * @param quant_por_pagina
	 * @param classe
	 * @param id_cidade
	 * @param preco_de
	 * @param preco_ate
	 * @param ano_de
	 * @param ano_ate
	 * @param revenda
	 * @param particular
	 * @param troca
	 * @param particular
	 * @param troca
	 */
	    
	    /*
		public ArrayList<Object> getVeiculos(int pagina, int quant_por_pagina, Class classe, long particular, long troca, int id_cidade, double preco_de, double preco_ate, double ano_de, double ano_ate, boolean revenda, boolean particular, boolean troca) {
			throw new UnsupportedOperationException();
		}
	 */

	/**
	 * 
	 * @param id
	 * @param classe
	 */
	
	//Classe para teste de paginação
	public List testePaginacao(int quant_reg_pagina){
		
		
		ArrayList<Carro> n_lista;
		Session sessao = factory.openSession();
		Criteria cri =  sessao.createCriteria(Carro.class);
		ArrayList lista = (ArrayList<Carro>) cri.list();
		System.out.println("Tamanho da lista: "+lista.size());
		
		Paginacao p =  new Paginacao(lista,quant_reg_pagina);
		
		int total_paginas = p.getTotalPaginas();
		
	
		
		for(int i=1;i<=total_paginas;i++)
		{
			System.out.println("Pagina: "+i);
			n_lista = (ArrayList<Carro>) p.getPaginaElements(i);
			
			for(int j=0;j<n_lista.size();j++)
			{
			  System.out.printf("%d: %s\n",n_lista.get(j).getId(),n_lista.get(j).getNome());	
			}
			
			
		}	
		
		return lista;
	}
	
	public Object getVeiculo(long id, Class classe) {
		
		Session sessao =  factory.openSession();
		Criteria  cri = sessao.createCriteria(classe);
		cri.add(Restrictions.eq("id",id));
		return cri.uniqueResult();
	}
	
	
	
	
	//Recupera todas as marcas de carro
	public ArrayList<ItemVeiculo> getAllMarcasCarro(){
		
		ArrayList<ItemVeiculo> itens = new ArrayList<ItemVeiculo>();
		ItemVeiculo item= null;
		
		try{
			

		
			Connection con =  Banco.abreBanco();
			Statement stm = con.createStatement();
			ResultSet res =  stm.executeQuery("SELECT * FROM marca");
			
			while(res.next()){
			
			 item = new ItemVeiculo();
			 item.setCod(res.getInt("id"));
			 item.setNome(res.getString("nome"));
			 itens.add(item);
			 
				
			}
			
			stm.close();
			res.close();
			
		}catch(Exception e){
			
			Debug.gerar("util", "VeiculoDAO", "getAllModelos", e.getMessage());
		}
		
		return itens;
		
	}
	
	
	
	
	//PARA BUSCA
	public ArrayList<Veiculo> getVeiculosBusca(int cod_fab,int cod_modelo,int ano_de,int ano_ate,int qui_de,int aqui_ate,int tipo_venda,double preco_de,double preco_ate)
	{	
		ArrayList<Veiculo> veiculos;
		Session sessao;
		Criteria cri;
		
		sessao = HibernateUtil.getSessaoV().openSession();
		cri = sessao.createCriteria(Veiculo.class);
		
		//Somente os veiculos que estão validados
		cri.add(Restrictions.eq("statusValidacao",Pagamento.VALIDACAO_OK));
		
		
		cri.add(Restrictions.eq("statusPagamento",Pagamento.CONFIRMADO));
		
		//Somente anuncios pagos
		
		if(cod_fab!=0)
		cri.add(Restrictions.eq("cod_fabricante",cod_fab));
		//Eqs 
		if(cod_modelo!=0)
		cri.add(Restrictions.eq("codModelo", cod_modelo));
		
		//Ano
		if(ano_ate!=0)
		cri.add(Restrictions.le("anoModelo",ano_ate));	
		
		cri.add(Restrictions.ge("anoFabricacao",ano_de));
		
		//Preço
		cri.add(Restrictions.ge("preco",preco_de));
		
		if(preco_ate!=0)
		cri.add(Restrictions.le("preco",preco_ate));
		
		if(tipo_venda!=3)
		cri.add(Restrictions.eq("tipoVenda", tipo_venda));	
		
		
		veiculos = (ArrayList<Veiculo>) cri.list();
		
		
		
		return veiculos;
		
	}
	
	//Recupera todos os modelo de veiculo a partir de um fabricante
	public ArrayList<ItemVeiculo> getAllModelos(int fab){
		
		ArrayList<ItemVeiculo> itens = new ArrayList<ItemVeiculo>();
		ItemVeiculo item= null;
		
		try{
			
			
		
			Connection con =  Banco.abreBanco();
			Statement stm = con.createStatement();
			ResultSet res =  stm.executeQuery("SELECT * FROM modelo WHERE marca="+fab+"");
			
			while(res.next()){
			
				
				
			 	
			 item = new ItemVeiculo();
			 item.setCod(res.getInt("id"));
			 item.setNome(res.getString("nome"));
			 itens.add(item);
			 System.out.println("Codigo do veiculo="+item.getCod());
				
			}
			
			stm.close();
			res.close();
			
		}catch(Exception e){
			
			Debug.gerar("util", "VeiculoDAO", "getAllModelos", e.getMessage());
		}
		
		return itens;
		
	}
	
	
	public void inserirModelos(ArrayList<String> modelos,int marca){
		
		
		try{
			
			Connection con =  Banco.abreBanco();
			PreparedStatement stm =  con.prepareStatement("INSERT INTO modelo(nome,marca) VALUES(?,?)");
		
			
			for(int i=0;i<modelos.size();i++)
			{
				
			stm.setString(1,modelos.get(i));	
			stm.setInt(2,marca);	
			stm.execute();
			}
			
		}catch(Exception e){
			
		}
	}
	
	
	//Recupera o modelo a partir do codigo
	public String getNomeModelo(long cod){
		
		
		try{
			
			Connection con = Banco.abreBanco();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM modelo WHERE id="+cod);
			
			if(res.next())
			return res.getString("nome");
			else
			return "---";	
			
		}catch(Exception e){
			
			Debug.gerar("Modelo", "VeiculoDAO", "getNomeModelo", e.getMessage());
			return "---";
		}
	}
	
	
	//Recupera o fabricante a partir do codigo
		public String getNomeFabricante(long cod){
			
			
			try{
				
				Connection con = Banco.abreBanco();
				Statement stm = con.createStatement();
				ResultSet res = stm.executeQuery("SELECT * FROM marca WHERE id="+cod);
				
				if(res.next())
				return res.getString("nome");
				else
				return "---";	
				
			}catch(Exception e){
				
				Debug.gerar("Modelo", "VeiculoDAO", "getNomeFabricante", e.getMessage());
				return "---";
			}
		}
	
	
	public String getTipo(long id_veiculo){
		
		

		try{
			
			Connection con = Banco.abreBanco();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM veiculo WHERE ID_VEICULO="+id_veiculo);
			
			if(res.next())
			return res.getString("TIPO_VEICULO");
			else
			return "---";	
			
		}catch(Exception e){
			
			Debug.gerar("Modelo", "VeiculoDAO", "getTipo", e.getMessage());
			return "---";
		}
		
	}
	
}
