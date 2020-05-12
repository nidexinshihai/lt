package com.lt.xzh.controller;

import com.lt.xzh.dto.AccessTokenDTO;
import com.lt.xzh.dto.GithubUser;
import com.lt.xzh.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("c486bb109be952c1df8b");
        accessTokenDTO.setClient_secret("29ddff6f7fc1a09abe127113a1057ee4a4af20af");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url("http://localhost/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }


}
