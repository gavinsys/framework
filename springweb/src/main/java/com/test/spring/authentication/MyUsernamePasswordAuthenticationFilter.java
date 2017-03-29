package com.test.spring.authentication;

import java.sql.Timestamp;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.test.spring.dao.UserAttemptsDao;
import com.test.spring.dao.UserDao;
import com.test.spring.entity.User;
import com.test.spring.entity.UserAttempts;


public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{  
	@Autowired  
	private PasswordEncoder passwordEncoder;  
	@Autowired  
	private SaltSource saltSource; 
	@Autowired  
	private UserDetailsService userDetailsService;
	@Autowired
	private MessageSource messageSource;
    private UserDao userDao;
	private UserAttemptsDao userAttemptsDao;
	
	private boolean postOnly = true;  
    private boolean allowEmptyValidateCode = false;  
    private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;  
    private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;  
    public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";  
    public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "rand";  
    public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "code";
    
    @Override  
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	Locale locale = new CookieLocaleResolver().resolveLocale(request);
        if (postOnly && !request.getMethod().equals("POST")) {  
            throw new AuthenticationServiceException(messageSource.getMessage("auth.method", new Object[]{request.getMethod()}, locale));  
        }  
        String username = obtainUsername(request);  
        String password = obtainPassword(request);
        if (StringUtils.isEmpty(username)) {  
        	throw new AuthenticationServiceException(messageSource.getMessage("login.username.empty", null, locale));
        }  
        if (StringUtils.isEmpty(password)) {  
        	throw new AuthenticationServiceException(messageSource.getMessage("login.password.empty", null, locale));
        }
        if (!isAllowEmptyValidateCode())  
            checkValidateCode(request);
        username = username.trim();  
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if(userDetails == null){
			throw new AuthenticationServiceException(messageSource.getMessage("login.username.error", null, locale));
		}
		if(!userDetails.isAccountNonLocked()){
			throw new AuthenticationServiceException(messageSource.getMessage("login.islock", null, locale));
		}
		String encodedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(userDetails));
		if(!encodedPassword.equals(userDetails.getPassword())){
			int count = 3;
			UserAttempts userAttempts = userAttemptsDao.findUserAttemptsByUserName(username);
			if(userAttempts == null){
				userAttempts = new UserAttempts();
				userAttempts.setAttempts(0);
				userAttempts.setUsername(username);
				userAttempts.setLastModified(new Timestamp(System.currentTimeMillis()));
				userAttemptsDao.insert(userAttempts);
				throw new AuthenticationServiceException(messageSource.getMessage("login.password.error", null, locale));
			}
			if(userAttempts.getAttempts()<count){
				userAttempts.setAttempts(userAttempts.getAttempts()+1);
				userAttempts.setUsername(username);
				userAttempts.setLastModified(new Timestamp(System.currentTimeMillis()));
				userAttemptsDao.update(userAttempts);
				if(userAttempts.getAttempts()==count){
					User user = userDao.findUserByUserName(username);
					user.setAccountNonLocked(false);
					userDao.update(user);
					throw new AuthenticationServiceException(messageSource.getMessage("login.locked", null, locale));
				}else{
					throw new AuthenticationServiceException(messageSource.getMessage("login.lock", new Object[]{count-userAttempts.getAttempts()}, locale));
				}
			}
			
		}
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);  
        
        HttpSession session = request.getSession(false);  
  
        if (session != null || getAllowSessionCreation()) {  
            request.getSession().setAttribute(  
                    SPRING_SECURITY_LAST_USERNAME_KEY,  
                    TextEscapeUtils.escapeEntities(username));  
        }  
        
        setDetails(request, authRequest);  
        
        UserAttempts userAttempts = userAttemptsDao.findUserAttemptsByUserName(username);
        if(userAttempts != null){
        	userAttemptsDao.delete(userAttempts);
        }
        return this.getAuthenticationManager().authenticate(authRequest);  
    }  
      
    protected void checkValidateCode(HttpServletRequest request) {  
    	Locale locale = new CookieLocaleResolver().resolveLocale(request);
        String sessionValidateCode = obtainSessionValidateCode(request);  
        String validateCodeParameter = obtainValidateCodeParameter(request);  
        if (StringUtils.isEmpty(validateCodeParameter)  
                || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {  
        	throw new AuthenticationServiceException(messageSource.getMessage("validateCode.error", null, locale));
        }  
    }   
    private String obtainValidateCodeParameter(HttpServletRequest request) {  
        return request.getParameter(validateCodeParameter);  
    }  
  
    protected String obtainSessionValidateCode(HttpServletRequest request) {  
        Object obj = request.getSession()  
                .getAttribute(sessionvalidateCodeField);  
        return null == obj ? "" : obj.toString();  
    }  
	public boolean isAllowEmptyValidateCode() {
		return allowEmptyValidateCode;
	}
	public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
		this.allowEmptyValidateCode = allowEmptyValidateCode;
	}
	public String getSessionvalidateCodeField() {
		return sessionvalidateCodeField;
	}
	public void setSessionvalidateCodeField(String sessionvalidateCodeField) {
		this.sessionvalidateCodeField = sessionvalidateCodeField;
	}
	public String getValidateCodeParameter() {
		return validateCodeParameter;
	}
	public void setValidateCodeParameter(String validateCodeParameter) {
		this.validateCodeParameter = validateCodeParameter;
	}  
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserAttemptsDao getUserAttemptsDao() {
		return userAttemptsDao;
	}

	public void setUserAttemptsDao(UserAttemptsDao userAttemptsDao) {
		this.userAttemptsDao = userAttemptsDao;
	}
}  