export class Command {
	constructor(
		
		private _x:number,
		private _y: number,
		private _commande: string,
	){}

	public get x() : number {
		return this._x;
	}
	public get y() : number {
		return this._y;
	}
	public get commande() : string {
		return this._commande;
	}

}
