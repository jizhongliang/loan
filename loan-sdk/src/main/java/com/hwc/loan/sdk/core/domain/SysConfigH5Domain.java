package com.hwc.loan.sdk.core.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class SysConfigH5Domain {

    private String register;
    private String login;
    private String creditBorrow;
    private String mortgageBorrow;
    private String creditRepay;
    private String mortgageRepay;
    private String creditRreRepay;
    private String mortgageRreRepay;
    private String about;
    private String problem;
    private String notes;

    public void setRegister(String register) {
        this.register = register;
    }

    public String getRegister() {
        return this.register;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setCreditBorrow(String creditBorrow) {
        this.creditBorrow = creditBorrow;
    }

    public String getCreditBorrow() {
        return this.creditBorrow;
    }

    public void setMortgageBorrow(String mortgageBorrow) {
        this.mortgageBorrow = mortgageBorrow;
    }

    public String getMortgageBorrow() {
        return this.mortgageBorrow;
    }

    public void setCreditRepay(String creditRepay) {
        this.creditRepay = creditRepay;
    }

    public String getCreditRepay() {
        return this.creditRepay;
    }

    public void setMortgageRepay(String mortgageRepay) {
        this.mortgageRepay = mortgageRepay;
    }

    public String getMortgageRepay() {
        return this.mortgageRepay;
    }

    public void setCreditRreRepay(String creditRreRepay) {
        this.creditRreRepay = creditRreRepay;
    }

    public String getCreditRreRepay() {
        return this.creditRreRepay;
    }

    public void setMortgageRreRepay(String mortgageRreRepay) {
        this.mortgageRreRepay = mortgageRreRepay;
    }

    public String getMortgageRreRepay() {
        return this.mortgageRreRepay;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return this.about;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getProblem() {
        return this.problem;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

} 