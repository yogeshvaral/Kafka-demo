package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class DispatcherDemo {
    private  static  final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        KafkaProducer kafkaProducer = new KafkaProducer<Integer,String>(props);
        Thread[] dispatchers = new Thread[AppConfigs.fileEvents];
        for(int i=0;i< AppConfigs.fileEvents;i++){
            dispatchers[i] = new Thread(new Dispatcher(kafkaProducer,AppConfigs.topicName,AppConfigs.fileLocations[i]));
            dispatchers[i].start();
        }
        try{
            for(Thread i: dispatchers)
                i.join();

        }catch (InterruptedException interruptedException){
            logger.error("Main thread intruppted");
        }
        finally {
            kafkaProducer.close();
        }
    }
}
