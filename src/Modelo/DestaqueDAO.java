package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Comunicacao;
import util.Debug;
import util.Destaque;
import util.InfoDestaque;
import util.Plano;

public class DestaqueDAO {
	
	
	
	//Verifica se j� esxiste um banner destaque cadastrado para o ve�culo
	
	public boolean existeBannerDestaque(long id_veiculo){
		
		 Session sessao = HibernateUtil.getSessaoV().openSession();
		 
		 Criteria cri =  sessao.createCriteria(Destaque.class);
		 
		 cri.add(Restrictions.eq("codVeiculo",id_veiculo));
		 
		 //Ativo
		 cri.add(Restrictions.eq("status",1));
		 
		 //Banner destaque
		 cri.add(Restrictions.eq("tipoDestaque",Destaque.DESTAQUE_BANNER));
		 
		 if(cri.uniqueResult()!=null)
		 return true;
		 
		 else return false;
	}

	
	
	//RECUPERA UM DESTAQUE
	public Destaque getDestaque(long id){
		
		
		Session  sessao  =  HibernateUtil.getSessaoV().openSession();
		
		return (Destaque) sessao.createCriteria(Destaque.class).add(Restrictions.eq("id",id)).uniqueResult();
		
	}
	
	public void insert(Destaque d){
		
		//REGRAS
		//1-N�o � permitido adicionar um veiculo mais de uma vez no destaque
		//2-O ve�culo inserirdo no plano BANNER � automaticamente inserido no PLANO_INFERIOR tamb�m
		//3-No caso de inser��o de um veciulo j� existente, acontece a prorroga��o de data final
		
		     
		    System.out.println("Inserindo novo destaque");
		      
		    //Remove o destaque caso ele exista
		    removeDestaque(d.getCodVeiculo(),d.getTipoDestaque());
		
		
		    Session sessao = HibernateUtil.getSessaoV().openSession();
		    
	
			sessao.save(d);
			
			
			//Se for destaque banner � inserido como destaque Inferior tambem
			if(d.getTipoDestaque()==Destaque.DESTAQUE_BANNER){
				
				Destaque destaque_inf = new Destaque();
				destaque_inf.setCodVeiculo(d.getCodVeiculo());
				destaque_inf.setDataFim(d.getDataFim());
				destaque_inf.setDataIni(d.getDataIni());
				
				

			     //Verifica se o pagamento do ve�culo est� ok
			    boolean  pagamento_ok = new VeiculoDAO().pagamentoOK(d.getCodVeiculo()); 
			    
			    if(pagamento_ok)
				destaque_inf.setStatus(1);//Confirmado
				else
				destaque_inf.setStatus(0);//N�o confirmado
				
				
				destaque_inf.setTipoDestaque(Destaque.DESTAQUE_INFERIOR);
				
				
			
				sessao.save(destaque_inf);
				
				
				
			}
			
			sessao.close();
		
	}
	
	
	//Remove um destaque
	public void removeDestaque(long id_veiculo,int tipo_destaque){
		
     try{
			
			Connection con =  Banco.abreBanco();
			Statement  stm =  con.createStatement();
			stm.executeUpdate("DELETE  FROM destaque WHERE COD_VEICULO="+id_veiculo+" && TIPO_DESTAQUE="+tipo_destaque);
			
			
			//Ser for destaque banner, deleta tamb�m oo inferior
			if(tipo_destaque==Destaque.DESTAQUE_BANNER)
			stm.executeUpdate("DELETE  FROM destaque WHERE COD_VEICULO="+id_veiculo+" && TIPO_DESTAQUE="+Destaque.DESTAQUE_INFERIOR);
		
			
			
			
			
		}catch(Exception e){
			
			Debug.gerar("","DestaqueDAO","removeDestaque",e.getMessage());
			
		} 
		
		
		
	}
	
	
	//Ativa um destaque(Ato de pagamento)
	public void ativaDestaque(int prioridade,long id_veiculo){
		
		switch(prioridade){
		
		case Plano.PRIORIDADE_ULTRA:	
		System.out.println("Destaque Ultra");
		this.ativaDestaqueBanner(id_veiculo);
		this.ativaDestaqueInferior(id_veiculo);
		break;
		
		
		case  Plano.PRIORIDADE_MEGA: 
		System.out.println("Destaque Mega");
		this.ativaDestaqueInferior(id_veiculo);	
		break;
		
		default: 
		System.out.println("Nenhum destaque definido para o tipo de plano");	
		
		
		}
		
	}
	
