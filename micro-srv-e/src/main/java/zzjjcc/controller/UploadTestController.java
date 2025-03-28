package zzjjcc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploadTest")
public class UploadTestController {

    @GetMapping("/uploadFile")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        this.uploadByMachine(request, response);
    }

    private void uploadByMachine(HttpServletRequest request, HttpServletResponse response) {

        try {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : list) {
                FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File("D:\\hj-work\\logs\\tmp"));
            }
            response.setStatus(200);
        }
        catch (Exception e) {
            response.setStatus(500);
        }
    }

}