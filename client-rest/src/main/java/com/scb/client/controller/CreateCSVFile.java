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

import com.opencsv.CSVWriter;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class CreateCSVFile
{


  public static void main(String[] args) throws IOException {

    CSVWriter csvWriter = new CSVWriter(new FileWriter("example.csv"));

    Biff8EncryptionKey.setCurrentUserPassword("narendra");

    csvWriter.writeNext(new String[]{"1", "jan", "Male", "20"});
    csvWriter.writeNext(new String[]{"2", "con", "Male", "24"});
    csvWriter.writeNext(new String[]{"3", "jane", "Female", "18"});
    csvWriter.writeNext(new String[]{"4", "ryo", "Male", "28"});

    csvWriter.close();
  }
}

