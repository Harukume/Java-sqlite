package com.pwr.lab03.kayak.util;

import java.time.LocalDate;

public interface TimeListener {
    void onDateChanged(LocalDate newDate);
}