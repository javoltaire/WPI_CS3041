import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import Model.Category;
import Model.Command;
import Model.Enums.CATEGORIES;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 * @Author Jules Voltaire on 3/31/2016.
 * Main class
 * XML tutorial followed: http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
 */
public class Main extends Application {

    private List<Category> categories = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/MainPage.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

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