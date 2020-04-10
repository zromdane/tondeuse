/*
{
	"gazon" : {
	"infGauche" : 5,
	"supDroite" : 5
	}
	"tondeuse" : {
	"positionX" : 1,
	"positionY" : 2,
	"direction" : "N"
	}

	"commande" : "GAGAGAGAA"
}
*/
package com.majustic.demo;
//ici ajouter les getters et les setters, des constructeurs,
//on peut utiliser lombok
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

class Gazon
{public int infGauche; public int supDroite;}

class Tondeuse
{public int positionX;public  int positionY;public  Character direction;}

public class Etat
{
 	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Gazon gazon;
	public Tondeuse tondeuse;
	public String commande;
	//ici on va executer la commande sur la tondeuse
	public Result executerCommande(Etat e) {
		

        	logger.info("Etat - infG : "+ e.gazon.infGauche +" supG :" +  e.gazon.supDroite);
       	 	
		//System.out.println("infG "+ e.gazon.infGauche + " supG " +  e.gazon.supDroite);
		//System.out.println("e.tondeuse.direction : "+ e.tondeuse.direction);
	
		return new Result();
	};

	
  public  Resource loadLog() {
    try {
	Path root = Paths.get("logs");
      Path file = root.resolve("tondeuse.log");
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }


}
