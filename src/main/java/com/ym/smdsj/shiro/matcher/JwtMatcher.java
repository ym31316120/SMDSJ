package com.ym.smdsj.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * @author ym
 * @date 2019/3/12
 **/
@Component
public class JwtMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        String jwt = (String) authenticationInfo.getCredentials();
        System.out.println("jwt="+jwt);
//        JwtAccount jwtAccount = null;
//        try{
//            jwtAccount = JsonWebTokenUtil.parseJwt(jwt,JsonWebTokenUtil.SECRET_KEY);
//        } catch(SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e){
//            throw new AuthenticationException("errJwt"); // 令牌错误
//        } catch(ExpiredJwtException e){
//
//            throw new AuthenticationException("expiredJwt"); // 令牌过期
//        } catch(Exception e){
//            throw new AuthenticationException("errJwt");
//        }
//        if(null == jwtAccount){
//            throw new AuthenticationException("errJwt");
//        }

        return true;
    }
}
