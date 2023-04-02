package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "TimeTable")
public class TimeTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will creat the uniq and respectively id
    int dayOfWeek;
    String fromHour;
    String toHour;


    public TimeTable() {
    }



    public TimeTable(int dayOfWeek, String fromHour, String toHour) {
        this.dayOfWeek = dayOfWeek;
        this.fromHour = fromHour;
        this.toHour = toHour;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getFromHour() {
        return fromHour;
    }

    public void setFromHour(String fromHour) {
        this.fromHour = fromHour;
    }

    public String getToHour() {
        return toHour;
    }

    public void setToHour(String toHour) {
        this.toHour = toHour;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "dayOfWeek=" + dayOfWeek +
                ", fromHour='" + fromHour + '\'' +
                ", toHour='" + toHour + '\'' +
                '}';
    }

}