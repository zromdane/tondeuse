package com.majustic.demo;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;


@RestController
@CrossOrigin
public class TendeuseRestService {

	
	@RequestMapping(value="/command")
	public Result command(@RequestBody Etat e){
		//System.out.println("infG "+ e.gazon.infGauche + " supG " +  e.gazon.supDroite);
		//System.out.println("e.tondeuse.direction : "+ e.tondeuse.direction);
		
		System.out.println("e.gazon.infGauche : "+ e.gazon.infGauche+ 
					" tondeuse "+e.tondeuse.positionX+" "+e._commande);
		return e.executerCommande(e);
	}


 	@GetMapping("/log")
  	public ResponseEntity<Resource> getLog() {
    		Resource file = new Etat().loadLog();
    		return ResponseEntity.ok()
        	       .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  	}



}


