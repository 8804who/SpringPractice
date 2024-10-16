package com.test.main.service;

import com.test.main.dto.LogDto;
import com.test.main.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public void saveLog(LogDto logDto) {
        logRepository.saveLog(logDto);
    }
}
