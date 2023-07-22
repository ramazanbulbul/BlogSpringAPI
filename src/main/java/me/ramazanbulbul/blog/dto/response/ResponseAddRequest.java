package me.ramazanbulbul.blog.dto.response;

import jakarta.persistence.Table;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class ResponseAddRequest {
     String responseJson;
     String requestURI;
     String requestId;
     String requestURL;
     String requestedSessionId;
     String authType;
     String method;
     String remoteHost;
     String remoteAddr;
     int remotePort;
     String serverName;
     int serverPort;
     String localName;
     String localAddr;
     int localPort;
     String servletPath;
     public ResponseAddRequest(String responseJson, HttpServletRequest httpRequest){
          this.responseJson = responseJson;
          this.requestURI = httpRequest.getRequestURI();
          this.requestId = httpRequest.getRequestId();
          this.requestURL = httpRequest.getRequestURL().toString();
          this.requestedSessionId = httpRequest.getRequestedSessionId();
          this.authType = httpRequest.getAuthType();
          this.method = httpRequest.getMethod();
          this.remoteHost = httpRequest.getRemoteHost();
          this.remoteAddr = httpRequest.getRemoteAddr();
          this.remotePort = httpRequest.getRemotePort();
          this.serverName = httpRequest.getServerName();
          this.serverPort = httpRequest.getServerPort();
          this.localName = httpRequest.getLocalName();
          this.localAddr = httpRequest.getLocalAddr();
          this.localPort = httpRequest.getLocalPort();
          this.servletPath = httpRequest.getServletPath();
     }
}
