function loadGUI() {


	BPMNDiagram.createBoxMenu();
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////

	/**
	 * Mostra o painel de abrir arquivo
	 */
	let box01 = document.querySelector('.box-01');

	document.querySelector('.open-file').addEventListener('click', () => {

		box01.style.display = 'block';
	});
        
        document.querySelector('.open-option-open-dialog').addEventListener('click', (event) => {
            
            
                event.stopPropagation();
                event.preventDefault();
                
       
                
                let opcao = document.querySelector('[name="opcao-abrir"]').checked;
                //console.log(opcao);
                //console.log(event.dataTransfer.files);
                if (opcao) {
                    //pacote de laboratório
                    let valor = document.querySelector('[name="fileInput"]').files[0];
                    let reader = new FileReader();
                    
                    reader.onloadend = function(){

                        if (reader.readyState == 2) {
                            
                            let data = reader.result;
                            
                            $.ajax({
                        	url: ( window.location.href + "/abrirPacote "),
                                type: "post",
                                async: true,
                                data: data,
                                cache: false
                            })
                            .done(function(data){
                                
                                //console.log(data);
                                data = JSON.parse(data);
                                //console.log(data);
                        
                                let dados = {};
                        
                                dados.protocol = ( data.box.protocol == "" ? JSON.parse("{\"element\": []}") : data.box.protocol);
                                
                                BPMNDiagram.experiment = data.box.experimento;
                                BPMNDiagram.protocol = dados.protocol;
                                
                                diagram.import(JSON.stringify(dados));
                                    
                            })
                            .fail(function(){
                                    console.log("pãã");
                            });
                        }
                    };
                    
                    reader.readAsBinaryString(valor);     
                    
                }
                else{
                    //protocolo de experimentação
                    
                    let valor = document.querySelector('[name="fileInput"]').files[0];
                    let reader = new FileReader();
                    
                    reader.onloadend = function(){

                        if (reader.readyState == 2) {
                            
                            let data = reader.result;
                            
                            $.ajax({
                        	url: ( window.location.href + "/abrirProtocolo"),
                                type: "post",
                                async: true,
                                data: data,
                                cache: false
                            })
                            .done(function(data){

                                    diagram.import(data);
                                    
                            })
                            .fail(function(){
                                    console.log("pãã");
                            });
                        }
                    };
                    
                    reader.readAsBinaryString(valor);

                }
            
            
                box01.style.display = 'none';
        });
        

	document.querySelector('.cancel-option-open-dialog').addEventListener('click', () => {

		box01.style.display = 'none';
	});

	//////////////////////////////////////////////
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	/**
	 * Mostra o painel de salvar arquivo
	 */
	let box02 = document.querySelector('.box-02');

	document.querySelector('.save-file').addEventListener('click', () => {

		/*let blob = new Blob(["Hello, world!"], {type: "text/plain;charset=utf-8"});
		saveAs(blob, "hello world.txt");*/
		box02.style.display = 'block';

	});

	document.querySelector('.save-option-save-dialog').addEventListener('click', () => {

		let opcao = document.querySelector('[name="opcao-salvar"]').checked;
		
		if (opcao) {
			//Pacote de laboratório
                        let struct = diagram.export();
                        let data = {};
                
                        data.protocol = struct;
                        data.experimento = BPMNDiagram.experiment;
                        
                         let nome = document.querySelector("[name='nome-arquivo']").value;
                        
                        if (nome == "") {
                            confirm("O nome do arquivo deve ser preenchido!");
                            return;
                        }
                        
                        if (nome.indexOf(".xml") == -1) {
                            nome = `${nome}.xml`;
                        }
              
                        $.ajax({
                        	url: ( window.location.href + "/salvarPacote"),
                                type: "post",
                                async: true,
                                data: `{"box": ${JSON.stringify(data)}}`,
                                cache: false
                        })
                        .done(function(data){
                 
                                saveAs(new Blob([data]), nome);
                        })
                        .fail(function(){
                                console.log("pãã");
                        });
		}
		else{
			//Protocolo de Experimentação
                        
                        let struct = diagram.export();
                        let data = JSON.stringify(struct);

			
                        let nome = document.querySelector("[name='nome-arquivo']").value;
                        
                        if (nome == "") {
                            confirm("O nome do arquivo deve ser preenchido!");
                            return;
                        }
                        
                        if (nome.indexOf(".bpmn") == -1) {
                            nome = `${nome}.bpmn`;
                        }
              
                        $.ajax({
                        	url: ( window.location.href + "/salvarProtocolo"),
                                type: "post",
                                async: true,
                                data: data,
                                cache: false
                        })
                        .done(function(data){
                 
                                saveAs(new Blob([data]), nome);
                        })
                        .fail(function(){
                                console.log("pãã");
                        });
		}


		box02.style.display = 'none';
        

	});

	document.querySelector('.cancel-option-save-dialog').addEventListener('click', () => {

		box02.style.display = 'none';
	});



	//////////////////////////////////////////////
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	/**
	 * Carrega um novo diagrama vazio
	 */
	document.querySelector('.new-file').addEventListener('click', () => {

		if (window.elements.length == 0) window.location.reload();
		else {

			if (confirm("Você tem certeza que deseja sair?")) {
				window.location.reload();
			}
		}
	});

	document.querySelector('.export-file').addEventListener('click', () => {


		if (window.elements.length == 0) {
			confirm("Não há elementos no diagrama");
			return;
		}

		html2canvas($(diagram.selector), {
			onrendered: function (canvas) {


				canvas.toBlob(function (blob) {
					saveAs(blob, "diagrama.png");
				});

			}
		});
	});
        
        


	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////

        $('.box-descricao .close').click(function(){
            
            $('.box-descricao').animate({
                top: -420
            });
        });

	$('.painel-lateral .button').click(function () {

		if ($('.painel-lateral').css('right') == '-253px') {
			$('.painel-lateral').animate({
				right: 0
			});

			$(this).html('▶');
		}
		else {
			$('.painel-lateral').animate({
				right: -253
			});

			$(this).html('◄');
		}
	});

	$('.painel-lateral .button-save').click(function () {

                if (BPMNDiagram.painelVinculacao == true) {
                    
                    let lista = [];
                    //salvar a vinculação
                    console.log(document.querySelectorAll('[name="item-check"]'));
                    
                    document.querySelectorAll('[name="item-check"]')
                    .forEach((e) => {
                        if (e.checked) lista.push(e.value)
                    })
                    
                    
                    //console.log(lista)
                    let elemento = get(Interface.id);
                    
                    
                    elemento.vinculos = []; 
                    
                    lista.forEach((a) => {
                        
                       
                            
                            elemento.vinculos.push(a);
         
                    });
                    
                    BPMNDiagram.painelVinculacao = false;
                    
                }
                else{
                    
                    let lista = [];

                    $('.painel-lateral input').each(function () {

                            lista.push($(this).val());
                    });

                    let objeto = get(Interface.id);
                    objeto.atualizar(lista);

                }
		
                           
                $('.painel-lateral .button').trigger('click');

                $('.painel-lateral > .options').empty();
                $('.painel-lateral > .options').append('<p>Sem seleção</p>');

                $('.painel-lateral > .button-save').css('display', 'none');

	});

	$('.context-menu').mouseleave(function () {
		$(this).css('display', 'none');
	});

};


