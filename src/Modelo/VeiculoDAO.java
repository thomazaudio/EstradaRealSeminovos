package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;




import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import util.Anuncio;
import util.Carro;
import util.Comunicacao;
import util.Debug;
import util.ItemVeiculo;
import util.Ordenacao;
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
		Transaction tx = sessao.beginTransaction(); 
		sessao.save(v);
		tx.commit();
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
		Transaction tx = sessao.beginTransaction(); 
		sessao.saveOrUpdate(v);
		tx.commit();
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
	
	
	//Desativa um veículo
	public void desativaVeiculo(long id_veiculo){
		

		try{
			
			Connection con =  Banco.abreBanco();
			Statement  stm =  con.createStatement();
			stm.executeUpdate("UPDATE veiculo set STATUS=0 WHERE ID_VEICULO="+id_veiculo);
			
			stm.close();
			
					
			
			//Envia a mensagem para o usuário dizendo que o anuncio doi desativado
			new Comunicacao().sendMensagemAnuncioDesativado(id_veiculo);
			
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","DestaqueDAO","desativaVeiculo", e.getMessage());
			
		}
		
		
	}
	
	
		//Ativa
		public void ativaVeiculo(long id_veiculo){
			

			try{
				
				Connection con =  Banco.abreBanco();
				Statement  stm =  con.createStatement();
				stm.executeUpdate("UPDATE veiculo set STATUS=1 WHERE ID_VEICULO="+id_veiculo);
				
				stm.close();
				
						
				
				//Envia a mensagem para o usuário dizendo que o anuncio foi reativado
			
				
				
			}catch(Exception e){
				
				Debug.gerar("Modelo","DestaqueDAO","desativaVeiculo", e.getMessage());
				
			}
			
			
		}
	
	//Classe para teste de pagina��o
	public List testePaginacao(int quant_reg_pagina){
		
		
		ArrayList<Carro> n_lista;
		Session sessao = factory.openSession();
		Criteria cri =  sessao.createCriteria(Carro.class);
		ArrayList lista = (ArrayList<Carro>) cri.list();
		System.out.println("Tamanho da lista: "+lista.size());
		
		Paginacao p =  new Paginacao(lista,quant_reg_pagina);
		
		long total_paginas = p.getTotalPaginas();
		
	
		
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
	public ArrayList<Veiculo> getVeiculosBusca(int cod_fab,int cod_modelo,int ano_de,int ano_ate,int qui_de,int aqui_ate,int tipo_venda,double preco_de,double preco_ate,String classe,int ini, int total,ArrayList<Criterion> rest,int ordem,int tipoOrdem,long id_revenda)
	{	
		ArrayList<Veiculo> veiculos;
		Session sessao;
		Criteria cri;
		
		sessao = HibernateUtil.getSessaoV().openSession();
		
		
		cri = sessao.createCriteria(Veiculo.class,"v");
		
		cri.add(Restrictions.eq("class",classe));
		
		
	   
	   	
	 
	   //RESTRIÇÃO RELACIONADA A UMA REVENDA ESPECIFICA
	   if(id_revenda!=0)
	   {
	   System.out.println("Busca relacionada a revenda: "+id_revenda);	   
	   DetachedCriteria c =     DetachedCriteria.forClass(Anuncio.class, "a");
	   c.setProjection(Projections.distinct(Projections.property("veiculo.id")));
	   c.add(Restrictions.eqProperty("a.veiculo.id","v.id"));
	   c.add(Restrictions.eq("a.idUsuario",id_revenda));
	 
	   cri.add(Subqueries.propertyIn("id",c));
	   }
	   else
	   {
		  System.out.println("Busca Normal");  
	   }
	
	 
	 
		
		//Paginação
		cri.setFirstResult(ini);
		cri.setMaxResults(total);
		
		//Somente os veiculos que est�o validados
		cri.add(Restrictions.eq("statusValidacao",Pagamento.VALIDACAO_OK));
		
		//Somente anuncios pagos
		cri.add(Restrictions.eq("statusPagamento",Pagamento.CONFIRMADO));
		
		//Somente veículos não vencidos
		cri.add(Restrictions.eq("status",1));
		
		
		
		//Adicona as restri��es restantes
		for(int i=0;i<rest.size();i++)
		cri.add(rest.get(i));
		
		
		
		
		//Tipo de ordena��o
		
		if(ordem==Ordenacao.CRESCENTE)
		{
		
		switch(tipoOrdem)
		{
		
		case Ordenacao.ORD_DESTAQUE: cri.addOrder(Order.asc("prioridade_anuncio"));
		break;
		
		case Ordenacao.ORD_VALOR :cri.addOrder(Order.asc("preco"));
		break;
		
		case Ordenacao.ORD_ANO:cri.addOrder(Order.asc("anoFabricacao"));
		break;
		}
		
		}
		
		else if(ordem==Ordenacao.DECRESCENTE)
		{
			
			
			
			switch(tipoOrdem)
			{
			
			case Ordenacao.ORD_DESTAQUE: cri.addOrder(Order.desc("prioridade_anuncio"));
			break;
			
			case Ordenacao.ORD_VALOR :cri.addOrder(Order.desc("preco"));
			break;
			
			case Ordenacao.ORD_ANO:cri.addOrder(Order.desc("anoFabricacao"));
			break;
			}
			
		}
		
		
		
		
		
		veiculos = (ArrayList<Veiculo>) cri.list();
		
		System.out.println("Quantidade de veículos: "+veiculos.size());
		
		
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
	
	
	//Recupera todos os modelo de moto a partir de um fabricante
		public ArrayList<ItemVeiculo> getAllModelosMoto(int fab){
			
			ArrayList<ItemVeiculo> itens = new ArrayList<ItemVeiculo>();
			ItemVeiculo item= null;
			
			try{
				
				
			
				Connection con =  Banco.abreBanco();
				Statement stm = con.createStatement();
				ResultSet res =  stm.executeQuery("SELECT * FROM modelo_moto WHERE marca="+fab+"");
				
				while(res.next()){
				
					
					
				 	
				 item = new ItemVeiculo();
				 item.setCod(res.getInt("id"));
				 item.setNome(res.getString("nome"));
				 itens.add(item);
				 System.out.println("Codigo da moto="+item.getCod());
					
				}
				
				stm.close();
				res.close();
				
			}catch(Exception e){
				
				Debug.gerar("util", "VeiculoDAO", "getAllModelos", e.getMessage());
			}
			
			return itens;
			
		}
	
	
	public void inserirModelosVeiculo(ArrayList<String> modelos,int marca){
		
		
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
	
	
	
	//Insere Modelos de moto
    public void inserirModelosMoto(ArrayList<String> modelos,int marca){
		
		
		try{
			
			Connection con =  Banco.abreBanco();
			PreparedStatement stm =  con.prepareStatement("INSERT INTO modelo_moto(nome,marca) VALUES(?,?)");
		
			
			for(int i=0;i<modelos.size();i++)
			{
				
			stm.setString(1,modelos.get(i));	
			stm.setInt(2,marca);	
			stm.execute();
			}
			
		}catch(Exception e){
			
		}
	}
    
    
  //Insere Marcas  de moto
   public void inserirMarcasMoto(ArrayList<String> marcas){
		
		
		try{
			
			Connection con =  Banco.abreBanco();
			PreparedStatement stm =  con.prepareStatement("INSERT INTO marca_moto(nome) VALUES(?)");
		
			
			for(int i=0;i<marcas.size();i++)
			{
				
			stm.setString(1,marcas.get(i));	
			stm.execute();
		
			}
			
		}catch(Exception e){
			
		}
	}
	
	
  
   

	//Recupera o fabricante de uma moto do codigo
		public String getNomeFabricanteMoto(long cod){
			
			
			try{
				
				Connection con = Banco.abreBanco();
				Statement stm = con.createStatement();
				ResultSet res = stm.executeQuery("SELECT * FROM marca_moto WHERE id="+cod);
				
				if(res.next())
				return res.getString("nome");
				else
				return "---";	
				
			}catch(Exception e){
				
				Debug.gerar("Modelo", "VeiculoDAO", "getNomeFabricante", e.getMessage());
				return "---";
			}
		}
	
   
 //Recupera o modelo a partir do codigo
 public String getNomeModeloMoto(long cod){
 		
 		
 		try{
 			
 			Connection con = Banco.abreBanco();
 			Statement stm = con.createStatement();
 			ResultSet res = stm.executeQuery("SELECT * FROM modelo_moto WHERE id="+cod);
 			
 			if(res.next())
 			return res.getString("nome");
 			else
 			return "---";	
 			
 		}catch(Exception e){
 			
 			Debug.gerar("Modelo", "VeiculoDAO", "getNomeModelo", e.getMessage());
 			return "---";
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
	
	
	    //RECUPERA A QUANTIDADE TOTAL DE REGISTROS OBEDECENDO UMA RESTRI��O
		public long count(String classe,ArrayList<Criterion> rest){
			
			long size = 0;
			try {
			
		    Criteria criteria =  HibernateUtil.getSessaoV().openSession().createCriteria(Veiculo.class);
			
			criteria.add(Restrictions.eq("class",classe));
			
		
			
			//Adiciona o restante das restri��es
			for(int i=0;i<rest.size();i++)
			criteria.add(rest.get(i));	
			
			criteria.setProjection(Projections.rowCount());
			size = (Long) criteria.uniqueResult();
			//getHibernate().commit();
			} catch (Exception e) {
			//getHibernate().rollback();
			e.printStackTrace();
			} finally {
			//getHibernate().close();
			}
			return size;
			}
		
		
		
		public boolean pagamentoOK(long id_veiculo){
			
			try{
				
				Connection con = Banco.abreBanco();
				Statement stm =  con.createStatement();
				ResultSet res =  stm.executeQuery("SELECT ID_VEICULO FROM veiculo WHERE ID_VEICULO="+id_veiculo);
				
				if(res.next())
				return true;	
				
				else
				return false;
				
			}catch(Exception e){
				
				Debug.gerar("","VeiculoDAO","pagamentoOK",e.getMessage());
				
				return true;
				
			}
			
		}
		
		
		//RECUPERA O VALOR DA PRIORIDADE DE UM VEÍCULO
		public int getPrioridadeVeiculo(long idVeiculo){
			
			int prioridade = 0;
			
			try{
				
				Connection con = Banco.abreBanco();
				Statement stm =  con.createStatement();
				ResultSet res =  stm.executeQuery("select PRIORIDADE_ANUNCIO from veiculo WHERE ID_VEICULO="+idVeiculo);
				
				if(res.next())
				prioridade = res.getInt("PRIORIDADE_ANUNCIO");
				
			}catch(Exception e){
				
				Debug.gerar("Modelo","VeiculoDAO","getPrioridadeVeiculo", e.getMessage());
			}
			
			return prioridade;
			
			
		}
	
}
