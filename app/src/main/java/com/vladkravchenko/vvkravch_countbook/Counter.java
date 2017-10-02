/*
 * CMPUT 301
 * VERSION: 1.0
 * 2017-09-30
 * Copyright (c) 2017 VVKRAVCH CMPUT 301 University of Alberta - All Rights reserved. You may use, distribute or modify this code under terms and conditions of the Code of Student Behaviour at University odf Alberta.
 * You may find a copy of the license in this project. Otherwise please contact vvkravch@ualberta.ca
 */

package com.vladkravchenko.vvkravch_countbook;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vlad Kravchnko on 9/29/2017.
 */
/**
 * Represents a Counter.
 * @author vvkravch
 * @version 1.0
 * @since 1.0
 *
 */
public class Counter implements Serializable{
    private Date date;
    private String name;
    private int initialValue;
    private int currentValue;
    private String comment;

    /**
     * Counstructs a Counter.
     * @param name counters name
     * @param initialValue Counters initial Count
     */


    public Counter(String name, int initialValue){
        this.date = new Date();
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment="";
    }
    public Counter (String name, int initialValue,
                    String comment) {
        this.date = new Date();
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment = comment;
    }
    @Override
    /**
     * @return toString returns CountBookActivity representation
     */
    public String toString() {
        return date.toString() + " | " + name + " | "+ currentValue + " | " + comment;
    }

    /**
     *
     * @return toString2 returns EditCount representation
     */
    public String toString2() {
        String newline = System.getProperty("line.separator");
        return date.toString() + newline + "Name:" +name + newline + "Count:" + currentValue + newline + "Comment:" + comment;
    }

    /**
     * Getters and setters
     *
     */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
            this.comment = comment;
        }

}

