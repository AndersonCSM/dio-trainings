package br.com.dio.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class IOFilePersistence implements  FilePersistence{

    private final String currentDir = System.getProperty("user.dir");
    private final String storedDir = "/managedFiles/IO/";
    private final String fileName;

    public IOFilePersistence(String fileName) throws IOException{
        this.fileName = fileName;
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        clearFile();
    }


    @Override
    public String write(String data) {
        try(var fileWriter = new FileWriter(currentDir + storedDir + fileName, true);
            var bufferedWrite = new BufferedWriter(fileWriter);
            var printWrite = new PrintWriter(bufferedWrite);)
        {
            printWrite.println(data);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean remove(String sentence) {

        return false;
    }

    @Override
    public String replace(String oldContent, String newContent) {
        return "";
    }

    @Override
    public String findAll() throws FileNotFoundException {
        var content = new StringBuilder();
        try (var reader = new BufferedReader(new FileReader( currentDir+ storedDir + fileName))){
            String line;
            do {
                line = reader.readLine();
                if ((line != null)) content.append(line)
                        .append(System.lineSeparator());

            }while (line != null);
        } catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        String found = "";
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))){
            String line = reader.readLine();
            while (line != null){
                if (line.contains(sentence)){
                    found = line;
                    break;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }

    private void clearFile(){
        try {
        OutputStream outputStream = new FileOutputStream(new File(currentDir + storedDir + fileName));
        outputStream.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void createFile(){

    }
}
