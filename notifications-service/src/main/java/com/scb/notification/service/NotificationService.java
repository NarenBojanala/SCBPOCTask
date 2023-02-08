/* ***************************************************************************************
 * (c) COPYRIGHT Kuecker Pulse Integration, L.P. 2023 All Rights Reserved
 * No part of this copyrighted work may be reproduced, modified, or distributed
 * in any form or by any means without the prior written permission of Kuecker Pulse
 * Integration, L.P.
 ****************************************************************************************/
package com.scb.notification.service;

import com.scb.notification.model.NotificationRequest;
import com.scb.notification.model.NotificationResponse;

/**
 * Represents a TODO
 *
 * @author naren
 */
public interface NotificationService
{
  NotificationResponse process(NotificationRequest notificationService);

}
