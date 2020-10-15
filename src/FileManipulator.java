import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

public class FileManipulator {
    private String path;    //Field

    public FileManipulator(String path){    //Constructor
        this.path = path;

    }

    Path filePath = Paths.get(path);      //Path of file
    List<String> fileLines;     //String list of lines

    {
        try {
            fileLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);       //read lines
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void decodeWriter(String input, int lineNumber) {    //write to file
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());  //encode base 64

        try {
            fileLines.set(lineNumber, encodedString);       //set new input on line number
            Files.write(filePath,fileLines,StandardCharsets.UTF_8);     //Write it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String decodeReader(int lineNumber){     //read from file
        byte[] decodedBytes = Base64.getDecoder().decode(fileLines.get(lineNumber));        //Decode base 64
        String decodedString = new String(decodedBytes);        //Convert to string
        return decodedString;
    }

    public void normalWriter(String input, int lineNumber){
        try {
            fileLines.set(lineNumber, input);       //set new input on line number
            Files.write(filePath,fileLines,StandardCharsets.UTF_8);     //Write it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String normalReader(int lineNumber){     //read from file
        return fileLines.get(lineNumber);
    }
}