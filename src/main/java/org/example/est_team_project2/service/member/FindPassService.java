package org.example.est_team_project2.service.member;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.member.Member;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FindPassService {

    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String sender = "rudcjs40@gmail.com";

    // 임시 비번 생성
    public static String genTempPassword() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder nickname = new StringBuilder(8);
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            nickname.append(characters.charAt(index));
        }

        return nickname.toString();
    }

    // 맴버 찾아서 임시 비번 업데이트
    public String createTempPassword(String email) {
        String tempPassword = genTempPassword();
        Optional<Member> findMember = memberRepository.findByEmail(email);
        Member member = findMember.orElseThrow(NoSuchElementException::new);
        member.changePassword(bCryptPasswordEncoder.encode(tempPassword));
        memberRepository.save(member);
        return tempPassword;
    }

    // 임시 비번 발신
    public void sendTempPassword(String email, String tempPassword) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(sender);
            helper.setTo(email);
            helper.setSubject("멍PEDIA 임시 비밀번호 발급");
            String body = "임시 비번 : " + tempPassword;
            helper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("여기서 오류남ㅜ");
        }
    }



}





