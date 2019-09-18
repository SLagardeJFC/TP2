package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        //S3 : On n'imprime pas le ticket si la balance est en dessous du prix du ticket
        public void noPrintIfNotEnoughMoney(){
            assertEquals("On peut imprimer le ticket alors qu'on n'a pas assez d'argent",false,machine.printTicket());
        }
        
        @Test
        //S4 : On imprime le ticket si la somme est suffisante
        public void printIfEnough(){
            machine.insertMoney(60);
            assertTrue("On ne peut pas imprimer le ticket car le montant n'est pas suffisant",machine.printTicket());
        }
        
        @Test
        //S5 : On vérifie que la balance a été décrémentée lors de l'impression du ticket
        public void decremente(){
            int v=60;
            machine.insertMoney(v);
            machine.printTicket();
            assertEquals("La balance n'a pas correctement été décrémentée",v-PRICE, machine.getBalance());
        }
        
        @Test
        //S6 : On vérifie si le total a été mis à jour après l'impression d'un ticket
        public void totalUpdate(){
            int v=50;
            int t=machine.getTotal();
            machine.insertMoney(v);
            machine.printTicket();
            int t1=machine.getTotal();
            assertEquals("Le total n'a pas été mis à jour",t+50,t1);
        }
        
       @Test
       //S7 : refund rend correctement la monnaie ?
       public void refundCorrect(){
           int v=120;
           machine.insertMoney(v);
           machine.printTicket();
           assertEquals("Refund n'a pas bien rendu la monnaie",v-PRICE,machine.refund());
       }
       
       @Test
       //S8 : refund() remet la balance à zéro ?
       public void refundBalanceZero(){
           int v =600;
           machine.insertMoney(v);
           machine.refund();
           assertEquals("La balance n'a pas été remise à zéro",0,machine.getBalance());
       }
        
       @Test
       //S9 : On ne peut pas insérer de montant négatif
       public void noNegative(){
           machine.insertMoney(-50);
           assertEquals("Une valeur négative a été ajoutée",0,machine.getBalance());
       }
       
       @Test(expected=IllegalArgumentException.class)
       //S10 : Machine a cout négatif non créable ?
       public void nonPossibleNegativeTicketCost(){
           TicketMachine m = new TicketMachine(-10);
       }
}
