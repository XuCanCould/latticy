package lin.cms.extension.file;

import lin.cms.module.file.Uploader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * created by Xu on 2024/3/30 15:36.
 */
@Configuration(proxyBeanMethods = false)
public class UploaderConfiguration {

    @Bean
    @Order
    @ConditionalOnMissingBean
    public Uploader uploader() {
        return new LocalUploader();
//        除此之外需要将配置文件的 domain 修改成云的域名
//        return new QiniuUploader();
    }

}
