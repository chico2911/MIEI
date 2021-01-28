package iata.group.webapp;

//import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import netscape.javascript.JSObject;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WelcomeController {
	Temperatura temp = new Temperatura();
	Interruptor inte = new Interruptor("");
	MaquinaLavar maq = new MaquinaLavar();
	Autoclismo aut = new Autoclismo();
	Torneira tor = new Torneira();

	@GetMapping("/getSensores")
	public String sensores() {
		try {

			List<String> sensores = new ArrayList<>();
			try {
				sensores.add(temp.ultimatemperatura());
			}
			catch (Exception e) {
				sensores.add("0");
			}
			int x = inte.getState();
			try {
				sensores.add(String.valueOf(x));
			}
			catch (Exception e) {
				sensores.add("0");
			}
			x = maq.totaldescargas();
			try{
				sensores.add(String.valueOf(x));
			}
			catch (Exception e) {
				sensores.add("0");
			}
			x = aut.totaldescargas();
			try{
				sensores.add(String.valueOf(x));
			}
			catch (Exception e) {
				sensores.add("0");
			}
			x = tor.totaldescargas();
			try{
				sensores.add(String.valueOf(x));}
			catch (Exception e) {
				sensores.add("0");
			}
			String tortrn="";
			for(String str : sensores){
				tortrn+=str + "-";
			}
			return tortrn;
		}
		catch (Exception e){
			return "0-0-0-0-0";
		}
	}

	@GetMapping("/getTemperatura")
	public String temperatura() {
		try {
			return temp.ultimatemperatura();
		}
		catch (Exception e){
			return "0";
		}
	}

	@GetMapping("/getTemperatura24")
	public String temperatura24() {
		try {
			return temp.last24hours();
		}
		catch (Exception e){
			return "0";
		}
	}

	@GetMapping("/getTemperatura7days")
	public String temperatura7() {
		try {
			return temp.last7days();
		}
		catch (Exception e){
			return "0";
		}
	}

	@GetMapping("/getTemperatura365")
	public String temperatura365() {
		try {
			return temp.last365days();
		}
		catch (Exception e){
			return "0";
		}
	}

	@GetMapping("/getTorneiraTotal")
	public int torneira() {
		try {
			return tor.totaldescargas();
		}
		catch (Exception e){
			return 0;
		}
	}
	@GetMapping("/getMaquinaTotal")
	public int maquina() {
		try {
			return maq.totaldescargas();
		}
		catch (Exception e){
			return 0;
		}
	}

	/////////////////////// autoclismo


	@GetMapping("/getAutoclismoTotalUsado")
	public int autoclismo() {
		try {
			return aut.totaldescargas();
		}
		catch (Exception e){
			return 0;
		}
	}

	@GetMapping("/getAutoclismoTotalGasto")
	public int autoclismogasto() {
		try {
			return aut.totalgasto();
		}
		catch (Exception e){
			return 0;
		}
	}

	@GetMapping("/changeStateInterruptor")
	public void changeStateInte() {
		try {
			inte.changeState();
		}
		catch (Exception ignored){

		}
	}





	//@PostMapping("/employees")

}
