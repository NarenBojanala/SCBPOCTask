/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.notification.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a TODO
 *
 * @author naren
 */
@Getter
@Setter
@XmlRootElement(name = "Foo")
public class MessageDetails
{
  /**
   * Creates a new instance of the {@link MessageDetails} class.
   */
  @XmlElement
  private String messageVersion;
  @XmlElement
  private MessageType messageType;
}
