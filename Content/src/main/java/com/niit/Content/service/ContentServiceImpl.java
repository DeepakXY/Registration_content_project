package com.niit.Content.service;

import com.niit.Content.model.CommonUser;
import com.niit.Content.model.Contents;
import com.niit.Content.model.User;
import com.niit.Content.model.UserDTO;
import com.niit.Content.proxy.Proxi;
import com.niit.Content.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    private Proxi proxi;

    @Override
    public User registerUser(CommonUser commonUser) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUseremail(commonUser.getUseremail());
        userDTO.setUsername(commonUser.getUsername());
        userDTO.setPasswd(commonUser.getPasswd());
        ResponseEntity<?> response = proxi.sendUserObjectToAuthApp(userDTO);
        System.out.println(response);

        User user = new User(commonUser.getUseremail(), commonUser.getUsername(), commonUser.getPasswd(), new ArrayList<>());
        return contentRepository.insert(user);
    }

    @Override
    public User addContent(Contents contents, String userEmail) {
        User user = contentRepository.findById(userEmail).get();


        if (user.getContents() == null) {
            user.setContents(Arrays.asList(contents));
        } else {

            List<Contents> contentsList = user.getContents();
            contentsList.add(contents);
            user.setContents(contentsList);
        }
        return contentRepository.save(user);
    }

    @Override
    public User deleteContent(String userEmail, String contentname) throws Exception {
        boolean contectIdExists = false;

        if (contentRepository.findById(userEmail).isEmpty()) {
            throw new Exception("User Not Available");
        }

        User user = contentRepository.findById(userEmail).get();

        List<Contents> contentsList = user.getContents();

        contectIdExists = contentsList.removeIf(i -> i.getContentname().equals(contentname));

        if (!contectIdExists) {
            throw new Exception("contents not available");
        }
        user.setContents(contentsList);
        return contentRepository.save(user);
    }

    @Override
    public List<Contents> getAllContents(String userEmail) throws Exception {
        return contentRepository.findById(userEmail).get().getContents();
    }
}
