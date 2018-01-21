let diagram;

window.onload = function () {


	let selector = ".bpmn-diagram";
	diagram = new BPMNDiagram(selector);



	BPMNSettings.initialize();
	BPMNSettings.diagramSelector = selector;


	d3.select(selector)
	.style('background-color','#fff')
	.on("dblclick.zoom", null);

	BPMNDiagram.language = "pt-br";

	BPMNDiagram.protocol = null;
	BPMNDiagram.experiment = {
		nome: "",
		descricao: "",
		tema: "",
		areatecnica: "",
		tipo: "",
		dominio: "",
		idioma: "",
		replicacao: 0,
		diagrama: {},
		cronogramas: [],
		experimentadores: [],
		definicao: {},
		questoes: [],
		hipoteses: [],
		variaveisDependentes: [],
		variaveisIndependentes: [],
		participantes: [],
		artefatos: [],
		validades: [],
		grupos: [],
		defeitos: [],
		interpretacoes: [],
		conclusoes: []
	}


	loadGUI();

	$('.painel-lateral .button').trigger('click');

	//diagram.setIconMouse(BPMNEvent.START_EVENT_NONE);

}
