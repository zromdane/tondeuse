export class Etat {
	constructor(
		
		private _x:number,
		private _y: number,
		private _direction: string,
	){}

	public get x() : number {
		return this._x;
	}
	public get y() : number {
		return this._y;
	}
	public get direction() : string {
		return this._direction;
	}

}
