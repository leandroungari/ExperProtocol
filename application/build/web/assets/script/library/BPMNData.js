class BPMNData extends BPMNElement {


	constructor(x, y, name, count) {

		let text = `
		<g x="500" y="800" width="1400">
		<text class='value-data' y="2500" dy="250" style="font-size: 500px; font-family:Tahoma; text-anchor:middle;">Descrição</text>
		</g>
		`;


		super(`data${count}`, `<g transform='scale(${40 / 2000})'><rect width="1800" height="1800" fill="#fff"></rect>${name.content}${text}</g>`);

		this.element
			.attr('class', 'item')
			.attr('transform', `translate(${x - 20},${y - 20})`);
                
                this.name = name.icon;

		this.wrap(d3.select(this.id).select('.value-data'), 3500, 1000);

		this.x = x;
		this.y = y;
		this.dx = 20;
		this.dy = 20;

		this.attributes = {};
		this.attributes.Descrição = "Descrição";


		this.transicoesOrigem = [];
		this.transicoesDestino = [];

		if (name.icon == "data-store") {
			this.points = [
				{ x: 20, y: 0 },
				{ x: 0, y: 40 },
				{ x: 0, y: -20 },
				{ x: -20, y: 0 }
			];
		}
		else {
			this.points = [
				{ x: 20, y: 0 },
				{ x: 0, y: 35 },
				{ x: 0, y: -25 },
				{ x: -20, y: 0 }
			];
		}

		BPMNDiagram.refreshListener();

	}

	extract() {

		let transições = [];
		this.transicoesOrigem.forEach((e) => {

			let t = get(e);
			transições.push(t);
		});

		this.transicoesDestino.forEach((e) => {

			let t = get(e);
			transições.push(t);
		});

		return {
			id: this.id,
			type: this.constructor.name,
			x: this.x,
			y: this.y,
                        name: this.name,
			description: this.attributes.Descrição,
			elements: [],
			transitions: transições,
                        vinculos: this.vinculos
		}
	}

	atualizar(lista) {

		this.attributes.Descrição = lista[0];
		$('.value-data', this.id).text(this.attributes.Descrição);

		this.wrap(d3.select(this.id).select('.value-data'), 3500, 1000);
	}
}
