const name = (name) => {

    return document.getElementsByName(name)[0];
};

class DataExperiment {

    static block01(){

        BPMNDiagram.experiment.definicao = {
            id: "definicao-01",
            objetivo: name("objeto-estudo").value,
            finalidade: name("finalidade-estudo").value,
            respeito: name("enfoque").value,
            pontodevista: name("perspectiva").value,
            contexto: name("contexto").value
        }

        return BPMNDiagram.experiment.definicao.id;
    }

    static block02(){

        BPMNDiagram.experiment.id = "experiment-01";
        BPMNDiagram.experiment.nome = name("nome-geral").value;
        BPMNDiagram.experiment.descricao = name("descricao-geral").value;
        BPMNDiagram.experiment.tema = name("tema-geral").value;
        BPMNDiagram.experiment.areatecnica = name("areatecnica-geral").value;
        BPMNDiagram.experiment.tipo = name("tipo-geral").value;
        BPMNDiagram.experiment.dominio = name("dominio-geral").value; 
        BPMNDiagram.experiment.idioma = name("idioma-geral").value; 

        return BPMNDiagram.experiment.id;
    }

    static block03(){

        BPMNDiagram.experiment.validades.push({
            id: "validade" + name("id-validade").value,
            tipo: name("validade-tipo").value,
            avaliacao: name("validade-avaliacao").value
        });

        return "validade" + name("id-validade").value;
    }

    static block04(){

        BPMNDiagram.experiment.grupos.push({
            id: "grupo" + name("grupo-identificador").value,
            observacao: name("grupo-observacao").value
        });

        return "grupo" + name("grupo-identificador").value;
    }

    static block05(){

        BPMNDiagram.experiment.artefatos
        .filter((a) => {return a.id == name("select-artefato").value})
        .forEach((e) => {

            e.arquivos.push({
                path_arquivo: name("interpretacao-arquivo").files[0]
            });
        });
    }

    static block06(){

        BPMNDiagram.experiment.questoes.push({
            id: "questao" + name("id-questao").value,
            questao: name("questao").value,
            metricas: []
        });

        return "questao" + name("id-questao").value;
    }

    static block07(){

        BPMNDiagram.experiment.hipoteses.push({
            id: "hipotese" + name("hipotese-id").value,
            hipoteseNula: name("nula").value,
            hipoteseAlternativa: name("alternativa").value
        });

        return "hipotese" + name("hipotese-id").value;
    }

    static block08(){

        BPMNDiagram.experiment.variaveisDependentes.push({
            id: "dependente" + name("id-dependente").value,
            var: name("dependente").value
        });

        return "dependente" + name("id-dependente").value;
    }

    static block09(){

        BPMNDiagram.experiment.variaveisIndependentes.push({
            id: "independente" + name("id-independente").value,
            var: name("independente").value
        });

        return "independente" + name("id-independente").value;
    }

    static block10(){

        BPMNDiagram.experiment.participantes.push({
            id: "participante" + name("id-participante").value,
            grupo: name("nome-grupo-participante").value,
            pessoa: {
                nome: name("nome-participante").value,
                email: name("email-participante").value,
                telefone: name("telefone-participante").value,
                instituicao: {
                    nome: name("nome-instituicao").value,
                    sigla: name("sigla-instituicao").value,
                    pais: name("pais-instituicao").value
                }
            }
        });

        return "participante" + name("id-participante").value;
    }

    static block11(){

        BPMNDiagram.experiment.artefatos.push({
            id: "ferramenta" + name("id-ferramenta").value,
            nome: name("ferramenta").value,
            descricao: name("ferramenta-descricao").value,
            tipo: "Ferramentas Informatizadas",
            arquivos: []
        });

        return "ferramenta" + name("id-ferramenta").value;
    }

    static block12(){

        BPMNDiagram.experiment.artefatos.push({
            id: "material" + name("id-materiais").value,
            nome: name("materiais").value,
            descricao: name("materiais-descricao").value,
            tipo: "Materiais de Realização de Testes",
            arquivos: []
        });

        return "material" + name("id-materiais").value;
    }

    static block13(){


        BPMNDiagram.experiment.artefatos.push({
            id: "treinamento" + name("id-treinamento").value,
            nome: name("treinamento").value,
            descricao: name("treinamento-descricao").value,
            tipo: "Materiais de Treinamento",
            arquivos: []
        });

        return "treinamento" + name("id-treinamento").value;
    }

    static block14(){

        BPMNDiagram.experiment.artefatos.push({
            id: "questionario" + name("id-questionario").value,
            nome: name("questionario").value,
            descricao: name("questionario-descricao").value,
            tipo: "Questionários",
            arquivos: []
        });

        return "questionario" + name("id-questionario").value;
    }

    static block15(){

        BPMNDiagram.experiment.artefatos.push({
            id: "formulario" + name("id-formulario").value,
            nome: name("formulario").value,
            descricao: name("formulario-descricao").value,
            tipo: "Formulários",
            arquivos: []
        });

        return "formulario" + name("id-formulario").value;
    }

    static block16(){

        BPMNDiagram.experiment.cronogramas.push({
            id: "cronograma" + name("cronograma-id").value,
            definicao: name("cronograma-definicao").value,
            data_inicial: name("cronograma-data-inicial").value,
            data_final: name("cronograma-data-final").value,
            tipo: name("cronograma-tipo").value,
            tempo: name("cronograma-tempo").value
        });

        return "cronograma" + name("cronograma-id").value;
    }

    static block17(){

        BPMNDiagram.experiment.defeitos.push({
            id: "defeito" + name("defeito-identificacao").value,
            local: name("defeito-local").value,
            requisito: name("defeito-requisito").value,
            classeDefeito: name("defeito-classe").value,
            descricao: name("defeito-descricao").value 
        });

        return "defeito" + name("defeito-identificacao").value;
    }

    static block18(){

        BPMNDiagram.experiment.interpretacoes.push({
            id: "interpretacao" + name("interpretacao-id").value,
            resultados: name("interpretacao-resultados").value,
            arquivos: []
        });

        return "interpretacao" + name("interpretacao-id").value;
    }

    static block19(){

        BPMNDiagram.experiment.conclusoes.push({
            id: "conclusao" + name("conclusao-id").value,
            conclusao: name("conclusao").value
        });

        return "conclusao" + name("conclusao-id").value;
    }

    static block20(){

        BPMNDiagram.experiment.questoes
        .filter((e) => {return e.id == name("opcao-questao").value})
        .forEach((a) => {

            a.metricas.push({
                id: "metrica" + name("id-metrica").value,
                metrica: name("metrica").value,
                descricao: name("metrica-descricao").value
            });
        });

        return "metrica" + name("id-metrica").value;
        
    }

    static block21(){

        BPMNDiagram.experiment.experimentadores.push({
            id: "experimentador" + name("id-experimentador").value,
            nome: name("nome-experimentador").value,
            email: name("email-experimentador").value
        });

        return "experimentador" + name("id-experimentador").value;
    }

    static block25(){

        BPMNDiagram.experiment.interpretacoes
        .filter((e) => { return e.id == name("opcao-interpretacao").value})
        .forEach((a) => {

            a.arquivos.push({
                path_arquivo: name("interpretacao-arquivo").files[0]
            });
        });

        
    }

    

    
}