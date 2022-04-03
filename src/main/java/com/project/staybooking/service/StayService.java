package com.project.staybooking.service;

import com.project.staybooking.exception.StayNotExistException;
import com.project.staybooking.model.Stay;
import com.project.staybooking.model.StayImage;
import com.project.staybooking.model.User;
import com.project.staybooking.repository.StayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StayService {
    private StayRepository stayRepository;
    private ImageStorageService imageStorageService;

    @Autowired
    public StayService(StayRepository stayRepository, ImageStorageService imageStorageService) {
        this.stayRepository = stayRepository;
        this.imageStorageService = imageStorageService;
    }

    public List<Stay> listByUser(String username) {
        return stayRepository.findByHost(new User.Builder().setUsername(username).build());
    }

    public Stay findByIdAndHost(Long stayId, String username) throws StayNotExistException {
        Stay stay = stayRepository.findByIdAndHost(stayId, new User.Builder().setUsername(username).build());
        if (stay == null) {
            throw  new StayNotExistException("Stay doesn't exist");
        }
        return stay;
    }

    public void add(Stay stay, MultipartFile[] images) {
        List<StayImage> stayImages = new ArrayList<>();
        // for each images. imageStorageService. save(image)
        // collect url of image
        // set url to stay object
//        for (MultipartFile image: images) {
//            String url = imageStorageService.save(image);
//            StayImage stayImage = new StayImage(url, stay);
//            stayImages.add(stayImage);
//        }

        List<String> urls = Arrays.stream(images)
                .parallel()
                .map(image -> imageStorageService.save(image))
                .collect(Collectors.toList());
        for (String url: urls) {
            stayImages.add(new StayImage(url, stay));
        }
        stay.setImages(stayImages);
        stayRepository.save(stay);

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long stayId, String username)  throws StayNotExistException {
        Stay stay = findByIdAndHost(stayId, username);

        stayRepository.deleteById(stayId);
    }
 }
