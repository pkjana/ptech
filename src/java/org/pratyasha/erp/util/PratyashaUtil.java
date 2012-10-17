/*
    Copyright (c) 2011 Pratashya Technology,Kharagpur. All Rights Reserved.
    This software is the confidential and proprietary information of Pratashya Technology,Kharagpur.
    You shall not disclose such Confidential Information and shall use it only in
    accordance with the terms of the license agreement you entered into with Pratashya Tech.
 */

package org.pratyasha.erp.util;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.awt.image.BufferedImage;
import java.beans.PropertyEditorSupport;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.io.HWPFFileSystem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.pratyasha.erp.login.LoginDetails;

/**
 * All web utilities related to this application goes here
 * @author Debasis Jana
 * @version 0.1
 */
public class PratyashaUtil {

    private static final int USER_VALIDATION_RANDOM_LIMIT = 32; //validation number size

    //application configuration data
    public static final Properties APP_CONFIGURATION = new Properties();

    /**
     * Creates JSON script for given list of mapped row
     * @param data List of mapped row
     * @return Returns JSON script
     */
    public static String JSONScript(List<Map<String, Object>> data) {
        int s = data.size();
        if(s == 0) return "[]";
        Map<String, Object> first = data.get(0);
        Set<String> c_set = first.keySet();
        StringBuilder JSON = new StringBuilder("[");
        //data iterator
        for(int i = 0; i < s; i++) {
            JSON.append("{");
            Map<String, Object> row = data.get(i);
            Iterator<String> columns = c_set.iterator();
            while(true) {                
                String col = columns.next();
                boolean b = columns.hasNext();
                JSON.append(col + ":" + PratyashaUtil.JSONScript(row.get(col)) + (b ? "," : ""));
                if(!b) break; //if no more column exists
            }
            JSON.append("}" + (i < s -1 ? "," : ""));
        }
        JSON.append("]");
        return JSON.toString();
    }

    /**
     * Gets JSON script for a value according to its type.<br/>
     * Supported types are: String, Number and SQL Date
     * @param value Column value
     * @return Returns JSON script
     */
    private static String JSONScript(Object value) {
        if(value == null) return null;
        if(value instanceof String) return "'" + PratyashaUtil.escapedHTML(value.toString()) + "'";
        else if(value instanceof Number || value instanceof BigDecimal) return value.toString(); //number
        else if(value instanceof Date) { //SQL date
            Calendar c = Calendar.getInstance();
            c.setTime((Date)value);
            return "new Date(" + c.get(Calendar.YEAR) + ", " + c.get(Calendar.MONTH) + ", " + c.get(Calendar.DATE) + ")";
        }
        else return null;
    }

    /**
     * Writes contents into response and commits response
     * @param response Response object
     * @param contents Contents to be written
     */
    public static void writeIntoResponse(HttpServletResponse response, byte[] contents) {
        try{
            ServletOutputStream so = response.getOutputStream();
            so.write(contents);
            so.flush(); //flushes (response commit)
        } catch(IOException e) {
            throw new IllegalStateException("Writing error in response", e);
        }
    }

    /**
     * Writes contents into response for a given MIME type and commits response
     * @param response Response object
     * @param contents Contents to be written
     * @param mime Given MIME type
     */
    public static void writeIntoResponse(HttpServletResponse response, byte[] contents, String mime) {
        response.setContentType(mime);
        PratyashaUtil.writeIntoResponse(response, contents);
    }

    /**
     * Writes contents into response for a given MIME type and commits response
     * @param response Response object
     * @param contents Contents to be written
     * @param mime Given MIME type
     */
    public static void writeIntoResponse(HttpServletResponse response, String contents, String mime) {
        PratyashaUtil.writeIntoResponse(response, contents.getBytes(), mime);
    }

    /**
     * Gets escaped HTML for a given HTML. It only escapes ', " and \n.<br/>
     * For details see <a href="http://www.theukwebdesigncompany.com/articles/entity-escape-characters.php"></a><br/>
     * @param HTML Given HTML
     * @return Escaped HTML
     */
    public static String escapedHTML(String HTML) {
        return HTML.replaceAll("'", "&#39;").replaceAll("\"", "&#34;").replaceAll("\r\n?|\n", "<br/>");
    }

