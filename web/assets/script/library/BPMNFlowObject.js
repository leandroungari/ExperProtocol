class BPMNFlowObject extends BPMNElement {

	constructor(id, content) {

		super(id, content);

		this.element
			.attr('data-selected', false);

		this.transicoesOrigem = [];
		this.transicoesDestino = [];
	}
}


class BPMNEvent extends BPMNFlowObject {

	constructor(x, y, name, count) {

		let text = `
		<g x="500" y="800" width="1400">
			<text class='value-event' y="2500" dy="250" style="font-size: 500px; font-family:Tahoma; text-anchor:middle;"></text>
		</g>
		`;


		super(`event${count}`, `<g transform='scale(${40 / 2000})'><circle cx="1030"  stroke-width="0" stroke="url(#raio)" cy="1020" r="1000" fill="#fff"></circle>${name.content}${text}</g>`);

		this.attributes = {};
		this.attributes.Descrição = "";

		this.wrap(d3.select(this.id).select('.value-event'), 3000, 1030);
                this.name = name.icon;
		this.element
			.attr('class', 'item')
			.attr('transform', `translate(${x - 20},${y - 20})`)

		this.x = x;
		this.y = y;
		this.dx = 20;
		this.dy = 20;

		this.rx = 20;
		this.ry = 20;

		this.points = [
			{ x: 0, y: -20 },
			{ x: 20, y: 0 },
			{ x: -20, y: 0 },
			{ x: 0, y: 20 }
		];

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
		$('.value-event', this.id).text(this.attributes.Descrição);
		this.wrap(d3.select(this.id).select('.value-event'), 3000, 1030);
	}
}


class BPMNActivity extends BPMNFlowObject {

	constructor(x, y, name, count) {

		super(`activity${count}`, `
		<g transform='scale(${100 / 2000})'>
			${name.content}
			<g x="500" y="800" width="1400">
				<text class='value-activity' y="800" dy="250" style="font-size: 200px; font-family:Tahoma; text-anchor:middle;">Exemplo</text>
			</g>
		</g>
	`);

		/*
		<switch>
					<foreignObject x="150" y="650" width="1700" height="970">
						<p class="value-activity" xmlns="http://www.w3.org/1999/xhtml" style="font-size: 170px; font-family:Tahoma; text-align: center; line-height: 300px; vertical-align: middle">Exemplo</p>
					</foreignObject>
					<text x="20" y="20">Your SVG viewer cannot display html.</text>
				</switch>
		*/

		this.element
			.attr('class', 'item')
			.attr('transform', `translate(${x - 50},${y - 40})`);

                this.name = name.icon;
		this.wrap(d3.select(this.id).select('.value-activity'), 1800, 1000);

		this.x = x;
		this.y = y;
		this.dx = 50;
		this.dy = 40;

		this.points = [
			{ x: 53, y: 0 },
			{ x: 0, y: 43 },
			{ x: 0, y: -43 },
			{ x: -53, y: 0 }
		];

		this.attributes = {
			Descrição: "Exemplo",
		};

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

		let count = 0;
		for (let x in this.attributes) {

			this.attributes[x] = lista[count++];
		}

		$('.value-activity', this.id).text(this.attributes.Descrição);

		this.wrap(d3.select(this.id).select('.value-activity'), 1800, 1000);
	}
}

class BPMNGateway extends BPMNFlowObject {

	constructor(x, y, name, count) {

		let text = `
		<g x="500" y="800" width="1400">
		<text class='value-gateway' y="2500" dy="250" style="font-size: 500px; font-family:Tahoma; text-anchor:middle;"></text>
		</g>
		`;

		super(`gateway${count}`, `<rect x="16" y="-12" transform="rotate(45)" width="24" height="24" fill="#fff"></rect><g transform='scale(${40 / 2000})'>${name.content}${text}</g>`);
                this.name = name.icon;

		this.element
			.attr('class', 'item')
			.attr('transform', `translate(${x - 20},${y - 20})`);


		this.wrap(d3.select(this.id).select('.value-gateway'), 3500, 1000);

		this.x = x;
		this.y = y;
		this.dx = 20;
		this.dy = 20;

		this.points = [
			{ x: 20, y: 0 },
			{ x: 0, y: 20 },
			{ x: 0, y: -20 },
			{ x: -20, y: 0 }
		];

		this.attributes = {
			Descrição: "",
		};

		BPMNDiagram.refreshListener();
	}

	atualizar(lista) {

		let count = 0;
		for (let x in this.attributes) {

			this.attributes[x] = lista[count++];
		}

		$('.value-gateway', this.id).text(this.attributes.Descrição);

		this.wrap(d3.select(this.id).select('.value-gateway'), 1800, 1000);
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
}
