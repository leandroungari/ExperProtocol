/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmn.factory;

import bpmn.BusinessProcessDiagram;
import bpmn.artifact.Artifact;
import bpmn.artifact.DataInput;
import bpmn.artifact.DataObject;
import bpmn.artifact.DataOutput;
import bpmn.artifact.DataStore;
import bpmn.artifact.TextAnnotation;
import bpmn.connectionobject.Association;
import bpmn.connectionobject.ConnectionObject;
import bpmn.connectionobject.DataAssociation;
import bpmn.connectionobject.MessageFlow;
import bpmn.connectionobject.SequenceFlow;
import bpmn.element.Element;
import bpmn.event.EndEvent;
import bpmn.event.EndEventCancel;
import bpmn.event.EndEventCompensation;
import bpmn.event.EndEventError;
import bpmn.event.EndEventEscalation;
import bpmn.event.EndEventLink;
import bpmn.event.EndEventMessage;
import bpmn.event.EndEventMultiple;
import bpmn.event.EndEventSignal;
import bpmn.event.EndEventTerminate;
import bpmn.event.Event;
import bpmn.event.IntermediateEvent;
import bpmn.event.IntermediateEventCatchCancel;
import bpmn.event.IntermediateEventCatchCompensation;
import bpmn.event.IntermediateEventCatchCondition;
import bpmn.event.IntermediateEventCatchError;
import bpmn.event.IntermediateEventCatchEscalation;
import bpmn.event.IntermediateEventCatchLink;
import bpmn.event.IntermediateEventCatchMessage;
import bpmn.event.IntermediateEventCatchMultiple;
import bpmn.event.IntermediateEventCatchNonInterruptingCondition;
import bpmn.event.IntermediateEventCatchNonInterruptingEscalation;
import bpmn.event.IntermediateEventCatchNonInterruptingMessage;
import bpmn.event.IntermediateEventCatchNonInterruptingMultiple;
import bpmn.event.IntermediateEventCatchNonInterruptingParallelMultiple;
import bpmn.event.IntermediateEventCatchNonInterruptingSignal;
import bpmn.event.IntermediateEventCatchNonInterruptingTimer;
import bpmn.event.IntermediateEventCatchParallelMultiple;
import bpmn.event.IntermediateEventCatchSignal;
import bpmn.event.IntermediateEventCatchTimer;
import bpmn.event.IntermediateEventThrowCompensation;
import bpmn.event.IntermediateEventThrowEscalation;
import bpmn.event.IntermediateEventThrowLink;
import bpmn.event.IntermediateEventThrowMessage;
import bpmn.event.IntermediateEventThrowMultiple;
import bpmn.event.IntermediateEventThrowSignal;
import bpmn.event.StartEvent;
import bpmn.event.StartEventCompensation;
import bpmn.event.StartEventCondition;
import bpmn.event.StartEventError;
import bpmn.event.StartEventEscalation;
import bpmn.event.StartEventMessage;
import bpmn.event.StartEventMultiple;
import bpmn.event.StartEventNonInterruptingCondition;
import bpmn.event.StartEventNonInterruptingEscalation;
import bpmn.event.StartEventNonInterruptingMessage;
import bpmn.event.StartEventNonInterruptingMultiple;
import bpmn.event.StartEventNonInterruptingParallelMultiple;
import bpmn.event.StartEventNonInterruptingSignal;
import bpmn.event.StartEventNonInterruptingTimer;
import bpmn.event.StartEventParallelMultiple;
import bpmn.event.StartEventSignal;
import bpmn.event.StartEventTimer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import bpmn.factory.json.Protocol;
import bpmn.factory.json.Transicao;
import bpmn.gateway.Gateway;
import bpmn.gateway.GatewayComplex;
import bpmn.gateway.GatewayEventBased;
import bpmn.gateway.GatewayOR;
import bpmn.gateway.GatewayParallel;
import bpmn.gateway.GatewayXOR;
import bpmn.pool.Lane;
import bpmn.pool.Pool;
import bpmn.task.BusinessRuleTask;
import bpmn.task.ManualTask;
import bpmn.task.ReceiveTask;
import bpmn.task.ScriptTask;
import bpmn.task.SendTask;
import bpmn.task.ServiceTask;
import bpmn.task.Task;
import bpmn.task.UserTask;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.util.ArrayList;

/**
 *
 * @author leandroungari
 */
public class BPMNFactory {

    private static Protocol schema;
    private static BusinessProcessDiagram diagram;
    private static String xml;

    public static void create(String json) {

        BPMNFactory.schema = extract(json);
        BPMNFactory.decode();
        
    }

