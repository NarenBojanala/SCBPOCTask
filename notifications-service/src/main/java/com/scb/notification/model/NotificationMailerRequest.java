/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.notification.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a TODO
 *
 * @author naren
 */
@Getter
@Setter
public class NotificationMailerRequest
{
  /**
   * Creates a new instance of the {@link NotificationMailerRequest} class.
   */
      private Integer id;
      private String subject;
      private String payload;
      private String email;
      private AttachmentPasswordDetail attachmentPasswordDetail;
      private SendDetails send;
      private Acknowledgement ack1;
      private Acknowledgement ack3;
      private String dealId;
}
