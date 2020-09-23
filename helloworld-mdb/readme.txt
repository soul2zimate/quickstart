1 start server with standalone-full.xml and configure the socket-binding and remote connector for remote broker
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=remote-artemis:add(host=localhost, port=61616)
/subsystem=messaging-activemq/remote-connector=remote-artemis:add(socket-binding=remote-artemis)
/subsystem=messaging-activemq/pooled-connection-factory=remote-artemis:add(connectors=[remote-artemis], entries=[java:/jms/remoteCF])


2 Configure the queue in the Artemis broker.xml deployment descriptor file.

            <address name="testQueue">
                <anycast>
                    <queue name="testQueue" />
                </anycast>
            </address>
            <address name="testTopic">
                <multicast/>
            </address>
            
Start the artemis server with ./artemis run

3 Deploy the quickstart example

mvn clean package wildfly:deploy


4 check result

Go to page http://127.0.0.1:8080/helloworld-mdb/HelloWorldMDBServletClientRemoteArtemis?queue and http://127.0.0.1:8080/helloworld-mdb/HelloWorldMDBServletClientRemoteArtemis?topic

check the sent messages messages in page.

Quickstart: Example demonstrates the use of JMS 2.0 and EJB 3.2 Message-Driven Bean in JBoss EAP.
Sending messages to ActiveMQQueue[testQueue]

The following messages will be sent to the destination:
Message (0): This is message 1
Message (1): This is message 2
Message (2): This is message 3
Message (3): This is message 4
Message (4): This is message 5

and received messages in console log. 

16:50:50,703 INFO  [class org.jboss.as.quickstarts.mdb.TestQueueMDB] (Thread-5 (ActiveMQ-client-global-threads)) Received Message from test queue: This is message 1
16:50:50,759 INFO  [class org.jboss.as.quickstarts.mdb.TestQueueMDB] (Thread-6 (ActiveMQ-client-global-threads)) Received Message from test queue: This is message 2
16:50:50,809 INFO  [class org.jboss.as.quickstarts.mdb.TestQueueMDB] (Thread-5 (ActiveMQ-client-global-threads)) Received Message from test queue: This is message 3
16:50:50,888 INFO  [class org.jboss.as.quickstarts.mdb.TestQueueMDB] (Thread-6 (ActiveMQ-client-global-threads)) Received Message from test queue: This is message 4
16:50:50,931 INFO  [class org.jboss.as.quickstarts.mdb.TestQueueMDB] (Thread-5 (ActiveMQ-client-global-threads)) Received Message from test queue: This is message 5
