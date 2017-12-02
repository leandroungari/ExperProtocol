"use strict"


class BPMNDiagram {

	/** Construtor BPMNDiagram
	* 
	* @param String selector Refere-se ao seletor do elemento DOM do diagrama. 
	*/
	constructor(selector) {

		this.selector = selector;
		this.numElements = 0;

		//Lista de todos os elementos no diagrama
		window.elements = [];

		//Lista de elementos que formarão a nova transição
		window.listaTransicao = [];


		//////////////////////////////////////////
		///

		this.component = document.querySelector(this.selector);


		/*var svg = d3.select("svg");
		svg
		.call(d3.zoom()
		.scaleExtent([1 / 2, 8])
		.on("zoom", zoomed))
	

		function zoomed() {
			svg.attr("transform", d3.event.transform);
			console.log(d3.event.transform + " ");
		}*/

		/////////////////////////////////////////
		this.icon = document.createElement('i');
		this.icon.className = 'mouse-icon';
		document.querySelector('body').appendChild(this.icon);



		d3.select(this.selector).attr('width', window.innerWidth).attr('height', window.innerHeight);

		this.component.addEventListener('click', (event) => {

			if (this.newElement != null) {

				let lista = ['lane-divide-three', 'lane-divide-two', 'lane-insert-below', 'lane-insert-above'];

				if (lista.findIndex((e) => { return this.newElement.icon == e; }) != -1) return;

				let offsetX, offsetY;

				if (document.querySelector(this.selector).transform.baseVal[0] != null) {
					offsetX = document.querySelector(this.selector).transform.baseVal[0].matrix.e;
					offsetY = document.querySelector(this.selector).transform.baseVal[0].matrix.f;
				}
				else {
					offsetX = offsetY = 0;
				}

				let element = this.createElement(event.x - offsetX, event.y - offsetY, this.numElements++);
				element.container = this.selector;
				window.elements.push(element);

				this.setIconMouse();
				this.newElement = null;
			}

		});

	}

	import(json) {

		let data = JSON.parse(json);
		let transitions = [];
		let protocol = data.protocol;

		console.log(data);

		if (protocol.element != null) {
			this.analyse(protocol.element, transitions);

		}

		BPMNSettings.diagramSelector = this.selector;
		this.link(transitions);
		BPMNSettings.diagramSelector = this.selector;

	}

	link(array) {

		if (array.length === 0) return;

		let transition = array.pop();

		if (transition.container != this.selector) BPMNSettings.diagramSelector = `${transition.container} .content-lane`;
		else BPMNSettings.diagramSelector = `${this.selector} .transition`;

		BPMNDiagram.desenharTransicao(transition.origem, transition.destino);

		array = array.filter((t) => {
			return (!(transition.origem == t.origem && transition.destino == t.destino));
		}, transition);

		this.link(array);

	}

	analyse(array, transitions, container = this.selector) {

		if (array == null) return;

		if (Array.isArray(array)) {

			array.forEach((a) => {
				a.container = container;
				this.classify(a);
				this.createLanes(a);

				//transição
				if (a["bpmn.factory.json.Transicao"] != null) {

					if (Array.isArray(a["bpmn.factory.json.Transicao"])) {

						a["bpmn.factory.json.Transicao"].forEach((t) => {
							t.container = container;
							transitions.push(t);
						});
					}
					else {

						a["bpmn.factory.json.Transicao"].container = container;
						transitions.push(a["bpmn.factory.json.Transicao"]);
					}
				}

				//elementos
				this.analyse(a.element, transitions, a.id);

			});
		}
		else {

			array.container = container;
			this.classify(array);
			this.createLanes(array);

			if (array["bpmn.factory.json.Transicao"] != null) {

				if (Array.isArray(array["bpmn.factory.json.Transicao"])) {

					array["bpmn.factory.json.Transicao"].forEach((t) => {
						transitions.push(t);
					});
				}
				else {

					transitions.push(array["bpmn.factory.json.Transicao"]);
				}
			}

			//elementos
			this.analyse(array.element, transitions, array.id);
		}

	}

