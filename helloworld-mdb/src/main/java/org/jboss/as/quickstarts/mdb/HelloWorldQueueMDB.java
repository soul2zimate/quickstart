/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <p>
 * A simple Message Driven Bean that asynchronously receives and processes the messages that are sent to the queue.
 * </p>
 * 
 * @author Serge Pagop (spagop@redhat.com)
 * 
 */
@MessageDriven(name = "HelloWorldQueueMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "HELLOWORLDMDBQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "maximumRedeliveries", propertyValue = "4"),
        @ActivationConfigProperty(propertyName = "initialRedeliveryDelay", propertyValue = "7000"),

        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class HelloWorldQueueMDB implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message rcvMessage) {
        TextMessage textMessage = (TextMessage) rcvMessage;
        try {
            System.out.println("\n=> " + new java.util.Date() + "\tTestMDB Received Message >  " + textMessage.getText());
            System.out.println("\tbefore mdc.setRollbackOnly()");
            mdc.setRollbackOnly();
            System.out.println("\tafter mdc.setRollbackOnly()");
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
