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

	BPMNDiagram.diagram = {
		Largura: window.innerWidth,
		Altura: window.innerHeight
	};

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

	//lista de arquivos das interpretacoes
	BPMNDiagram.interpretacoesArquivos = [];

	//lista de arquivos dos artefatos
	BPMNDiagram.artefatosArquivos = [];

	//Arquivos do pacote aberto
	BPMNDiagram.artefatosArquivosPacote = [];
	BPMNDiagram.interpretacoesArquivosPacote = [];
	
	loadGUI();

	$('.painel-lateral .button').trigger('click');

	//diagram.setIconMouse(BPMNEvent.START_EVENT_NONE);

}
