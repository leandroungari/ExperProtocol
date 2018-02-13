class BPMNSwimlane extends BPMNElement {

	constructor(id, content) {

		super(id, content);
	}
}

class BPMNLane {

	constructor(id, pool, x, y) {

		this.id = `${pool}lane${id}`;
		this.container = pool;

		this.width = ( get(this.container.substring(1)) == null ? 570 : get(this.container.substring(1)).width - 30);
		this.height = 200;

		this.attributes = {
			Descrição: '',
			Altura: this.height,
		}

		this.x = x;
		this.y = y;
		this.name = "lane";

	}

	getX() {
		return this.x + get(this.container).x;
	}

	getY() {
		return this.y + get(this.container).y;
	}

	atualizar(lista) {

		this.attributes.Descrição = lista[0];
		$('.value-lane', this.id).text(this.attributes.Descrição);

		this.attributes.Altura = Number.parseInt(lista[1]);
		this.height = this.attributes.Altura;

		
		d3.select(this.id).select('rect').attr('height', this.height);

		let alturaPiscina = get(this.container.substring(1)).lanes.reduce((t, e) => t + e.height, 0);
		d3.select(this.container).select('.c1').attr('height', alturaPiscina);
		d3.select(this.container).select('.c2').attr('height', alturaPiscina);

		d3.select(this.container).select('.title-swim')
		.attr('width', this.height)		
		.attr('transform', `rotate(-90) translate(${-alturaPiscina + 5}, 8)`);

		d3.select(this.container).select('.title-swim').select('text')
		.attr('x', alturaPiscina/2 - 5);

		d3.select(`${this.id}`).select('g')
		.attr('width', this.height)
		.attr('transform', `rotate(-90) translate(${-this.height + 5}, 8)`);

		d3.select(`${this.id}`).select('g').select('text')
		.attr('x', this.height/2 - 5);

		let swim = get(this.container.substring(1));

		swim.height = alturaPiscina;
		swim.dy = alturaPiscina/2;

		//atualizando o posicionamento das raias
		swim.update();
	}

	extract() {

		return {
			id: this.id,
			type: this.constructor.name,
			x: this.x,
			y: this.y,
			name: this.name,
			height: this.height,
			description: this.attributes.Descrição,
			elements: [],
			vinculos: this.vinculos
		}
	}

}

class BPMNPool extends BPMNSwimlane {

	constructor(x, y, name, count, hasLane = true, currentId = true) {

		let text = `<g class='title-swim' width="200" transform='rotate(-90) translate(-195,8)'>
		<text x="95" y="10" class='value-pool' style='font-family:Tahoma; font-size: 12px; text-anchor:middle;' >Exemplo</text>
		</g>`;


		if (currentId === true) super(`pool${count}`, `<g>${name.content}${text}<g class='content-swim'></g></g>`);
		else super(currentId, `<g>${name.content}${text}<g class='content-swim'></g></g>`);

		this.width = 600;
		this.height = 0;

		this.name = name.icon;
		this.numLanes = 0;
		this.attributes = {
			Descrição: "Exemplo",
			Largura: this.width 
		};
		

		this.x = x;
		this.y = y;

		this.dx = this.width / 2;
		this.dy = this.height / 2;        

		this.element
		.attr('class', 'item')
		.attr('transform', `translate(${x - this.dx},${y - this.dy})`);

		this.lanes = [];
		if (hasLane) this.addLaneBelow();


	}

	update() {

		if (this.lanes == null) return; 

		let lista = this.lanes.sort((a,b) => { 
			if (a.y < b.y) return -1;
			else if (a.y > b.y) return 1;
			return 0;
		})
	
		let posicaoY = 0;
		lista.forEach((a) => {

			d3.select(a.id)
			.attr('x', a.x)
			.attr('y', posicaoY)
			.attr('transform', `translate(${a.x},${posicaoY})`);

			//atualiza y do objeto
			a.y = posicaoY;

			posicaoY += a.height;
		});

		BPMNDiagram.reposicionarTransicoesPool(get(this.id.substring(1)));
	}

	addLaneAbove() {
		
		let id = `${this.id}lane${this.numLanes}`.substring(1);

		d3.select(`${this.id} .content-swim`)
		.insert('g', ':first-child')
		.attr('id', id)
		.attr('class', 'lane')
		.attr('transform', `translate(30,0)`);

		d3.select(`#${id}`)
		.append('rect')
		.attr('stroke-width', 5)
		.attr('stroke', '#000')
		.attr('width', this.width-30)
		.attr('height', 200)
		.attr('fill', 'transparent');

		d3.select(`#${id}`)
		.append('g')
		.attr('width', 200)
		.attr('transform', 'rotate(-90) translate(-195,8)')
		.append('text')
		.attr('x', 95)
		.attr('y', 10)
		.attr('class', 'value-lane')
		.attr('style', 'font-family:Tahoma; font-size: 12px; text-anchor:middle;');

		d3.select(`#${id}`)
		.append('g')
		.attr('class', 'content-lane')

		for (let x in this.lanes) {

			this.lanes[x].y += 200;
			d3.select(this.lanes[x].id).attr('transform', `translate(30,${this.lanes[x].y})`);
		}

		let l = new BPMNLane(this.numLanes, this.id, 30, 0);

		this.lanes.push(l);
		window.elements.push(l);

		//modificando a piscina
		let distancia = 0;
		for (let x in this.lanes) distancia += this.lanes[x].height;

			let select = d3.select(this.id);

		select.selectAll(`.c1, .c2`).attr('height', distancia);
		select.select('.title-swim')
		.attr(`transform`, `rotate(-90) translate(${-distancia + 5}, 8)`)
		.attr(`width`, `${distancia}`);

		let x = parseInt(select.select('.title-swim').select('.value-pool').attr('x'));
		select.select('.title-swim').select('.value-pool').attr('x', distancia/2 - 5);

		this.numLanes++;

		this.height += 200;
		this.y += 100;
		this.dy = this.height / 2;

		//corrigindo o posicionamento da piscina
		this.y -= 200;

		d3.select(this.id)
		.attr('class', 'item')
		.attr('transform', `translate(${this.x - this.dx},${this.y - this.dy})`);

		//reposicionando as transições
		BPMNDiagram.reposicionarTransicoesPool(this);

		BPMNDiagram.refreshListener();

		this.update();

	}

