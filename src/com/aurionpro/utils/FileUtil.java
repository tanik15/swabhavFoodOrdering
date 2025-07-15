package com.aurionpro.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
 private static final String ORDER_ID_FILE = "D:/AurionPro_java/MiniProjects/FoodOrderingApp/LastOrderId.txt";

 public static int readLastOrderId() throws IOException {
     try (BufferedReader br = new BufferedReader(new FileReader(ORDER_ID_FILE))) {
         return Integer.parseInt(br.readLine());
     }
 }

 public static void writeLastOrderId(int id) {
     try (FileWriter fw = new FileWriter(ORDER_ID_FILE)) {
         fw.write(String.valueOf(id));
     }catch (Exception e) {
		// TODO: handle exception
    	 System.out.println();
	}
 }
}

