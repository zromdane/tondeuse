
import { Component, Input, ElementRef,OnInit } from '@angular/core';
//import { FormBuilder, FormGroup } from '@angular/forms';
import {HttpClient, HttpHeaders,HttpResponse} from '@angular/common/http';
import { Command } from './command';
import { Etat } from './etat';
import datax  from  './data.json';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':'application/json'
  })
};
@Component({
selector: 'app-root',
template: `
 <form #form1="ngForm" (ngSubmit)="onSubmit()">      
        
	X : <input type="text" name="x" id="x" [(ngModel)]="etat.tondeuse.positionX"/> <br/>
        Y : <input type="text" name="y" id="y" [(ngModel)]="etat.tondeuse.positionY" /> <br/>
	Direction : <input type="text" name="direction" id="direction" [(ngModel)]="etat.tondeuse.direction" /> <br/>

	inf Gauche : <input type="text" name="infGauche" id="x" [(ngModel)]="etat.gazon.infGauche"/> <br/>
	sup Droite : <input type="text" name="supDroite" id="x" [(ngModel)]="etat.gazon.supDroite"/><br/>

        Commande : <input type="text" name="commande" id="commande" [(ngModel)]="etat._commande"/><br/>

    	X final : <input type="text" name="Xfinal" id="Xfinal" [(ngModel)]="Xfinal"/><br/>
	Y final  : <input type="text" name="Yfinal" id="Yfinal" [(ngModel)]="Yfinal"/><br/>
	Direction finale : <input type="text" name="directionFinale" id="directionFinale" [(ngModel)]="directionFinale"/><br/>     
       
	 <button type="submit">Envoyer la commande</button>
	 </form>

 <button type="button" (click)="getLog()">Afficher le Log</button>
 <button type="button" (click)="deleteLog()">Supprimer le Log</button>   
 <br/>
 <label for="log">Log:</label>
  <br/>
 <textarea id="log" name="log" rows="20" cols="55" [(ngModel)]="txtlog">  </textarea>



`,
styles: []
})
export class AppComponent implements OnInit {
	etat=datax;
 	Xfinal:number;
	Yfinal :number;
	directionFinale:string;
	txtlog:string="Cliquer sur Afficher le Log";

	ngOnInit() { console.log(datax) ;}
	constructor(private http: HttpClient) {}
	onSubmit() {
		console.log(datax) ;
	    	let url:string;
		url="http://127.0.0.1:8080/command";
		this.http.post<any>(url, datax ,httpOptions  ).subscribe(
		      (res) => {this.Xfinal=res.positionX;
				this.Yfinal=res.positionY;
				this.directionFinale=res.direction;
				
				 },
		      (err) => {console.log(err);}
		    );	
	
  	}
	deleteLog(){
		let url="http://127.0.0.1:8080/deletelog";
		this.http.delete(url).subscribe(
 			() =>this.txtlog="",
		      	(err) => {console.log(err); console.log("c");}
		    );	
	
	}

	getLog(){
		
		const httpOptions2 = {
		  headers: new HttpHeaders({
		    'Accept': 'text/plain,*/*',
		    'Access-Control-Allow-Headers': 'Content-Type',
		    'Content-Type': 'text/plain',
		  })
		};
		let url:string;
		url="http://127.0.0.1:8080/log";
		this.http.get(url,{ responseType: 'text' }).subscribe(
		      (res) => {console.log(res); this.txtlog=res;},
		      (err) => {console.log(err); this.txtlog="le fichier est vide ou introuvable"}
		    );	
	}
}