    /**
     * Sends plain text(html/xml/text) mail with an authentication logic
     * @param mailer Mailing details
     * @return Returns true if success otherwise false
     */
    public static boolean mail(Mailer mailer) {
        Properties configuration = mailer.getConfiguration();
        Authenticator auth = mailer.getAuthenticator();
        //opens session object according to authorization logic
        Session session = auth != null ? Session.getDefaultInstance(configuration, auth) : Session.getDefaultInstance(configuration);

        String from = mailer.getUser();
        String[] to = mailer.getTo();

        Message msg = new MimeMessage(session);
        try{
            InternetAddress addressFrom = null; //from address code
            try {
                addressFrom = new InternetAddress(from);
            } catch(AddressException e){return false;} //if from code is malformed then returns false
            msg.setFrom(addressFrom); //sets from code
            msg.setRecipients(Message.RecipientType.TO, PratyashaUtil.recipients(to));

            //Multipart mp = new MimeMultipart(); //MIME mutipart(message+file attachment)
            BodyPart msg_p = new MimeBodyPart(); //for mail body
            msg.setSubject(mailer.getSubject()); //sets title
            msg.setSentDate(new java.util.Date()); //sent date is current date
            //sets text message with message type which supports only text format. no binary message allowed
            msg_p.setContent(mailer.getBody(), mailer.getMime());

            //msg.setContent(mp); //adds total body(message+file attachment)
            Transport.send(msg); //sends mail
        } catch(MessagingException e) { //messaging exception
            if(e instanceof SendFailedException){ //message sent failure because of non-accessible addresses
                //system tries to send mail by one shot. if fails then system discards those invalid addresses and resends mail with valid addresses.
                SendFailedException sfe = (SendFailedException)e; //explicit typecast
                Address[] v_a_s = sfe.getValidUnsentAddresses(); //gets valid unsent addresses
                try{
                    //makes a recursive call. hopefully next time this block will not execute for the call
                    msg.setRecipients(Message.RecipientType.TO, v_a_s); //sets recipients
                    Transport.send(msg); //sends again
                } catch(MessagingException ex) {
                    //next time it will not execute for previous error
                }
            }else return false; //other exception then returns false
        }
        return true;
    }

    /**
     * Gets recipient list from to array list
     * @param to_s To array list
     * @return Returns recipient list
     */
    public static InternetAddress[] recipients(String to_s[]) {
        //list to store valid address then ias gets populated. given addresses may contain malformed address so intially ias cant be allocated with given addresses length
        List<InternetAddress> ia_l = new ArrayList();
        for(String to: to_s) {
            try{
                InternetAddress ia = new InternetAddress(to);
                ia_l.add(ia); //well formed address gets added to list
            }catch(AddressException e){
                //skips malformed address
            }
        }
        return ia_l.toArray(new InternetAddress[]{});
    }

    /**
     * Vaildates email format
     * @param email Email format
     * @param Returns true if success otherwise false
     */
    public static boolean validateEmail(String email) {
        return email.matches(".+@.+\\..+");
    }

    /**
     * Thrown when password encryption problem arises
     */
    public static class PasswordEncryptionException extends RuntimeException {

        public PasswordEncryptionException(String message) {
            super(message);
        }

        public PasswordEncryptionException(String message, Throwable e) {
            super(message, e);
        }
    }

    /**
     * Password encryption logic<br/>
     * For details see <a href="http://www.daniweb.com/software-development/java/threads/70469/password-encryptdecrypt">here</a>
     */
    public static class PasswordUtil {
        private static String algorithm = "DESede"; //digest algorithm
        private static Key key = null;
        private static Cipher cipher = null;

        static {
            try{
                PratyashaUtil.PasswordUtil.key = KeyGenerator.getInstance(algorithm).generateKey();
                PratyashaUtil.PasswordUtil.cipher = Cipher.getInstance(algorithm);
            } catch (Exception e) {
                throw new PratyashaUtil.PasswordEncryptionException(e.getMessage(), e);
            }
        }

        //encryption logic
        public static byte[] encrypt(String input) {
            try{
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] inputBytes = input.getBytes();
                return cipher.doFinal(inputBytes);
            } catch (Exception e) {
                throw new PratyashaUtil.PasswordEncryptionException(e.getMessage(), e);
            }
        }

