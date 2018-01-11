function loadGUI() {


    BPMNDiagram.createBoxMenu();

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////

    /**
     * Lista de opções formularios
     */
     $("[class*='li-']").click(function (event) {


        const [element, id] = event.target.className.split("-");
        $(`.block-${id}`).slideToggle();
        //console.log(id);
        switch(id){

            case "25":
            
            document.querySelector("[name='opcao-interpretacao']").innerHTML = "";
            BPMNDiagram.experiment
            .interpretacoes.map((e) => {return e.id})
            .forEach((e) => {

                let option = document.createElement('option');
                option.value = e;
                option.innerHTML = e;
                
                document.querySelector("[name='opcao-interpretacao']").appendChild(option);
            });

            break;

            case "20":
            
            document.querySelector("[name='opcao-questao']").innerHTML = "";
            BPMNDiagram.experiment
            .questoes.map((e) => {return e.id})
            .forEach((e) => {

                let option = document.createElement('option');
                option.value = e;
                option.innerHTML = e;
                
                document.querySelector("[name='opcao-questao']").appendChild(option);
            });

            break;

            case "10":
            
            document.querySelector("[name='nome-grupo-participante']").innerHTML = "";
            BPMNDiagram.experiment
            .grupos.map((e) => {return e.id})
            .forEach((e) => {

                let option = document.createElement('option');
                option.value = e;
                option.innerHTML = e;
                
                document.querySelector("[name='nome-grupo-participante']").appendChild(option);
            });

            break;

            case "05":
            
            document.querySelector("[name='select-artefato']").innerHTML = "";
            BPMNDiagram.experiment
            .artefatos.map((e) => {return e.id})
            .forEach((e) => {

                let option = document.createElement('option');
                option.value = e;
                option.innerHTML = e;
                
                document.querySelector("[name='select-artefato']").appendChild(option);
            });

            break;
        }
    });

    /**
     * Adicionar o dado recurso do bloco ao experimento
     */
     $("[class*='adicionar-block-']").click(function (event) {

        let number = event.target.className.split(" ")[1].split('-')[2];
        //console.log(number);

        let id = null;
        switch (number) {

            case "01":
            id = DataExperiment.block01();
            break;

            case "02":
            id = DataExperiment.block02();
            break;

            case "03":
            id = DataExperiment.block03();
            break;

            case "04":
            id = DataExperiment.block04();
            break;

            case "05":
            DataExperiment.block05();
            break;

            case "06":
            id = DataExperiment.block06();
            break;

            case "07":
            id = DataExperiment.block07();
            break;

            case "08":
            id = DataExperiment.block08();
            break;

            case "09":
            id = DataExperiment.block09();
            break;

            case "10":
            id = DataExperiment.block10();
            break;

            case "11":
            id = DataExperiment.block11();
            break;

            case "12":
            id = DataExperiment.block12();
            break;

            case "13":
            id = DataExperiment.block13();
            break;

            case "14":
            id = DataExperiment.block14();
            break;

            case "15":
            id = DataExperiment.block15();
            break;

            case "16":
            id = DataExperiment.block16();
            break;

            case "17":
            id = DataExperiment.block17();
            break;

            case "18":
            id = DataExperiment.block18();
            break;

            case "19":
            id = DataExperiment.block19();
            break;

            case "20":
            id = DataExperiment.block20();
            break;

            case "21":
            id = DataExperiment.block21();
            break;


            case "25":
            DataExperiment.block25();
            break;

        }
        //console.log(`.block-${id}`);
        $(`.block-${number}`).slideUp();

        if (id != null) {

            get(Interface.opcaoData).vinculos.push(id);
        }

    });
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

            reader.onloadend = function () {

                if (reader.readyState == 2) {

                    let data = reader.result;

                    $.ajax({
                        url: (window.location.href + "/abrirPacote "),
                        type: "post",
                        async: true,
                        data: data,
                        cache: false
                    })
                    .done(function (data) {

                            //console.log(data);
                            data = JSON.parse(data);
                            //console.log(data);

                            let dados = {};

                            dados.protocol = (data.box.protocol == "" ? JSON.parse("{\"element\": []}") : data.box.protocol);

                            BPMNDiagram.experiment = data.box.experimento;
                            BPMNDiagram.protocol = dados.protocol;

                            diagram.import(JSON.stringify(dados));

                        })
                    .fail(function () {
                        console.log("pãã");
                    });
                }
            };

            reader.readAsBinaryString(valor);

        } else {
            //protocolo de experimentação

            let valor = document.querySelector('[name="fileInput"]').files[0];
            let reader = new FileReader();

            reader.onloadend = function () {

                if (reader.readyState == 2) {

                    let data = reader.result;

                    $.ajax({
                        url: (window.location.href + "/abrirProtocolo"),
                        type: "post",
                        async: true,
                        data: data,
                        cache: false
                    })
                    .done(function (data) {

                        diagram.import(data);

                    })
                    .fail(function () {
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
                url: (window.location.href + "/salvarPacote"),
                type: "post",
                async: true,
                data: `{"box": ${JSON.stringify(data)}}`,
                cache: false
            })
            .done(function (data) {

                saveAs(new Blob([data]), nome);
            })
            .fail(function () {
                console.log("pãã");
            });
        } else {
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
                url: (window.location.href + "/salvarProtocolo"),
                type: "post",
                async: true,
                data: data,
                cache: false
            })
            .done(function (data) {

                saveAs(new Blob([data]), nome);
            })
            .fail(function () {
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

        var node = document.querySelector(diagram.selector);
        saveAs(new Blob([node.outerHTML]), "diagrama.svg");
    });


     document.querySelector('.preferences').addEventListener('click', () => {

        $('.settings').animate({
            top: 100
        }, 1000);
    });

     document.querySelector('.settings .close').addEventListener('click', () => {

        $('.settings').animate({
            top: -500
        }, 1000);
    });

     $('.caixa-experimento .close').click(function () {

        $(this).parent().slideToggle();
    });



     document.querySelector('.radio-pt').addEventListener('click', () => {

        Object.entries(language.pt).forEach(([k, v]) => {

            document.querySelector(k).innerHTML = v;
        });

        $(".caixa-experimento .btn").val("Adicionar");
    });

     document.querySelector('.radio-en').addEventListener('click', () => {

        Object.entries(language.en).forEach(([k, v]) => {

            document.querySelector(k).innerHTML = v;
        });

        $(".caixa-experimento .btn").val("Add");
    });

    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    $('.box-descricao .close').click(function () {

        $('.box-descricao').slideUp();
    });

    $('.painel-lateral .button').click(function () {

        if ($('.painel-lateral').css('right') == '-253px') {
            $('.painel-lateral').animate({
                right: 0
            });

            $(this).html('▶');
        } else {
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

        } else {

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

    return window.elements.filter((value) => {
        return value.id == "#" + id
    })[0];
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

        window.elements.filter((el) => {
            return el.transicoesOrigem != null
        }).forEach((e) => {
            e.transicoesOrigem = e.transicoesOrigem.filter((a) => {
                return !lista.includes(a)
            });
            e.transicoesDestino = e.transicoesDestino.filter((b) => {
                return !lista.includes(b)
            });
        });


        window.elements = window.elements.filter((a) => {
            return id != a.id.substring(1);
        });
        window.elements = window.elements.filter((a) => {
            return a.id.indexOf('transicao') == -1 || (a.origem != id && a.destino != id)
        })

        d3.select(`#${id}`).remove();
    }

    static removerTransicao(id) {

        let transicao = get(id);

        window.elements.filter((e) => {
            return e.transicoesOrigem != null;
        }).forEach((a) => {

            a.transicoesOrigem = a.transicoesOrigem.filter((u) => {
                return u != id;
            });
            a.transicoesDestino = a.transicoesDestino.filter((u) => {
                return u != id;
            });
        });

        window.elements = window.elements.filter((a) => {
            return a.id != `#${id}`;
        });

        d3.select(`#${id}`).remove();

    }

    static modificar(id) {

        Interface.id = id;

        painelLateral(id);

        $('.painel-lateral .button').trigger('click');
    }

    /*static vincular(id) {

        Interface.id = id;
        painelVinculação(id)
        $('.painel-lateral .button').trigger('click');

    }*/

    static exibir(id) {

        Interface.id = id;

        $('.box-descricao > .content').empty();

        exibirDescricao(id);


        $('.box-descricao').slideDown();
    }
}

function exibirDescricao(id) {

    let element = get(id);

    if (element.vinculos == null || element.vinculos.length == 0) {

        d3.select('.box-descricao > .content')
        .append('p')
        .style('line-height', "300px")
        .style('vertical-align', 'middle')
        .style('text-align', 'center')
        .text('Não há elementos do experimento vinculados.')

    } else {

        element.vinculos.forEach((a) => {

            getObjectById(BPMNDiagram.experiment, a);

            console.log(result);

            d3.select('.box-descricao > .content').append('div')
            .style('padding', '15px 25px')
            .attr('class', `item-${result.id} box-item`)

            d3.select(`.item-${result.id}`).raise()
            .append('h1')
            .style('font-size', '16px')
            .text(`Identificador: ${result.id}`)

            //o resultado fica na variavel result
            Object.keys(result).forEach((ele) => {

                if (ele != "id" && !isObject(result[ele]) && !Array.isArray(result[ele]) && result[ele] != null ) {

                    d3.select(`.item-${result.id}`)
                    .append('p')
                    .style('font-size', '14px')
                    .style('padding', '3px 0')
                    .style('margin-left', '35px')
                    .text(`${ele}: ${result[ele]}`)
                }
                /*else if (Array.isArray(result[ele])) {

                    d3.select(`.item-${result.id}`)
                    .append('p')
                    .style('font-size', '14px')
                    .style('padding', '3px 0')
                    .style('margin-left', '35px')
                    .text(`${ele}: ${result[ele].join(", ")}.`);
                }*/

            }, result);
        });
    }
}

var result;

function getObjectById(object, id) {


    if (object.hasOwnProperty("id")) {

        if (object["id"] == id) {

            result = object;
            return;
        }
    }

    if (Array.isArray(object)) {

        object.forEach((a) => {

            getObjectById(a, id);
        });
    }

    if (!isObject(object)) return;

    Object.keys(object).forEach((a) => {
        //console.log(a);
        getObjectById(object[a], id);
    });

}


function isObject(a) {
    return (!!a) && (a.constructor === Object);
}

function menuContexto(x, y, categoria, id) {


    d3.select('.context-menu').html('').style('display', 'block');
    d3.select('.context-menu').style('top', `${y}px`).style('left', `${x}px`);

    if (categoria == "element") {

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

        const show = (event) => {
            //console.log();
            //$(`.${d3.event.target.className.substring(3)}`).animate({
            //    top: 100
            //}, 1000);
            $(`.${event.target.className.substring(3)}`).slideToggle();
        };

        //se é um objeto de dados
        if (id.indexOf('data') != -1) {

            Interface.opcaoData = id;

            d3.select('.context-menu')
            .append('li')
            .attr('class', 'experimento')
            .text('Experimento')
            .attr('class', 'menu-experimento')
            .append('ul');

            d3.select('.menu-experimento ul')
            .append('li')
            .attr('class', 'li-definicao')
            .text('Definição')
            //.on('click', show)

            $('.li-definicao').click(show);

            d3.select('.menu-experimento ul')
            .append('li')
            .attr('class', 'li-planejamento')
            .text('Planejamento')
            //.on('click', show)

            $('.li-planejamento').click(show);

            d3.select('.menu-experimento ul')
            .append('li')
            .attr('class', 'li-execucao')
            .text('Execução')
            //.on('click', show)
            //
            $('.li-execucao').click(show);

            d3.select('.menu-experimento ul')
            .append('li')
            .attr('class', 'li-analise')
            .text('Análise e Interpretação')
            //.on('click', show)

            $('.li-analise').click(show);

            d3.select('.menu-experimento ul')
            .append('li')
            .attr('class', 'li-apresentacao')
            .text('Apresentação e Empacotamento')
            //.on('click', show)

            $('.li-apresentacao').click(show);


            //////////////////
            d3.select('.context-menu')
            .append('li')
            .attr('class', 'exibir')
            .text('Exibir detalhes');

            $('.exibir').click(() => {

                Interface.exibir(id);
            });
            
        }

    } else {

        d3.select('.context-menu')
        .append('li')
        .attr('class', 'remover-transicao')
        .text('Remover')
        .on('click', () => {

            Interface.removerTransicao(id);
            $('.context-menu').trigger('mouseleave');
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