	addLaneBelow() {

		
		let distancia = 0;
		//console.log(this.width + "");
		for (let x in this.lanes) {
			distancia += this.lanes[x].height;
		}

		let id = `${this.id}lane${this.numLanes}`.substring(1);

		d3.select(`${this.id} .content-swim`)
		.append('g')
		.attr('id', id)
		.attr('class', 'lane')
		.attr('transform', `translate(30,${(distancia)})`);

		d3.select(`#${id}`)
		.append('rect')
		.attr('stroke-width', 5)
		.attr('stroke', '#000')
		.attr('width', this.width-30)
		.attr('height', 200)
		.attr('fill', 'transparent');

		d3.select(`#${id}`)
		.append('g')
		.attr('width', 200)
		.attr('transform', 'rotate(-90) translate(-195,8)')
		.append('text')
		.attr('x', 95)
		.attr('y', 10)
		.attr('class', 'value-lane')
		.attr('style', 'font-family:Tahoma; font-size: 12px; text-anchor:middle;');

		d3.select(`#${id}`)
		.append('g')
		.attr('class', 'content-lane')

		let l = new BPMNLane(this.numLanes, this.id, 30, distancia);
		this.lanes.push(l);
		window.elements.push(l); 

		let select = d3.select(this.id);

		//modificando a piscina
		//
		distancia += 200;

		select.selectAll(`.c1, .c2`).attr('height', distancia);
		select.select('.title-swim')
		.attr(`transform`, `rotate(-90) translate(${-distancia + 5}, 8)`)
		.attr(`width`, `${distancia}`);

		let x = parseInt(select.select('.title-swim').select('.value-pool').attr('x'));
		if (this.numLanes != 0) select.select('.title-swim').select('.value-pool').attr('x', distancia/2 - 5);

		this.numLanes++;

		this.height += 200;
		this.y += 100;
		this.dy = this.height / 2;

		BPMNDiagram.refreshListener();
	}

	insert(id, x, y, height) {

		let distancia = 0;

		for (let a in this.lanes) {
			distancia += this.lanes[a].height;
		}

		id = id.substring(1);

		d3.select(`${this.id} .content-swim`)
		.append('g')
		.attr('id', id)
		.attr('class', 'lane')
		.attr('transform', `translate(30,${(distancia)})`);

		d3.select(`#${id}`)
		.append('rect')
		.attr('stroke-width', 5)
		.attr('stroke', '#000')
		.attr('width', this.width-30)
		.attr('height', height)
		.attr('fill', 'transparent');

		d3.select(`#${id}`)
		.append('g')
		.attr('width', height)
		.attr('transform', 'rotate(-90) translate(-195,8)')
		.append('text')
		.attr('x', 95)
		.attr('y', 10)
		.attr('class', 'value-lane')
		.attr('style', 'font-family:Tahoma; font-size: 12px; text-anchor:middle;');

		d3.select(`#${id}`)
		.append('g')
		.attr('class', 'content-lane')

		let l = new BPMNLane(this.numLanes, this.id, 30, distancia);
		this.lanes.push(l);
		window.elements.push(l);

		l.height = height;

		let select = d3.select(this.id);

		//modificando a piscina
		//
		distancia += height;

		select.selectAll(`.c1, .c2`).attr('height', distancia);
		select.select('.title-swim')
		.attr(`transform`, `rotate(-90) translate(${-distancia + 5}, 8)`)
		.attr(`width`, `${distancia}`);

		//let x = parseInt(select.select('.title-swim').select('.value-pool').attr('x'));
		if (this.numLanes != 0) select.select('.title-swim').select('.value-pool').attr('x', distancia/2 - 5);

		this.numLanes++;

		this.height += height;
		this.dy = this.height / 2;

		d3.select(this.id)
		.attr('class', 'item')
		.attr('transform', `translate(${this.x - this.dx},${this.y - this.dy})`);

		//reposicionando as transições
		BPMNDiagram.reposicionarTransicoesPool(this);
		BPMNDiagram.refreshListener();
	}

	atualizar(lista) {

		this.attributes.Descrição = lista[0];
		this.attributes.Largura = Number.parseInt(lista[1]);
		//console.log(this.attributes.Largura)
		this.width = this.attributes.Largura;
		this.x -= this.dx;
		this.dx = this.width/2;
		this.x += this.dx;

		let l = this.width - 30;

		$('.value-pool', this.id).text(this.attributes.Descrição);

		this.element.select('.c1')
		.attr('width', this.attributes.Largura);

		this.element.select('.content-swim')
		.selectAll('.lane').each(function(){
			
			let element = get(d3.select(this).attr('id'));
			element.width = l;

			d3.select(this).select('rect')
			.attr('width', l);
		});


	}

	extract() {

		return {
			id: this.id,
			type: this.constructor.name,
			x: this.x,
			y: this.y,
			name: this.name,
			width: this.width,
			description: this.attributes.Descrição,
			elements: [],
			vinculos: this.vinculos
		}
	}

}