function get(id) {

	return window.elements.filter((value) => { return value.id == "#" + id })[0];
}

class Interface {

	static remover(id) {

		let objeto = get(id);

		let lista = [];
		for (let x in objeto.transicoesOrigem) {
			lista.push(objeto.transicoesOrigem[x]);
			$(`#${objeto.transicoesOrigem[x]}`).remove();
		}

		for (let x in objeto.transicoesDestino) {
			lista.push(objeto.transicoesDestino[x]);
			$(`#${objeto.transicoesDestino[x]}`).remove();
		}

		window.elements.filter((el) => { return el.transicoesOrigem != null }).forEach((e) => {
			e.transicoesOrigem = e.transicoesOrigem.filter((a) => { return !lista.includes(a) });
			e.transicoesDestino = e.transicoesDestino.filter((b) => { return !lista.includes(b) });
		});


		window.elements = window.elements.filter((a) => { return id != a.id.substring(1); });
		window.elements = window.elements.filter((a) => { return a.id.indexOf('transicao') == -1 || (a.origem != id && a.destino != id) })

		$(`#${id}`).remove();
	}

	static modificar(id) {

		Interface.id = id;

		painelLateral(id);

		$('.painel-lateral .button').trigger('click');
	}
        
        static vincular(id){
                
                Interface.id = id;
                painelVinculação(id)
                $('.painel-lateral .button').trigger('click');
                
                
        }
        
        static exibir(id){
            
                Interface.id = id;
                
                $('.box-descricao > .content').empty();
                
                exibirDescricao(id);
                

                
                $('.box-descricao').animate({
                    top: ($(document).height()/2-200)
                });
        }
}

function exibirDescricao(id){
    
    let element = get(id);
    
    if (element.vinculos == null || element.vinculos.length == 0) {
        
        d3.select('.box-descricao > .content')
                .append('p')
                .style('line-height', "300px")
                .style('vertical-align','middle')
                .style('text-align','center')
                .text('Não há elementos do experimento vinculados.')
    }
    else{
        
        element.vinculos.forEach((a) => {
            getObjectById(BPMNDiagram.experiment, a);
            
            d3.select('.box-descricao > .content').append('div')
                    .style('padding','15px 25px')
                    .attr('class', `item-${result.id} box-item`)
            
            //o resultado fica na variavel result
            Object.keys(result).forEach((ele) => {
                
                if (ele == "id") {
                    d3.select(`.item-${result.id}`)
                            .append('h1')
                            .style('font-size','20px')
                            .text(`ID: #${result.id}`)
                }
                else{
                    d3.select(`.item-${result.id}`)
                            .append('p')
                            .style('font-size','14px')
                            .style('padding', '3px 0')
                            .style('margin-left','35px')
                            .text(`${ele}: ${result[ele]}`)
                }
                
            }, result)
        });
    }
}

