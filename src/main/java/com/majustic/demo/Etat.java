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
{public int infGauche=4; public int supDroite=5;}

class Tondeuse
{public int positionX=1;public  int positionY=3;public  Direction direction=Direction.N;}

enum Direction {
    N,E,S,W;
    private static Direction[] vals = values();
    public Direction next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
    public Direction previous()
    {
	int index=this.ordinal()-1;
	if (index<0)
	 index=vals.length-1;
        return vals[index];
    }
}
public class Etat
{
 	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Gazon gazon=new Gazon();
	public Tondeuse tondeuse=new Tondeuse();
	public String _commande="DDDDD";
	public boolean hit=false;

	void avancer(){
	 int oldY=tondeuse.positionY;
	 int oldX=tondeuse.positionX;
 	 switch (tondeuse.direction){
	    case N:
		tondeuse.positionY++;
		break;
	    case S:
		tondeuse.positionY--;
		break;
	    case W:
		tondeuse.positionX--;
		break;
	    case E:
	       tondeuse.positionX++;
		break;
	 }
	 if (tondeuse.positionY > gazon.infGauche || tondeuse.positionY <0)
			 {tondeuse.positionY=oldY;hit=true;}
	 if (tondeuse.positionX > gazon.supDroite || tondeuse.positionX <0)
			 {tondeuse.positionX=oldX;hit=true;}
	};
	public void initEtat(Etat e){//todo, check the state e first, throws exception if necessary
	 gazon=e.gazon;tondeuse=e.tondeuse;_commande=e._commande; 
	};
	//ici on va executer la commande sur la tondeuse	
	public void executerCommande(){
	 logger.info("Etat - : " + "x"    + tondeuse.positionX+" y:"   + tondeuse.positionY +
			           " dir:"+ tondeuse.direction+" commande: "+_commande);	 
	     
	 for (int i=0; i<this._commande.length(); i++){
	 Character exec=this._commande.charAt(i);
	 if ( exec == 'G' )
	  tondeuse.direction=tondeuse.direction.previous();
	 else if( exec == 'D')
	  tondeuse.direction=tondeuse.direction.next();
	 else if (exec == 'A')
	     avancer();
	 //log here x,y and direction !!!
	 logger.info("Etat -" + " x:"    + tondeuse.positionX +" y:"   + tondeuse.positionY +
			        " dir:"  + tondeuse.direction +" hit:" + hit);	 
	 }     
	};
	
	public Result getResult() {
		
		Result r=new Result();
		r.direction=tondeuse.direction;
		r.positionX=tondeuse.positionX;
		r.positionY=tondeuse.positionY;
		return r;
	};

	
	public  static Resource loadLog() {
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
	public  static void deleteLog() {
            try {
	      Path root = Paths.get("logs");
	      Path file = root.resolve("tondeuse.log");

	      Files.delete(file);
	    }
	     catch(IOException e) 
            { 
	     System.out.println("erreur IO ..."); 
	    }
	}

}
