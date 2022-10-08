//package com.valkcastellani.site.rest.image.service;
//
//import com.valkcastellani.site.rest.image.exception.ImageStorageException;
//import com.valkcastellani.site.rest.image.exception.MyImageNotFoundException;
//import com.valkcastellani.site.rest.image.property.ImageStorageProperties;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.UUID;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.name.Rename;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * @author Valk Castellani
// * @version 1.0
// * @date 2021-05-28
// */
//@Service
//public class ImageStorageService {
//
//    private final Path imageStorageLocation;
//
//    @Autowired
//    public ImageStorageService(ImageStorageProperties imageStorageProperties) {
//        this.imageStorageLocation = Paths.get(imageStorageProperties.getUploadDir())
//                .toAbsolutePath().normalize();
//
//        try {
//            Files.createDirectories(this.imageStorageLocation);
//        } catch (Exception ex) {
//            throw new ImageStorageException("Could not create the directory where the uploaded images will be stored.", ex);
//        }
//    }
//
//    public String storeImage(MultipartFile image) {
//        // Normalize image name
//        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
//
//        try {
//            // Check if the image's name contains invalid characters
//            if (imageName.contains("..")) {
//                throw new ImageStorageException("Sorry! Imagename contains invalid path sequence " + imageName);
//            }
//
//            // Copy image to the target location (Replacing existing image with the same name)
//            imageName = UUID.randomUUID().toString().trim() + "_" + imageName.trim();
//            Path targetLocation = this.imageStorageLocation.resolve(imageName);
//            Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            //Gerando Thumbnail 
//            Thumbnails.of(this.imageStorageLocation.resolve(imageName).toString()).size(40, 40).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
//
//            return imageName;
//        } catch (IOException ex) {
//            throw new ImageStorageException("Could not store image " + imageName + ". Please try again!", ex);
//        }
//    }
//
//    public Resource loadImageAsResource(String imageName) {
//        try {
//            Path imagePath = this.imageStorageLocation.resolve(imageName).normalize();
//            Resource resource = new UrlResource(imagePath.toUri());
//            if (resource.exists()) {
//                return resource;
//            } else {
//                throw new MyImageNotFoundException("Image not found " + imageName);
//            }
//        } catch (MalformedURLException ex) {
//            throw new MyImageNotFoundException("Image not found " + imageName, ex);
//        }
//    }
//}