var result;

function getObjectById(object, id){
    

    if (object.hasOwnProperty("id")) {
 
        if (object["id"] == id) {
 
            result = object;
            return;
        } 
    }
    
    if (!isObject(object)) return; 
    
    Object.keys(object).forEach((a) => {
       
        getObjectById(object[a], id);
    });
    
}


function isObject(a) {
    return (!!a) && (a.constructor === Object);
}

function menuContexto(x, y, categoria, id) {


	d3.select('.context-menu').html('').style('display', 'block');
	d3.select('.context-menu').style('top', `${y}px`).style('left', `${x}px`);

	d3.select('.context-menu')
		.append('li')
		.attr('class', 'modificar')
		.text('Modificar descrição')
		.on('click', function () {
			Interface.modificar(id);
		});

	//Se for uma lane não adiciona botão de remover
	if (id.indexOf('lane') == -1) {
		
                d3.select('.context-menu')
		.append('li')
		.attr('class', 'remover')
		.text('Remover')
		.on('click', function () {

			Interface.remover(id);
			$('.context-menu').trigger('mouseleave');
		});
	}
        
        if (BPMNDiagram.experiment != null && id.indexOf('lane') == -1 && id.indexOf('pool')){ 
            
            d3.select('.context-menu')
                .append('li')
                .attr('class','vincular')
                .text('Vincular ...')
                .on('click', () => {
                        
                        
                        Interface.vincular(id);
                
                });
             
                    
            d3.select('.context-menu')
                    .append('li')
                    .attr('class','exibir')
                    .text('Exibir detalhes')
                    .on('click', () => {
                        
                        Interface.exibir(id);
                    })
        }
}

function painelLateral(id) {

	let painel = d3.select('.painel-lateral > .options');

	$('.painel-lateral > .options').empty();

	let element = get(id);

	for (let a in element.attributes) {

		painel.append('label').text(a);
		painel.append('input').attr('value', element.attributes[a]);

	}

	$('.painel-lateral .button-save').css('display', 'inline');
}

function painelVinculação(id) {

	//let painel = d3.select('.painel-lateral > .options');

        BPMNDiagram.painelVinculacao = true;

	$('.painel-lateral > .options').empty();

	//painel de vinculo
        
        //console.log(BPMNDiagram.experiment)
   
        collapse(BPMNDiagram.experiment, '.painel-lateral > .options');

	$('.painel-lateral .button-save').css('display', 'inline');
}

function collapse(object, container){
        
        d3.select(container)
        .append('ul')
                .attr('id', 'tree')
                .attr('class', 'collapsibleList')
                .attr('style', 'margin-top: 30px; margin-left: 20px;')
        
        let num = 0;
        
        classify(object, d3.select('.collapsibleList'), ++num, "experimento");
        

}

function classify(object, container, num, nome){
    
    
    if (object.constructor === Array) {
 
        container
                .append('ul')
                .attr('id', `element${num}`)
                .append('li')
                .attr('class','list-array')
                .text(nome)
                .append('ul');
        
        
        
        $(`#element${num} > li`).click((event) => {
                
                
                event.stopPropagation();
                //console.log(`#element${num} > li > ul`)
                $(`#element${num-1} ul`).slideToggle();
         });
        
        let novo = d3.select(`#element${num} ul`);
        object.forEach((e, i) => {
            
            classify(e, novo, ++num, i);
        });
    }
    else if (object.constructor === Object ){
        
        container
                .append('ul')
                .attr('id', `element${num}`)
                .style('position','relative')
                .append('li')
                .attr('class','list-attribute')
                .text(nome)
                .append('ul');
        
                
        
                
        
        let novo = d3.select(`#element${num} ul`);
        
        for(let x in object){
            if (x == "diagrama") continue; 
            classify(object[x], novo, ++num, x);
        }
        
    }
    else{
        
        if (object === "") return;
        else if (nome == "id"){

            
            container.node().parentElement
                    .setAttribute('id', object);
            
            
            
            if (object.indexOf("experimento") == -1) d3.select(d3.select(`#${object}`)._groups["0"]["0"].parentElement)
                .append('input')
                .attr('type','checkbox')
                .attr('name','item-check')
                .attr('value', object);
            
            /*d3.select(`#${object}`).on('click', () => {
                    
                     
                    
                d3.select(`#${object} > li`)
                .style("background","#000")
                .style("color","#fff");
            });*/
            
            
            
            
            
            
            return;
        }
        
        container
                .append('li')
                .attr('class','list-item')
                .text(`${nome}: ${object}`);
                
    }
}