	createLanes(pool) {

		if (pool.name != 'participant') return;

		//console.log(pool);

		let list = [];
		if (Array.isArray(pool.element)) {

			pool.element.forEach((t) => {
				list.push(t);
			});
		}
		else {

			list.push(pool.element);
		}

		list.sort((a, b) => {
			if (a.y < b.y) return -1;
			else if (a.y > b.y) return 1;
			return 0;
		});



		let parent = get(pool.id.substring(1));
		list.forEach((lane) => {
			//console.log(lane);
			parent.insert(lane.id);
		});
	}

	classify(element) {

		let type = element.type;
		let nome = element.name;

		let object = eval(type + "." + nome.toUpperCase().replace(/-/g, "_"));

		let name = object;
		let xcoor = element.x;
		let ycoor = element.y;
		let novoElemento;
		this.numElements++;


		//corrigir a criação de lanes
		if (nome == 'lane-none') {

			//console.log("aqui");
			return;
		}

		if (element.container != this.selector) BPMNSettings.diagramSelector = `${element.container} .content-lane`;
		else BPMNSettings.diagramSelector = element.container;



		switch (name.type) {

			case "start-event":
			case "intermediate-event":
			case "end-event":

				novoElemento = new BPMNEvent(xcoor, ycoor, name, 0);

				break;

			case "gateway":

				novoElemento = new BPMNGateway(xcoor, ycoor, name, 0);

				break;

			case "data":

				novoElemento = new BPMNData(xcoor, ycoor, name, 0);

				break;

			case "lane":

				novoElemento = new BPMNPool(xcoor, ycoor, name, 0, false, element.id.substring(1));
				console.log(novoElemento.width + "");
				console.log(element)
				break;

			case "annotation":

				novoElemento = new BPMNTextAnnotation(xcoor, ycoor, name, 0);

				break;

			case "activity":

				novoElemento = new BPMNActivity(xcoor, ycoor, name, 0);

				break;

			default:

				console.log("erro");
		}




		let idAntigo = novoElemento.id;
		novoElemento.id = element.id;


		novoElemento.element = d3.select(idAntigo);
		novoElemento.element.attr('id', novoElemento.id.substring(1));
		novoElemento.element = d3.select(novoElemento.id.substring(1));

		novoElemento.container = element.container;

		let attributes = [
			element.description,
			element.width
		];

		novoElemento.atualizar(attributes);

		novoElemento.vinculos = [];

		if (element.string != null) element.string.forEach((a) => {
			novoElemento.vinculos.push(a);
		});


		window.elements.push(novoElemento);
		BPMNSettings.diagramSelector = this.selector;

	}

	export() {

		let diagram = {



			elements: []

		}

		window.elements.filter((e) => { return e.container == this.selector }).forEach((e) => {
			let novo = e.extract();
			diagram.elements.push(novo);
		});

		diagram.elements.forEach((e) => {

			window.elements
				.filter((a) => {

					if (a.container == null) return false;

					let c = (a.container.startsWith('#') ? a.container : `#${a.container}`);
					return c == e.id;
				})
				.forEach((el) => {

					let novo = el.extract();
					e.elements.push(novo);

					window.elements
						.filter((al) => {

							if (al.container == null) return false;

							let c = (al.container.startsWith('#') ? al.container : `#${al.container}`);
							return c == novo.id;
						})
						.forEach((o) => {

							let novoE = o.extract();
							novo.elements.push(novoE);
						});
				});


		}, window.elements);

		return diagram;
	}

	createElement(xcoor, ycoor, count) {

		let name = this.newElement;

		let novoElemento;
		switch (name.type) {

			case "start-event":
			case "intermediate-event":
			case "end-event":

				novoElemento = new BPMNEvent(xcoor, ycoor, name, count);

				break;

			case "gateway":

				novoElemento = new BPMNGateway(xcoor, ycoor, name, count);

				break;

			case "data":

				novoElemento = new BPMNData(xcoor, ycoor, name, count);

				break;

			case "lane":

				novoElemento = new BPMNPool(xcoor, ycoor, name, count);
				break;

			case "annotation":

				novoElemento = new BPMNTextAnnotation(xcoor, ycoor, name, count);

				break;

			case "activity":

				novoElemento = new BPMNActivity(xcoor, ycoor, name, count);

				break;

			default:

				console.log("erro");
		}

		return novoElemento;
	}

