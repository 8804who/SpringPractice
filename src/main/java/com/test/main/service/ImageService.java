package com.test.main.service;

import com.test.main.dto.AuthorityDto;
import com.test.main.dto.ImageDto;
import com.test.main.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    public String imageUpload(ImageDto imageDto){
        if (!imageDto.getImage().isEmpty()) {
            String uploadPath = "C:\\Users\\user\\IdeaProjects\\board\\src\\main\\webapp\\resources\\img\\profile";
            UUID uuid = UUID.randomUUID();
            String imageFileName = uuid + "_" + imageDto.getImage().getOriginalFilename();
            File saveFile = new File(uploadPath, imageFileName);

            try {
                imageDto.getImage().transferTo(saveFile);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            return imageFileName;
        }
        return null;
    }

    public void reset()
    {
        File folder = new File("C:\\Users\\user\\IdeaProjects\\board\\src\\main\\webapp\\resources\\img\\profile");
        File[] files = folder.listFiles();

        for(File file: files) {
            file.delete();
        }
    }
}
