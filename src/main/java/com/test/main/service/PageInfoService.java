package com.test.main.service;

import com.test.main.dto.PageInfoDto;
import com.test.main.repository.PageInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PageInfoService {
    private final PageInfoRepository pageInfoRepository;

    public PageInfoDto readPageInfo(String pageUrl) {
        return pageInfoRepository.readPageInfo(pageUrl);
    }
}
