package algorithm;

import java.util.*;

/**
 * Design a simple message queue. Provide data structure and API design.
 * The queue supports following methods:
 * - "send(topic) method could send to different topic channels.
 * - "consume(topic, offset)" supports consuming from designated topic with different start offset.
 * One message could be consumed multiple times by different consumers.
 * When queue is full, drops oldest message
 *
 *
 * class Topic {
 *     String topic;
 *     int offset;
 *
 * }
 */

public class MessageQueue {
    private static List<String> topicList = new ArrayList<>();
    private int size;

    private Map<String, List<String>> queueMap = new HashMap<>();

    public MessageQueue(int size) {
        topicList.add("1");
        topicList.add("2");
        topicList.add("3");
        this.size = size;
    }

    public void send(String topic, String message) {
        if (!topicList.contains(topic)) {
            throw new RuntimeException("topic not exists");
        }
        if (queueMap.get(topic) == null) {
            queueMap.put(topic, new LinkedList<>() {

            });
        }
        queueMap.get(topic).add(message);
    }

    public void consume(String topic, int offset) {
        if (!topicList.contains(topic)) {
            throw new RuntimeException("topic not exists");
        }
        if (queueMap.get(topic).size() == 0) {
            throw new RuntimeException("queue is empty");
        }
        if (queueMap.get(topic).get(offset) == null) {
            throw new RuntimeException("no message on the offset");
        }

        for (int i = offset; i < queueMap.get(topic).size(); i++) {
            System.out.println(queueMap.get(topic).get(i));
        }
    }

    public static void main(String[] args) {
        MessageQueue mq = new MessageQueue(2);
        mq.send("1", "msg1");
        mq.send("2", "msg2");
        mq.send("3", "msg3");
        mq.send("1", "msg4");
        mq.send("1", "msg5");

        mq.consume("1", 0);

    }
}
