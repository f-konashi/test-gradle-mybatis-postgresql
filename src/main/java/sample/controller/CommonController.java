package sample.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;

import sample.security.MyUserDetails;

public abstract class CommonController {
    // *********************************************************************
    // privateメソッド一覧
    // *********************************************************************

    /**
     * ログインユーザーの会員情報を取得します.
     * 
     * @param principal
     * @return 会員情報
     */
    protected MyUserDetails getLoginUserData(Principal principal) {
        Authentication auth = (Authentication) principal;
        return (MyUserDetails) auth.getPrincipal();
    }
}
