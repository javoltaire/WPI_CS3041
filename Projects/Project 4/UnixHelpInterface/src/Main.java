import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller.ControllerSingleton;
import Model.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import Model.Category;
import Model.Command;
import Model.Enums.CATEGORIES;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @Author Jules Voltaire on 3/31/2016.
 * Main class
 * XML tutorial followed: http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
 */
public class Main extends Application {

    public static ObservableList<Category> categories = FXCollections.observableArrayList();
    private Category recent = new Category(CATEGORIES.RECENT.getFormalName());
    private Category custom = new Category(CATEGORIES.CUSTOM.getFormalName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();
        printData();
        //Parent root = FXMLLoader.load(getClass().getResource("View/MainPage.fxml"));
        //MainPage mainPage = new MainPage(categories);
        ControllerSingleton.getInstace().getMainPage().navigateToCategoriesView(categories);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(ControllerSingleton.getInstace().getMainPage(), 1920, 1080));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void readData(String filePath){
        try{
            File file = new File(filePath);                 // Create a file object with the given path

            // Creating a document to read the data;
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            // Get the root element;
            Element rootElement = document.getDocumentElement();

            // Reading the document
            if (rootElement.hasChildNodes()){                                                                   // If the root element has children
                NodeList catNodeList = rootElement.getChildNodes();                                                 // Get the list of child Nodes (Should be a list of categories)
                for(int cti = 0; cti< catNodeList.getLength(); cti++){                                                    // Loop through the list of Node Elements
                    org.w3c.dom.Node currentCatNode = catNodeList.item(cti);                                       // Get the current Node
                    if(currentCatNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE){                           // If the node is an Element
                        Category currentCategory = new Category(getFormalName(currentCatNode.getNodeName()));        // Create a new Category type
                        if(currentCatNode.hasChildNodes()){                                                          // if the current category node has child nodes
                            NodeList commandNodeList = currentCatNode.getChildNodes();                                   // Get a list of the command nodes
                            for(int cmi = 0; cmi < commandNodeList.getLength(); cmi++){                                               // Loop through the list of command nodes
                                org.w3c.dom.Node currentCommandNode = commandNodeList.item(cmi);                               // Get the current command node element
                                if(currentCommandNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE){                       // Check if the node is an actual element
                                    Element currentCommandElement = (Element)currentCommandNode;
                                    String commandName = currentCommandElement.getTagName();
                                    String commandDescription = currentCommandElement.getElementsByTagName("description").item(0).getTextContent();
                                    String commandDetails = currentCommandElement.getElementsByTagName("details").item(0).getTextContent();
                                    String commandFormat = currentCommandElement.getElementsByTagName("format").item(0).getTextContent();
                                    String commandExample = currentCommandElement.getElementsByTagName("example").item(0).getTextContent();
                                    String commandSourceLink = currentCommandElement.getElementsByTagName("sourcelink").item(0).getTextContent();
                                    boolean commandIsRecentlyUsed = Boolean.parseBoolean(currentCommandElement.getAttribute("recentlyused"));
                                    Command currentCommand = new Command(commandName, commandDescription, currentCategory, commandDetails,commandSourceLink,commandIsRecentlyUsed);

                                    org.w3c.dom.Node commandOptionNode = currentCommandElement.getElementsByTagName("options").item(0);
                                    if (commandOptionNode.hasChildNodes()){
                                        NodeList commandOptions = commandOptionNode.getChildNodes();
                                        for (int cmoi = 0; cmoi < commandOptions.getLength(); cmoi++){
                                            org.w3c.dom.Node currentOptionNode = commandOptions.item(cmoi);
                                            if(currentOptionNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE){
                                                String optionName = currentOptionNode.getNodeName();
                                                String optionDescription = currentOptionNode.getTextContent();
                                                Item currentOption = new Item(optionName, optionDescription);
                                                currentCommand.optionsProperty().add(currentOption);
                                            }
                                        }
                                    }
                                    currentCategory.getCommands().add(currentCommand);
                                    if(commandIsRecentlyUsed){
                                        recent.getCommands().add(currentCommand);
                                    }
                                }
                            }
                        }
                        categories.add(currentCategory);                                                                    // Add the category to the list of categories
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFormalName(String name){
        String formalName = "Category name Enum bot found";
        if (CATEGORIES.RECENT.isEqualTo(name)){
            formalName = CATEGORIES.RECENT.getFormalName();
        }
        else if (CATEGORIES.FILEANDFOLDER.isEqualTo(name)){
            formalName = CATEGORIES.FILEANDFOLDER.getFormalName();
        }
        else if (CATEGORIES.PROCESSMANAGEMENT.isEqualTo(name)){
            formalName = CATEGORIES.PROCESSMANAGEMENT.getFormalName();
        }
        else if (CATEGORIES.NETWORK.isEqualTo(name)){
            formalName = CATEGORIES.NETWORK.getFormalName();
        }
        else if (CATEGORIES.CUSTOM.isEqualTo(name)){
            formalName = CATEGORIES.CUSTOM.getFormalName();
        }
        return formalName;
    }

    private void initialize(){
        categories.add(recent);
        readData("src/Resources/CommandsList.xml");
        categories.add(custom);
    }

    private void printData(){
        System.out.println("All Categories");
        for(Category cat : categories){
            System.out.println("\t" + cat.toPrintableString());
        }
    }

 //region to be fixed
// private void SaveCategory(Category category){
//
//     try{
//         //Building a document
//         DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//         DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//         Document document = documentBuilder.newDocument();
//
//         //Creating the root element
//         Element rootElement = document.createElement("Categories");
//         document.appendChild(rootElement);
//
//         Element catElement = document.createElement(category.getName().replaceAll("\\s",""));        // Create a category element
//         rootElement.appendChild(catElement);                                    // Add the element to the root
//         catElement.setAttribute("id", category.getId().toString());             // Set the id as an attribute
//
//         if(category.getName().equals(CATEGORIES.RECENT.toString())){
//             for(Command command : category.getCommands()){
//                 Element recentIdElement = document.createElement("id");
//                 recentIdElement.appendChild(document.createTextNode(command.getId().toString()));
//                 catElement.appendChild(recentIdElement);
//             }
//         }
//         else{
//             for(Command command : category.getCommands()){
//                 Element commandELement = document.createElement(command.getName().replaceAll("\\s",""));
//                 catElement.appendChild(commandELement);
//                 commandELement.setAttribute("id", command.getId().toString());
//
//                 // Description element
//                 Element descElement = document.createElement("Description");
//                 descElement.appendChild(document.createTextNode(command.getDescription()));
//                 commandELement.appendChild(descElement);
//
//                 // Details element
//                 Element detailsElement = document.createElement("Details");
//                 detailsElement.appendChild(document.createTextNode(command.getDetails()));
//                 commandELement.appendChild(detailsElement);
//
//                 // Details element
//                 Element exampleElement = document.createElement("Example");
//                 exampleElement.appendChild(document.createTextNode(command.getExample()));
//                 commandELement.appendChild(exampleElement);
//
//                 // See also element
//                 Element seeAlsoElement = document.createElement("SeeAlso");
//                 for(Command seeAlsoCommand : command.getSeeAlso()){
//                     Element idElement = document.createElement("id");
//                     idElement.appendChild(document.createTextNode(seeAlsoCommand.getId().toString()));
//                     seeAlsoElement.appendChild(idElement);
//                 }
//
//                 // Details element
//                 Element sourceLinkElement = document.createElement("Source");
//                 sourceLinkElement.appendChild(document.createTextNode(command.getSourceLink()));
//                 commandELement.appendChild(sourceLinkElement);
//
//             }
//         }
//
//         // Saving to xml file
//         TransformerFactory transformerFactory = TransformerFactory.newInstance();
//         Transformer transformer = transformerFactory.newTransformer();
//         DOMSource source = new DOMSource(document);
//         StreamResult result = new StreamResult(new File("src/Resources/CommandsList.xml"));
//         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//         transformer.transform(source, result);
//
//     } catch (TransformerException e) {
//         e.printStackTrace();
//     } catch (ParserConfigurationException e) {
//         e.printStackTrace();
//     }
// }
//
//    private void readXMLFile(String path){
//        try {
//            File xmlFile = new File(path);
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document = documentBuilder.parse(xmlFile);
//            document.getDocumentElement().normalize();
//
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void createData(){
//        Category recentCategory = new Category(CATEGORIES.RECENT.toString());
//        Category fileAndFolderCategory = new Category(CATEGORIES.FILEANDFOLDER.toString());
//        Category compressingCategory = new Category(CATEGORIES.COMPRESSING.toString());
//        Category processManCategory = new Category(CATEGORIES.PROCESSMANAGEMENT.toString());
//        Category networkCategory = new Category(CATEGORIES.NETWORK.toString());
//        Category customCategory = new Category(CATEGORIES.CUSTOM.toString());
//
//        categories.add(recentCategory);
//        categories.add(fileAndFolderCategory);
//        categories.add(compressingCategory);
//        categories.add(processManCategory);
//        categories.add(networkCategory);
//        categories.add(customCategory);
//
//        Command lsCommand = new Command("ls", "list directory contents", fileAndFolderCategory);
//        Command cpCommand = new Command("cp", "copy files", fileAndFolderCategory);
//        Command touchCommand = new Command("touch", "change file access and modification times", fileAndFolderCategory);
//        Command compressCommand = new Command("compress", "compress data", compressingCategory);
//        Command uncompressCommand = new Command("uncompress", "expand data", compressingCategory);
//        Command killCommand = new Command("kill", "terminate or signal a process", fileAndFolderCategory);
//        Command rcpCommand = new Command("rcp", "remote file copy", networkCategory);
//
//        lsCommand.setDetails("For each operand that names a file of a type other than directory, ls " +
//                "     displays its name as well as any requested, associated information.  For" +
//                "     each operand that names a file of type directory, ls displays the names" +
//                "     of files contained within that directory, as well as any requested, asso" +
//                "     ciated information." +
//                "     If no operands are given, the contents of the current directory are dis-" +
//                "     played.  If more than one operand is given, non-directory operands are" +
//                "     displayed first; directory and non-directory operands are sorted sepa-" +
//                "     rately and in lexicographical order.");
//
//        lsCommand.setSourceLink("https://www.freebsd.org/cgi/man.cgi?query=ls&apropos=0&sektion=0&manpath=FreeBSD+10.2-RELEASE&arch=default&format=html");
//
//        recentCategory.getCommands().add(lsCommand);
//        fileAndFolderCategory.getCommands().add(lsCommand);
//        fileAndFolderCategory.getCommands().add(cpCommand);
//        fileAndFolderCategory.getCommands().add(touchCommand);
//        compressingCategory.getCommands().add(compressCommand);
//        compressingCategory.getCommands().add(uncompressCommand);
//        processManCategory.getCommands().add(killCommand);
//        networkCategory.getCommands().add(rcpCommand);
//
//    }
    //endregion
}