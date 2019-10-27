package maincontainer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Vendeur3Gui extends JFrame {
    private JTextArea TextArea = new JTextArea();
    //déclaration de l'agent Client
    private Vendeur3Agent Vendeur3;
    public Vendeur3Gui (){
        JPanel Panel = new JPanel();
        Panel.setLayout(new FlowLayout());

        TextArea.setFont(new Font("Serif", Font.ITALIC, 15));

        // Panel.add(TextArea);
        this.setTitle("Vendeur 3");
        this.setLayout(new BorderLayout());
        this.add(Panel, BorderLayout.NORTH);
        this.add(new JScrollPane(TextArea), BorderLayout.CENTER);
        this.setSize(350,500);
        this.setVisible(true);
    }
    public Vendeur3Agent getVendeur3Agent(){
        return Vendeur3;
    }

    public void setVendeur3Agent(Vendeur3Agent Vendeur){
        this.Vendeur3 =Vendeur;
    }
    public void showMessage(String msg, boolean append){
        if(append==true){
            TextArea.append(msg+"\n");
            TextArea.append("------------------------------------------------------------\n");
        }
        else{
            TextArea.setText(msg);
        }
    }
}
