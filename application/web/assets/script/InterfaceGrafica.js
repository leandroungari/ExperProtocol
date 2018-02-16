class InterfaceGrafica {

    static abrirPacoteLaboratorio() {

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
            interpretacoes: BPMNDiagram.interpretacoesArquivos.map(a => a.arquivo.name),
            artefatos: BPMNDiagram.artefatosArquivos.map(a => a.arquivo.name),
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
                InterfaceGrafica.abrirArquivos(lista.fileList);
                document.querySelector('.box-04').style.display = 'none';
            })
            .fail(function () {
                console.log("pãã");
            });
    }

    static abrirArquivos(lista) {

        if (lista.interpretacao != null && !Array.isArray(lista.interpretacao)) lista.interpretacao = [lista.interpretacao]; 
        if (lista.artefato      != null && !Array.isArray(lista.artefato))      lista.artefato      = [lista.artefato]; 

        //abrir os arquivos, boa pergunta como!!!
    }
}