    public static Protocol getSchema() {
        return schema;
    }

    public static void setSchema(Protocol schema) {
        BPMNFactory.schema = schema;
    }

    public static BusinessProcessDiagram getDiagram() {
        return diagram;
    }
    
    
    
    
    public static void decode(){
        
        diagram = new BusinessProcessDiagram(schema.getWidth(), schema.getHeight());

        //percorrendo os elementos
        Element r, s, t;
        ConnectionObject a, b, c;

        for (bpmn.factory.json.Element e : schema.getElements()) {

            r = get(e);

            //tratar transições
            if (e.getTransitions() != null) {
                for (Transicao f : e.getTransitions()) {

                    r.getTransitions().add(transition(f));
                }
            }

            if (e.getElements() != null) {
                for (bpmn.factory.json.Element el : e.getElements()) {

                    s = get(el);

                    //tratar transições
                    if (el.getTransitions() != null) {
                        for (Transicao g : el.getTransitions()) {

                            s.getTransitions().add(transition(g));
                        }
                    }

                    if (el.getElements() != null) {
                        for (bpmn.factory.json.Element ele : el.getElements()) {

                            t = get(ele);

                            //tratar transições
                            if (ele.getTransitions() != null) {
                                for (Transicao h : ele.getTransitions()) {

                                    t.getTransitions().add(transition(h));
                                }
                            }
                            
                            t.setVinculos(ele.getVinculos());
          
                            s.getElements().add(t);
                        }

                    }
                    
                    s.setVinculos(el.getVinculos());

                    r.getElements().add(s);

                }
            }
            
            r.setVinculos(e.getVinculos());

            diagram.getElements().add(r);
        }
    }

    private static Element get(bpmn.factory.json.Element e) {

        Element element = null;

        switch (e.getType()) {

            case "BPMNEvent":

                element = addEvent(e);
                break;

            case "BPMNGateway":
                element = addGateway(e);
                break;

            case "BPMNActivity":
                element = addTask(e);
                break;

            case "BPMNData":
            case "BPMNTextAnnotation":
                element = addArtifact(e);
                break;

            case "BPMNLane":
                element = addLane(e);
                break;

            case "BPMNPool":
                element = addPool(e);
                break;
        }

        return element;
    }

    private static Protocol extract(String json) {

        
        //System.out.println(json);
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias("protocol", Protocol.class);
        xstream.autodetectAnnotations(true);
        return (Protocol) xstream.fromXML(json);
    }

    public static String getXML() {

        XStream x = new XStream(new DomDriver());
        x.autodetectAnnotations(true);
        xml = x.toXML(diagram);

        return xml;
    }

    public static BusinessProcessDiagram fromXML(String xml) {

        XStream x = new XStream(new DomDriver());
       
        BPMNFactory.initializeAlias(x);
        //x.autodetectAnnotations(true);
        BusinessProcessDiagram b = (BusinessProcessDiagram) x.fromXML(xml);
        diagram = b;
        return b;
    }