	setIconMouse(element = null) {

		if (element == null) {

			document.querySelector('.mouse-icon').removeEventListener('mouse', function () { });
			this.icon.style.display = 'none';
			return;
		}

		this.newElement = element;
		this.icon.className = 'mouse-icon bpmn-icon-' + element.icon;
		this.icon.style.display = 'block';

		this.component.addEventListener('mousemove', (event) => {

			event.stopPropagation();
			this.icon.style.top = (event.y - 10) + 'px';
			this.icon.style.left = (event.x + 10) + 'px';
		});
	}

	static calcularTransicao(origem, destino) {

		let a = get(origem);
		let b = get(destino);

		let transicao = {};
		transicao.origem = origem;
		transicao.destino = destino;

		origem = (a.container == '.bpmn-diagram' ? { x: 0, y: 0 } : get(a.container.substring(1)));
		destino = (b.container == '.bpmn-diagram' ? { x: 0, y: 0 } : get(b.container.substring(1)));

		let distancia = new Array();
		let l = 0, m = 0;

		let po, pd;

		if ((a.container != '.bpmn-diagram' && b.container != '.bpmn-diagram') &&
			(a.container.substring(0, a.container.indexOf('lane')) != b.container.substring(0, b.container.indexOf('lane')))) {

			po = get(get(a.container.substring(1)).container.substring(1));
			pd = get(get(b.container.substring(1)).container.substring(1));

			for (let i = 0; i < a.points.length; i++) {

				distancia[i] = new Array();
				for (let j = 0; j < b.points.length; j++) {

					distancia[i][j] = Math.sqrt(Math.pow(((a.x + a.points[i].x + origem.x + po.x - po.dx) - (b.x + b.points[j].x + destino.x + pd.x - pd.dx)), 2) + Math.pow(((a.y + a.points[i].y + origem.y + po.y - po.dy) - (b.y + b.points[j].y + destino.y + pd.y - pd.dy)), 2));
					if (distancia[i][j] < distancia[l][m]) {
						l = i;
						m = j;
					}
				}
			}
		}
		else {
			for (let i = 0; i < a.points.length; i++) {

				distancia[i] = new Array();
				for (let j = 0; j < b.points.length; j++) {

					distancia[i][j] = Math.sqrt(Math.pow(((a.x + a.points[i].x + origem.x) - (b.x + b.points[j].x + destino.x)), 2) + Math.pow(((a.y + a.points[i].y + origem.y) - (b.y + b.points[j].y + destino.y)), 2));
					if (distancia[i][j] < distancia[l][m]) {
						l = i;
						m = j;
					}
				}
			}
		}

		transicao.pontoOrigem = l;
		transicao.pontoDestino = m;
		//console.log(l + " --- " + m);
		transicao.id = '#transicao' + diagram.numElements;

		return transicao;
	}

