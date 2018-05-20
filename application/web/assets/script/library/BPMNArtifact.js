
class BPMNArtifact extends BPMNElement {

	constructor(id, content) {

		super(id, content);
	}
}

class BPMNGroup extends BPMNArtifact {

	constructor(x, y, name, count) {

		super(`group${count}`, name.content);
	}
}

class BPMNTextAnnotation extends BPMNArtifact {

	constructor(x, y, name, count) {

		let text = `
		<g>
		<text class='value-annotation' y="10" dy="250" style="font-size: 12px; font-family:sans-serif; text-anchor:middle;"></text>
		</g>
		`;


		super(`textannotation${count}`, `<g><rect width="15" x="0" y="0" stroke-width="0" stroke="url(#raio)" height="40" fill="transparent"></rect>${name.content}${text}</g>`);

                this.name = name.icon;
		this.element
			.attr('class', 'item')
			.attr('transform', `translate(${x - 20},${y - 20})`);

		this.attributes = {};
		this.attributes.Descrição = "";

		this.wrap(d3.select(this.id).select('.value-annotation'), 100, 60);

		this.x = x;
		this.y = y;
		this.dx = 20;
		this.dy = 20;

		this.transicoesOrigem = [];
		this.transicoesDestino = [];

		this.points = [
			{ x: -25, y: 0 }
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
		$('.value-annotation', this.id).text(this.attributes.Descrição);

		this.wrap(d3.select(this.id).select('.value-annotation'), 100, 60);
	}
}