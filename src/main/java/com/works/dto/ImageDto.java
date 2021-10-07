package com.works.dto;

import com.works.config.Config;
import com.works.entities.Diary;
import com.works.entities.Image;
import com.works.repositories.ImageRepository;
import com.works.utils.ERest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageDto {

    final ImageRepository iRepo;

    private final String UPLOAD_DIR =  "src/main/resources/static/uploads/";
    long maxFileUploadSize = 2048;

    public ImageDto(ImageRepository iRepo) {
        this.iRepo = iRepo;
    }

    // imageList - start
    public Map<ERest,Object> imageList(String pageNumber){
        Map<ERest,Object> hm = new LinkedHashMap<>();
        try {
            int ipageNumber = Integer.parseInt(pageNumber);
            Pageable pageable = PageRequest.of(ipageNumber, Config.pageSize);
            List<Image> pageList = iRepo.findByOrderByIidAsc(pageable);
            Long totalcount = iRepo.count();
            hm.put(ERest.status,true);
            hm.put(ERest.message, "Image Listeleme işlemi başarılı");
            hm.put(ERest.totalSize,totalcount);
            hm.put(ERest.result, pageList);
            hm.put(ERest.pageStatus, (Config.pageSize * ipageNumber) + " - " + Config.pageSize);
        }catch (Exception ex){
            hm.put(ERest.status,false);
            hm.put(ERest.message,"Image Listeleme işlemi sırasında hata oluştu!");
        }
        return hm;
    }
    // imageList - end

    // imageUpload - start
    public Map<ERest,Object> imageUpload(MultipartFile file){
        Image iresult = null ;
        int sendSuccessCount = 0;
        String errorMessage = "";
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!file.isEmpty() ) {
            long fileSizeMB = file.getSize() / 1024;
            if ( fileSizeMB > maxFileUploadSize ) {
                System.err.println("Dosya boyutu çok büyük Max 2MB");
                errorMessage = "Dosya boyutu çok büyük Max "+ (maxFileUploadSize / 1024) +"MB olmalıdır";
            }else {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String ext = fileName.substring(fileName.length()-5, fileName.length());
                String uui = UUID.randomUUID().toString();
                fileName = uui + ext;
                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    sendSuccessCount += 1;

                    Image image = new Image();
                    image.setImagename(fileName);
                    iresult = iRepo.save(image);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Yükleme Başarılı");
            hm.put(ERest.result,iresult );
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, errorMessage);
        }
        return hm;
    }
    // imageUpload - end

    // imageUpload - start
    public Map<ERest,Object> imageUpdate(MultipartFile file, String iid){
        int imageid = Integer.parseInt(iid);
        Image iresult = null ;
        int sendSuccessCount = 0;
        String errorMessage = "";
        Map<ERest, Object> hm = new LinkedHashMap<>();
        if (!file.isEmpty() ) {
            long fileSizeMB = file.getSize() / 1024;
            if ( fileSizeMB > maxFileUploadSize ) {
                System.err.println("Dosya boyutu çok büyük Max 2MB");
                errorMessage = "Dosya boyutu çok büyük Max "+ (maxFileUploadSize / 1024) +"MB olmalıdır";
            }else {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String ext = fileName.substring(fileName.length()-5, fileName.length());
                String uui = UUID.randomUUID().toString();
                fileName = uui + ext;
                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    sendSuccessCount += 1;


                    Image image = new Image();
                    image.setImagename(fileName);
                    image.setIid(imageid);
                    iresult = iRepo.saveAndFlush(image);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Güncelleme Başarılı");
            hm.put(ERest.result,iresult );
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, errorMessage);
        }
        return hm;
    }
    // imageUpload - end

    // deleteImage - start
    public Map<ERest, Object> deleteImage( String stIid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {

            int iid = Integer.parseInt(stIid);
            Image oldImgName = iRepo.findById(iid).get();
            iRepo.deleteById(iid);
            File fileimage = new File(UPLOAD_DIR + oldImgName.getImagename());
            if (fileimage.exists()) {
                fileimage.delete();
            }
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Image Silme başarılı");
            hm.put(ERest.result, stIid);
        }catch (Exception ex){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Image Silme işlemi sırasında hata oluştu!");
            hm.put(ERest.result, stIid);
        }
        return hm;
    }
    // deleteImage - end
}
