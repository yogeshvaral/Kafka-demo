package guru.learningjournal.kafka.examples;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.util.Scanner;


public class Dispatcher implements  Runnable {
    private  static  final Logger logger = LogManager.getLogger();
    private String fileLocation;
    private String topic;
    private KafkaProducer kafkaProducer;
    public Dispatcher(KafkaProducer kafkaProducer,String topic,String fileLocation){
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
        this.fileLocation =  fileLocation;
    }
    @Override
    public void run() {

        logger.info("Start processing");
        File file = new File(fileLocation);
        int counter = 0;
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                kafkaProducer.send(new ProducerRecord<>(topic,null,line));
                counter++;
            }
            logger.info("Finished sending" + counter + "Messages from "+ fileLocation);
        }catch ( Exception e){
            throw  new RuntimeException(e);
        }
    }
}
