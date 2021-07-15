package ru.itis.aivar.comics.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import ru.itis.aivar.comics.app.utils.images.ImageStorageUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ImageController {

    @Autowired
    private ImageStorageUtil imageStorageUtil;

    @GetMapping(
            value = "/images/**",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    @ResponseBody
    public byte[] getImage(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        return imageStorageUtil.getImageBytes(path.substring("/images/".length()));
    }

}
