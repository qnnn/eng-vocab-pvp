package com.eng.server.service;

import com.eng.server.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = queryStudent(username);

        if (student!=null){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            GrantedAuthority grantedAuthority1 = new SimpleGrantedAuthority("ADMIN");
            grantedAuthorities.add(grantedAuthority);
            grantedAuthorities.add(grantedAuthority1);
            return new User(student.getSno().toString(),student.getPassword(),grantedAuthorities);
        }



        return null;
    }

    private Student queryStudent(String username){
        return jdbcTemplate.queryForObject("select sno,s_name,password from t_student where sno = ?", new Object[]{username}, (rs, rowNum) -> {
            Student student = new Student();
            student.setSno(rs.getInt("sno"));
            student.setSName(rs.getString("s_name"));
            student.setPassword(rs.getString("password"));
            return student;
        });
    }
}
