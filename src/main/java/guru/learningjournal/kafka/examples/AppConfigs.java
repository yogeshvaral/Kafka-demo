package guru.learningjournal.kafka.examples;

class AppConfigs {
    final static String applicationID = "StorageDemo";
    final static String bootstrapServers = "localhost:9092";
    final static String topicName = "invoice";
    final static int numEvents = 500000;
    final static int fileEvents = 2;
    final static String[]  fileLocations = {"C:\\Yogesh\\Hadoop\\Kafka\\Kafka_PrashantPandey\\01-storage-demo\\01-storage-demo\\data\\NSE05NOV2018BHAV.csv","C:\\Yogesh\\Hadoop\\Kafka\\Kafka_PrashantPandey\\01-storage-demo\\01-storage-demo\\data\\NSE06NOV2018BHAV.csv"};
}
