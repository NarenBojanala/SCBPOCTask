package com.scb.notification.controller.xcro;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import com.scb.notification.model.NotificationRequest;
import com.scb.notification.model.NotificationResponse;
import com.scb.notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.jms.Topic;
import java.io.StringWriter;
import java.util.List;

/**
 * Represents a TODO
 *
 * @author naren
 */

@RestController
@RequestMapping(value = "v1/notification-api")
public class NotificationController
{
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private Environment env;

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@PostMapping(value = "/convertjsontoxml")
	public ResponseEntity<NotificationResponse> getNotificationData(@RequestBody NotificationRequest notificationRequest) {
		try {
			NotificationResponse notificationResponse ;
			notificationResponse = notificationService.process(notificationRequest);
			return ResponseEntity.ok( notificationResponse);
		} catch (Exception ex) {
			logger.error("There is a issue with current request {}",ex.getMessage() );
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	@PostMapping(path ="/notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<NotificationRequest> newNotification(@RequestBody NotificationRequest notificationRequest) {
		try {
			Topic empTopic = jmsTemplate.getConnectionFactory().createConnection()
					.createSession(true,1).createTopic("NotificationTopic");
			int empId = (int)(Math.random() * 50 + 1);
			logger.info("Sending NotificationRequest Object: " + notificationRequest);
			jmsTemplate.convertAndSend(empTopic, notificationRequest);
			return new ResponseEntity<>(notificationRequest, HttpStatus.OK);

		} catch (Exception exception) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void convetJSONtoXML(NotificationRequest notificationRequest)
	{
		try {
		String json= objectMapper.writeValueAsString(notificationRequest);
		JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);
		xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		ObjectWriter ow = xmlMapper.writer().withRootName("Product");
		StringWriter sw = new StringWriter();
		ow.writeValue(sw, jsonNode);
		//NotificationRequest notificationRequest1 = ow.writeValue(sw, jsonNode);
		//System.out.println(xml);
		//String response = publishXmltoOPS(xml);
		} catch (Exception e) {
			List<String> errors = null;
			errors.add(e.getMessage());
			logger.error("Can not process current request {}" +e.getMessage());

		}
		//return xml;
	}
}

