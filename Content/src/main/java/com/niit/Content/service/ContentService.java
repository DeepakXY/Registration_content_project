package com.niit.Content.service;

import com.niit.Content.model.CommonUser;
import com.niit.Content.model.Contents;
import com.niit.Content.model.User;

import java.util.List;
//component=> Service

public interface ContentService {

    User registerUser(CommonUser commonUser);

    User addContent(Contents contents, String userEmail);

    User deleteContent(String userEmail, String contantname) throws Exception;

    List<Contents> getAllContents(String userEmail) throws Exception;
}
