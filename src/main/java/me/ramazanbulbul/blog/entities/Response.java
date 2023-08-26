package me.ramazanbulbul.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "response_table")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long responseId;
    @Column(columnDefinition="text")
    String responseJson;
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP")
    Date responseDate;

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
    String servletPath;

    String localName;
    String localAddr;
    int localPort;
}
