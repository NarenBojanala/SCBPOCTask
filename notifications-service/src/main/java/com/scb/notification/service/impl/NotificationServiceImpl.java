/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.notification.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.scb.notification.model.*;
import com.scb.notification.model.Process;
import com.scb.notification.restclient.odp.ODPRestClient;
import com.scb.notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TODO
 *
 * @author naren
 */

@Service
public class NotificationServiceImpl implements NotificationService
{

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ODPRestClient ODPRestClient;

  private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

  /**
   * Creates a new instance of the {@link NotificationServiceImpl} class.
   */
  public NotificationResponse process(NotificationRequest notificationRequest)
  {
    NotificationResponse notificationResponse = new NotificationResponse();
    try {
      logger.info("Entered convert Json to xml process");
      notificationResponse.setExternalRefId(notificationRequest.getRefId());
      SendEmailRequest sendEmailRequest = prepareRequest(notificationRequest);
      String json= objectMapper.writeValueAsString(sendEmailRequest);
      JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
      xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);
      xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
      ObjectWriter ow = xmlMapper.writer().withRootName("sendEmailRequest");
      StringWriter sw = new StringWriter();
      ow.writeValue(sw, jsonNode);
      String convertedXML = sw.toString();
      System.out.println(convertedXML);
     // String response = ODPRestClient.publishXmlToOPS(convertedXML);
      return notificationResponse;
    } catch (Exception e) {
      List<String> errors = new ArrayList<>();
      errors.add(e.getMessage());
      logger.error("Can not process current request {}" +e.getMessage());
      notificationResponse.setErrors(errors);
      notificationResponse.setWarnings(errors);
    }
    return notificationResponse;
  }

  private SendEmailRequest prepareRequest(NotificationRequest notificationRequest)
  {
    SendEmailRequest sendEmailRequest= new SendEmailRequest();
    MessageDetails messageDetails= new MessageDetails();
    Header header= new Header();
    header.setMessageDetails(messageDetails);
    messageDetails.setMessageVersion("1.0");
    MessageType messageType= new MessageType();
    messageType.setTypeName("Communication");
    SubType SubType = new SubType();
    SubType.setSubTypeName("sendEmail");
    messageType.setSubType(SubType);
    messageDetails.setMessageType(messageType);
    sendEmailRequest.setHeader(header);
    MessageSender messageSender = new MessageSender();
    messageSender.setMessageSender("SCROE");
    SenderDomain senderDomain= new SenderDomain();
    senderDomain.setDomainName("GroupFunctions");
    SubDomainName sub = new SubDomainName();
    sub.setSubDomainType("Communication");
    senderDomain.setSubDomainName(sub);
    messageSender.setCountryCode(notificationRequest.getCountry());
    messageSender.setSenderDomain(senderDomain);
    OriginationDetails originationDetails= new OriginationDetails();
    originationDetails.setMessageSender(messageSender);
    originationDetails.setTrackingId("scroe-167362802571874870526340");
    originationDetails.setInitiatedTimestamp("2023-01-13T16:40:25.719Z");
    originationDetails.setMessageTimestamp("2023-01-13T16:40:25.719Z");
    header.setCaptureSystem("MDIS");
    header.setOriginationDetails(originationDetails);
    header.setProcess(new Process());


    SendEmailReqPayload sendEmailReqPayload = new SendEmailReqPayload();
    sendEmailRequest.setSendEmailReqPayload(sendEmailReqPayload);
    sendEmailReqPayload.setPayloadFormat("XML");
    sendEmailReqPayload.setPayloadVersion("1.0");
    SendEmailReq sendEmailReq = new SendEmailReq();
    sendEmailReqPayload.setSendEmailReq(sendEmailReq);


    CommunicationInfo communicationInfo= new CommunicationInfo();
    sendEmailReq.setCommunicationInfo(communicationInfo);
    communicationInfo.setUniqueSenderReference("scroe-167362802571874870526340");
    communicationInfo.setDateTimeStamp("2023-01-13T16:40:25.719Z");

    DestinationChannelInfo destinationChannelInfo= new DestinationChannelInfo();
    communicationInfo.setDestinationChannelInfo(destinationChannelInfo);

    Email email= new Email();
    destinationChannelInfo.setEmail(email);
    email.setToAddress("toSend");
    email.setSenderAddress("SG@sc.com");
    email.setMessageSubject("Q1REUyBGaWxlIE5vdCBSZWNlaXZlZC0xMy1KQU4tMjAyMw==");
    email.setMessageContent("JzwhRE9DVFlQRSBodG1sIFBVQkxJQyAiLS8vVzNDLy9EVEQgWEhUTUw");
    email.setLanguage("ENG");
    email.setMimeType("text/html");
    email.setUtfIndicator("Y");
    email.setMulitpleRecepientsIndicator("Y");

    return sendEmailRequest;
  }

}
