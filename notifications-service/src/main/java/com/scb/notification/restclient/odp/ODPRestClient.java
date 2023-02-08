/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.notification.restclient.odp;

import com.scb.notification.controller.xcro.NotificationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author naren
 */
@Service
public class ODPRestClient
{
  /**
   * Creates a new instance of the {@link ODPRestClient} class.
   */

  @Autowired
  private RestTemplate restTemplate;

  private static final Logger logger = LoggerFactory.getLogger(ODPRestClient.class);

  public String publishXmlToOPS(String requestXML)
  {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity entity = new HttpEntity(requestXML, headers  );
    ResponseEntity<String> response;
    try{
      response = restTemplate.exchange("baseURL", HttpMethod.POST, entity, String.class);
      return response.toString();
    } catch(HttpClientErrorException | HttpServerErrorException e) {
      logger.error("Received Unexpected error from ODP service" +e.getMessage());
      e.printStackTrace();
    } catch(Exception e) {
      logger.error("Received error from ODP service" +e.getMessage());
    }
    return "";
  }
}
