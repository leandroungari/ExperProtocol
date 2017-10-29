
import bpmn.factory.BPMNFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leandroungari
 */
public class Teste {


    public static void main(String[] args) {
        
        String json =" {\"protocol\": {\"elements\":[{\"id\":\"#pool0\",\"type\":\"BPMNPool\",\"x\":447,\"y\":171,\"name\":\"participant\",\"width\":600,\"description\":\"Exemplo\",\"elements\":[{\"id\":\"#pool0lane0\",\"type\":\"BPMNLane\",\"x\":30,\"y\":0,\"name\":\"lane\",\"height\":200,\"description\":\"\",\"elements\":[{\"id\":\"#event2\",\"type\":\"BPMNEvent\",\"x\":70,\"y\":95,\"name\":\"start-event-none\",\"description\":\"\",\"elements\":[],\"transitions\":[{\"origem\":\"event2\",\"destino\":\"activity3\",\"pontoOrigem\":3,\"pontoDestino\":2,\"id\":\"#transicao4\",\"tipo\":\"message-flow\"}]},{\"id\":\"#event9\",\"type\":\"BPMNEvent\",\"x\":226,\"y\":99,\"name\":\"intermediate-event-none\",\"description\":\"\",\"elements\":[],\"transitions\":[{\"origem\":\"event9\",\"destino\":\"gateway11\",\"pontoOrigem\":1,\"pontoDestino\":3,\"id\":\"#transicao12\",\"tipo\":\"sequence-flow\"},{\"origem\":\"activity5\",\"destino\":\"event9\",\"pontoOrigem\":2,\"pontoDestino\":3,\"id\":\"#transicao10\",\"tipo\":\"message-flow\"}]},{\"id\":\"#gateway11\",\"type\":\"BPMNGateway\",\"x\":353,\"y\":98,\"name\":\"gateway-parallel\",\"description\":\"\",\"elements\":[],\"transitions\":[{\"origem\":\"gateway11\",\"destino\":\"event13\",\"pontoOrigem\":0,\"pontoDestino\":2,\"id\":\"#transicao14\",\"tipo\":\"sequence-flow\"},{\"origem\":\"event9\",\"destino\":\"gateway11\",\"pontoOrigem\":1,\"pontoDestino\":3,\"id\":\"#transicao12\",\"tipo\":\"sequence-flow\"}]},{\"id\":\"#event13\",\"type\":\"BPMNEvent\",\"x\":478,\"y\":99,\"name\":\"end-event-none\",\"description\":\"\",\"elements\":[],\"transitions\":[{\"origem\":\"gateway11\",\"destino\":\"event13\",\"pontoOrigem\":0,\"pontoDestino\":2,\"id\":\"#transicao14\",\"tipo\":\"sequence-flow\"}]}]}]},{\"id\":\"#pool1\",\"type\":\"BPMNPool\",\"x\":427,\"y\":427,\"name\":\"participant\",\"width\":600,\"description\":\"Exemplo\",\"elements\":[{\"id\":\"#pool1lane0\",\"type\":\"BPMNLane\",\"x\":30,\"y\":0,\"name\":\"lane\",\"height\":200,\"description\":\"\",\"elements\":[{\"id\":\"#activity3\",\"type\":\"BPMNActivity\",\"x\":79,\"y\":83,\"name\":\"manual-task\",\"description\":\"Exemplo\",\"elements\":[],\"transitions\":[{\"origem\":\"activity3\",\"destino\":\"activity5\",\"pontoOrigem\":0,\"pontoDestino\":3,\"id\":\"#transicao7\",\"tipo\":\"sequence-flow\"},{\"origem\":\"event2\",\"destino\":\"activity3\",\"pontoOrigem\":3,\"pontoDestino\":2,\"id\":\"#transicao4\",\"tipo\":\"message-flow\"}]},{\"id\":\"#activity5\",\"type\":\"BPMNActivity\",\"x\":242,\"y\":82,\"name\":\"manual-task\",\"description\":\"Exemplo\",\"elements\":[],\"transitions\":[{\"origem\":\"activity5\",\"destino\":\"event9\",\"pontoOrigem\":2,\"pontoDestino\":3,\"id\":\"#transicao10\",\"tipo\":\"message-flow\"},{\"origem\":\"activity3\",\"destino\":\"activity5\",\"pontoOrigem\":0,\"pontoDestino\":3,\"id\":\"#transicao7\",\"tipo\":\"sequence-flow\"}]}]}]}]}}";
        
        BPMNFactory.create(json);
        String xml = BPMNFactory.getXML();
        
        System.out.println(xml);
    }
}