        //decryption logic
        public static String decrypt(byte[] encryptionBytes) {
            try {
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
                String recovered = new String(recoveredBytes);
                return recovered;
            } catch(Exception e ) {
                throw new PratyashaUtil.PasswordEncryptionException(e.getMessage(), e);
            }
        }
    }

    /**
     * Validates JPEG image according to given with & height
     * @param img_c Image stream
     * @param w Given width
     * @param h Given height
     * @return Returns true if success otherwise false
     */
    public static boolean validateJPEGImage(byte[] img_c, int w, int h) {
        ByteArrayInputStream img_stream = new ByteArrayInputStream(img_c);
        BufferedImage img = null;
        try{
            //validates image as jpeg format
            img = JPEGCodec.createJPEGDecoder(img_stream).decodeAsBufferedImage();
        }catch(ImageFormatException e){
            return false;
        } catch(IOException e) {
            return false;
        }
        //gets uploaded dimension
        int img_h = img.getHeight();
        int img_w = img.getWidth();
        try{
            img_stream.close();
        } catch(IOException e) {}
        return img_h == h && img_w == w;
    }

    /**
     * Simple password encryption decryption logic
     */
    public static class SimplePasswordUtil {
        //encryption
        public static String encrypt(String input) {
            int l = input.length();
            char ep[] = new char[l];
            for(int i = 0; i< l;i ++) {
                char c = input.charAt(i);
                //simple logic
                ep[i] = (char)(c + (char)5);
            }
            return new String(ep);
        }

        //decrypt
        public static String decrypt(String encrypt) {
            int l = encrypt.length();
            char dp[] = new char[l];
            for(int i = 0; i< l;i ++) {
                char c = encrypt.charAt(i);
                //simple logic
                dp[i] = (char)(c - (char)5);
            }
            return new String(dp);
        }
    }

    /**
     * Gets user email validation  number
     * @return Returns random validation number
     */
    public static String userValidationNumber() {
        //char a[] = {65, 26};
        Random random = new Random();
        //return String.valueOf(random.nextInt() * random.nextInt());
        StringBuilder number = new StringBuilder();
        //16 chars random number
        int l = PratyashaUtil.USER_VALIDATION_RANDOM_LIMIT /2;
        for(int i = 0; i < l; i++) {
            int r = random.nextInt(26);
            number.append((char)(65 + r));
        }
        //random character string added with random number
        number.append(random.nextInt(1000) * new java.util.Date().getTime());
        //16 chars random number
        for(int i = 0; i < l; i++) {
            int r = random.nextInt(26);
            number.append((char)(97 + r));
        }
        return number.toString();
    }

    /**
     * string date converter
     */
    public static class DatePropertyEditor extends PropertyEditorSupport {

        private String format = "yyyy/mm/dd"; //default date format

        public DatePropertyEditor() {}

        public DatePropertyEditor(String format){
            this.format = format;
        }

        @Override
        public void setAsText(String date) throws IllegalArgumentException {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if(date.isEmpty()) return; //skips if empty
            try{
                setValue(sdf.parse(date));
            } catch(ParseException e) {}
        }
        
    }

    /**
     * Checks whether current user is ADM or not
     * @param request Request object
     * @return Returns true if success otherwise false
     */
    public static boolean adm(HttpServletRequest request) {
        return adm(request.getSession(true));
    }

    /**
     * Checks whether current user is ADM or not
     * @param session Session object
     * @return Returns true if success otherwise false
     */
    public static boolean adm(HttpSession session) {
        LoginDetails login = (LoginDetails)session.getAttribute("loginDetails");
        if(login == null) return false;
        return PratyashaUtil.adm(login);
    }

    /**
     * Checks whether current user is ADM or not
     * @param login Login details
     * @return Returns true if success otherwise false
     */
    public static boolean adm(LoginDetails login) {
        return login.getUserID().equals(PratyashaUtil.APP_CONFIGURATION.getProperty("ADM"));
    }

    /**
     * Gets login details
     * @param request Request object
     * @return Returns login details
     */
    public static LoginDetails login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return PratyashaUtil.login(session);
    }

    /**
     * Gets login details
     * @param session Sessionobject
     * @return Returns login details
     */
    public static LoginDetails login(HttpSession session) {
        if(session == null) return null;
        return (LoginDetails)session.getAttribute("loginDetails");
    }

    //program tester
    public static void main(String ...a){
        //System.out.println(validateEmail("debasis.jana.iit@gmail.com"));
        /*try{
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update("debasis jana".getBytes());
            byte[] b = md.digest();
            System.out.println(new String(md.digest(b)));
        } catch(NoSuchAlgorithmException e){
            
        }*/

        System.out.println(PratyashaUtil.PasswordUtil.decrypt(PratyashaUtil.PasswordUtil.encrypt("abc12345678")));
        //System.out.println("");
        //System.out.print(PratyashaUtil.PasswordUtil.decrypt("[B@15afd61".getBytes()));
        /*try{
            POIFSFileSystem f = HWPFDocument.verifyAndBuildPOIFS(new FileInputStream("d:/test.jpg"));
        }catch(IOException e) {
            e.printStackTrace(System.out);
        }*/
    }

}