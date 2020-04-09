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
class Gazon
{public int infGauche; public int supDroite;}

class Tondeuse
{public int positionX;public  int positionY;public  Character direction;}

public class Etat
{
	public Gazon gazon;
	public Tondeuse tondeuse;
	public String commande;
	//ici on va executer la commande sur la tondeuse
	public void executerCommande() {};
}
