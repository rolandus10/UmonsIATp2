/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maincontainer;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ir. M. El Adoui
 */

public class Vendeur2Agent extends GuiAgent {
   private Vendeur2Gui gui;
   String prop = "boite";
   double priceUnite=79.99;
   static double priceTot=0;
   String piece;

   int nbr;
    @Override
    protected void setup(){
        gui = new Vendeur2Gui();
        //association d'une interface à l'agent
        gui.setVendeur2Agent(this);
        gui.showMessage("Démarrage de l'agent Vendeur 2 - Je suis en écoute", true);
        
        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);

        parallelBehaviour.addSubBehaviour(new Behaviour(){
        int fin;
        @Override
        public boolean done(){
           if(fin ==5) {
               fin =0;
               return true;
           }
           else return false;
       }
       @Override
       public void action(){ 
           ACLMessage msg = receive();
           String[] tmp;
           if(msg!=null){
               try {                   
                   switch(msg.getPerformative()){
                   case ACLMessage.CFP:   
                       
                       tmp = (String[]) msg.getContentObject();
                       piece = tmp[0];
                       nbr= Integer.parseInt(tmp[1]);                            
                       gui.showMessage("Vendeur 2 : Type de message reçu CFP, je propose mon service ...", true);
                       gui.showMessage("J'ai reçu une demande pour "+tmp[1]+"\t"+tmp[0], true);
                       ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);
                       message.addReceiver(new AID("courtierAgent", AID.ISLOCALNAME));
                       try {
                           message.setContentObject(new String[]{prop,priceUnite+""});
                           message.setOntology("Vente2");
                           message.setLanguage("Français");
                           send(message);
                           gui.showMessage("Je propose la pièce suivante au courtier : "+prop+" au prix unitaire de : "+ priceUnite, true);
                           send(message);
                       } catch (IOException ex) {
                           Logger.getLogger(Vendeur2Agent.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       break;
                       
                   case ACLMessage.ACCEPT_PROPOSAL:

                       
                       fin =5;
                       break;
                   case ACLMessage.REFUSE:
                       gui.showMessage(msg.getContent(), true);
                       fin =5;
                       //myAgent.doDelete();
                       break;
                   default : break; 
               } 
               } 
               catch (UnreadableException ex) {
                   Logger.getLogger(Vendeur2Agent.class.getName()).log(Level.SEVERE, null, ex);
               }   
               }          
            else block();
       }
          }); 
    }  
    @Override 
    protected void takeDown(){
    }
    @Override
    protected void onGuiEvent(GuiEvent arg0){      
    }
}
