class InterfaceGrafica {

    static abrirPacoteLaboratorio() {

        //Arquivos do pacote aberto
        BPMNDiagram.artefatosArquivosPacote = [];
        BPMNDiagram.interpretacoesArquivosPacote = [];

        let valor = document.querySelector('[name="fileInput"]').files[0];
        let reader = new FileReader();

        if (valor == null) return;

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

                        data = JSON.parse(data);

                        let dados = {};
                        dados.protocol = (data.box.protocol == "" ? JSON.parse("{\"element\": []}") : data.box.protocol);

                        //ajustando experimento
                        diagram.readExperiment(data.box.experimento);

                        //atribuindo protocolo
                        BPMNDiagram.protocol = dados.protocol;
                        diagram.import(JSON.stringify(dados));

                        //caminho da pasta do pacote
                        document.querySelector('.box-04').style.display = 'block';
                    })
                    .fail(function () {
                        console.log("pãã");
                    });
            }
        };

        reader.readAsText(valor);
    }

    static abrirProtocoloExperimentacao() {

        let valor = document.querySelector('[name="fileInput"]').files[0];
        let reader = new FileReader();

        if (valor == null) return;

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

        reader.readAsText(valor);
    }

    static salvarPacoteLaboratorio() {
        let struct = diagram.export();
        let data = {};

        data.protocol = struct;
        data.experimento = BPMNDiagram.experiment;

        ////////////////////
        /// Extração dos arquivos
        /// 

        BPMNDiagram.interpretacoesArquivos.forEach((arquivo) => {

            var reader = new FileReader();
            reader.addEventListener("load", function () {

                saveAs(new Blob([this.result]), arquivo.arquivo.name);
            }, false);

            reader.readAsDataURL(arquivo.arquivo);
        });


        BPMNDiagram.artefatosArquivos.forEach((arquivo) => {

            var reader = new FileReader();
            reader.addEventListener("load", function () {

                saveAs(new Blob([this.result]), arquivo.arquivo.name);
            }, false);

            reader.readAsDataURL(arquivo.arquivo);
        });

        let nome = document.querySelector("[name='nome-arquivo']").value;

        if (nome == "") {
            confirm("O nome do arquivo deve ser preenchido!");
            return;
        }

        if (nome.indexOf(".xml") == -1) nome = `${nome}.xml`;

        //nome do arquivo
        BPMNDiagram.nomePacote = nome;

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
    }

    static salvarProtocoloExperimentacao() {
        let struct = diagram.export();
        let data = JSON.stringify(struct);

        let nome = document.querySelector("[name='nome-arquivo']").value;

        if (nome == "") {
            confirm("O nome do arquivo deve ser preenchido!");
            return;
        }

        if (nome.indexOf(".bpmn") == -1) nome = `${nome}.bpmn`;

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

    static modificarIdiomaPortugues() {
        let select;
        Object.entries(language.pt).forEach(([k, v]) => {

            select = document.querySelector(k);
            if (select != null) select.innerHTML = v;
        });

        BPMNDiagram.language = "pt-br";
        $(".caixa-experimento .btn").val("Adicionar");
    }

    static modificarIdiomaIngles() {
        let select;
        Object.entries(language.en).forEach(([k, v]) => {

            select = document.querySelector(k);
            if (select != null) select.innerHTML = v;
        });

        BPMNDiagram.language = "en-us";
        $(".caixa-experimento .btn").val("Add");
    }

    static comprimirPacote() {

        if (BPMNDiagram.nomePacote == null) {

            window.alert("Você precisa salvar o pacote de laboratório antes!!");
            return;
        }

        let data = {
            caminho: document.querySelector("[name='caminho-pasta']").value,
            interpretacoes: BPMNDiagram.interpretacoesArquivos
                .map(a => a.arquivo.name)
                .concat(BPMNDiagram.interpretacoesArquivosPacote),
            artefatos: BPMNDiagram.artefatosArquivos
                .map(a => a.arquivo.name)
                .concat(BPMNDiagram.artefatosArquivosPacote),
            nome: BPMNDiagram.nomePacote
        }

        $.ajax({
                url: (window.location.href + "/comprimirPacote"),
                type: "post",
                async: true,
                data: JSON.stringify(data),
                cache: false
            })
            .done(function (data) {

                document.querySelector('.box-03').style.display = 'none';
            })
            .fail(function () {
                console.log("pãã");
            });
    }

    static carregarArquivosPacote() {

        let data = {
            caminho: document.querySelector("[name='caminho-pasta-abrir-lista']").value,
        }

        if (data.caminho == null || data.caminho == "") return;

        $.ajax({
                url: (window.location.href + "/carregarArquivosPacote"),
                type: "post",
                async: true,
                data: JSON.stringify(data),
                cache: false
            })
            .done(function (data) {

                let lista = JSON.parse(data);
                InterfaceGrafica.abrirArquivos(lista.filelist);
                document.querySelector('.box-04').style.display = 'none';
            })
            .fail(function () {
                console.log("pãã");
            });
    }

    static abrirArquivos(lista) {

        if (lista.listaInterpretacao != null && !Array.isArray(lista.listaInterpretacao)) lista.listaInterpretacao = [lista.interpretacao];
        if (lista.listaArtefato != null && !Array.isArray(lista.listaArtefato)) lista.listaArtefato = [lista.artefato];

        //abrir os arquivos, boa pergunta como!!!
        console.log(lista);

        //Arquivos do pacote aberto
        BPMNDiagram.artefatosArquivosPacote = lista.listaArtefato.map(f => f.file);
        BPMNDiagram.interpretacoesArquivosPacote = lista.listaInterpretacao.map(f => f.file);
    }

    static criarListagem(result) {

        InterfaceGrafica.criarCaixa(result);
        InterfaceGrafica.criarBotoes(result);

        //o resultado fica na variavel result
        let valores = Object.entries(formatarTexto(result));

        valores.forEach(([attribute, value]) => {

            d3.select(`.item-${result.id}`)
                .append('p')
                .style('font-size', '14px')
                .style('padding', '2px 0')
                .style('margin-left', '25px')
                .text(`${attribute}: ${value}`)
        });

        let counter = 0;
        valores.forEach(([attribute, value]) => {

            if (attribute != "Arquivo(s)" && attribute != "Archive(s)") {

                d3.select(`.form-${result.id}`)
                    .append('label')
                    .style('display', 'block')
                    .style('font-size', '14px')
                    .style('padding', '2px 0')
                    .style('margin-left', '25px')
                    .text(`${attribute}`)

                if (attribute == "Descrição" || attribute == "Description") {

                    d3.select(`.form-${result.id}`)
                        .append('textarea')
                        .attr('class', 'formValue')
                        .style('margin-left', '25px')
                        .style('padding', '3px 6px')
                        .attr('cols', '30')
                        .attr('rows', '10')
                        .text(value)
                } else {

                    if (value != "" ) d3.select(`.form-${result.id}`)
                        .append('input')
                        .style('margin-left', '25px')
                        .attr('class', 'formValue')
                        .attr('type', 'text')
                        .attr('value', value)
                }
            }

        });

        d3.select(`.form-${result.id}`)
        .append('div')
        .style('margin-left', '100px')
        .append('input')
        .attr('data-container', `.form-${result.id}`)
        .attr('data-id', `${result.id}`)
        .attr('class', `button-salvar-${result.id}`)
        .attr('type', 'submit')
        .attr('class', 'btn form-salvar')
        .attr('value', (BPMNDiagram.language == "pt-br" ? "Salvar" : "Save"))
        .style('margin','25px auto')
        .style('display','inline-flex')
        .style('justify-content', 'center');
        
        d3.select(`.form-${result.id}`).select('div')
        .append('input')
        .attr('type', 'submit')
        .attr('data-id', `${result.id}`)
        .attr('data-container', `.form-${result.id}`)
        .attr('class', 'btn form-cancelar')
        .attr('value', (BPMNDiagram.language == "pt-br" ? "Cancelar" : "Cancel"))
        .style('margin','25px 0 0 10px')
        .style('display','inline-flex')
        .style('justify-content', 'center');

        //evento salvar
        $(`.form-${result.id} .form-salvar`).click((event) => {

            let dataContainer = event.target.getAttribute('data-container');
            $(dataContainer).fadeOut();

            let dataId = event.target.getAttribute('data-id');

            let dados = [];
            document.querySelectorAll(`${dataContainer} .formValue`).forEach((a) => {
                dados.push(a.value);
            });

            console.log(dados)

            InterfaceGrafica.modificarDados(dados, dataId);

            //atualizando a exibição
            $(dataContainer).fadeOut();
            $(`.item-${dataId}`).slideDown();
            
            
            $('.box-descricao > .content').empty();

            exibirDescricao(Interface.id);
        });

        //evento cancelar
        $(`.form-${result.id} .form-cancelar`).click((event) => {

            let dataContainer = event.target.getAttribute('data-container');
            let dataId = event.target.getAttribute('data-id');

            $(dataContainer).fadeOut();
            $(`.item-${dataId}`).fadeIn();
        });

        let str = document.querySelector(`.item-${result.id} > p`).innerHTML;
        if (str[str.length - 2] == ':') document.querySelector(`.item-${result.id} > p`).innerHTML = str.substring(0, str.length - 2);
        else document.querySelector(`.item-${result.id} > p`).innerHTML = str;

        let strForm = document.querySelector(`.form-${result.id} > label`).innerHTML;
        if (str[str.length - 2] == ':') document.querySelector(`.form-${result.id} > label`).innerHTML = strForm.substring(0, strForm.length);
        else document.querySelector(`.form-${result.id} > label`).innerHTML = strForm;

        d3.select(`.item-${result.id} > p`)
            .style('font', 'bold 20px DinPro')
            .style('margin-bottom', '5px')
            .style('text-transform', 'uppercase');

        d3.select(`.form-${result.id} > label`)
            .style('font', 'bold 20px DinPro')
            .style('margin-bottom', '5px')
            .style('text-transform', 'uppercase');
    }

    static criarCaixa(result) {

        d3.select('.box-descricao > .content')
            .append('div')
            .style('padding', '15px 25px')
            .style('position', 'relative')
            .attr('class', `item-${result.id}`)

        d3.select(`.item-${result.id}`)
            .append('div')
            .attr('class', 'buttons')
            .style('width', '28px')
            .style('height', '12px')
            .style('position', 'absolute')
            .style('top', '10px')
            .style('right', '30px')

        d3.select('.box-descricao > .content')
            .append('div')
            .style('display', 'none')
            .style('padding', '15px 25px')
            .style('position', 'relative')
            .attr('class', `form-${result.id}`)
    }

    static criarBotoes(result) {

        d3.select(`.item-${result.id}`).select('.buttons')
            .append('div')
            .style('display', 'inline-block')
            .attr('class', 'modificar')
            .attr('title', 'Modificar')
            .attr('data-id', result.id)
            .style('width', '12px')
            .style('height', '12px')
            .style('background-image', `url('./assets/images/modificar.png')`)
            .style('background-size', '12px 12px')
            .style('cursor', 'pointer')

        d3.select(`.item-${result.id}`).select('.buttons')
            .append('div')
            .style('display', 'inline-block')
            .attr('class', 'remover')
            .attr('title', 'Remover')
            .attr('data-id', result.id)
            .style('margin-left', '4px')
            .style('width', '12px')
            .style('height', '12px')
            .style('background-image', `url('./assets/images/excluir.png')`)
            .style('background-size', '12px 12px')
            .style('cursor', 'pointer')

        $(`.item-${result.id} .remover`).click(event => InterfaceGrafica.removerElemento(event, result));
        $(`.item-${result.id} .modificar`).click(event => {

            let dataId = event.target.getAttribute('data-id');
            
            $(`.item-${result.id}`).fadeOut();    
            $(`.form-${dataId}`).slideDown();
        });
    }

    static removerElemento(event, result) {

        let dataId = event.target.getAttribute('data-id');
        InterfaceGrafica.limparDados(dataId);

        window.elements.forEach(e => {

            if (e.vinculos != null) e.vinculos = e.vinculos.filter(id => id != dataId);
            else e.vinculos = [];
        });

        $(`.item-${result.id}`).slideUp();
    }

    static limparDados(dataId) {

        if (dataId.includes("definicao")) {

            BPMNDiagram.experiment.definicao = {
                id: "definicao-01",
                objetivo: "",
                finalidade: "",
                respeito: "",
                pontodevista: "",
                contexto: "",
            }
        } else if (dataId.includes("experiment-01")) {

            BPMNDiagram.experiment.id = "experiment-01";
            BPMNDiagram.experiment.nome = "";
            BPMNDiagram.experiment.descricao = "";
            BPMNDiagram.experiment.tema = "",
            BPMNDiagram.experiment.areatecnica = "";
            BPMNDiagram.experiment.tipo = "";
            BPMNDiagram.experiment.dominio = "";
            BPMNDiagram.experiment.idioma = "";

        } else if (dataId.includes("validade")) {

            BPMNDiagram.experiment.validades = BPMNDiagram.experiment.validades.filter(v => v.id != dataId);
        } else if (dataId.includes("grupo")) {

            BPMNDiagram.experiment.grupos = BPMNDiagram.experiment.grupos.filter(g => g.id != dataId);
        } else if (dataId.includes("questao")) {

            BPMNDiagram.experiment.questoes = BPMNDiagram.experiment.questoes.filter(q => q.id != dataId);
        } else if (dataId.includes("hipotese")) {

            BPMNDiagram.experiment.hipoteses = BPMNDiagram.experiment.hipoteses.filter(h => h.id != dataId);
        } else if (dataId.includes("independente")) {

            BPMNDiagram.experiment.variaveisIndependentes = BPMNDiagram.experiment.variaveisIndependentes.filter(i => i.id != dataId)
        } else if (dataId.includes("dependente")) {

            BPMNDiagram.experiment.variaveisDependentes = BPMNDiagram.experiment.variaveisDependentes.filter(d => d.id != dataId);
        } else if (dataId.includes("participante")) {

            BPMNDiagram.experiment.participantes = BPMNDiagram.experiment.participantes.filter(p => p.id != dataId);
        } else if (dataId.includes("ferramenta")) {

            BPMNDiagram.experiment.artefatos = BPMNDiagram.experiment.artefatos.filter(f => f.id != dataId);
        } else if (dataId.includes("material")) {

            BPMNDiagram.experiment.artefatos = BPMNDiagram.experiment.artefatos.filter(m => m.id != dataId);
        } else if (dataId.includes("treinamento")) {

            BPMNDiagram.experiment.artefatos = BPMNDiagram.experiment.artefatos.filter(t => t.id != dataId);
        } else if (dataId.includes("questionario")) {

            BPMNDiagram.experiment.artefatos = BPMNDiagram.experiment.artefatos.filter(q => q.id != dataId);
        } else if (dataId.includes("formulario")) {

            BPMNDiagram.experiment.artefatos = BPMNDiagram.experiment.artefatos.filter(f => f.id != dataId);
        } else if (dataId.includes("cronograma")) {

            BPMNDiagram.experiment.cronogramas = BPMNDiagram.experiment.cronogramas.filter(c => c.id != dataId);
        } else if (dataId.includes("defeito")) {

            BPMNDiagram.experiment.defeitos = BPMNDiagram.experiment.defeitos.filter(d => d.id != dataId);
        } else if (dataId.includes("interpretacao")) {

            BPMNDiagram.experiment.interpretacoes = BPMNDiagram.experiment.interpretacoes.filter(i => i.id != dataId);
        } else if (dataId.includes("conclusao")) {

            BPMNDiagram.experiment.conclusoes = BPMNDiagram.experiment.conclusoes.filter(c => c.id != dataId);
        } else if (dataId.includes("metrica")) {

            BPMNDiagram.experiment.questoes.forEach(questao => {

                questao.metricas = questao.metricas.filter(m => m.id != dataId);
            });
        } else if (dataId.includes("objetivo")) {

            BPMNDiagram.experiment.objetivos = BPMNDiagram.experiment.objetivos.filter(o => o.id != dataId);
            
        } else if (dataId.includes("experimentador")) {

            BPMNDiagram.experiment.experimentadores = BPMNDiagram.experiment.experimentadores.filter(e => e.id != dataId)
        }
    }

    static modificarDados(dados, dataId) {
        
        if (dataId.includes("definicao")) {

            BPMNDiagram.experiment.definicao = {
                id: "definicao-01",
                objetivo: dados[0],
                finalidade: dados[1],
                respeito: dados[2],
                pontodevista: dados[3],
                contexto: dados[4],
            }
        } else if (dataId.includes("experiment-01")) {

            BPMNDiagram.experiment.id = "experiment-01";
            BPMNDiagram.experiment.nome = dados[0];
            BPMNDiagram.experiment.descricao = dados[1];
            BPMNDiagram.experiment.tema = dados[2];
            BPMNDiagram.experiment.areatecnica = dados[3];
            BPMNDiagram.experiment.tipo = dados[4];
            BPMNDiagram.experiment.dominio = dados[5];
            BPMNDiagram.experiment.idioma = dados[6];

        } else if (dataId.includes("validade")) {

            let validade = BPMNDiagram.experiment.validades
            .filter(v => v.id == dataId)[0];

            validade.tipo = dados[0];
            validade.avaliacao = dados[1];

        } else if (dataId.includes("grupo")) {

            let grupo = BPMNDiagram.experiment.grupos
            .filter(g => g.id == dataId)[0];

            grupo.id = dados[0];
            grupo.observacao = dados[1];

        } else if (dataId.includes("questao")) {

            let questao = BPMNDiagram.experiment.questoes
            .filter(q => q.id == dataId)[0];

            questao.questao = dados[0];

        } else if (dataId.includes("hipotese")) {

            let hip = BPMNDiagram.experiment.hipoteses
            .filter(h => h.id == dataId)[0];

            hip.hipoteseNula = dados[0];
            hip.hipoteseAlternativa = dados[1];

        } else if (dataId.includes("independente")) {

            let ind = BPMNDiagram.experiment.variaveisIndependentes
            .filter(i => i.id == dataId)[0];

            ind.var = dados[0];
        }
        else if (dataId.includes("dependente")) {

            let dep = BPMNDiagram.experiment.variaveisDependentes
            .filter(d => d.id == dataId)[0];

            dep.var = dados[0];

        }  else if (dataId.includes("participante")) {

            let part = BPMNDiagram.experiment.participantes
            .filter(p => p.id == dataId)[0];

            part.grupo = dados[0];
            part.pessoa.nome = dados[1];
            part.pessoa.email = dados[2];
            part.pessoa.telefone = dados[3];
            part.pessoa.instituicao.nome = dados[4];
            part.pessoa.instituicao.sigla = dados[5];
            part.pessoa.instituicao.pais = dados[6];
            

        } else if (dataId.includes("ferramenta")) {

            let ferramenta = BPMNDiagram.experiment.artefatos
            .filter(f => f.id == dataId)[0];

            ferramenta.nome = dados[0];
            ferramenta.descricao = dados[1];
            ferramenta.tipo = dados[2];

        } else if (dataId.includes("material")) {

            let material = BPMNDiagram.experiment.artefatos
            .filter(m => m.id == dataId)[0];

            material.nome = dados[0];
            material.descricao = dados[1];
            material.tipo = dados[2];

        } else if (dataId.includes("treinamento")) {

            let treinamento = BPMNDiagram.experiment.artefatos
            .filter(t => t.id == dataId)[0];

            treinamento.nome = dados[0];
            treinamento.descricao = dados[1];
            treinamento.tipo = dados[2];

        } else if (dataId.includes("questionario")) {

            let quest = BPMNDiagram.experiment.artefatos
            .filter(q => q.id == dataId)[0];

            quest.nome = dados[0];
            quest.descricao = dados[1];
            quest.tipo = dados[2];

        } else if (dataId.includes("formulario")) {

            let form = BPMNDiagram.experiment.artefatos
            .filter(f => f.id == dataId)[0];

            form.nome = dados[0];
            form.descricao = dados[1];
            form.tipo = dados[2];

        } else if (dataId.includes("cronograma")) {

            let cro = BPMNDiagram.experiment.cronogramas
            .filter(c => c.id == dataId)[0];

            cro.definicao = dados[0];
            cro.data_inicial = dados[1];
            cro.data_final = dados[2];
            cro.tipo = dados[3];
            cro.tempo = dados[4];

        } else if (dataId.includes("defeito")) {

            let def = BPMNDiagram.experiment.defeitos
            .filter(d => d.id == dataId)[0];

            def.local = dados[0];
            def.requisito = dados[1];
            def.classeDefeito = dados[2];
            def.descricao = dados[3];

        } else if (dataId.includes("interpretacao")) {

            let inter = BPMNDiagram.experiment.interpretacoes
            .filter(i => i.id == dataId)[0];

            inter.resultados = dados[0];
            
        } else if (dataId.includes("conclusao")) {

            let conc = BPMNDiagram.experiment.conclusoes
            .filter(c => c.id == dataId)[0];

            conc.conclusao = dados[0];

        } else if (dataId.includes("metrica")) {

            BPMNDiagram.experiment.questoes.forEach(questao => {

                let met = questao.metricas
                .filter(m => m.id == dataId)[0];

                met.metrica = dados[0];
                met.descricao = dados[1];

            });
        } else if (dataId.includes("objetivo")) {

            let obj = BPMNDiagram.experiment.objetivos
            .filter(o => o.id == dataId)[0];

            obj.objetivo = dados[0];
            obj.descricao = dados[1];
            
        } else if (dataId.includes("experimentador")) {

            let exp = BPMNDiagram.experiment.experimentadores
            .filter(e => e.id == dataId)[0];

            exp.nome = dados[0];
            exp.email = dados[1];
        }

    
    }
}