/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experiment.factory;

import bpmn.BusinessProcessDiagram;
import bpmn.factory.BPMNFactory;
import bpmn.factory.json.Protocol;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import experiment.ArquivoArtefato;
import experiment.ArquivoInterpretacao;
import experiment.ArquivoTecnica;
import experiment.Artefato;
import experiment.Caracterizacao;


import experiment.Contexto;
import experiment.Cronograma;
import experiment.Defeito;
import experiment.Definicao;
import experiment.Experimentador;
import experiment.Experimento;
import experiment.Grupo;
import experiment.Hipotese;
import experiment.Instituicao;
import experiment.Interpretacao;
import experiment.Metrica;
import experiment.Participante;
import experiment.Pessoa;

import experiment.Tecnica;
import experiment.Validade;
import experiment.VariavelDependente;
import experiment.VariavelIndependente;

/**
 *
 * @author leandroungari
 */
public class ExperimentFactory {

    
    private static Protocol protocol;
    private static Experimento experimento;
    private static ExperimentBox box;
    
    public static Experimento fromXML(String xml) {
    
        XStream x = new XStream(new DomDriver());
        
        ExperimentFactory.initialize(x);
        BPMNFactory.initializeAlias(x);
        experimento = (Experimento) x.fromXML(xml);
        
        return experimento;
    }

    public static Protocol getProtocol() {
        return protocol;
    }

    public static void setProtocol(Protocol protocol) {
        ExperimentFactory.protocol = protocol;
    }

    public static Experimento getExperimento() {
        return experimento;
    }

    public static void setExperimento(Experimento experimento) {
        ExperimentFactory.experimento = experimento;
    }

    public static ExperimentBox getBox() {
        return box;
    }

    public static void setBox(ExperimentBox box) {
        ExperimentFactory.box = box;
    }
    
    
    
    public static String export(){
        
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        
        initialize(xstream);
        xstream.alias("box", ExperimentBox.class);
        xstream.alias("protocol", Protocol.class);
        xstream.autodetectAnnotations(true);
        
        box = new ExperimentBox(experimento, protocol);
        
        return xstream.toXML(box);
    }
    
    public static ExperimentBox extract(String json){
        
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        
        BPMNFactory.initializeAlias(xstream);
        ExperimentFactory.initialize(xstream);
        
        xstream.alias("box", ExperimentBox.class);
        xstream.alias("protocol", Protocol.class);
        xstream.autodetectAnnotations(true);
        
        box = (ExperimentBox) xstream.fromXML(json);
        
        experimento = box.getExperimento();
        protocol = box.getProtocol();
        
        return box;
    }
    
    public static String toXML(){
        
        BPMNFactory.setSchema(protocol);
        
        BPMNFactory.decode();
        
        BusinessProcessDiagram process = BPMNFactory.getDiagram();
        
        experimento.setDiagrama(process);
        
        XStream x = new XStream(new DomDriver());
        
        BPMNFactory.initializeAlias(x);
        ExperimentFactory.initialize(x);
        
        
        
        return x.toXML(experimento);
    }
    
    
    
    public static void initialize(XStream x){
        
        
        //x.alias("Alternativa", Alternativa.class);
        x.alias("ArquivoArtefato", ArquivoArtefato.class);
        x.alias("ArquivoInterpretacao", ArquivoInterpretacao.class);
        x.alias("ArquivoTecnica", ArquivoTecnica.class);
        
        x.alias("Artefato", Artefato.class);
        x.alias("Caracterizacao", Caracterizacao.class);
        //x.alias("ClasseDefeito", ClasseDefeito.class);
        //x.alias("Configuracao", Configuracao.class);
        x.alias("Contexto", Contexto.class);
        x.alias("Cronograma", Cronograma.class);
        x.alias("Defeito", Defeito.class);
        x.alias("Definicao", Definicao.class);
        
        x.alias("Experimentador", Experimentador.class);
        x.alias("Experimento", Experimento.class);
        x.alias("Grupo", Grupo.class);
        
        x.alias("Hipotese", Hipotese.class);
        //x.alias("HipoteseAlternativa", HipoteseAlternativa.class);
        
        x.alias("Instituicao", Instituicao.class);
        x.alias("Interpretacao", Interpretacao.class);
        
        x.alias("Metrica", Metrica.class);
        x.alias("Participante", Participante.class);
        x.alias("Pessoa", Pessoa.class);
        //x.alias("Resposta", Resposta.class);
        //x.alias("RespostaConfiguracao", RespostaConfiguracao.class);
        
        x.alias("Tecnica", Tecnica.class);
        //x.alias("TipoValidade", TipoValidade.class);

        x.alias("Validade", Validade.class);
        x.alias("VariavelDependente", VariavelDependente.class);
        x.alias("VariavelIndependente", VariavelIndependente.class);
        
    }
    
    
    
}