	static desenharTransicao(origem, destino) {

		let a = get(origem);
		let b = get(destino);

		let transicao = BPMNDiagram.calcularTransicao(origem, destino);

		origem = (a.container == '.bpmn-diagram' ? { x: 0, y: 0 } : get(a.container.substring(1)));
		destino = (b.container == '.bpmn-diagram' ? { x: 0, y: 0 } : get(b.container.substring(1)));

		let container;
		let type;
		if (a.container == b.container) container = BPMNSettings.diagramSelector;
		else container = `${get(a.container.substring(1)).container} .content-swim`;

		if (a.id.startsWith("#textannotation") || b.id.startsWith("#textannotation")) {
			d3.select(container).append('polyline')
				.attr('id', 'transicao' + diagram.numElements)
				.attr('stroke-dasharray', '4,4')
				.attr('points', `${a.x + a.points[transicao.pontoOrigem].x},${a.y + a.points[transicao.pontoOrigem].y} ${b.x + b.points[transicao.pontoDestino].x},${b.y + b.points[transicao.pontoDestino].y}`)
				.attr('stroke', '#000')
				.attr('stroke-width', '4');

			type = "association";
		}
		//Não existe transição entre dois elementos do tipo "data"
		else if (a.id.startsWith("#data") && b.id.startsWith("#data")) {
			return;
		}
		else if (a.id.startsWith("#data") || b.id.startsWith("#data")) {
			d3.select(container).append('polyline')
				.attr('id', 'transicao' + diagram.numElements)
				.attr('stroke-dasharray', '1.5,3')
				.attr('points', `${a.x + a.points[transicao.pontoOrigem].x},${a.y + a.points[transicao.pontoOrigem].y} ${b.x + b.points[transicao.pontoDestino].x},${b.y + b.points[transicao.pontoDestino].y}`)
				.attr('stroke', '#000')
				.attr('stroke-width', '2.1')
				.attr('marker-end', 'url(#arrow)');

			type = "data-association";
		}
		else if (a.container.substring(0, a.container.indexOf('lane')) != b.container.substring(0, b.container.indexOf('lane'))) {

			let ox, oy, dx, dy;

			let po = get(get(a.container.substring(1)).container.substring(1));
			let pd = get(get(b.container.substring(1)).container.substring(1));

			ox = a.x + a.points[transicao.pontoOrigem].x + origem.x + po.x - po.dx;
			oy = a.y + a.points[transicao.pontoOrigem].y + origem.y + po.y - po.dy;
			dx = b.x + b.points[transicao.pontoDestino].x + destino.x + pd.x - pd.dx;
			dy = b.y + b.points[transicao.pontoDestino].y + destino.y + pd.y - pd.dy;

			container = diagram.selector;

			d3.select(`${container} .transition`).append('polyline')
				.attr('id', 'transicao' + diagram.numElements)
				.attr('stroke-dasharray', '6,6')
				.attr('points', `${ox},${oy} ${dx},${dy}`)
				.attr('stroke', '#000')
				.attr('stroke-width', '1.8')
				.attr('marker-end', 'url(#arrow-white)');

			d3.select(`${container} .transition`).raise();

			type = "message-flow";
		}
		else {


			let ox, oy, dx, dy;
			if (a.container == b.container) {
				ox = a.x + a.points[transicao.pontoOrigem].x;
				oy = a.y + a.points[transicao.pontoOrigem].y;
				dx = b.x + b.points[transicao.pontoDestino].x;
				dy = b.y + b.points[transicao.pontoDestino].y;
			}
			else {

				ox = a.x + a.points[transicao.pontoOrigem].x + origem.x;
				oy = a.y + a.points[transicao.pontoOrigem].y + origem.y;
				dx = b.x + b.points[transicao.pontoDestino].x + destino.x;
				dy = b.y + b.points[transicao.pontoDestino].y + destino.y;
			}

			d3.select(container).append('polyline')
				.attr('id', 'transicao' + diagram.numElements)
				.attr('points', `${ox},${oy} ${dx},${dy}`)
				.attr('stroke', '#000')
				.attr('stroke-width', '1.8')
				.attr('marker-end', 'url(#arrow)');

			type = "sequence-flow"
		}



		//Adiciona a nova transição a lista de transições de cada elemento
		//let t = document.querySelector('#' + 'transicao' + diagram.numElements);
		a.transicoesOrigem.push('transicao' + diagram.numElements);
		b.transicoesDestino.push('transicao' + diagram.numElements);

		diagram.numElements++;
		transicao.tipo = type;
		window.elements.push(transicao);

		BPMNDiagram.refreshListener();
	}

	static verificarLimites(entry) {
		let container;

		if (entry.item.container == '.bpmn-diagram') {
			container = {
				width: d3.select('svg').attr('width'),
				height: d3.select('svg').attr('height')
			}
		}
		else {
			container = get(entry.item.container.substring(1));
		}


		if (0 < entry.x && entry.x < container.width && 0 < entry.y && entry.y < container.height) {
			return true;
		}

		return false;
	}

	static drag(a) {

		let alvo = get(a._groups[0][0].id);

		if (BPMNDiagram.verificarLimites({
			item: alvo,
			x: d3.event.x,
			y: d3.event.y
		}) == false) return;

		a.attr('transform', `translate(${d3.event.x - alvo.dx},${d3.event.y - alvo.dy})`);
		alvo.x = d3.event.x;
		alvo.y = d3.event.y;

		BPMNDiagram.corrigirTransições(alvo);
	}

