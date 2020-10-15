import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

public class FileManipulator {

    private final String userHome = System.getProperty("user.home");
    private final String filenameExtension = "washcard";
    private final String filenameSuggestion = "Wash Card";

    public FileManipulator(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public String readFile(int lineNumber, boolean decoded) throws IOException {     //read from file
        String pathToFile = "";

        JFileChooser loadFileChooser = makeFileChooser("Please insert a wash card.",1);
        loadFileChooser.requestFocus();
        int selectedFile = loadFileChooser.showOpenDialog(null);

        if(selectedFile == JFileChooser.APPROVE_OPTION){                                                                  //if 'open' was pressed.
            pathToFile = loadFileChooser.getSelectedFile().getAbsolutePath();                                               //get the absolute path to that file.
            //System.out.println(filename);                                                                               /* used for debug */
        } else if(selectedFile == JFileChooser.ERROR_OPTION || selectedFile == JFileChooser.CANCEL_OPTION){               //if user closes the dialog window without pressing open, return null.
            System.out.println("No Wash Card inserted.\n");
            return null;
        }

        List<String> fileLines = Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
        if (decoded) {
            return new String(Base64.getDecoder().decode(fileLines.get(lineNumber)));
        }
        else {
            return Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8).get(lineNumber);
        }
    }

    public void writeFile(String pathToFile, String input, boolean decoded) throws IOException {
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());  //encode base 64
        FileWriter writer = new FileWriter(pathToFile);
        if(decoded){
            writer.write(encodedString);
        }
        else{
            writer.write(input);
        }
        writer.close();
    }

    private JFileChooser makeFileChooser(String dialogTitle, int dialogType){
        JFileChooser fileChooser = new JFileChooser(userHome, FileSystemView.getFileSystemView());                        //creates a new dialog windows which starts in the users home folder.
        fileChooser.setFileFilter(new FileNameExtensionFilter(filenameSuggestion, filenameExtension));                     //sets up a filter for file-ending/extension to accept "Ticket"-files (.stub).
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setDialogType(dialogType);
        fileChooser.setMultiSelectionEnabled(false);                                                                      //disable the option for selecting multiple files in dialog window.
        if(dialogType == 2){
            fileChooser.setSelectedFile(new File("WashCard"));                                                     //suggested filename
        }
        return fileChooser;
    }
}