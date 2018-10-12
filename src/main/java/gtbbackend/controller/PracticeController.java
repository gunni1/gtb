package gtbbackend.controller;

import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.PracticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;


@RestController
public class PracticeController
{
    @Autowired
    private PracticeRepository practiceRepository;

    @GetMapping("/practices/{userId}/{practiceKey}")
    public ResponseEntity<List<PracticeDto>> getPractices(@PathVariable String userId, @PathVariable String practiceKey)
    {
        List<PracticeDto> usersPractices = practiceRepository.findByUserIdAndKey(userId, practiceKey);
        return ResponseEntity.ok(usersPractices);
    }


}
