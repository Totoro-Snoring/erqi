package com.aaa.cloud.service;

import com.aaa.cloud.properties.FtpProperties;
import com.aaa.cloud.utils.FileNameUtils;
import com.aaa.cloud.utils.FtpUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.aaa.cloud.staticproperties.TimeFormatProperties.*;

/**
 * @Author: zcl
 * @ClassName: UpLoadService
 * @Description: 文件上传api
 * @Date: 2020/7/10 19:15
 **/
@Service
public class UploadService {

@Autowired
private FtpProperties ftpProperties;

    public Boolean upload(MultipartFile multipartFile) {
        //1.获取远程文件名称（为了获取后缀名）
        String oldFileName = ftpProperties.getUsername();
        //2.生成一个随机的新的文件名
        String newFileName = FileNameUtils.getFileName();
        //3. 截取后缀，赋给新newFileName
        newFileName += oldFileName.substring(oldFileName.lastIndexOf("."));
        //4. 获取文件的上传路径（2020/07/10）
        // TODO 暂时没有完成，目前使用的是apache开源基金会的日期工具类，不符合咱们团队的技术水平，需要自己手动编写
        String filePath = DateUtils.formatDate(new Date(), DATE_FORMAT);
        //5. 调用文件上传工具类
        try {
            return FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
