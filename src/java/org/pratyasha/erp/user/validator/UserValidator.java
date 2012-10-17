/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.user.validator;

import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.apache.poi.hwpf.HWPFDocument;
import org.pratyasha.erp.user.command.Education;
import org.pratyasha.erp.user.command.User;
import org.pratyasha.erp.user.command.WorkExperience;
import org.pratyasha.erp.util.PratyashaUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Registered user validator class
 * @author Debasis Jana
 */
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class c) {
        return c.equals(User.class);
    }

    @Override
    public void validate(Object user, Errors errors) {
        User u = (User)user; //no need to check type
        boolean insert = u.getMode() == 'I';
        String email = u.getEmail();
        //String name = u.getName();
        String password = u.getPassword();
        String c_password = u.getConfirm(); //confirm password
        Date dob = u.getDob();
        String phone = u.getPhone();
        byte[] resume = u.getResume();
        byte[] photo = u.getPhoto();
        byte[] sign = u.getSign();

        if(insert && !email.matches(".+@.+\\..+")) errors.rejectValue("email", "email.invalid", "Invalid email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name", "empty data");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "empty.sex", "empty data");
        if(dob == null) errors.rejectValue("dob", "dob.empty", "Empty date of birth");
        if(password!= null && (!password.equals(c_password) || password.isEmpty() || c_password.isEmpty()
           || password.length() < 10 && password.matches(PratyashaUtil.APP_CONFIGURATION.getProperty("password_validation")) ) ) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
        }
        boolean re = resume.length > 0;
        if(insert && !re) errors.rejectValue("resume", "empty.resume", "resume not uploaded");
        else if(re) {
            //word file format validation
            try {
                HWPFDocument.verifyAndBuildPOIFS(new ByteArrayInputStream(resume));
            } catch(IOException e) {
                errors.rejectValue("resume", "invalid.resume", "expected resume format is word 2003-2007");
            }
        }

        if(phone != null && (phone.isEmpty() || phone.matches(PratyashaUtil.APP_CONFIGURATION.getProperty("phone_validation"))) ) {
            errors.rejectValue("phone", "invalid.phone", "Invalid phone no");
        }

        Iterator<Education> education = u.getEducation().iterator();
        Iterator<WorkExperience> experience = u.getExperience().iterator();
        //education validation
        while(education.hasNext()) {
            Education e = education.next();
            if(!e.getCode().isEmpty()) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "education.place", "empty.education.place", "Incomplete education school/college data");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "education.approval", "empty.education.approval", "Incomplete board/university data");
                int year = e.getYear();
                if(year < 0) errors.rejectValue("education.year", "invalid.education.year", "Invalid passing year");
                double marks = e.getMarks();
                if(marks < 0 || marks > 100) errors.rejectValue("education.marks", "invalid.education.marks", "Invalid marks (%)");
            }
        }
        //experience validation
        while(experience.hasNext()) {
            WorkExperience e = experience.next();
            if(!e.getOrgnization().isEmpty()) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "experience.from", "empty.experience.from", "empty from date");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "experience.to", "empty.experience.to", "empty to date");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "experience.to", "empty.experience.role", "empty role");
            }
        }

        //photo and sign validation
        if(photo != null && photo.length > 0) {
            //photo dimension
            String photo_d[] = PratyashaUtil.APP_CONFIGURATION.getProperty("photo_dimension").split("X");
            int w = Integer.parseInt(photo_d[0]), h = Integer.parseInt(photo_d[1]);
            if(PratyashaUtil.validateJPEGImage(photo, w, h))
                errors.rejectValue("photo", "photo.invalid", "Invalid photo dimension (expected " + w + "X" + h+ ") or invalid photo format (JPEG expected)");
        }
        if(photo != null && sign.length > 0) {
            //sign dimension
            String sign_d[] = PratyashaUtil.APP_CONFIGURATION.getProperty("sign_dimension").split("X");
            int w = Integer.parseInt(sign_d[0]), h = Integer.parseInt(sign_d[1]);
            if(PratyashaUtil.validateJPEGImage(sign, Integer.parseInt(sign_d[0]), Integer.parseInt(sign_d[1])))
                errors.rejectValue("photo", "photo.invalid", "Invalid photo dimension (expected " + w + "X" + h+ ") or invalid photo format (JPEG expected)");
        }
    }

}