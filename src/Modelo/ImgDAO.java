package Modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;



import util.*;

public class ImgDAO {

	private Connection con;
	private PreparedStatement stm;
	
	
	
	/**
	 * 
	 * @param img
	 */
	public boolean insert(Imagem img) {
		
		
		try{
		con =  Banco.abreBanco();
		stm = con.prepareStatement("INSERT INTO imagens(IMG,ID_VEICULO) VALUES(?,?)");
		stm.setBytes(1,IOUtils.toByteArray(img.getImg()));
		stm.setLong(2,img.getId_veiculo());
		stm.execute();
		return true;
			
		}catch(Exception e){
		
			
			Debug.gerar("Modelo","ImgDAO","insert", e.getMessage());
			return false;
		}
		
	
	}

	/**
	 * 
	 * @param img
	 */
	public boolean update(Imagem img) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param img
	 */
	public boolean delete(long id_img) {
		
		
		//Verifica se a imagem é capa
		boolean isCapa = isCapa(id_img);
		
		System.out.println("isCapa="+isCapa);
		
		long id_veiculo=0;
		long img_post=0;
		
		try{
			
		Connection con =  Banco.abreBanco();
		Statement stm  =  con.createStatement();
		ResultSet res = stm.executeQuery("SELECT * FROM imagens WHERE ID_IMG="+id_img);
		
		if(res.next())
		id_veiculo = res.getLong("ID_VEICULO");
		
		stm.executeUpdate("DELETE FROM imagens WHERE ID_IMG="+id_img);
		
		if(isCapa)
		{
		res = stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo);
		
		if(res.next())
		img_post = res.getLong("ID_IMG");
		
		System.out.println("Id do veiculo: "+id_veiculo);
		
		this.marcaCapa(img_post, id_veiculo);
		
		}
		
		stm.close();
		
		
		
		return true;

		}catch(Exception e){
			Debug.gerar("Modelo","ImgDAO","delet", e.getMessage());
			return false;
		}
		
	}
	
	//Marca uma imagem como capa no sistema
	public boolean marcaCapa(long id_img,long id_veiculo){
		

		try{
			
		Connection con =  Banco.abreBanco();
		Statement stm  =  con.createStatement();
		
		//Retira a capa anterior
		stm.executeUpdate("UPDATE  imagens SET CAPA=0 WHERE ID_VEICULO="+id_veiculo);
		stm.close();
		
		stm = con.createStatement();
		stm.executeUpdate("UPDATE  imagens SET CAPA=1 WHERE ID_IMG="+id_img);
		stm.close();
		return true;

		}catch(Exception e){
			Debug.gerar("Modelo","ImgDAO","marcaCapa", e.getMessage());
			return false;
		}
		
	}
	

	/**
	 * 
	 * @param id_veiculo
	 */
	public ArrayList<Imagem> getImgs(long id_veiculo) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_veiculo
	 */
	public ArrayList<Imagem> getTumbs(long id_veiculo) {
		throw new UnsupportedOperationException();
	}
	
	
	
	  //Recupera a imagem de capa de um veiculo
      public Imagem getImgCapa(long id_veiculo){
		

		Imagem tumb = null;
		ResultSet res =null;
		Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo+" &&CAPA=1");
	    
	    if(res.next())
	    {
	    
	    tumb =  new Imagem();
	    tumb.setId(res.getLong("ID_IMG"));
	    tumb.setId_veiculo(res.getLong("id_veiculo"));
	    tumb.setImg(res.getBinaryStream("IMG"));
	    tumb.setCapa(res.getInt("CAPA"));
	    
	    	
	    }
	    else
	    {
	    	
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo);
	    
	  
	    if(res.next())
	    this.marcaCapa(res.getLong("ID_IMG"), id_veiculo);
	    
	   
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo+" &&CAPA=1");
	    
	    if(res.next())
	    {
	    
	    tumb =  new Imagem();
	    tumb.setId(res.getLong("ID_IMG"));
	    tumb.setId_veiculo(res.getLong("id_veiculo"));
	    tumb.setImg(res.getBinaryStream("IMG"));
	    tumb.setCapa(res.getInt("CAPA"));
	    
	    return tumb;
	    	
	    }
	   
	    }
	    
	    stm.close();
	    res.close();
	    	
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo","ImgDAO","getImgCapa", e.getMessage());
	    }
		
		
		//Converte a imagem para Tumb
		
        return tumb;
		
	}
      
      
      
      
    //Recupera a imagem de capa de um destaque
      public Imagem getImgBanner(long id){
		

		Imagem tumb = null;
		ResultSet res =null;
		Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT IMG_BANNER FROM destaque WHERE ID_DESTAQUE="+id);
	    
	    System.out.println("Id do destaque----: "+id);
	    
	    if(res.next())
	    {
	    
	    tumb =  new Imagem();
	    tumb.setImg(res.getBinaryStream("IMG_BANNER"));
	   
	    
	    	
	    }
	   
	   
	    
	    
	    stm.close();
	    res.close();
	    	
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo","ImgDAO","getImgBanner", e.getMessage());
	    }
		
		
		//Converte a imagem para Tumb
		
        return tumb;
		
	}
      
      
      //Recupera a imagem de capa de um veiculo EM TAMANHO TUMB
      public Imagem getTumbImgCapa(long id_veiculo){
		

		Imagem tumb = null;
		ResultSet res =null;
		Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo+" &&CAPA=1");
	    
	    if(res.next())
	    {
	    
	    tumb =  new Imagem();
	    tumb.setId(res.getLong("ID_IMG"));
	    tumb.setId_veiculo(res.getLong("id_veiculo"));
	    tumb.setImg(res.getBinaryStream("IMG"));
	    tumb.setCapa(res.getInt("CAPA"));
	    
	    	
	    }
	    else
	    {
	    	
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo);
	    
	  
	    if(res.next())
	    this.marcaCapa(res.getLong("ID_IMG"), id_veiculo);
	    
	   
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo+" &&CAPA=1");
	    
	    if(res.next())
	    {
	    
	    tumb =  new Imagem();
	    tumb.setId(res.getLong("ID_IMG"));
	    tumb.setId_veiculo(res.getLong("id_veiculo"));
	    tumb.setImg(res.getBinaryStream("IMG"));
	    tumb.setCapa(res.getInt("CAPA"));
	    
	    return tumb;
	    	
	    }
	   
	    }
	    
	    stm.close();
	    res.close();
	    	
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo","ImgDAO","getImgCapa", e.getMessage());
	    }
		
		
		//Converte a imagem para Tumb
		
        return tumb;
		
	}
	
	
	public Imagem getImg(long id_img){
		

		Imagem tumb = null;
		ResultSet res =null;
		Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_IMG="+id_img);
	    
	    if(res.next())
	    {
	    
	    tumb =  new Imagem();
	    tumb.setId(res.getLong("ID_IMG"));
	    tumb.setId_veiculo(res.getLong("id_veiculo"));
	    tumb.setImg(res.getBinaryStream("IMG"));
	    tumb.setCapa(res.getInt("CAPA"));
	    
	    	
	    }
	    else
	    Debug.gerar("Modelo","ImgDAO","getTumb","A imagem de id "+id_img+" não foi encontrada");
	    
	    stm.close();
	    res.close();
	    	
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo","ImgDAO","getTumb", e.getMessage());
	    }
		
		
		//Converte a imagem para Tumb
		
        return tumb;
		
	}
	
	public Imagem getTumb(long id_img){
		

		Imagem tumb = null;
		 ResultSet res =null;
		 Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_IMG="+id_img);
	    
	    if(res.next())
	    {
	    
	    tumb =  new Imagem();
	    tumb.setId(res.getLong("ID_IMG"));
	    tumb.setId_veiculo(res.getLong("id_veiculo"));
	    tumb.setImg(res.getBinaryStream("IMG"));
	    tumb.setCapa(res.getInt("CAPA"));
	    
	    	
	    }
	    else
	    Debug.gerar("Modelo","ImgDAO","getTumb","A imagem de id "+id_img+" não foi encontrada");
	    
	    stm.close();
	    res.close();
	    	
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo","ImgDAO","getTumb", e.getMessage());
	    }
		
		
	    
	    
		//Converte a imagem para Tumb
		
        return tumb;
	}
	
	
	//Recupera todos os ids das imagens de um veiculo
	public long[] getIdsImgs(long id_veiculo){
		
		long ids[]=null;
		int i=0;
		
		ResultSet res =null;
		Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo);
	    
	    if(res!=null){
	    	
               
	    	  res.beforeFirst();  
	    	  res.last();  
	    	  ids = new long[res.getRow()];  
	    	 
	    }
	    
	    res.beforeFirst();
	    while(res.next()){
	    	
	    ids[i] = res.getLong("ID_IMG");	
	    	
	    i++;	  
	    	
	    }
	    
	    res.close();
	    stm.close();
	   
	    for(i=0;i<ids.length;i++){
	    	
	    	System.out.println(ids[i]);
	    }
	    
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo", "ImgDAO","getIdsImgs", e.getMessage());
	    }
	    
	  
	   
	
	    //ORDENAÇÃO DO VETOR
	    ids = this.ordByCapa(ids, id_veiculo);
	   
	    
	    if(ids.length==0)
	    {
	    ids =  new long[1];	
	    ids[0]=583;
	    }
	    return ids;
		
	}
	
	//Recupera o id de uma imagem de capa
	public long getIdImgCapa(long id_veiculo){
		
		
		long id_img=0;
		ResultSet res =null;
		Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT * FROM imagens WHERE ID_VEICULO="+id_veiculo+" &&CAPA=1");
	    
	    if(res.next())
	    id_img=res.getLong("ID_IMG");
	    
	  
	    
	    stm.close();
	    res.close();
	    	
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo","ImgDAO","getIdImgCapa", e.getMessage());
	    }
		
		
	 return id_img;
	}
	
	
	//Verifica se uma imagem é capa
	public boolean isCapa(long id_img){
		
		int capa=0;
		
		try{
			
		Connection con = Banco.abreBanco();
		Statement stm  = con.createStatement();
		ResultSet res = stm.executeQuery("SELECT *  FROM imagens WHERE ID_IMG="+id_img);
		
		if(res.next())
		capa = res.getInt("CAPA");
	     
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","ImgDAO","isCapa", e.getMessage());
		}
		
		if(capa==1)
		return true;
		else
		return false;	
		
	}
	
	//Recupera a logo de um usuário
	public Imagem getLogo(long id_user){
		

		Imagem tumb = null;
		 ResultSet res =null;
		 Statement stm;
		
	    try
	    {
	    
	    Connection con = Banco.abreBanco();
	    stm = con.createStatement();
	    res =stm.executeQuery("SELECT * FROM usuario WHERE ID_USUARIO="+id_user);
	    
	    if(res.next())
	    {
	    	
	    tumb = new Imagem();	
	    tumb.setImg(res.getBinaryStream("IMG_PERFIL"));
	   
	    }
	    	
	    
	    else
	    Debug.gerar("Modelo","getLogo","","A imagem do usuario "+id_user+" não foi encontrada");
	    
	    stm.close();
	    res.close();
	    	
	    }catch(Exception e){
	    	
	    	Debug.gerar("Modelo","ImgDAO","getTumb", e.getMessage());
	    }
		
		
		//Converte a imagem para Tumb
		
        return tumb;
		
	}
	
	
	public Imagem getNoImg(){
		
		Imagem img = new Imagem();
		
		try{
			
			Connection con = Banco.abreBanco();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM elementos");
			
			if(res.next())
			{
			 img.setImg(res.getBinaryStream("NOT_IMG"));
			 img.setCapa(0);
			 img.setId_veiculo(0);
			}
			
			stm.close();
			res.close();
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","ImgDAO","getNoImg", e.getMessage());
		}
		
		
		return img;
	}
	
	//MÉTODO PARA ORDENAR OS IDS DAS IMAGENS de acordo com  a capa
	public long [] ordByCapa(long[] ids,long id_veiculo){
		
		  
		
	    if(ids.length>0)
	    {
	    long id_img = (this.getImgCapa(id_veiculo)).getId();
	    
	    int i=0;
	    while(ids[i]!=id_img)
	    i++;
	    
	    ids[i]=ids[0];
	    ids[0]=id_img;
	    
	    }
	   
		return ids;
	}
	
	
	
	//Método para recuperação de uma imagem de banner temporária
	 public byte[] getImgTempBanner(long id_veiculo){
		
		
		 
		
		 
		byte [] img= null;
		
		
	     try{
				
				Connection con = Banco.abreBanco();
				Statement stm =  con.createStatement();
				ResultSet res = stm.executeQuery("SELECT * FROM temp_img_banner WHERE ID_VEICULO="+id_veiculo);
				
				if(res.next())
				img =  res.getBytes("IMG_BANNER");
					
				else
				Debug.gerar("","ImgDAO","getImgTempBanner","Erro ao recupera imagem de banner temporária(Não existe) id_veiculo+"+id_veiculo);
					
				
				
	     }catch(Exception e){
	    	 
	    	 Debug.gerar("","ImgDAO","getImgTempBanner", e.getMessage());
	     }
	     
	     return img;
	}
	
	
	//Método para inserçao de imagem temporária para o banner destaque
	public void insertImgTempBanner(byte[] img,long id_veiculo){
		

     try{
			
			Connection con = Banco.abreBanco();
			Statement stm_ =  con.createStatement();
			
			//Deleta uma imagem anterior se existir
			stm_.executeUpdate("DELETE FROM temp_img_banner WHERE ID_VEICULO="+id_veiculo);
			
			
			PreparedStatement stm =  con.prepareStatement("INSERT INTO temp_img_banner(ID_VEICULO,IMG_BANNER) VALUES(?,?)");
			stm.setLong(1,id_veiculo);
			stm.setBytes(2,img);
			
			
			stm.execute();
			
			
			System.out.println("Imagem de banner temporaria inserida com sucesso!");
		
			
			
		}catch(Exception e){
			
			
			
			Debug.gerar("Modelo", "ImagDAO","inserImgBannerTemp", e.getMessage());
		}
		
	}
	

	//LOGO
	public void updateLogo(long id_user,byte[] logo){
		
		try{
			
			Connection con = Banco.abreBanco();
			PreparedStatement stm =  con.prepareStatement("UPDATE usuario set IMG_PERFIL = ? WHERE ID_USUARIO=?");
			stm.setBytes(1,logo);
			stm.setLong(2,id_user);
			
			stm.executeUpdate();
			
			
			System.out.println("Logo alterada com sucesso!");
		
			
			
		}catch(Exception e){
			
			System.out.println("Erro ao alterar logo"+e.getMessage());
			
			Debug.gerar("Modelo", "UsuarioDAO","updateLogo", e.getMessage());
		}
		
	}
	
	//MÉTODO PARA TESTAR A INTEGRIDADE DAS IMAGENS NO BANCO DE DADOS
	public File getFile( int id ){
	    Connection c = Banco.abreBanco();//busca uma conexao com o banco
	    File f = null;
	    try {
	        PreparedStatement ps = c.prepareStatement("SELECT ID_IMG, IMG FROM imagens WHERE ID_IMG = ?");
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if ( rs.next() ){
	            byte [] bytes = rs.getBytes("IMG");
	          
	 
	            //converte o array de bytes em file
	            f = new File("C:\\img\\"+"teste"+id+".jpg");
	            FileOutputStream fos = new FileOutputStream(f);
	            fos.write( bytes );
	            fos.close();
	        }
	        rs.close();
	        ps.close();
	        //c.close();
	        return f;
	}catch(Exception e){
		
		Debug.gerar("","ImgDAO","getImg", e.getMessage());
	}
	return null;
	}

	
	
	//Altera uma imagem de um banner destaque
	public void updateImgBannerDestaque(byte[] img, long id_veiculo){
		
		
try{
			
			Connection con = Banco.abreBanco();
			PreparedStatement stm =  con.prepareStatement("UPDATE destaque set IMG_BANNER = ? WHERE COD_VEICULO=? && TIPO_DESTAQUE="+Destaque.DESTAQUE_BANNER);
			stm.setBytes(1,img);
			stm.setLong(2,id_veiculo);
			
			stm.executeUpdate();
			
			
			System.out.println("Imagem de banner alterada com sucesso!");
		
			
			
		}catch(Exception e){
			
			System.out.println("Erro ao alterar imagem do banner"+e.getMessage());
			
			Debug.gerar("Modelo", "UsuarioDAO","updateImgBannerdestaque", e.getMessage());
		}
		
	}
	
	
}