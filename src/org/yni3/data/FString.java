package org.yni3.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author yni3
 */
final public class FString {

    final private StringBuilder sb = new StringBuilder();
    final private String filename;

    public static FString create(String filename) throws FileNotFoundException, IOException {
        return new FString(filename);
    }

    public FString(String filename) throws FileNotFoundException, IOException {
        read(filename, "UTF-8");
        this.filename = filename;
    }

    public FString(String filename, String encode) throws FileNotFoundException, IOException {
        read(filename, encode);
        this.filename = filename;
    }

    private void read(String filename, String encode) throws FileNotFoundException, IOException {
        BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encode));
        String line;
        while ((line = inFile.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
    }

    public String getFilename() {
        return this.filename;
    }

    @Override
    final public String toString() {
        return sb.toString();
    }

    final public StringBuilder getMutableString() {
        return sb;
    }
}