	static posicionarContainer(alvo, tc) {

		let ponto, po, pc;

		if ((alvo.container != diagram.selector && tc.container != diagram.selector) &&
			(alvo.container.substring(0, alvo.container.indexOf('lane')) != tc.container.substring(0, tc.container.indexOf('lane')))) {

			po = get(get(alvo.container.substring(1)).container.substring(1));
			pc = get(alvo.container.substring(1));

			ponto = {
				x: po.x - po.dx + pc.x,
				y: po.y - po.dy + pc.y
			}
		}
		else if (alvo.container != tc.container) {

			ponto = get(alvo.container.substring(1));
		}
		else {
			ponto = { x: 0, y: 0 };
		}

		return ponto;
	}

	/**
	* Corrige o posicionamento de todas as transições de todos os elementos pertence a swimlane.
	* @param BPMNSwimlane alvo Instância da classe do objeto alvo
	*/
	static corrigirTransições(alvo) {

		let ponto, pontoOposto;

		for (let x in alvo.transicoesOrigem) {

			let t = get(alvo.transicoesOrigem[x]);

			//fazer o calculo das posições novas e atribuir
			let tc = get(t.destino);

			let transicao = BPMNDiagram.calcularTransicao(t.origem, t.destino);
			t.pontoOrigem = transicao.pontoOrigem;
			t.pontoDestino = transicao.pontoDestino;

			ponto = BPMNDiagram.posicionarContainer(alvo, tc);
			pontoOposto = BPMNDiagram.posicionarContainer(tc, alvo);

			//let points = d3.select('#' + alvo.transicoesOrigem[x]).attr('points').split(" ");
			d3.select('#' + alvo.transicoesOrigem[x])
				.attr('points', `${alvo.x + alvo.points[t.pontoOrigem].x + ponto.x},${alvo.y + alvo.points[t.pontoOrigem].y + ponto.y} 
								 ${tc.x + tc.points[t.pontoDestino].x + pontoOposto.x},${tc.y + tc.points[t.pontoDestino].y + pontoOposto.y}
				`);
		}

		for (let x in alvo.transicoesDestino) {

			let t = get(alvo.transicoesDestino[x]);
			let tc = get(t.origem);
			//console.log(tc)
			let transicao = BPMNDiagram.calcularTransicao(t.origem, t.destino);
			t.pontoOrigem = transicao.pontoOrigem;
			t.pontoDestino = transicao.pontoDestino;

			ponto = BPMNDiagram.posicionarContainer(alvo, tc);
			pontoOposto = BPMNDiagram.posicionarContainer(tc, alvo);

			//let points = d3.select('#' + alvo.transicoesDestino[x]).attr('points').split(" ");
			//${tc.x + tc.points[t.pontoDestino].x + ponto.x},${tc.y + tc.points[t.pontoDestino].y + ponto.y}
			d3.select('#' + alvo.transicoesDestino[x])
				.attr('points', `${tc.x + tc.points[t.pontoOrigem].x + pontoOposto.x},${tc.y + tc.points[t.pontoOrigem].y + pontoOposto.y}  
								 ${alvo.x + alvo.points[t.pontoDestino].x + ponto.x},${alvo.y + alvo.points[t.pontoDestino].y + ponto.y}
				`);

		}
	}

	static reposicionarTransicoesPool(alvo) {

		d3.selectAll(`${alvo.id} .content-swim .lane`).each(function () {

			d3.select(this).selectAll(`.content-lane .item`).each(function () {

				BPMNDiagram.corrigirTransições(get(d3.select(this)._groups[0][0].id));
			});

		});
	}

