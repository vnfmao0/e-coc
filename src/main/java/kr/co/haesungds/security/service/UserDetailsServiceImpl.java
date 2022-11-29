package kr.co.haesungds.security.service;

import kr.co.haesungds.ecoc.admin.model.RoleUser;
import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${LEGACY_SYSTEM_CODE}")
    private String LEGACY_SYSTEM_CODE;

    private final UserMapper userMapper;

    @Override
    public UserDto loadUserByUsername(String userid) throws UsernameNotFoundException {
        log.debug("########################################");
        log.debug("in loadUserByUsername()");
        log.debug("username : " + userid);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("USERID", userid);
        paramMap.put("LEG_CD", LEGACY_SYSTEM_CODE);
        UserDto userDto = userMapper.loadUserByUsername(paramMap)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found. email=" + userid));

        userDto.setAuthorities(
//                Stream.concat(
//                        getRoles(Optional.ofNullable(userDto.getRoles()).orElse("")).stream(),
//                        getPrivileges(Optional.ofNullable(userDto.getPrivileges()).orElse("")).stream()
//                ).collect(Collectors.toList())
                //getRoles(Optional.ofNullable(userDto.getRoles()).orElse("")).stream().collect(Collectors.toList())
                getRolePrivileges(paramMap)
        );
        log.debug(userDto.toString());
        return userDto;
        //return new User(userDto.getUserid(), userDto.getPassword(), userDto.getAuthorities());
    }

    /*
    * roles(ADMIN,MANAGER,USER)를 ','로 split한 것을 루프돌면서 각각 new SimpleGrantedAuthority(role) 생성한 것을 LIST<SimpleGrantedAuthority>에 add
    */
    private List<SimpleGrantedAuthority> getRoles(String roles) {
        return Pattern.compile(",")
                .splitAsStream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<SimpleGrantedAuthority> getPrivileges(String privileges) {
        return Pattern.compile(",")
                .splitAsStream(privileges)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<GrantedAuthority> getRolePrivileges(Map<String, Object> paramMap) {
        List<RoleUser> roleList = userMapper.selectUserRoleList(paramMap);
        List<RoleUser> privilegesList = userMapper.selectUserPrivilegesList(paramMap);

        List<RoleUser> userRolePrivilegesList = new ArrayList<>();
        userRolePrivilegesList.addAll(roleList);
        userRolePrivilegesList.addAll(privilegesList);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        if(roleList.size() == 0) authorityList.add(new SimpleGrantedAuthority("USER"));
        for (RoleUser roleUser : roleList) {
            authorityList.add(new SimpleGrantedAuthority(roleUser.getRoleId()));
        }
        for (RoleUser roleUser : privilegesList) {
            authorityList.add(new SimpleGrantedAuthority(roleUser.getRoleId()));
        }

        return authorityList;

    }

}
