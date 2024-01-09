package com.example.Climate.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Util {
    public void csv2Dto1D() throws IOException {
        Set<String> s = new TreeSet<>();
        String inputFile = "C:/Users/admin/Downloads/dataset-Climate/Population.csv";
        String outputFile = "C:/Users/admin/Downloads/dataset-Climate/PopulationOut.csv";
        List<String[]> population2D = readCSV(inputFile);
        String[] fields = population2D.get(0);
        List<String[]> population1D = new ArrayList<>();
        population1D.add(new String[]{"Country Name", "Country Code", "Year", "Population"});
        for (int i = 2; i < fields.length; i++) {
            for (int j = 1; j < population2D.size(); j++) {
                String name = population2D.get(j)[0];
                String code = population2D.get(j)[1];
                String year = fields[i];
                String population = population2D.get(j)[i];
                s.add(name);
                String[] newRow = {name, code, year, population};
                population1D.add(newRow);
            }
        }
        for (String[] row : population1D) {
            System.out.println(String.join(" ", row));
        }
        System.out.println(s.size());
//        FileWriter writer = new FileWriter(outputFile);
//        for (String[] row: population1D) {
//            writer.append(String.join(",", row)).append("\n");
//        }
    }

    public List<String[]> readCSV(String inputFile) throws FileNotFoundException {
        try (CSVReader cr = new CSVReader(new FileReader(inputFile))) {
            return cr.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() {

    }

    public static void main(String[] args) throws IOException {
        Util util = new Util();
        util.csv2Dto1D();

    }
}
