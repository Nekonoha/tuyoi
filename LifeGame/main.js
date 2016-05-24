/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var SCREEN_SIZE = 500;
var SIDE_CELLS = 200;
var CELL_SIZE = SCREEN_SIZE / SIDE_CELLS;
var FPS = 10;
var canvas; //= document.getElementById('world');
var context; //= canvas.getContext('2d');


window.onload = function () {
	const le = SIDE_CELLS
	const ce = SIDE_CELLS
	const fie = []
	for(let i = 0; i < le; i++){
		for(let j = 0; j < ce; j++){
			if(fie[i] == undefined) fie[i] = []
			fie[i][j] = new Cell(Math.floor(Math.random() * 2))
		}
	}
	const lifegame = new Lifegame(fie)
	lifegame.clink()


	var field = new Array(SIDE_CELLS * SIDE_CELLS);
	var tempField = new Array(SIDE_CELLS * SIDE_CELLS);
	for (var i = 0; i < field.length; i++) {
		field[i] = 0;
		field[i] = Math.floor(Math.random() * 2);
	}
	canvas = document.getElementById('world');
	canvas.width = canvas.height = SCREEN_SIZE;
	var scaleRate = Math.min(window.innerWidth / SCREEN_SIZE, window.innerHeight / SCREEN_SIZE);
	canvas.style.width = canvas.style.height = SCREEN_SIZE * scaleRate + 'px';
	context = canvas.getContext('2d');
	context.fillStyle = 'rgb(200,0,100)';
	// update(field, tempField);

	newupdate(lifegame)
}

class Cell{
	constructor(status){
		this.cstatus = status
		this.nextStatus = 0
		this.clinks = []
	}

	clink(cell){
		if(cell != this) this.clinks.push(cell)
			}

	judge(){
		let count = 0
		for(const c of this.clinks) if(c.cstatus == 1 && ++count == 4) break

		if(this.cstatus == 0 && count == 3) this.nextStatus = 1
		else if(this.cstatus == 1 && 2 <= count && count <= 3) this.nextStatus = 1
			}

	update(){
		this.cstatus = this.nextStatus
		this.nextStatus = 0
	}
}

class WallCell extends Cell{
	judge(){
	}

	update(){
	}
}

class Lifegame{
	constructor(cellmap){
		this.cellmap = cellmap
	}

	update(){
		for(const line of this.cellmap) for(const cell of line) cell.judge()
		for(const line of this.cellmap) for(const cell of line) cell.update()
			}

	clink(){
		for(let i = 0; i < this.cellmap.length; i++){
			for(let j = 0; j < this.cellmap[i].length; j++){
				Lifegame.linkalg(this.cellmap, this.cellmap[i][j], i, j)
			}
		}
	}

	static linkalg(cellmap, cell, lindex, cindex){
		for(let i = -1; i < 2; i++){
			for(let j = -1; j < 2; j++){
				const lres = lindex + i
				const cres = cindex + j
				cell.clink(
					lres >= 0 &&
					cres >= 0 &&
					lres < cellmap.length &&
					cres < cellmap[lres].length ?
					cellmap[lres][cres] : new WallCell(0)
				)
			}
		}
	}
}

function newupdate(lifegame){
	lifegame.update()
	newdraw(lifegame.cellmap)
	setTimeout(newupdate, 1000 / FPS, lifegame); //再帰する
}

function update(field, tempField) {
	var n = 0; //自分の周囲の生きているセル
	tempField = field.slice(); //配列を複製

	for (var i = 0; i < tempField.length; i++) {
		for (var s = -1; s < 2; s++) {
			for (var t = -1; t < 2; t++) {
				//上下左右を調べる
				if (s == 0 && t == 0) continue; //自分は無視
				var c = i + (s * SIDE_CELLS) + t;
				//SIDE_CELLSが一辺の数なので、s*SIDE_CELLSで行,tで列を見る
				//iは自分の位置
				if (c >= 0 && c < tempField.length) {
					//配列からはみ出していないか、上下のチェック
					if (i < c && c % SIDE_CELLS != 0 || i > c && c % SIDE_CELLS != SIDE_CELLS - 1) {
						//左右のチェック、自身が左端や右端の場合は隣接する条件が違う
						//c%SIDE_CELLSが0ならｃは左端なのでｃは隣接しない
						//c%SIDE_CELLSがSIDE_CELLS-1ならｃは右端なのでｃは隣接しない
						//計算的にはArrayは０からはじまるのでこうなります(たぶん)
						if (tempField[c] == 1) n++;
					}
				}
			}
		}
		if (tempField[i] == 1  && (n == 2 || n == 3)) {
			// 自身が「生」でカウントが2か3
			field[i] = 1; // 「生」
		} else if (!tempField[i] == 1 && n == 3) {
			// 自身が「死」でカウントが3
			field[i] = 1; // 「生」
		} else  field[i] = 0; // 「死」

	}

	draw(field);
	setTimeout(update, 1000 / FPS, field, tempField); //再帰する
}

function draw(field) {
	context.clearRect(0, 0, SCREEN_SIZE, SCREEN_SIZE);
	for (var i = 0; i < field.length; i++) {
		var x = (i % SIDE_CELLS) * CELL_SIZE;
		var y = Math.floor(i / SIDE_CELLS) * CELL_SIZE;
		if (field[i]) context.fillRect(x, y, CELL_SIZE, CELL_SIZE);
	}
}

function newdraw(field){
	context.clearRect(0, 0, SCREEN_SIZE, SCREEN_SIZE);
	for (let i = 0; i < field.length; i++) {
		for(let j = 0; j < field[i].length; j++){
			let x = j * CELL_SIZE
			let y = i * CELL_SIZE
			if(field[i][j].cstatus) context.fillRect(x, y, CELL_SIZE, CELL_SIZE)
				}
	}
}
