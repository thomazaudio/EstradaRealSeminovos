package Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import util.Veiculo;


@ManagedBean
public class ComparacaoBean {
	
	
	private Map<Long, Boolean> selecionados = new HashMap<Long, Boolean>();
	
	ArrayList<Veiculo> veiculos;

	public Map<Long, Boolean> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Map<Long, Boolean> selecionados) {
		this.selecionados = selecionados;
	}
	
	
	//Seleciona os itens para comparação
	public void submit() {
        List<Veiculo> veiculos_selecionados = new ArrayList<Veiculo>();

        for (Veiculo veiculo : veiculos) {
            if (selecionados.get(veiculo.getId())) {
            	veiculos_selecionados.add(veiculo);
            }
        }

        
        
        System.out.println("Veiculos Selecionados");
        
        for(int i=0;i<veiculos_selecionados.size();i++){
        	
        
     
    }
	
	

	}
}