    private static Element addPool(bpmn.factory.json.Element e) {

        return new Pool(e.getId(), e.getX(), e.getY(), e.getWidth(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    private static Element addLane(bpmn.factory.json.Element e) {

        return new Lane(e.getId(), e.getX(), e.getY(), e.getHeight(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    private static Element addArtifact(bpmn.factory.json.Element e) {

        switch (e.getName()) {

            case "data-store":

                return new DataStore(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "data-input":

                return new DataInput(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "data-output":

                return new DataOutput(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "data-object":

                return new DataObject(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "text-annotation":

                return new TextAnnotation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        return null;
    }

    private static Element addTask(bpmn.factory.json.Element e) {

        switch (e.getName()) {

            case "manual-task":

                return new ManualTask(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "script-task":

                return new ScriptTask(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "send-task":

                return new SendTask(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "service-task":

                return new ServiceTask(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "user-task":

                return new UserTask(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "business-rule-task":

                return new BusinessRuleTask(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "receive-task":

                return new ReceiveTask(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "task-none":

                return new Task(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        return null;
    }

    private static Element addGateway(bpmn.factory.json.Element e) {

        switch (e.getName()) {

            case "gateway-parallel":

                return new GatewayParallel(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "gateway-none":

                return new Gateway(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "gateway-or":

                return new GatewayOR(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "gateway-complex":

                return new GatewayComplex(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "gateway-xor":

                return new GatewayXOR(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

            case "gateway-eventbased":

                return new GatewayEventBased(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        return null;
    }

    //método dos eventos
    private static Element addEvent(bpmn.factory.json.Element e) {

        switch (e.getName()) {

            case "start-event-none":
                return new StartEvent(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-non-interrupting-parallel-multiple":
                return new StartEventNonInterruptingParallelMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-condition":
                return new StartEventCondition(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-message":
                return new StartEventMessage(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-non-interrupting-message":
                return new StartEventNonInterruptingMessage(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-non-interrupting-timer":
                return new StartEventNonInterruptingTimer(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-escalation":
                return new StartEventEscalation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-non-interrupting-multiple":
                return new StartEventNonInterruptingMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-parallel-multiple":
                return new StartEventParallelMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-signal":
                return new StartEventSignal(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-multiple":
                return new StartEventMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-non-interrupting-condition":
                return new StartEventNonInterruptingCondition(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-compensation":
                return new StartEventCompensation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-non-interrupting-signal":
                return new StartEventNonInterruptingSignal(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-error":
                return new StartEventError(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-timer":
                return new StartEventTimer(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "start-event-non-interrupting-escalation":
                return new StartEventNonInterruptingEscalation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-non-interrupting-timer":
                return new IntermediateEventCatchNonInterruptingTimer(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-signal":
                return new IntermediateEventCatchSignal(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-timer":
                return new IntermediateEventCatchTimer(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-message":
                return new IntermediateEventCatchMessage(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-non-interrupting-escalation":
                return new IntermediateEventCatchNonInterruptingEscalation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-throw-message":
                return new IntermediateEventThrowMessage(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-cancel":
                return new IntermediateEventCatchCancel(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-parallel-multiple":
                return new IntermediateEventCatchParallelMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-error":
                return new IntermediateEventCatchError(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-none":
                return new IntermediateEvent(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-throw-compensation":
                return new IntermediateEventThrowCompensation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-throw-link":
                return new IntermediateEventThrowLink(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-non-interrupting-multiple":
                return new IntermediateEventCatchNonInterruptingMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-non-interrupting-message":
                return new IntermediateEventCatchNonInterruptingMessage(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-compensation":
                return new IntermediateEventCatchCompensation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-condition":
                return new IntermediateEventCatchCondition(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-link":
                return new IntermediateEventCatchLink(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-throw-escalation":
                return new IntermediateEventThrowEscalation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-non-interrupting-signal":
                return new IntermediateEventCatchNonInterruptingSignal(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-throw-signal":
                return new IntermediateEventThrowSignal(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-non-interrupting-parallel-multiple":
                return new IntermediateEventCatchNonInterruptingParallelMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-escalation":
                return new IntermediateEventCatchEscalation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-multiple":
                return new IntermediateEventCatchMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-throw-multiple":
                return new IntermediateEventThrowMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "intermediate-event-catch-non-interrupting-condition":
                return new IntermediateEventCatchNonInterruptingCondition(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-none":
                return new EndEvent(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-cancel":
                return new EndEventCancel(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-multiple":
                return new EndEventMultiple(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-compensation":
                return new EndEventCompensation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-error":
                return new EndEventError(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-terminate":
                return new EndEventTerminate(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-message":
                return new EndEventMessage(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-escalation":
                return new EndEventEscalation(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-signal":
                return new EndEventSignal(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            case "end-event-link":
                return new EndEventLink(e.getId(), e.getX(), e.getY(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }

        return null;
    }

    //inserir as transições
    private static ConnectionObject transition(Transicao t) {

        switch (t.getTipo()) {

            case "sequence-flow":

                return new SequenceFlow(t.getId(), t.getOrigem(), t.getDestino(), t.getPontoOrigem(), t.getPontoDestino());

            case "message-flow":

                return new MessageFlow(t.getId(), t.getOrigem(), t.getDestino(), t.getPontoOrigem(), t.getPontoDestino());

            case "data-association":

                return new DataAssociation(t.getId(), t.getOrigem(), t.getDestino(), t.getPontoOrigem(), t.getPontoDestino());

            case "association":

                return new Association(t.getId(), t.getOrigem(), t.getDestino(), t.getPontoOrigem(), t.getPontoDestino());

        }

        return null;
    }

    public static String export() {

        String s;

        schema = new Protocol();
        schema.setElements(new ArrayList<>());

        bpmn.factory.json.Element a, b, c;
        Transicao l, m, n;
        for (Element e : diagram.getElements()) {

            a = build(e);
            //analisar transições
            for (ConnectionObject ca : e.getTransitions()) {

                l = link(ca);
                a.getTransitions().add(l);
            }

            for (Element el : e.getElements()) {

                b = build(el);
                //analisar transições
                for (ConnectionObject cb : el.getTransitions()) {

                    m = link(cb);
                    b.getTransitions().add(m);
                }

                for (Element ele : el.getElements()) {

                    c = build(ele);
                    //analisar transições
                    for (ConnectionObject cc : ele.getTransitions()) {

                        n = link(cc);
                        c.getTransitions().add(n);
                    }

                    b.getElements().add(c);
                }

                a.getElements().add(b);
            }

            schema.getElements().add(a);
        }

        s = schema.toString();

        return s;
    }

    public static Protocol export(BusinessProcessDiagram d) {

    
        Protocol p = new Protocol();
        p.setElements(new ArrayList<>());

        bpmn.factory.json.Element a, b, c;
        Transicao l, m, n;
        for (Element e : d.getElements()) {

            a = build(e);
            //analisar transições
            for (ConnectionObject ca : e.getTransitions()) {

                l = link(ca);
                a.getTransitions().add(l);
            }

            for (Element el : e.getElements()) {

                b = build(el);
                //analisar transições
                for (ConnectionObject cb : el.getTransitions()) {

                    m = link(cb);
                    b.getTransitions().add(m);
                }

                for (Element ele : el.getElements()) {

                    c = build(ele);
                    //analisar transições
                    for (ConnectionObject cc : ele.getTransitions()) {

                        n = link(cc);
                        c.getTransitions().add(n);
                    }

                    b.getElements().add(c);
                }

                a.getElements().add(b);
            }

            p.getElements().add(a);
        }
        
        p.setWidth(d.getWidth());
        p.setHeight(d.getHeight());
        

        return p;
    }
    
    private static bpmn.factory.json.Element build(Element e) {

        if (e instanceof Pool) {
            return new bpmn.factory.json.Element(e.getId(), e.getX(), e.getY(), e.getType(), e.getName(), ((Pool) e).getWidth(), 0, e.getDescription(), new ArrayList<>(), new ArrayList<>(), e.getVinculos());
        } else if (e instanceof Lane) {
            return new bpmn.factory.json.Element(e.getId(), e.getX(), e.getY(), e.getType(), e.getName(), 0, ((Lane) e).getHeight(), e.getDescription(), new ArrayList<>(), new ArrayList<>(), e.getVinculos());
        } else {
            return new bpmn.factory.json.Element(e.getId(), e.getX(), e.getY(), e.getType(), e.getName(), 0, 0, e.getDescription(), new ArrayList<>(), new ArrayList<>(), e.getVinculos());
        }
    }

    private static bpmn.factory.json.Transicao link(ConnectionObject c) {

        return new Transicao(c.getId(), c.getOrigem(), c.getDestino(), c.getTipo(), c.getPontoOrigem(), c.getPontoDestino());
    }

    public static void initializeAlias(XStream x) {
        
        x.alias("BusinessProcessDiagram", BusinessProcessDiagram.class);
        x.alias("Artifact", Artifact.class);
        x.alias("DataInput", DataInput.class);
        x.alias("DataObject", DataObject.class);
        x.alias("DataOutput", DataOutput.class);
        x.alias("DataStore", DataStore.class);
        x.alias("TextAnnotation", TextAnnotation.class);
        
        x.alias("Association", Association.class);
        x.alias("ConnectionObject", ConnectionObject.class);
        x.alias("DataAssociation", DataAssociation.class);
        x.alias("MessageFlow", MessageFlow.class);
        x.alias("SequenceFlow", SequenceFlow.class);
        
        x.alias("Element", Element.class);
        
        x.alias("EndEvent", EndEvent.class);
        x.alias("EndEventCancel", EndEventCancel.class);
        x.alias("EndEventCompensation", EndEventCompensation.class);
        x.alias("EndEventError", EndEventError.class);
        x.alias("EndEventEscalation", EndEventEscalation.class);
        x.alias("EndEventLink", EndEventLink.class);
        x.alias("EndEventMessage", EndEventMessage.class);
        x.alias("EndEventMultiple", EndEventMultiple.class);
        x.alias("EndEventSignal", EndEventSignal.class);
        x.alias("EndEventTerminate", EndEventTerminate.class);
        
        x.alias("Event", Event.class);
        
        x.alias("IntermediateEvent", IntermediateEvent.class);
        x.alias("IntermediateEventCatchCancel", IntermediateEventCatchCancel.class);
        x.alias("IntermediateEventCatchCompensation", IntermediateEventCatchCompensation.class);
        x.alias("IntermediateEventCatchCondition", IntermediateEventCatchCondition.class);
        x.alias("IntermediateEventCatchError", IntermediateEventCatchError.class);
        x.alias("IntermediateEventCatchEscalation", IntermediateEventCatchEscalation.class);
        x.alias("IntermediateEventCatchLink", IntermediateEventCatchLink.class);
        x.alias("IntermediateEventCatchMessage", IntermediateEventCatchMessage.class);
        x.alias("IntermediateEventCatchMultiple", IntermediateEventCatchMultiple.class);
        x.alias("IntermediateEventCatchNonInterruptingCondition", IntermediateEventCatchNonInterruptingCondition.class);
        x.alias("IntermediateEventCatchNonInterruptingEscalation", IntermediateEventCatchNonInterruptingEscalation.class);
        x.alias("IntermediateEventCatchNonInterruptingMessage", IntermediateEventCatchNonInterruptingMessage.class);
        x.alias("IntermediateEventCatchNonInterruptingMultiple", IntermediateEventCatchNonInterruptingMultiple.class);
        x.alias("IntermediateEventCatchNonInterruptingParallelMultiple", IntermediateEventCatchNonInterruptingParallelMultiple.class);
        x.alias("IntermediateEventCatchNonInterruptingSignal", IntermediateEventCatchNonInterruptingSignal.class);
        x.alias("IntermediateEventCatchNonInterruptingTimer", IntermediateEventCatchNonInterruptingTimer.class);
        x.alias("IntermediateEventCatchParallelMultiple", IntermediateEventCatchParallelMultiple.class);
        x.alias("IntermediateEventCatchSignal", IntermediateEventCatchSignal.class);
        x.alias("IntermediateEventCatchTimer", IntermediateEventCatchTimer.class);
        x.alias("IntermediateEventThrowCompensation", IntermediateEventThrowCompensation.class);
        x.alias("IntermediateEventThrowEscalation", IntermediateEventThrowEscalation.class);
        x.alias("IntermediateEventThrowLink", IntermediateEventThrowLink.class);
        x.alias("IntermediateEventThrowMessage", IntermediateEventThrowMessage.class);
        x.alias("IntermediateEventThrowMultiple", IntermediateEventThrowMultiple.class);
        x.alias("IntermediateEventThrowSignal", IntermediateEventThrowSignal.class);
        
        x.alias("StartEvent", StartEvent.class);
        x.alias("StartEventCompensation", StartEventCompensation.class);
        x.alias("StartEventCondition", StartEventCondition.class);
        x.alias("StartEventError", StartEventError.class);
        x.alias("StartEventEscalation", StartEventEscalation.class);
        x.alias("StartEventMessage", StartEventMessage.class);
        x.alias("StartEventMultiple", StartEventMultiple.class);
        x.alias("StartEventNonInterruptingCondition", StartEventNonInterruptingCondition.class);
        x.alias("StartEventNonInterruptingEscalation", StartEventNonInterruptingEscalation.class);
        x.alias("StartEventNonInterruptingMessage", StartEventNonInterruptingMessage.class);
        x.alias("StartEventNonInterruptingMultiple", StartEventNonInterruptingMultiple.class);
        x.alias("StartEventNonInterruptingParallelMultiple", StartEventNonInterruptingParallelMultiple.class);
        x.alias("StartEventNonInterruptingSignal", StartEventNonInterruptingSignal.class);
        x.alias("StartEventNonInterruptingTimer", StartEventNonInterruptingTimer.class);
        x.alias("StartEventParallelMultiple", StartEventParallelMultiple.class);
        x.alias("StartEventSignal", StartEventSignal.class);
        x.alias("StartEventTimer", StartEventTimer.class);
        
        x.alias("Gateway", Gateway.class);
        x.alias("GatewayComplex", GatewayComplex.class);
        x.alias("GatewayEventBased", GatewayEventBased.class);
        x.alias("GatewayOR", GatewayOR.class);
        x.alias("GatewayParallel", GatewayParallel.class);
        x.alias("GatewayXOR", GatewayXOR.class);
        
        x.alias("Lane", Lane.class);
        x.alias("Pool", Pool.class);
        
        x.alias("BusinessRuleTask", BusinessRuleTask.class);
        x.alias("ManualTask", ManualTask.class);
        x.alias("ReceiveTask", ReceiveTask.class);
        x.alias("ScriptTask", ScriptTask.class);
        x.alias("SendTask", SendTask.class);
        x.alias("ServiceTask", ServiceTask.class);
        x.alias("Task", Task.class);
        x.alias("UserTask", UserTask.class);
        
        
        
    }
}
