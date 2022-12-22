/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2022 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.client.controller;

/**
 * Represents a TODO
 *
 * @author naren
 */
// Java Program to Illustrate Reading
// Data to Excel File Using Apache POI

// Import statements
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReadController {

  // Main driver method
  public static void main(String[] args) throws IOException
  {
    FileInputStream file = null;
    FileWriter fw = null ;

    // Try block to check for exceptions
    try {

      // Reading file from local directory
      file = new FileInputStream(
          new File("D:\\G001_CTDS_XP_20220204142556.xlsx"));
      fw = new FileWriter("D:\\testout.txt");

      // Create Workbook instance holding reference to
      // .xlsx file
      XSSFWorkbook workbook = new XSSFWorkbook(file);

      // Get first/desired sheet from the workbook
      XSSFSheet sheet = workbook.getSheetAt(0);

      // Iterate through each rows one by one
      Iterator<Row> rowIterator = sheet.iterator();

      // Till there is an element condition holds true
      while (rowIterator.hasNext()) {

        Row row = rowIterator.next();

        // For each row, iterate through all the
        // columns
        Iterator<Cell> cellIterator
            = row.cellIterator();

        while (cellIterator.hasNext()) {

          Cell cell = cellIterator.next();

          // Checking the cell type and format
          // accordingly
          switch (cell.getCellType()) {

            // Case 1
            case Cell.CELL_TYPE_NUMERIC:
             /* System.out.print(
                  cell.getNumericCellValue()
                  + "t");*/
              fw.write( cell.getNumericCellValue()
                        + "\t\n");
              break;

            // Case 2
            case Cell.CELL_TYPE_STRING:
             /* System.out.print(
                  cell.getStringCellValue()
                  + "t");*/
              fw.write(  cell.getStringCellValue()
                         + "\t\n");
              break;
          }
        }

        System.out.println("");
      }

      // Closing file output streams

    }

    // Catch block to handle exceptions
    catch (Exception e) {

      // Display the exception along with line number
      // using printStackTrace() method
      e.printStackTrace();
    }
    finally
    {
      file.close();
      fw.close();
    }
  }
}

