package br.com.marvin.kafkaproducerdemo;

public class KafkaProducerDemoApplication {

	public static void main(String[] args)
	{
		boolean Assyncrono = false;
		TestProducer producerThread = new TestProducer("Test", Assyncrono);

		producerThread.start();
	}

}
