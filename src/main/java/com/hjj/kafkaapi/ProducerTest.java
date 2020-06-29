package com.hjj.kafkaapi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author sober 2020-06-29-13:36
 */
public class ProducerTest {
    public static void main(String[] args) {
        //第一步：构造一个Properties实例
        Properties props = new Properties();
        //这三个参数必须指定，其他参数可根据需要进行指定，不指定即为默认值
        props.put("bootstrap.servers", "master:9092,slave1:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //第二步；使用上面的Properties实例构造KafkaProducer实例
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        /*
        第三步：构造待发送的消息对象即ProducerRecord实例，指定消息要被发送的topic、分区以及对应的
               key和value。注意，分区和key可以不用指定，由kafka自行确定目标分区。通过KafkaProducer实例
               的send方法发送消息
      */

        for (int i = 100; i < 200; i++) {
            producer.send(new ProducerRecord<String, String>("first", Integer.toString(i), Integer.toString(i)));
        }

        //第四步：释放KafkaProducer实例
        producer.close();
    }
}
