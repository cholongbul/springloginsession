package org.jmoh.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionConfig implements HttpSessionListener {

    private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

    //중복로그인 지우기
    public synchronized static String getSessionidCheck(String type, String compareId){
        String result = "";
        for( String key : sessions.keySet() ){
            HttpSession hs = sessions.get(key);
            if
            (hs != null &&  hs.getAttribute(type) != null //세션 type null인지 확인
                    && hs.getAttribute(type).toString().equals(compareId) //새로 로그인한 id와 세션에 등록된 id가 같은지 확인
            ){
                result =  key.toString(); //같으면 result 값에 세션 key를 대입
            }
        }
        removeSessionForDoubleLogin(result); //세션 key를 토대로 만료
        return result;
    }

    private static void removeSessionForDoubleLogin(String userId){
        System.out.println("remove userId : " + userId);
        if(userId != null && userId.length() > 0){
            sessions.get(userId).invalidate();
            sessions.remove(userId);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se);
        sessions.put(se.getSession().getId(), se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if(sessions.get(se.getSession().getId()) != null){
            sessions.get(se.getSession().getId()).invalidate();
            sessions.remove(se.getSession().getId());
        }
    }
}