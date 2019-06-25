package br.com.marvin.kafkaproducerdemo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class TestProducer extends Thread{
    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final Boolean Assyncrono;
    public static final String URL = "localhost";
    public static final int PORTA = 9092;
    public static final String CLIENT_ID = "TestProducer";

    public TestProducer(String topic, Boolean Assycrono) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", URL + ":" + PORTA);
        properties.put("client.id", CLIENT_ID);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
        this.topic = topic;
        this.Assyncrono = Assycrono;
    }
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = "Mensagem " + messageNo;
            long startTime = System.currentTimeMillis();
            if (Assyncrono) {
                producer.send(new ProducerRecord<>(topic,
                        messageNo,
                        messageStr), new DemoCallBack(startTime, messageNo, messageStr));
            } else {
                try {
                    producer.send(new ProducerRecord<>(topic,
                            messageNo,
                            messageStr)).get();
                    System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            ++messageNo;
        }
    }
}
