package com.example.Climate.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Util {
    public void csv2Dto1D() throws IOException {
        Set<String> s = new TreeSet<>();
        String inputFile = "C:/Users/admin/Downloads/dataset-Climate/Population.csv";
        String outputFile = "C:/Users/admin/Downloads/dataset-Climate/PopulationOut2.csv";
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
        System.out.println(s.size());
        FileWriter writer = new FileWriter(outputFile);
        for (String[] row : population1D) {
            if (row[2].equals("2013")) {
                writer.append(String.join(",", row)).append("\n");
            }
        }
    }

    public List<String[]> readCSV(String inputFile) throws FileNotFoundException {
        try (CSVReader cr = new CSVReader(new FileReader(inputFile))) {
            return cr.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        addDataToDatabase();
    }

    public static void addDataToDatabase() {
        String user = "root";
        String password = "30092002aA@";
        String jdbcUrl = "jdbc:postgresql://monorail.proxy.rlwy.net:39119/climate";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            Statement statement = connection.createStatement();
            String sqlFilePath = "F:\\Intern\\climate-change-awareness\\climate_dump.sql";

            // Read SQL dump file
            BufferedReader br = new BufferedReader(new FileReader(sqlFilePath));
            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sql.append(line).append("\n");
            }
            br.close();

            // Execute SQL statements
            String[] sqlStatements = sql.toString().split(";");
            for (String sqlStatement : sqlStatements) {
                if (!sqlStatement.trim().isEmpty()) {
                    statement.execute(sqlStatement);
                }
            }

            connection.close();
            System.out.println("SQL statements executed successfully.");

        } catch (SQLException | FileNotFoundException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
