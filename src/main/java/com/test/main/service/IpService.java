package com.test.main.service;

import com.test.main.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IpService {
    private final IpRepository ipRepository;

    public void banIp(String ipAddress) {
        ipRepository.banIp(ipAddress);
    }

    public boolean ipBanCheck(String ipAddress){
        return ipRepository.ipBanCheck(ipAddress);
    }
}
