package com.gabriel.music.redesocial.service;

import com.gabriel.music.redesocial.domain.user.ImageUser;
import com.gabriel.music.redesocial.domain.user.User;
import com.gabriel.music.redesocial.repository.ImageUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageUserService {

    @Value("${images-user-path}")
    private String pathImages;

    @Autowired
    private ImageUserRepository imageUserRepository;

    public List<ImageUser> findAll() {
        return imageUserRepository.findAll();
    }

    @Transactional
    public void saveAndWriteImageProfile(MultipartFile file, User user) throws IOException {
        if (!file.isEmpty()) {
            if (user.getImageProfile() != null) {
                this.deleteCurrentUserImage(user, file);
            } else {
                this.writeAndSaveFile(file, user);
            }
        }
    }

    @Transactional
    public void saveAndWriteBackgroundProfile(MultipartFile file, User user) throws IOException {
        try {
            if (!file.isEmpty()) {
                log.info(file.getContentType());
                byte[] bytes = file.getBytes();
                Path path = Paths.get(pathImages + "\\" + user.getUsername() + file.getOriginalFilename());
                Files.write(path, bytes);
            }

            ImageUser imageUser = new ImageUser(user.getUsername() + file.getOriginalFilename(), null, user, null);
            imageUserRepository.save(imageUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteImageReferenceFromDatabase(User user) {
        ImageUser imageUser = imageUserRepository.findByImageReference(user.getImageProfile().getImageReference());
        imageUserRepository.deleteById(imageUser.getId());
    }

    private void writeAndSaveFile(MultipartFile file, User user) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(pathImages + "\\" + user.getUsername() + file.getOriginalFilename());
        Files.write(path, bytes);
        ImageUser imageUser = new ImageUser(user.getUsername() + file.getOriginalFilename(), user, null, null);
        imageUserRepository.save(imageUser);
    }

    private void deleteCurrentUserImage(User user, MultipartFile file) throws IOException {
        deleteImageReferenceFromDatabase(user);
        if (imageUserRepository.findAll().isEmpty()) {
            Boolean verify = deleteFileFromDirectory(user, file);
            if (verify) writeAndSaveFile(file, user);
        }
    }

    private Boolean deleteFileFromDirectory(User user, MultipartFile file) throws IOException {
        Path path = Paths.get(pathImages + "/" + user.getImageProfile().getImageReference());
        Files.delete(path);
        return checkIfTheFileExists(user);
    }

    private Boolean checkIfTheFileExists(User user) {
        File file = new File(pathImages, user.getImageProfile().getImageReference());
        return file.exists() ? false : true;
    }

    public void deleteById(Long id) {
        imageUserRepository.deleteById(id);
    }
}
