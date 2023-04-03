package refactoring.semiproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import refactoring.semiproject.repository.PhotoRepository;
import refactoring.semiproject.service.photoService;

@Service
@RequiredArgsConstructor
public class photoServiceImpl implements photoService {

    private final PhotoRepository photoRepository;

}
