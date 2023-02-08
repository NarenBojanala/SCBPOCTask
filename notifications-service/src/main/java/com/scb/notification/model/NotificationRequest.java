/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.notification.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a TODO
 *
 * @author naren
 */
@Getter
@Setter
public class NotificationRequest
{
  /**
   * Creates a new instance of the {@link NotificationRequest} class.
   */
    private Integer id;
    private String refId;
    private String country;
    private String sender;
    private List<String> recipients;
    private List<String> cc;
    private List<String> bcc;
    private String replyTo;
    private String notificationType;
    private String subject;
    private String contentType;
    private String contentBody;
    private String encoding;
    private String priority;
    //private List<Attachments> attachments;

}
