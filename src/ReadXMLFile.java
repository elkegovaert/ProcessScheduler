import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLFile {

    public List<Process> leesProcessen(String bestandnaam) throws SAXException, IOException, ParserConfigurationException {

        List<Process> processList = new ArrayList<>();

        //aan te passen
        // GLENN
        // File xmlFile = new File("C:\\Users\\glenn\\eclipse-workspace\\ProcessScheduler\\"+bestandnaam);
        // ELKE
        File xmlFile = new File("C:\\Users\\glenn\\eclipse-workspace\\ProcessScheduler\\"+bestandnaam);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("process");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Process process = new Process();
                NodeList children = eElement.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node n = children.item(j);
                    if (n.getNodeType() == Node.ELEMENT_NODE) {
                        Element etmp = (Element) n;
                        if (etmp.getNodeName() == "pid") {
                            process.setId(Integer.parseInt(etmp.getTextContent()));
                        }
                        if (etmp.getNodeName() == "arrivaltime") {
                            process.setArrivalTime(Integer.parseInt(etmp.getTextContent()));
                        }
                        if (etmp.getNodeName() == "servicetime") {
                            int serviceTime = Integer.parseInt(etmp.getTextContent());
                            process.setServiceTime(serviceTime);
                            process.setRemainingServiceTime(serviceTime);
                        }
                    }
                }
                processList.add(process);
            }
        }
        //enkel voor dubbelcheck uitsschrijven
        /*
        for (int i = 0; i < processList.size(); i++) {
            System.out.println(processList.get(i).getId() + " " + processList.get(i).getArrivalTime() + " " + processList.get(i).getServiceTime());
        }
*/
        return processList;

    }

}