	//Ativa um destaque do tipo inferior
	public void ativaDestaqueInferior(long id_veiculo){
		
		
		 try{
			    Date data_ini =  new Date(Calendar.getInstance().getTimeInMillis()); 
			    Date data_fim =  new Date(Destaque.getDataFimDestaque(Calendar.getInstance(),Destaque.DESTAQUE_INFERIOR).getTimeInMillis());
				
				Connection con = Banco.abreBanco();
				PreparedStatement stm =  con.prepareStatement("UPDATE destaque set DATA_INI=?,DATA_FIM=?,STATUS =1 WHERE COD_VEICULO=? && TIPO_DESTAQUE="+Destaque.DESTAQUE_INFERIOR);
				stm.setDate(1,data_ini);
				stm.setDate(2,data_fim);
				stm.setLong(3,id_veiculo);
				
				
				stm.executeUpdate();
				stm.close();
				
				System.out.println("Destaque ativado com sucesso!");
			
				
				
			    }catch(Exception e){
			    	
			    	
			    	Debug.gerar("","DestaqueDAO","ativaDestaqueInferior",e.getMessage());
			    }
		
	}
	
	//Ativa um destaque do tipo banner
	public void ativaDestaqueBanner(long id_veiculo){
		
		
        try{
        	
        	Date data_ini =  new Date(Calendar.getInstance().getTimeInMillis()); 
			Date data_fim =  new Date(Destaque.getDataFimDestaque(Calendar.getInstance(),Destaque.DESTAQUE_BANNER).getTimeInMillis());
			
			Connection con = Banco.abreBanco();
			PreparedStatement stm =  con.prepareStatement("UPDATE destaque set DATA_INI=?,DATA_FIM=?,STATUS = ? WHERE COD_VEICULO=? && TIPO_DESTAQUE="+Destaque.DESTAQUE_BANNER);
			stm.setDate(1,data_ini);
			stm.setDate(2,data_fim);
			stm.setInt(3,1);
			stm.setLong(4,id_veiculo);
			
			
			stm.executeUpdate();
			
			
			System.out.println("Destaque ativado com sucesso!");
		
			
			
		    }catch(Exception e){
		    	
		    	
		    	Debug.gerar("","DestaqueDAO","ativaDestaqueBanner",e.getMessage());
		    }
		
	}
	
	
	//DESTATIVA UM DESTAQUE CADASTRADO
	//Comunica o usuário sobre o destaque desativado
	public void desativaDestaque(long idDestaque){
		
		
		
		try{
			
			Connection con =  Banco.abreBanco();
			Statement  stm =  con.createStatement();
			stm.executeUpdate("UPDATE destaque set STATUS=0 WHERE ID_DESTAQUE="+idDestaque);
			
			stm.close();
			
					
			
			//Envia a mensagem para o usuário dizendo que o destaque doi desativado
			new Comunicacao().sendMensagemDestaqueDesativado(idDestaque);;
			
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","DestaqueDAO","desativaDestaque", e.getMessage());
			
		}
	}
	
	//COLOCA TODOS OS VE�CULOS COMO DESTAQUE INFERIOR
	public void setAllDestaqueInferior(Calendar dataFim)
	{
		
		Destaque d;
		
		try{
			
			Connection con = Banco.abreBanco();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("Select ID_VEICULO from veiculo");
			
			while(res.next()){
				
				d =  new Destaque();
				
				d.setCodVeiculo(res.getLong("ID_VEICULO"));
				d.setDataIni(Calendar.getInstance());
				d.setDataFim(dataFim);
				d.setTipoDestaque(Destaque.DESTAQUE_INFERIOR);
				
				insert(d);
				
				
				
			}
			
			
		}catch(Exception e){
			
			
			Debug.gerar("", "DestaqueDAO","setAllDestaqueInferior", e.getMessage());
			
		}
		
	}
	
	//RECUPERA OS DESTAQUES A PARTIR DO TIPO
	public ArrayList<Destaque> getDestaques(int tipo){
		
		ArrayList<Destaque> destaques;
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		Criteria c =  sessao.createCriteria(Destaque.class);
		
		c.add(Restrictions.eq("tipoDestaque", tipo));
		
		//Destaques ativos no sistema
		c.add(Restrictions.eq("status",1));
		
		destaques = (ArrayList<Destaque>) c.list();
		
		return destaques;
		
	}
	
	
	
	
	//RECUPERA AS INFORMA��ES DE CADA DESTAQUE
	public InfoDestaque getInforDestaque(long codVeiculo,long idDestaque){
		
		InfoDestaque info = new InfoDestaque();
		
		info.setCodVeiculo(codVeiculo);
		
		Connection con=null;
		Statement stm=null;
		ResultSet res=null;
		try{
			
			con = Banco.abreBanco();
			stm =  con.createStatement();
			res =  stm.executeQuery("SELECT  TITULO,PRECO,ANO_FABRICACAO,ANO_MODELO FROM veiculo WHERE ID_VEICULO="+codVeiculo);
			
			if(res.next())
			{
				info.setIdDestaque(idDestaque);
				info.setTitulo(res.getString("TITULO"));
				info.setPreco(res.getDouble("PRECO"));
				info.setAnoFabricacao(res.getInt("ANO_FABRICACAO"));
				info.setAnoModelo(res.getInt("ANO_MODELO"));
				
			}
			
			
			
		}catch(Exception e){
			
			Debug.gerar("", "DestaqueDAO","getInfodestaque", e.getMessage());
		}
			
		
		
		return info;
		
	}
	
	
}
