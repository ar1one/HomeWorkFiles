package Homework2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<String> listOfSaves = new ArrayList<>();
        GameProgress gp1 = new GameProgress(10, 12, 14, 16);
        GameProgress gp2 = new GameProgress(17, 19, 21, 23);
        GameProgress gp3 = new GameProgress(24, 26, 28, 30);
        save("C:\\Games\\savegames\\data1.dat", gp1);
        listOfSaves.add("C:\\Games\\savegames\\data1.dat");
        save("C:\\Games\\savegames\\data2.dat", gp2);
        listOfSaves.add("C:\\Games\\savegames\\data2.dat");
        save("C:\\Games\\savegames\\data3.dat", gp3);
        listOfSaves.add("C:\\Games\\savegames\\data3.dat");

        zipFiles("C:\\Games\\savegames\\data.zip", listOfSaves);


    }

    public static void save(String url, GameProgress gp) {
        try (FileOutputStream fos = new FileOutputStream(url);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gp);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void zipFiles(String zip, List<String> list) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip))) {
            for (int i = 0; i < list.size(); i++) {
                try (FileInputStream fis = new FileInputStream(list.get(i))) {
                    ZipEntry entry = new ZipEntry("data" + (i + 1) + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
                File file = new File(list.get(i));
                file.delete();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}






