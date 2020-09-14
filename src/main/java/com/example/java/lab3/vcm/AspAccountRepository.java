package com.example.java.lab3.vcm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AspAccountRepository extends JpaRepository<AspAccount, Long> {
    AspAccount findAspAccountByPin(int pin);
}
