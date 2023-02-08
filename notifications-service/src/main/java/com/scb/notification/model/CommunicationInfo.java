/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.notification.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Represents a TODO
 *
 * @author naren
 */
@Getter
@Setter
public class CommunicationInfo
{
  /**
   * Creates a new instance of the {@link CommunicationInfo} class.
   */
 private String uniqueSenderReference;
 private String dateTimeStamp;
 private DestinationChannelInfo destinationChannelInfo;

}
