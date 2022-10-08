//package com.valkcastellani.site.rest.image;
//
//import com.valkcastellani.site.rest.image.service.ImageStorageService;
//import com.valkcastellani.site.rest.payload.UploadFileResponseDTO;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//import javax.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
///**
// * @author Valk Castellani
// * @version 1.0
// * @date 2019-11-21
// */
//@RestController("/image")
//public class ImageController {
//
//    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
//
//    @Autowired
//    private ImageStorageService imageStorageService;
//
//    @PostMapping("/upload")
//    public UploadFileResponseDTO uploadImage(@RequestParam("image") MultipartFile image) {
//        String imageName = imageStorageService.storeImage(image);
//
//        String imageDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download/")
//                .path(imageName)
//                .toUriString();
//
//        return new UploadFileResponseDTO(imageName, imageDownloadUri, image.getContentType(), image.getSize());
//    }
//
//    @PostMapping("/uploadMultipleImages")
//    public List<UploadFileResponseDTO> uploadMultipleImages(@RequestParam("images") MultipartFile[] images) {
//        return Arrays.asList(images)
//                .stream()
//                .map(image -> uploadImage(image))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/download/{imageName:.+}")
//    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
//        // Load image as Resource
//        Resource resource = imageStorageService.loadImageAsResource(imageName);
//
//        // Try to determine image's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine image type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//}
