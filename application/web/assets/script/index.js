let diagram; 

window.onload = function(){


	let selector = ".bpmn-diagram";

	diagram = new BPMNDiagram(selector);
	

	BPMNSettings.initialize();
	BPMNSettings.diagramSelector = selector;
	

	d3.select("svg").on("dblclick.zoom", null);

        BPMNDiagram.protocol = null;
        BPMNDiagram.experiment = null;


	loadGUI();

	$('.painel-lateral .button').trigger('click');

	//diagram.setIconMouse(BPMNEvent.START_EVENT_NONE);

}
