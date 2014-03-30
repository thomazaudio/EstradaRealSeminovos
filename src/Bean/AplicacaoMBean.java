package Bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


@ManagedBean(name = "AplicacaoMBean")
@SessionScoped
public class AplicacaoMBean {

    private ListDataModel model;
    private TreeNode root;

    /**
     * Creates a new instance of AplicacaoMBean
     */
    public AplicacaoMBean() {

        limparTabelas();

    }

    public ListDataModel getModel() {
        return model;
    }

    public void colocarNo(DragDropEvent dropEvent) {

        String idDrop = dropEvent.getDropId();
        String idDrag = dropEvent.getDragId();

        TreeNode drop = carregaNode(root, idDrop);

        Integer contadorLista = 0;

        Integer idEvento = Integer.parseInt(limpaDragDropId(idDrag, ":"));

        String nomeDrop = "";
        String corDrop = "";
        String miliDrop = "";

        for (Object e : model) {

            if (idEvento == contadorLista) {

                nomeDrop = ((Carro) e).getModelo();
                corDrop = ((Carro) e).getCor();
                miliDrop = String.valueOf(System.currentTimeMillis());
                break;

            }

            contadorLista++;

        }

        new DefaultTreeNode(nomeDrop + " - " + corDrop + " = " + miliDrop, drop);

    }

    /**
     * Metodo para limpar os textos do componente DRAG & DROP
     *
     * @param idDragDrop id componente
     * @return string limpa somente numeros, caso tree separados por _
     */
    public static String limpaDragDropId(String idDragDrop, String separador) {

        String retorno = "";

        String[] arrayDragDrop = idDragDrop.split(separador);

        for (String string : arrayDragDrop) {

            String[] arrayRetorno = string.split("_");

            for (String string1 : arrayRetorno) {

                if (validaStringNumero(string1)) {

                    if (retorno.length() > 0) {

                        retorno += "_" + string1;

                    } else {

                        retorno = string1;

                    }

                }

            }

        }

        return retorno;

    }

    public static boolean validaStringNumero(String s) {

        return s.matches("[0-9]*");

    }

    private TreeNode carregaNode(TreeNode treeNode, String idEvento) {

        idEvento = limpaDragDropId(idEvento, ":");

        String[] splitIdEvento = idEvento.split("_");

        for (Integer i = 0; i < splitIdEvento.length; i++) {

            treeNode = treeNode.getChildren().get(Integer.parseInt(splitIdEvento[i]));

        }

        return treeNode;

    }

    public void setModel(ListDataModel model) {
        this.model = model;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void limparTabelas() {

        List<Carro> carros = new ArrayList<Carro>();

        carros.add(new Carro("1987", "Verde", "Gol"));
        carros.add(new Carro("2000", "Azul", "Palio"));
        carros.add(new Carro("2008", "Amarelo", "Uno"));

        model = new ListDataModel(carros);

        root = new DefaultTreeNode("root", null);
        TreeNode Estoque = new DefaultTreeNode("Estoque", root);
        TreeNode Vendido = new DefaultTreeNode("Vendido", root);
        TreeNode Defeito = new DefaultTreeNode("Defeito", root);

    }
}
