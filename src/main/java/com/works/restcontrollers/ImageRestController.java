package com.works.restcontrollers;

import com.works.dto.ImageDto;
import com.works.utils.ERest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageRestController {

    final ImageDto iDto;
    public ImageRestController(ImageDto iDto) {
        this.iDto = iDto;
    }

    @GetMapping("/list/{pageNo}")
    public Map<ERest, Object> list(@PathVariable String pageNo){
        return  iDto.imageList(pageNo);
    }

    @PostMapping("/upload")
    public Map<ERest, Object> upload(@RequestParam("fileName") MultipartFile file) {
        return iDto.imageUpload(file);
    }

    @PutMapping("/update/{iid}")
    public Map<ERest, Object> update(@RequestParam("fileName") MultipartFile file ,@PathVariable String iid) {
        return iDto.imageUpdate(file,iid);
    }

    @DeleteMapping("/delete/{iid}")
    public Map<ERest, Object> delete( @PathVariable String iid){
        return iDto.deleteImage(iid);
    }
}
