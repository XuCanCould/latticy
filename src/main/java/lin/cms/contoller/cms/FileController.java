package lin.cms.contoller.cms;

import io.github.talelin.core.annotation.LoginRequired;
import lin.cms.bo.FileBO;
import lin.cms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * created by Xu on 2024/3/28 23:31.
 */
@RestController
@RequestMapping("/cms/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping
    @LoginRequired
    public List<FileBO> upload(MultipartHttpServletRequest request) {
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        return fileService.upload(multiFileMap);
    }
}
