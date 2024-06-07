package org.zmz.b.portal.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.b.biz.feign.FeignSrvC;
import org.zmz.common.R;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/srvc")
@Slf4j
public class SrvcController {

    @Resource
    FeignSrvC feignSrvC;

    @Resource
    ObjectMapper objectMapper;

    @GetMapping("/fooT1")
    public R<?> fooT1(HttpServletResponse response) {
        response.addHeader("error", "srvB error");
        try (Response resp = feignSrvC.fooT1();
             Response.Body feignBody = resp.body()) {
            Map<String, Object> bodyMap = objectMapper.readValue(feignBody.asInputStream(), new TypeReference<>() {
            });
            log.info("feignBody bodyMap{}", bodyMap);
            return R.ok(bodyMap);
        } catch (Exception e) {
            log.error("发生异常", e);
        }
        return R.ok();
    }

    @GetMapping("/bar1")
    public R<Map<String, Object>> bar1(HttpServletResponse response) {
        String mvcResponse = response.getHeader("response");
        log.info("springmvc 请求头: {}", mvcResponse);
        try (Response resp = feignSrvC.bar1();
             Response.Body feignBody = resp.body()) {
            Map<String, Object> bodyMap = objectMapper.readValue(feignBody.asInputStream(), new TypeReference<>() {
            });
            log.info("feignBody bodyMap{}", bodyMap);
            Collection<String> feignResponse = resp.headers().get("response");
            if (feignResponse != null && !feignResponse.isEmpty()) {
                String headerBody = feignResponse.iterator().next();
                String decodeJson = URLDecoder.decode(headerBody, Charset.defaultCharset());
                log.info("{}", decodeJson);
                Map<String, Object> map = objectMapper.readValue(decodeJson, new TypeReference<>() {
                });
                return R.ok(map);
            }
        } catch (Exception e) {
            log.error("json处理错误", e);
        }
        return R.ok(null);
    }

    @GetMapping("/bar2")
    public void bar2(HttpServletResponse response) {
        try (Response resp = feignSrvC.bar2();
             PrintWriter writer = response.getWriter()) {
            Collection<String> feignResponse = resp.headers().get("externalFileSystemResponse");
            if (feignResponse != null && !feignResponse.isEmpty()) {
                String headerBody = feignResponse.iterator().next();
                String decodeJson = URLDecoder.decode(headerBody, Charset.defaultCharset());
                log.info("{}", decodeJson);
                Map<String, Object> map = objectMapper.readValue(decodeJson, new TypeReference<>() {
                });
                writer.println(map);
                //writer.flush();
            }
        } catch (Exception e) {
            log.error("json处理错误", e);
        }
    }

}
