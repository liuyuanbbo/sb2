package org.zmz.b.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSApiTest {

    static class BizException extends RuntimeException {
        public BizException(String message) {
            super(message);
        }

        public BizException(Throwable cause) {
            super(cause);
        }

        public BizException() {
        }
    }

    @Test
    public void testMkDir() throws URISyntaxException {
        // 连接集群地址
        URI uri = new URI("hdfs://hadoop101:9020");
        Configuration cfg = new Configuration();
        String user = "root";
        try (FileSystem fs = FileSystem.get(uri, cfg, user)) {
            fs.mkdirs(new Path("/xiyou/sunwukong"));
        } catch (IOException | InterruptedException e) {
            throw new BizException(e);
        }
    }
}