	static refreshListener() {

		d3.selectAll('.item')
			.call(d3.drag()
				.on('start', function () {
					BPMNDiagram.dragStartX = d3.event.x;
					BPMNDiagram.dragStartY = d3.event.y;
					d3.select(this).raise().style('cursor', 'move');
				})
				.on('drag', function () {

					BPMNDiagram.drag(d3.select(this));

					d3.select(`${diagram.selector} .transition`).raise();

					BPMNDiagram.reposicionarTransicoesPool(get(d3.select(this)._groups[0][0].id));

				})
				.on('end', function () {

					d3.select(this).style('cursor', 'normal');

				})
			);

		d3.selectAll('.item, .lane')
			.on('focus', function () {

				//sugestões:
				//melhorar usabilidade da interface observando o uso do foco

				//d3.select(`${diagram.selector} .transition`).raise();				
			})
			.on('click', function () {

				d3.event.stopPropagation();
				let id = d3.select(this)._groups[0][0].id;

				//para a inserção de elementos em lanes de swimlanes
				if (id.indexOf('lane') != -1) {

					if (diagram.newElement != null) {

						if (diagram.newElement.icon == 'participant') return;
						//e a divisão da piscina em uma ou mais raias
						//['lane-divide-three', 'lane-divide-two', 'lane-insert-below', 'lane-insert-above']
						if (diagram.newElement.icon == 'lane-insert-below') {

							let swim = get(`${id}`.substring(0, `${id}`.indexOf('lane')));
							swim.addLaneBelow();
						}
						else if (diagram.newElement.icon == 'lane-insert-above') {

							let swim = get(`${id}`.substring(0, `${id}`.indexOf('lane')));
							swim.addLaneAbove();
						}
						else {
							//elaborar a inserção de elementos
							let box = BPMNSettings.diagramSelector;
							BPMNSettings.diagramSelector = `#${id} .content-lane`;

							let container = get(id);
							let parent = get(`${id}`.substring(0, `${id}`.indexOf('lane')));

							let offsetX, offsetY;

							if (document.querySelector(BPMNSettings.diagramSelector).transform.baseVal[0] != null) {
								offsetX = document.querySelector(BPMNSettings.diagramSelector).transform.baseVal[0].matrix.e;
								offsetY = document.querySelector(BPMNSettings.diagramSelector).transform.baseVal[0].matrix.f;
							}
							else {
								offsetX = offsetY = 0;
							}

							if (document.querySelector(diagram.selector).transform.baseVal[0] != null) {
								offsetX += document.querySelector(diagram.selector).transform.baseVal[0].matrix.e;
								offsetY += document.querySelector(diagram.selector).transform.baseVal[0].matrix.f;
							}

							let element = diagram.createElement(d3.event.x - (parent.x - parent.dx + container.x + offsetX), d3.event.y - (parent.y - parent.dy + container.y + offsetY), diagram.numElements++);
							element.container = container.id;
							window.elements.push(element);
							BPMNSettings.diagramSelector = box;
						}

						diagram.setIconMouse();
						diagram.newElement = null;
					}
				}

				if (id.indexOf('pool') != -1) {
					d3.select(`${diagram.selector} .transition`).raise();
					return;
				}
				if (window.listaTransicao[0] != id) {
					window.listaTransicao.push(id);
				}

				if (window.listaTransicao.length == 2) {

					let box = BPMNSettings.diagramSelector;
					let parent = get(id);

					if (parent.container != '.bpmn-diagram') BPMNSettings.diagramSelector = `${parent.container} .content-lane`;
					else BPMNSettings.diagramSelector = `${diagram.selector} .transition`;
					BPMNDiagram.desenharTransicao(window.listaTransicao[0], window.listaTransicao[1]);
					BPMNSettings.diagramSelector = box;
					//esvaziando a lista
					window.listaTransicao = [];
				}

				d3.select(`${diagram.selector} .transition`).raise();
			})
			.on('contextmenu', function () {

				d3.event.preventDefault();
				d3.event.stopPropagation();
				window.menuContexto(d3.event.x, d3.event.y, "element", d3.select(this).attr('id'));

				d3.select(`${diagram.selector} .transition`).raise();
			})


		d3.selectAll('[id^="transicao"]')
			.on('contextmenu', function () {

				d3.event.preventDefault();
				d3.event.stopPropagation();

				window.menuContexto(d3.event.x, d3.event.y, "transition", d3.select(this).attr('id'));
				BPMNDiagram.refreshListener();
			})


		let x, y;
		d3.select(diagram.selector)
			.call(d3.drag()
				.on('start', function () {
					x = d3.event.x; y = d3.event.y;

					let transform = document.querySelector(diagram.selector).transform.baseVal[0];
					if (transform != null) {

						x -= transform.matrix.e;
						y -= transform.matrix.f;
					}
				})
				.on('drag', function () {

					d3.select(this).attr('transform', `translate(${d3.event.x - x},${d3.event.y - y})`);

					window.elements.filter((e) => { return e.transicoesOrigem != null && e.transicoesOrigem.length > 0; }).forEach((e) => {

						BPMNDiagram.reposicionarTransicoesPool(e);
					});

				})
				.on('end', function () {

					let offset = [
						d3.event.x - x,
						d3.event.y - y
					];

					let diffBounds = [
						0, //document.querySelector(diagram.selector).width.baseVal.value - width,
						0  //document.querySelector(diagram.selector).height.baseVal.value - height
					];

					let width = Number.parseInt(d3.select(this).attr('width'));
					let height = Number.parseInt(d3.select(this).attr('height'));

					if (offset[0] > 0 || offset[0] + width < window.innerWidth) {
						d3.select(this).attr('width', width + Math.abs(offset[0]));
						if (offset[0] > 0) {
							d3.select(this).attr('transform', `translate(0,${offset[1]})`);
							offset[0] = 0;
							diffBounds[0] = document.querySelector(diagram.selector).width.baseVal.value - width;
						}
						
					}

					if (offset[1] > 0 || offset[1] + height < window.innerHeight) {
						d3.select(this).attr('height', height + Math.abs(offset[1]));
						if (offset[1] > 0) {
							d3.select(this).attr('transform', `translate(${offset[0]}, 0)`);
							offset[1] = 0;
							diffBounds[1] = document.querySelector(diagram.selector).height.baseVal.value - height;
						}
					}

					offset = [
						d3.event.x - x,
						d3.event.y - y
					];

					d3.selectAll(`svg > .item`).each(function () {

						let matrix = document.querySelector(`#${d3.select(this).attr('id')}`).transform.baseVal[0].matrix;
						let off = [
							matrix.e, matrix.f
						];

						d3.select(this).attr('transform', `translate(${off[0] + (offset[0] > 0 ? offset[0] : 0)},${off[1] + (offset[1] > 0 ? offset[1] : 0)})`);

					});

					
					let deslocamento;
					let boundTransition = document.querySelector('.transition').transform.baseVal[0];
					if (boundTransition != null) {
						deslocamento = [
							boundTransition.matrix.e,
							boundTransition.matrix.f
						];
					}
					else {
						deslocamento = [0,0];
					}

					d3.select('.transition')
					.attr('transform', `translate(${diffBounds[0] + deslocamento[0]},${diffBounds[1] + deslocamento[1]})`);
				})
			);
	}


