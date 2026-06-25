package com.dentalflow.pe.utils;

import java.time.LocalTime;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String v) throws Exception {
        return v != null ? LocalTime.parse(v) : null;
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
        return v != null ? v.toString() : null;
    }

}
