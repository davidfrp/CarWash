import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

public class FileManipulator {

    private final String PATH_USER_HOME = System.getProperty("user.home");
    private final String WASH_CARD_FILE_EXTENSION = "washcard";
    private final String WASH_CARD_FILE_NAME_SUGGESTION = "Wash Card";
    private final String CREDIT_CARD_FILE_EXTENSION = "card";
    private final String CREDIT_CARD_FILE_NAME_SUGGESTION = "Credit Card";

    public FileManipulator() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public String readFile(int lineNumber, boolean decoded) throws IOException {     //read from file
        String pathToFile = "";

        if (System.getProperty("os.name").equals("Mac OS X")) {
            FileDialog fd = makeFileDialog("Please insert a wash card.", FileDialog.LOAD, "card");
            pathToFile = fd.getFile();
            if (pathToFile.isEmpty()) {
                System.out.println("Window was closed. No wash card was inserted");
            }
        } else {
            JFileChooser loadFileChooser = makeJFileChooser("Please insert a wash card.", 1);
            loadFileChooser.setFileFilter(new FileNameExtensionFilter(WASH_CARD_FILE_NAME_SUGGESTION, WASH_CARD_FILE_EXTENSION));
            loadFileChooser.requestFocus();
            int selectedFile = loadFileChooser.showOpenDialog(null);

            if (selectedFile == JFileChooser.APPROVE_OPTION) {                                                                  //if 'open' was pressed.
                pathToFile = loadFileChooser.getSelectedFile().getAbsolutePath();                                               //get the absolute path to that file.
                //System.out.println(filename);                                                                               /* used for debug */
            } else if (selectedFile == JFileChooser.ERROR_OPTION || selectedFile == JFileChooser.CANCEL_OPTION) {               //if user closes the dialog window without pressing open, return null.
                System.out.println("No Wash Card inserted.\n");
                return null;
            }
        }

        List<String> fileLines = Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
        if (decoded) {
            return new String(Base64.getDecoder().decode(fileLines.get(lineNumber)));
        } else {
            return Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8).get(lineNumber);
        }
    }

    public void writeFileAtSpecificLocation(String input) {
        String filename = "";
        File fileToSave = null;

        if (System.getProperty("os.name").equals("Mac OS X")) {

            FileDialog fd = makeFileDialog("Please save your receipt somewhere safe.", FileDialog.SAVE, "txt");
            filename = fd.getFile();
            fileToSave = new File(fd.getFile());
            if (filename.isEmpty()) {
                System.out.println("Window was closed. No receipt printed.");
            } else {
                if (!filename.endsWith("txt")) {                                                         //in case the file the user specified does *not* end with file-ending, the program adds the file-ending (Windows like file-endings).
                    filename = fileToSave.getAbsolutePath() + ".txt";
                }

                try {
                    writeFile(filename, input, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {

            JFileChooser saveFileChooser = makeJFileChooser("Please save your receipt somewhere safe.", 2);
            saveFileChooser.setFileFilter(new FileNameExtensionFilter("Text Receipt", "txt"));
            int selectedFile = saveFileChooser.showSaveDialog(null);

            if (selectedFile == JFileChooser.APPROVE_OPTION) {                                                                //if 'save' was pressed.
                fileToSave = saveFileChooser.getSelectedFile();                                                          //get the file from the file-chooser that was specified.
                if (fileToSave.exists()) {
                    int option = JOptionPane.showConfirmDialog(null, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION); //if file already exists, ask to overwrite.
                    switch (option) {
                        case JOptionPane.YES_OPTION:
                            saveFileChooser.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                        case JOptionPane.CLOSED_OPTION:
                            System.out.println("Window was closed. No receipt dispensed.");
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            saveFileChooser.cancelSelection();
                            System.out.println("Window was closed. No receipt dispensed.");
                            return;
                    }
                }
                filename = fileToSave.getAbsolutePath();
            }

            if (!filename.endsWith("text")) { //in case the file the user specified does *not* end with file-ending, the program adds the file-ending (Windows like file-endings).
                filename = fileToSave.getAbsolutePath() + ".txt";
            }

            try {
                writeFile(filename, input, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (selectedFile == JFileChooser.CANCEL_OPTION | selectedFile == JFileChooser.ERROR_OPTION) {                  //if the user pressed cancel or close...
                System.out.println("Window was closed. No receipt printed.");
            }
        }
    }

    public void writeFile(String pathToFile, String input, boolean encoded) throws IOException {
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());  //encode base 64
        FileWriter writer = new FileWriter(pathToFile);
        if (encoded) {
            writer.write(encodedString);
        } else {
            writer.write(input);
        }
        writer.close();
    }


    public CreditCard loadCreditCard() {
        String filename = "";
        CreditCard loadedCreditCard;

        if (System.getProperty("os.name").equals("Mac OS X")) {

            FileDialog fd = makeFileDialog("Please insert your credit card.", FileDialog.LOAD, "card");
            filename = fd.getFile();
            if (filename.isEmpty()) {
                System.out.println("Window was closed. No credit card was inserted.");
                return null;
            }

        } else {
            /* GUI file chooser */
            JFileChooser loadFileChooser = makeJFileChooser("Please insert your credit card.", 1);          //1 is open dialog type (load file).
            loadFileChooser.setFileFilter(new FileNameExtensionFilter(CREDIT_CARD_FILE_NAME_SUGGESTION, CREDIT_CARD_FILE_EXTENSION));
            int selectedFile = loadFileChooser.showOpenDialog(null);                                                   //opens the dialog window and returns an integer depending on what the user did (approve, cancel, abort).

            if (selectedFile == JFileChooser.APPROVE_OPTION) {                                                               //if 'open' was pressed.
                filename = loadFileChooser.getSelectedFile().getAbsolutePath();                                              //get the absolute path to that file.
                //System.out.println(filename);                                                                              /* used for debug */
            } else if (selectedFile == JFileChooser.ERROR_OPTION || selectedFile == JFileChooser.CANCEL_OPTION) {            //if user closes the dialog window without pressing open, return null.
                System.out.println("Window was closed. No credit card was inserted..\n");
                return null;
            }
        }

        try {
            final ObjectInputStream bin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))); //BufferedInputStream is added for speed.
            loadedCreditCard = (CreditCard) bin.readObject();                                                            //The binary file is read and assigned to a ticket object.
            bin.close();                                                                                                 //The objectInputStream is closed and the object finalized.
            return loadedCreditCard;                                                                                     //The new CreditCard is returned.

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;                                                                                                 //return null is something goes wrong...
        }
    }

    private FileDialog makeFileDialog(String dialogTitle, int dialogtype, String ending) {
        FileDialog fd = new FileDialog(new JFrame(), dialogTitle, dialogtype);
        fd.setDirectory(PATH_USER_HOME);
        fd.setVisible(true);
        fd.setFilenameFilter((dir, name) -> name.endsWith(ending));
        return fd;
    }

    private JFileChooser makeJFileChooser(String dialogTitle, int dialogType) {
        JFileChooser fileChooser = new JFileChooser(PATH_USER_HOME, FileSystemView.getFileSystemView());                        //creates a new dialog windows which starts in the users home folder.
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setDialogType(dialogType);
        fileChooser.setMultiSelectionEnabled(false);                                                                      //disable the option for selecting multiple files in dialog window.
        if (dialogType == 2) {
            fileChooser.setSelectedFile(new File("Receipt"));                                                     //suggested filename
        }
        return fileChooser;
    }

    public void saveCreditCardAsBin(String filename, CreditCard creditCard) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        try {
            System.out.println("Writing Credit Card to drive...");
            out.writeObject(creditCard);                                                                                     //Here the file is written to the specified path.
        } catch (IOException e) {
            System.out.println("Error. Could not write file.");
            e.printStackTrace();
        } finally {                                                                                                      //a finally clause happen regardless of the operation was successful or not.
            out.close();                                                                                                 //we then close and empty the ObjectOutputStream
            out.flush();
            System.out.println("File closed successfully.");
        }
    }
}
