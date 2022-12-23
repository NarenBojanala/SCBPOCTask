
package com.scb.client.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.StringWriter;

/**
 * Represents a TODO
 *
 * @author naren
 */


public class JsonToXmlController
{
  public String convertJsonToXml(String json, String rootName) {
    ObjectMapper jsonMapper = new ObjectMapper();
    try {
      JsonNode jsonNode = jsonMapper.readValue(json, JsonNode.class);
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
      xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);
      xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
      ObjectWriter ow = xmlMapper.writer().withRootName(rootName);
      StringWriter sw = new StringWriter();
      ow.writeValue(sw, jsonNode);
      String xml = sw.toString();
      return xml;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) throws IOException {
    JsonToXmlController example = new JsonToXmlController();
    String json = "{\"name\":\"Java Tutorials\",\"author\":\"Jake Parker\",\"language\":\"English\",\"price\":35.00}";
    String xml = example.convertJsonToXml(json, "Product");
    System.out.println(xml);
  }
}