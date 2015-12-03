package sample.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sample.model.UserAuthority;
import sample.model.UserInfo;
import sample.service.UserAuthorityService;
import sample.service.UserInfoService;

/**
 * DBから認証情報を確認するためのサービス
 *
 * @author f-konashi
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
   @Autowired
   private UserInfoService userInfoService;
   
   @Autowired
   private UserAuthorityService userAuthorityService;

   /**
    * 独自処理に書き換え　DBから認証情報を取得する
    *
    * @param String 検索対象のユーザーID
    * @return UserDetails ユーザーの詳細情報と権限
    * @author f-konashi
    */
   @Override
   public UserDetails loadUserByUsername(String loginId)
           throws UsernameNotFoundException {

       // ログインIDから、ユーザー情報を取得する。
       UserInfo userInfo = userInfoService.getUserByLoginId(loginId);
       //　ユーザー情報を取得できたか確認する。
       if (userInfo == null) {
    	   // 取得できなかった場合は、例外を投げる。
           throw new UsernameNotFoundException("");
       }
       
       // ログインIDから、ユーザー権限を取得する。
       List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
       List<UserAuthority> userAuthrityList = userAuthorityService.getUserAuthorityByUserId(userInfo.getUserId());
       for (UserAuthority userAuthrity: userAuthrityList) {
           authorityList.add(new SimpleGrantedAuthority(userAuthrity.getAuthority()));
       }
       
       return new MyUserDetails(userInfo, authorityList);
   }
}
