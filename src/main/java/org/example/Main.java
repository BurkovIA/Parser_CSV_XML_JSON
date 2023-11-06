package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> list = parseXML("src/xml/data.xml");
        String json = listToJson(list);
        writeString(json);
    }
    private static List<Employee> parseXML(String s) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/xml/data.xml"));
        Node staff = document.getDocumentElement();
        NodeList nodeList = staff.getChildNodes();
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Element element = (Element) node_;
                Employee employee = new Employee();
                employee.setId(Long.valueOf(element.getElementsByTagName("id").item(0).getTextContent()));
                employee.setFirstName(element.getElementsByTagName ("firstName").item(0).getTextContent());
                employee.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
                employee.setCountry(element.getElementsByTagName ("country").item(0).getTextContent());
                employee.setAge(Integer.parseInt(element.getElementsByTagName ("age").item(0).getTextContent()));
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
    private static String listToJson(List<Employee> list) {
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list,listType);
        return json;
    }
    private static void writeString(String json) {
        File fileJson = new File("data2.json");
        try (FileWriter fileWriter = new FileWriter(fileJson, true)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
