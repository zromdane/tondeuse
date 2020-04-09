package com.majustic.demo;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
public class TendeuseRestService {


	@RequestMapping(value="/command")
	public Result command(@RequestBody Etat e){
		System.out.println("infG "+ e.gazon.infGauche + " supG " +  e.gazon.supDroite);
		System.out.println("e.tondeuse.direction : "+ e.tondeuse.direction);
		
		return new Result();
	}

}
