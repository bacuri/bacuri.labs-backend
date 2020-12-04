package com.bacurilab.backend.controller;

import com.bacurilab.backend.model.*;
import com.bacurilab.backend.model.pk.DependentProfileVaccinePk;
import com.bacurilab.backend.repository.*;
import com.bacurilab.backend.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;


@RestController
@RequestMapping("/test")
public class MeuTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DependentProfileRepository dependentProfileRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ContextService contextService;

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    DependentProfileVaccineRepository dependentProfileVaccineRepository;

//    @Autowired
//    EmailService emailService;

    @GetMapping("/vaccine")
    String vaccine(@RequestParam("vaccineId") Long vaccineId, @RequestParam("profileId") Long profileId ) {
        Vaccine v = new Vaccine();
        v.setId(vaccineId);

        DependentProfile d = new DependentProfile();
        d.setId(profileId);

        DependentProfileVaccine dv = new DependentProfileVaccine(new DependentProfileVaccinePk(d, v));
        dependentProfileVaccineRepository.getTimeline(profileId);
//        dependentProfileVaccineRepository.save(dv);

        return "ok";
    }

    @GetMapping("/teste")
    String test() {
        Vaccine v = new Vaccine();
        v.setName("2222222");
        v.setDosage(Dosage.DOSAGE_UNIQUE);
        v.setInitialRange(0);
        v.setFinalRange(0);
        v.setPreventedDiseases("Difteria e Tétano");
        v.setNextVaccine(vaccineRepository.findAll().get(0));
        vaccineRepository.save(v);
        return "ok";
    }

    @GetMapping("/tester")
    String minhaString() {
        User u = userRepository.findAll().get(0);
        DependentProfile dp = new DependentProfile();
        dp.setDateOfBirth(LocalDateTime.now());
        dp.setGender(Gender.MALE);
        dp.setLastName("teste");
        dp.setFirstName("setFirstName");
//        associatedDependencyRepository.save(dp);
//
        u.getDependentProfiles().add(dp);
//        userRepository.save(u);
        //emailService.sendSimpleMessage("jherson_k-f@hotmail.com", "teste01", "teste02");
        return "ok";
    }

    @GetMapping("/email")
    String sendmail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("harysonjherson@gmail.com", "24081998Jh");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("harysonjherson@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tutorialspoint@gmail.com"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);

        return "email";
    }
}
