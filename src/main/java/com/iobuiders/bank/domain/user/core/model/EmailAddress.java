package com.iobuiders.bank.domain.user.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@Data
public class EmailAddress {

    @Column(name="email")
    private String value;

    public EmailAddress(String value) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches()){
            this.value = value;
        } else {
            throw new IllegalArgumentException("Provided value is not an email address");
        }
    }

    private EmailAddress(){}
}