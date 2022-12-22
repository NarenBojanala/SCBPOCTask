/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2022 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.client.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Represents a TODO
 *
 * @author naren
 */

public class CreatePasswordProtectedPdfExample
{
  /**
   * Creates a new instance of the {@link CreatePasswordProtectedPdfExample} class.
   */
  public static void main(String[] args) {
    try {
      String pdfFilePath = "D:/Create Password Protected Pdf Example.pdf";

      OutputStream fos = new FileOutputStream(new File(pdfFilePath));
      Document document = new Document();
      PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);

      String userPassword = "a";
      String ownerPassword = "b";

      pdfWriter.setEncryption(userPassword.getBytes(),
                              ownerPassword.getBytes(), PdfWriter.ALLOW_PRINTING,
                              PdfWriter.ENCRYPTION_AES_128);

      document.open();


      document.add(new Paragraph("This is Password protected PDF file"));

      document.close();
      fos.close();

      System.out.println("PDF created in >> " + pdfFilePath);

    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
}
