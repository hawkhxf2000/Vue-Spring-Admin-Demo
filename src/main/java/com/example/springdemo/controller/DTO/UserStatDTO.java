package com.example.springdemo.controller.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserStatDTO {
    private ArrayList<String> xAxis;
    private ArrayList<Long> yAxis;
}
