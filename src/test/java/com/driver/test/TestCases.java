package com.driver.test;

import com.driver.AirBnb;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest(classes = AirBnb.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

}


