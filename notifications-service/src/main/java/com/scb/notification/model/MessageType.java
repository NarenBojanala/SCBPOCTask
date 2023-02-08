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
public class MessageType
{
  /**
   * Creates a new instance of the {@link MessageType} class.
   */
 private String typeName;
  private SubType subType;
}
