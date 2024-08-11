package org.zmz.d.test.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Slf4j
public class ConcurrentMapTest {

    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    String path = "D:\\worksp\\ToolBox\\IdeaProjects\\sb2\\micro-srv-d\\src\\test\\resources\\tmp\\";

    @Test
    public void t1() {
        int len = ALPHA.length();
        log.info("len={}", len);

        int count = 200;
        List<String> list = new ArrayList<>(count * len);

        for (int i = 0; i < len; i++) {
            char ch = ALPHA.charAt(i);
            for (int j = 0; j < count; j++) {
                list.add(String.valueOf(ch));
            }
        }

        // 打乱
        Collections.shuffle(list);

        for (int i = 0; i < len; i++) {
            String filePath = path + (i + 1) + ".txt";
            try (
                    PrintWriter writer = new PrintWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(filePath)
                            )
                    )
            ) {
                String content = String.join("\n", list.subList(i * count, (i + 1) * count));
                writer.print(content);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void t2() {
        wordCounts(() -> new ConcurrentHashMap<String, LongAdder>(), (countMap, words) -> {
            for (String word : words) {
                LongAdder value = countMap.computeIfAbsent(word, k -> new LongAdder());
                value.increment();
                countMap.put(word, value);
            }
        });
    }


    private <V> void wordCounts(Supplier<Map<String, V>> supplier, BiConsumer<Map<String, V>, List<String>> consumer) {
        Map<String, V> countMap = supplier.get();

        List<Thread> ts = new ArrayList<>(26);

        for (int i = 0; i < 26; i++) {
            int idx = i;
            Thread thread = new Thread(() -> {
                // 读取文件
                List<String> words = readFile(idx);
                consumer.accept(countMap, words);
            });

            ts.add(thread);
        }

        // 启动线程
        for (Thread t : ts) {
            t.start();
        }

        // 等待所有线程调用结束
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("{}", countMap);
    }

    private List<String> readFile(int idx) {
        String filePath = path + (idx + 1) + ".txt";
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
