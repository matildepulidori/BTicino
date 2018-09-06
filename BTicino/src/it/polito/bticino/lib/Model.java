package it.polito.bticino.lib;

import java.util.*;

import it.polito.bticino.connessione.*;
import it.polito.bticino.lib.Who.WhoName;

public class Model {

	
	public BTicinoSocket sock;
	private Map<WhoName, Who> who;
	
	private Light luceAll;
	private Light luce1;
	private Light luce2;
	private Light luce3;
	private Automation tapparella;
	
	
	public Model() {
		
		sock = new BTicinoSocket();
		
		//Invio il messaggio per stabilire una sessione di comandi
		boolean sessioneComandi = sock.sendMessage("*99*0##");
		

		if (sessioneComandi == true) {
			who = new TreeMap<>();
			//aggiungo impianto luci
			who.put(WhoName.LIGHTING, new Who(WhoName.LIGHTING, 1));
			//aggiungo impianto automazione
			who.put(WhoName.AUTOMATION, new Who(WhoName.AUTOMATION, 2));
			
			luceAll = new Light(1, "lights", this);
			luce1 = new Light(1, "light1", this);
			luce2 = new Light(1, "light2", this);
			luce3 = new Light(1, "light3", this);
			
			tapparella = new Automation(21, "tapparella", this);
		} else {
			System.err.println("Impossibile stabilire una sessione di comandi");
		}
	}
	
	
	/*public void getStati() {
		List<String> stati = new ArrayList<String>(sock.getStati());
	}*/


	public Light getLuceAll() {
		return luceAll;
	}


	public Light getLuce1() {
		return luce1;
	}


	public Light getLuce2() {
		return luce2;
	}


	public Light getLuce3() {
		return luce3;
	}


	public Automation getTapparella() {
		return tapparella;
	}


	public Map<WhoName, Who> getWho() {
		return who;
	}

	public BTicinoSocket getSocket() {
		return this.sock;
	}

	
}
