
import { Component, Input, ElementRef,OnInit } from '@angular/core';
//import { FormBuilder, FormGroup } from '@angular/forms';
import {HttpClientModule,HttpClient, HttpHeaders} from '@angular/common/http';
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
	 <button type="button" (click)="getLog()">Afficher le Log</button>

      
    </form>

`,
styles: []
})
export class AppComponent implements OnInit {
	etat=datax;
 	Xfinal:number;
	Yfinal :number;
	directionFinale:string;

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
	
	getLog(){
		let url:string;
		url="http://127.0.0.1:8080/log";
		this.http.get<any>(url).subscribe(
		      (res) => {console.log(res); },
		      (err) => {console.log(err);}
		    );	
	}
}