	static createBoxMenu() {

		let menuPrincipal = [
			{ className: 'BPMNEvent', value: BPMNEvent.START_EVENT_NONE },
			{ className: 'BPMNActivity', value: BPMNActivity.TASK_NONE },
			{ className: 'BPMNGateway', value: BPMNGateway.GATEWAY_NONE },
			{ className: 'BPMNLane', value: BPMNLane.PARTICIPANT },
			{ className: 'BPMNData', value: BPMNData.DATA_OBJECT },
			{ className: 'BPMNTextAnnotation', value: BPMNTextAnnotation.TEXT_ANNOTATION }
		];

		let subMenu = [
			[
				BPMNEvent.START_EVENT_NONE,
				BPMNEvent.START_EVENT_NON_INTERRUPTING_PARALLEL_MULTIPLE,
				BPMNEvent.START_EVENT_CONDITION,
				BPMNEvent.START_EVENT_MESSAGE,
				BPMNEvent.START_EVENT_NON_INTERRUPTING_MESSAGE,
				BPMNEvent.START_EVENT_NON_INTERRUPTING_TIMER,
				BPMNEvent.START_EVENT_ESCALATION,
				BPMNEvent.START_EVENT_NON_INTERRUPTING_MULTIPLE,
				BPMNEvent.START_EVENT_PARALLEL_MULTIPLE,
				BPMNEvent.START_EVENT_SIGNAL,
				BPMNEvent.START_EVENT_MULTIPLE,
				BPMNEvent.START_EVENT_NON_INTERRUPTING_CONDITION,
				BPMNEvent.START_EVENT_COMPENSATION,
				BPMNEvent.START_EVENT_NON_INTERRUPTING_SIGNAL,
				BPMNEvent.START_EVENT_ERROR,
				BPMNEvent.START_EVENT_TIMER,
				BPMNEvent.START_EVENT_NON_INTERRUPTING_ESCALATION,
				BPMNEvent.INTERMEDIATE_EVENT_NONE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_NON_INTERRUPTING_TIMER,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_SIGNAL,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_TIMER,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_MESSAGE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_NON_INTERRUPTING_ESCALATION,
				BPMNEvent.INTERMEDIATE_EVENT_THROW_MESSAGE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_CANCEL,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_PARALLEL_MULTIPLE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_ERROR,
				BPMNEvent.INTERMEDIATE_EVENT_THROW_COMPENSATION,
				BPMNEvent.INTERMEDIATE_EVENT_THROW_LINK,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_NON_INTERRUPTING_MULTIPLE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_NON_INTERRUPTING_MESSAGE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_COMPENSATION,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_CONDITION,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_LINK,
				BPMNEvent.INTERMEDIATE_EVENT_THROW_ESCALATION,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_NON_INTERRUPTING_SIGNAL,
				BPMNEvent.INTERMEDIATE_EVENT_THROW_SIGNAL,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_NON_INTERRUPTING_PARALLEL_MULTIPLE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_ESCALATION,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_MULTIPLE,
				BPMNEvent.INTERMEDIATE_EVENT_THROW_MULTIPLE,
				BPMNEvent.INTERMEDIATE_EVENT_CATCH_NON_INTERRUPTING_CONDITION,
				BPMNEvent.END_EVENT_NONE,
				BPMNEvent.END_EVENT_CANCEL,
				BPMNEvent.END_EVENT_MULTIPLE,
				BPMNEvent.END_EVENT_COMPENSATION,
				BPMNEvent.END_EVENT_ERROR,
				BPMNEvent.END_EVENT_TERMINATE,
				BPMNEvent.END_EVENT_MESSAGE,
				BPMNEvent.END_EVENT_ESCALATION,
				BPMNEvent.END_EVENT_SIGNAL,
				BPMNEvent.END_EVENT_LINK
			],
			[
				BPMNActivity.MANUAL_TASK,
				BPMNActivity.SCRIPT_TASK,
				BPMNActivity.SEND_TASK,
				BPMNActivity.SERVICE_TASK,
				BPMNActivity.USER_TASK,
				BPMNActivity.BUSINESS_RULE_TASK,
				BPMNActivity.RECEIVE_TASK,
				BPMNActivity.TASK_NONE
			],
			[
				BPMNGateway.GATEWAY_PARALLEL,
				BPMNGateway.GATEWAY_NONE,
				BPMNGateway.GATEWAY_OR,
				BPMNGateway.GATEWAY_COMPLEX,
				BPMNGateway.GATEWAY_XOR,
				BPMNGateway.GATEWAY_EVENTBASED
			],
			[
				BPMNLane.PARTICIPANT,
				//BPMNLane.LANE_DIVIDE_THREE,
				//BPMNLane.LANE_DIVIDE_TWO,
				BPMNLane.LANE_INSERT_BELOW,
				BPMNLane.LANE_INSERT_ABOVE
			],
			[
				BPMNData.DATA_STORE,
				BPMNData.DATA_INPUT,
				BPMNData.DATA_OBJECT,
				BPMNData.DATA_OUTPUT
			],
			[
				BPMNTextAnnotation.TEXT_ANNOTATION
			]
		];


		let mouseBox = document.querySelector('.mouse-box');

		for (let e in menuPrincipal) {

			let div = document.createElement('div');
			div.className = 'box-item';

			let i = document.createElement('i');
			i.className = 'bpmn-icon-' + menuPrincipal[e].value.icon;
			i.title = menuPrincipal[e].value.title.en;

			div.appendChild(i);
			mouseBox.appendChild(div);

			let submenuContext = document.createElement('div');
			submenuContext.className = 'sub-menu';

			for (let a in subMenu[e]) {

				let icon = document.createElement('i');
				icon.className = 'item-context bpmn-icon-' + subMenu[e][a].icon;
				icon.setAttribute("data-class", menuPrincipal[e].className);
				icon.setAttribute("data-link", subMenu[e][a].icon);
				icon.title = subMenu[e][a].title.en;
				icon.addEventListener('click', () => {

					let object = eval(icon.getAttribute("data-class") + "." + icon.getAttribute("data-link").toUpperCase().replace(/-/g, "_"));
					diagram.setIconMouse(object);
				});

				submenuContext.appendChild(icon);
			}

			div.appendChild(submenuContext);
		}
	}




}



