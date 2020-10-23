package com.freesoft.julianfrontend;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Named
@SessionScoped
public class Julian implements Serializable {

    private static final long serialVersionUID = -1110733631543658209L;

    private static final String URL = "http://localhost:8080/julian-1.0-SNAPSHOT/webapi/convert/%s";

    private Date dateIn;
    private String jd;

    public String convert() {
        Client client = ClientBuilder.newClient();
        String gregorianDay = getGregorianDay().replace(" ", "-").replace(":", "-");
        String url = urlFor(gregorianDay);

        WebTarget webTarget = client.target(url);

        jd = webTarget.request().get(String.class);

        return "/response.xhtml";
    }

    public String getGregorianDay() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(dateIn.toInstant(), ZoneId.of("UTC"));
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String urlFor(String gregorianDay) {
        return String.format(URL, gregorianDay);
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }
}
